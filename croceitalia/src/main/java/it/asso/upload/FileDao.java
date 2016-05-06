/**
 * 
 */

package it.asso.upload;

import it.asso.util.ApplConfig;
import it.asso.util.AssoConst_itf;
import it.asso.util.AssoException;
import it.asso.util.AssoLogger;
import it.asso.util.Dominio_itf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.cms.jpa.dao.impl.DominioDao;
import org.cms.jpa.object.impl.Dominio;
import org.cms.jpa.object.impl.DominioModel_itf;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author paolo
 */
@Repository("fileDao")
@Transactional(readOnly = true)
public class FileDao extends DominioDao implements FileManager_itf {

	/**
     * 
     */
	private static final String	MaxSize			= "MaxSize";
	private static final String	Repository		= "Repository";
	private static final String	RepositoryTmp	= "RepositoryTmp";
	private static final String	Slash			= "/";

	/**
	 * @param c_ofs_nomefile
	 * @return
	 * @throws Exception
	 */
	public File_itf creaCartella(String idParent, String fileName, Dominio dominio) throws Exception {

		File_itf nuovoFile = creaCartella(idParent, DBFile.DIR, fileName, dominio);
		return nuovoFile;
	}

	/**
	 * @param idParent
	 * @param tipo
	 * @param nomeCartella
	 * @return
	 * @throws Exception
	 */
	private File_itf creaCartella(String parentId, String tipo, String nomeCartella, Dominio dominio) throws Exception {

		// Controllo che non esista un file con quel nome
		String newName = findName(parentId, nomeCartella);
		if (newName == null) {
			throw new AssoException("Non è possibile creare la cartella: " + nomeCartella);
		}

		// in ogni caso lo riempio
		DBFile child = new DBFile();
		child.setParent_id(parentId);
		child.setNomefile(newName);
		child.setTipo(tipo);
		child.setDominio(dominio);

		save(child);

		// Creo la cartella fisica
		String child_id = child.getId();
		String parentPath = getRealFullPath(child_id);
		File container = new File(parentPath);
		if (!container.exists()) {
			container.mkdirs();
		}

		return child;
	}

	/**
	 * @param c_ofs_nomefile
	 * @return
	 * @throws Exception
	 */
	public File_itf creaFile(String idParent, String fileName) throws Exception {

		File_itf nuovoFile = creaFile(idParent, DBFile.FILE, fileName);
		return nuovoFile;
	}

	/**
	 * Creo un file con nome nomefile figlio di parent_id. Se il nome esiste già lo cambio aggiungendo un progressivo
	 * come windows
	 * 
	 * @param tipo
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	private File_itf creaFile(String parent_id, String tipo, String nomefile) throws Exception {

		File_itf fileEsistente = getFile(parent_id, nomefile);
		if (fileEsistente != null) {
			throw new AssoException("File esistente! Cancellarlo prima di ricaricarlo: " + nomefile);
		}

		// ora faccio la insert
		DBFile child = new DBFile();
		child.setNomefile(nomefile);
		child.setParent_id(parent_id);
		child.setTipo(tipo);

		return child;
	}

	/*
	 * Questo metodo cancella ricorsivamente file e cartella dal repository fisico della macchina
	 * 
	 * @see it.asso.upload.FileManager_itf#delete(it.asso.upload.File_itf)
	 */
	public void delete(File_itf file) throws Exception {

		// Cancello i figli ricorsivamente
		List<File_itf> filesContenuti = getChildren(file.getId());
		for (Iterator<File_itf> iterator = filesContenuti.iterator(); iterator.hasNext();) {
			File_itf child = iterator.next();
			if (child.isFolder()) {
				delete(child);
			} else {
				deleteDiskFile(child);
			}
		}
		// Non ho piu' figli cancello
		if (file.isFolder()) {
			deleteDiskFolder(file);
		} else {
			deleteDiskFile(file);
		}
	}

	/**
	 * @param file
	 * @throws AssoException
	 */
	private void deleteDiskFile(File_itf file) throws AssoException {

		String parent_id = file.getIdParent();
		String parentPath = getRealFullPath(parent_id);
		String path = parentPath + file.getNomefile();

		File container = new File(path);
		boolean deleted = false;
		if (!container.exists()) {
			// Il file non esiste
			deleted = true;
		} else {
			deleted = container.delete();
		}

		if (deleted) {
			deleteById(file.getId());
		} else {
			throw new AssoException("Impossibile cancellare il file: " + file.getId());
		}

		AssoLogger.GetInstance().logInfo("Cancellato file: " + path);
	}

	/**
	 * @param file
	 * @throws AssoException
	 */
	private void deleteDiskFolder(File_itf file) throws AssoException {

		// quando arrivo qui ho già cancellato tutti i file della directory che sono stati registrati su dbfile

		String path = getRealFullPath(file.getId());
		File folder = new File(path);
		if (folder.exists()) {
			// a questo punto prima di cancellare la directory provo a cancellare tutti gli eventuali file creati e non
			// registrati
			for (File fileEntry : folder.listFiles()) {
				if (fileEntry.isDirectory()) {
					throw new AssoException("Impossibile cancellare la cartella " + file.getNomefile() + ". id="
							+ file.getId());
				} else {
					fileEntry.delete();
				}

			}
			// ora cancello la directory fisica
			boolean deleted = folder.delete();
			if (!deleted) {
				throw new AssoException("Impossibile cancellare la cartella " + file.getNomefile() + ". id="
						+ file.getId());
			}
		}
		deleteById(file.getId());

		AssoLogger.GetInstance().logInfo("Cancellata Cartella: " + path);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.promowizard.model.FileManager_itf#fillDocument(it.promowizard.model .File_itf, java.sql.Blob)
	 */
	public void fillDocument(File_itf nuovoFile, Blob blob) throws Exception {

		DBFile file = (DBFile) nuovoFile;
		file.setBlob(blob);
		update(file);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.promowizard.model.FileManager_itf#fillDocument(it.promowizard.model .File_itf, java.io.InputStream)
	 */
	public void fillDocument(File_itf nuovoFile, InputStream inputStream) throws Exception {

		DBFile file = (DBFile) nuovoFile;
		Blob blob = null;
		file.setBlob(blob);
		update(file);
	}

	/**
	 * Filtra il documento in base al tipo. Torna true se il documento è del tipo passato come parametro. Altrimenti
	 * false. Serve a costruire liste di oggetti omogenei quali immagini, file excel , etc.
	 * 
	 * Tipi Ammessi: Image, Document
	 * 
	 * @param type
	 * @param doc
	 * @return
	 */
	private boolean filter(String type, File_itf doc) {

		if (type.equals("Image") && doc.isImage()) {
			return true;
		}

		if (type.equals("Document") && doc.isDocument()) {
			return true;
		}

		return false;
	}

	//@Override
	public File_itf findById(String id) {

		File_itf file = (File_itf) super.findById(id);

		return file;
	}

	/**
	 * @param parentId
	 * @param nomeFile
	 * @return
	 * @throws Exception
	 */
	private String findName(String parentId, String nomeFile) throws Exception {

		File_itf fileEsistente = getFile(parentId, nomeFile);
		if (fileEsistente != null) {
			return findName(parentId, nomeFile, 1);
		}
		return nomeFile;
	}

	/**
	 * @param idParent
	 * @param nomeCartella
	 * @param i
	 * @return
	 * @throws Exception
	 */
	private String findName(String idParent, String nomeCartella, int i) throws Exception {

		String newName = nomeCartella + " (" + i + ")";
		// Controllo se esiste un file con quel nome
		File_itf file = getFile(idParent, newName);
		if (file == null) {
			// Il file non esiste
			// Il nome è buono
			return newName;
		} else {
			// Il file esiste ne cerco un altro
			int next = i + 1;
			if (i < 100) {
				return findName(idParent, nomeCartella, next);
			} else {
				// LA RICORSIONE SI INTERROMPE PER i=99
				return null;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.promowizard.model.FileManager_itf#getChildren(java.lang.String)
	 */
	public List<File_itf> getChildren(String parent_id) {

		EntityManager em = getEntityManager();
		Query query = em
				.createQuery("select obj from DBFile obj where obj.parent_id=:parent_id order by obj.tipo, obj.id desc ");
		query.setParameter("parent_id", parent_id);

		@SuppressWarnings("unchecked")
		List<File_itf> resultList = query.getResultList();

		close(em);

		return resultList;
	}

	public List<File_itf> getAllChildren(String parent_id, String userId, String tipoFile, String ordinamento) {

		EntityManager em = getEntityManager();
		// Query query = em
		// .createQuery("select obj from DBFile obj where obj.parent_id in (select f.id from DBFile f where f.parent_id =:parent_id) order by obj.parent_id desc, obj.id ");
		String sqlQuery = "select obj from DBFile obj, DBFile f where ";
		if (!tipoFile.equals(""))
			sqlQuery += "obj.description like :tipoFile and ";
		sqlQuery += "obj.description not like '%Log elaborazione%' and "; // escludo il file di log
		sqlQuery += "obj.autore = :autore and obj.parent_id = f.id and f.parent_id = :parent_id ";
		if (ordinamento.equals("nome"))
			sqlQuery += " order by f.nomefile, obj.parent_id desc, obj.id ";
		else
			sqlQuery += " order by obj.parent_id desc, obj.id ";;
		Query query = em.createQuery(sqlQuery);
		query.setParameter("parent_id", parent_id);
		query.setParameter("autore", userId);
		if (!tipoFile.equals(""))
			query.setParameter("tipoFile", tipoFile + "%");
		@SuppressWarnings("unchecked")
		List<File_itf> resultList = query.getResultList();

		close(em);

		return resultList;
	}

	public List<File_itf> getAllInviati(String parent_id, String userId, String tipoFile, String ordinamento) {

		EntityManager em = getEntityManager();
		// Query query = em
		// .createQuery("select obj from DBFile obj where obj.parent_id in (select f.id from DBFile f where f.parent_id =:parent_id) order by obj.parent_id desc, obj.id ");
		String sqlQuery = "select obj from DBFile obj, DBFile f where ";
		if (!tipoFile.equals(""))
			sqlQuery += "(obj.description like 'XMLProprio%' or obj.description like :tipoFile )and ";
		sqlQuery += "obj.idProtocollo is not null and "; // prendo solo quelli protocollati (quindi accolti) //
		// sqlQuery += "obj.description not like '%Log elaborazione%' and "; // escludo il file di log
		sqlQuery += "obj.nomefile not like '%.txt%' and "; // escludo il file di log
		sqlQuery += "obj.autore = :autore and obj.parent_id = f.id and f.parent_id = :parent_id ";
		if (ordinamento.equals("nome"))
			sqlQuery += " order by f.nomefile, obj.parent_id desc, obj.id ";
		else
			sqlQuery += " order by obj.parent_id desc, obj.id ";;
		Query query = em.createQuery(sqlQuery);
		query.setParameter("parent_id", parent_id);
		query.setParameter("autore", userId);
		if (!tipoFile.equals(""))
			query.setParameter("tipoFile", tipoFile + "%");
		@SuppressWarnings("unchecked")
		List<File_itf> resultList = query.getResultList();

		close(em);

		return resultList;
	}

	//@Override
	public Class<?> getEntityClass() {

		return DBFile.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.promowizard.model.FileManager_itf#getFile(java.lang.String)
	 */
	public File_itf getFile(String fileId) throws AssoException {

		File_itf file = (DBFile) findById(DBFile.class, fileId);

		return file;
	}

	public File_itf getDirForUser(String user_id) throws AssoException {

		EntityManager em = null;
		try {
			em = getEntityManager();
			Query query = em.createQuery("select obj from DBFile obj where obj.nomefile=:nomeFile");
			query.setParameter("nomeFile", user_id);

			File_itf file = (DBFile) query.getSingleResult();

			return file;

		} finally {
			close(em);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.asso.upload.FileManager_itf#getFile(java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public File_itf getFile(String parent_id, String nomeFile) throws AssoException {

		EntityManager em = null;
		try {
			em = getEntityManager();
			Query query = em
					.createQuery("select obj from DBFile obj where obj.parent_id=:parent_id and obj.nomefile=:nomeFile");
			query.setParameter("parent_id", parent_id);
			query.setParameter("nomeFile", nomeFile);

			List<File_itf> resultList = query.getResultList();
			if (resultList.isEmpty()) {
				return null;
			}

			File_itf file = (DBFile) query.getSingleResult();

			return file;

		} finally {
			close(em);
		}

	}

	/**
	 * @param idGruppo
	 * @return
	 * @throws AssoException
	 */
	public File_itf getFirstLevel(String dominio_uid) throws AssoException {

		EntityManager em = null;
		try {
			em = getEntityManager();
			Query query = em
					.createQuery("select obj from DBFile obj where obj.tipo=:tipo and obj.dominio.uid=:dominio_uid");
			query.setParameter("tipo", "TOP");
			query.setParameter("dominio_uid", dominio_uid);
			File_itf file = (DBFile) query.getSingleResult();

			if (file == null) {
				throw new AssoException("Livello TOP del Dominio inesistente: " + dominio_uid);
			}
			return file;

		} finally {
			close(em);
		}
	}

	/**
	 * Torna il percorso logico del file fino alla cartella TopLevel
	 * 
	 * @see it.promowizard.model.FileManager_itf#getFullPath()
	 */
	public String getFullPath(String idFile) throws AssoException {

		File_itf file = getFile(idFile);

		String fullPath = file.getNomefile();
		if (!file.isFirstLevel()) {
			String idParent = file.getIdParent();
			String fullPathParent = getFullPath(idParent);
			fullPath = fullPathParent + Slash + fullPath;
		}
		return fullPath;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.asso.upload.FileManager_itf#getInputStream(java.lang.String)
	 */
	public InputStream getInputStream(String fileId) throws Exception {

		File_itf file = getFile(fileId);

		String parent_id = file.getIdParent();
		String parentPath = getRealFullPath(parent_id);
		String path = parentPath + file.getNomefile();

		File resource = new File(path);

		AssoLogger.GetInstance().logInfo("Scaricata risorsa: " + path);

		InputStream inputStream = new FileInputStream(resource);

		return inputStream;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.asso.upload.FileManager_itf#upload(javax.servlet.http.HttpServletRequest ,
	 * org.cms.jpa.object.impl.Dominio)
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getModelMap(HttpServletRequest request, Dominio dominio) throws Exception {

		DiskFileItemFactory factory = new DiskFileItemFactory();
		// maximum size that will be stored in memory
		factory.setSizeThreshold(4096);

		// the location for saving data that is larger than
		// getSizeThreshold()
		String tmpDir = ApplConfig.GetParameter(RepositoryTmp);

		factory.setRepository(new File(tmpDir));

		ServletFileUpload upload = new ServletFileUpload(factory);

		// maximum size before a FileUploadException will be thrown
		long maxSize = new Long(ApplConfig.GetParameter(MaxSize));
		upload.setSizeMax(maxSize);

		List<FileItem> fileItems = upload.parseRequest(request);
		List allegati = new ArrayList();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put(AssoConst_itf.DOMINIO, dominio);

		Iterator<FileItem> iterator = fileItems.iterator();
		while (iterator.hasNext()) {
			FileItem fileItem = iterator.next();

			// filename on the client
			String fileName = fileItem.getName();
			String fieldName = fileItem.getFieldName();
			if (fileName != null) {
				if (fieldName.equals("file"))
					map.put("file", fileItem);
				if (fieldName.equals("allegati"))
					allegati.add(fileItem);
			} else {
				String fieldValue = fileItem.getString();
				map.put(fieldName, fieldValue);

			}
		}
		map.put("allegati", allegati);

		return map;
	}

	/*
	 * Torna il percorso fisico del file sul disco
	 * 
	 * @see it.promowizard.model.FileManager_itf#getFullPath()
	 */
	public String getRealFullPath(String folder_id) throws AssoException {

		File_itf file = getFile(folder_id);

		String fullPath = folder_id;
		if (!file.isFirstLevel()) {
			String parent_id = file.getIdParent();
			String fullPathParent = getRealFullPath(parent_id);
			fullPath = fullPathParent + fullPath + Slash;
		} else {
			String root = getRoot(file.getDominio());
			fullPath = root;
		}
		return fullPath;
	}

	/**
	 * @return
	 */
	public String getRoot(Dominio_itf dominio) {

		// Aggiungo il dominio alla cartella che conterrà le risorse
		// Diversi domini usarenno cartelle diverse
		String parentPath = ApplConfig.GetParameter(Repository);

		parentPath += dominio.getUid();
		parentPath += Slash;

		return parentPath;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.asso.upload.FileManager_itf#getTypeMap(java.lang.String, java.lang.String,
	 * org.cms.jpa.object.impl.Dominio)
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> getTypeMap(String type, String nomeFile, Dominio dominio) {

		EntityManager em = getEntityManager();
		Query query = null;

		if (nomeFile.equals("ALL")) {
			query = em.createQuery("select obj from DBFile obj where obj.dominio.uid=:dominioUid");
		} else {
			query = em
					.createQuery("select obj from DBFile obj where obj.dominio.uid=:dominioUid and obj.nomefile like :nomeFile ");
			query.setParameter("nomeFile", nomeFile + "%");
		}
		query.setParameter("dominioUid", dominio.getUid());

		Iterator<File_itf> resultList = query.getResultList().iterator();
		Map<String, String> answer = new TreeMap<String, String>();
		while (resultList.hasNext()) {
			File_itf doc = resultList.next();
			if (filter(type, doc)) {
				answer.put(doc.getId(), doc.getNomefile());
			}
		}

		close(em);

		return answer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.asso.upload.FileManager_itf#update(it.asso.upload.File_itf)
	 */
	public void update(File_itf file) throws AssoException {

		Object obj = file;
		this.update(obj);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.asso.upload.FileManager_itf#upload(javax.servlet.http.HttpServletRequest ,
	 * org.cms.jpa.object.impl.Dominio)
	 */
	public File_itf upload(HttpServletRequest request, Dominio dominio) throws Exception {

		Map<String, Object> modelMap = getModelMap(request, dominio);

		File_itf dbFile = upload(modelMap);

		return dbFile;
	}

	/*
	 * Recupera file e attributi dalla mappa passato come parametro (non-Javadoc)
	 * 
	 * @see it.asso.upload.FileManager_itf#upload(javax.servlet.http.HttpServletRequest ,
	 * org.cms.jpa.object.impl.Dominio)
	 */
	@SuppressWarnings("unchecked")
	public File_itf upload(Map<String, Object> modelMap) throws Exception {

		// carico e scrivo gli allegati
		List<FileItem> fileItems = (List<FileItem>) modelMap.get("allegati");
		Iterator<FileItem> iterator = fileItems.iterator();
		while (iterator.hasNext()) {
			FileItem fileItem = iterator.next();
			upload(modelMap, fileItem);
		}
		// carico e scrivo il file excel o xml da elaboare
		FileItem fileItem = (FileItem) modelMap.get("file");
		File_itf dbFile = upload(modelMap, fileItem);

		return dbFile;
	}

	/**
	 * @param modelMap
	 * @param fileItem
	 * @return
	 * @throws Exception
	 */
	public File_itf upload(Map<String, Object> modelMap, FileItem fileItem) throws Exception {

		String parent_id = (String) modelMap.get("parent_id");
		DBFile dbFile = null;

		String fileName = fileItem.getName();
		String delimiter = "\\\\";
		String[] split = fileName.split(delimiter);
		fileName = split[split.length - 1];
		dbFile = (DBFile) creaFile(parent_id, fileName);

		Dominio dominio = (Dominio) modelMap.get(AssoConst_itf.DOMINIO);
		dbFile.setDominio(dominio);

		String state = (String) modelMap.get("state");
		if (state == null) {
			state = DominioModel_itf.WORKINPROGRESS;
		}
		dbFile.setState(state);

		String title = (String) modelMap.get("title");
		if (title == null) {
			title = fileName;
		}
		dbFile.setTitle(title);

		String description = (String) modelMap.get("description");
		if (description == null) {
			description = fileName;
		}
		dbFile.setDescription(description);

		String autore = (String) modelMap.get("autore");
		if (autore == null) {
			autore = dominio.getUid();
		}
		dbFile.setAutore(autore);

		save(dbFile);

		// save comment and filename to database
		String parentPath = getRealFullPath(parent_id);
		File container = new File(parentPath);
		if (!container.exists()) {
			container.mkdirs();
		}
		// write the file
		File file = new File(parentPath, fileName);
		fileItem.write(file);

		return dbFile;
	}

	/**
	 * @param file
	 * @param filename
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private File writeFileFromBlob(DBFile file, String filename) throws Exception {

		Blob blob = file.getBlob();
		File blobFile = new File(filename);
		FileOutputStream outStream = new FileOutputStream(blobFile);
		InputStream inStream = blob.getBinaryStream();

		byte[] b = new byte[1];
		while ((inStream.read(b)) > 0) {
			outStream.write(b);
			outStream.flush();
		}

		inStream.close();
		outStream.close();

		return blobFile;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.promowizard.model.FileManager_itf#writeOn(java.lang.String, java.io.OutputStream)
	 */
	public void writeOn(String idFile, OutputStream out) throws AssoException {

		InputStream inputStream = null;
		try {
			File_itf file = getFile(idFile);

			String parent_id = file.getIdParent();
			String parentPath = getRealFullPath(parent_id);
			String path = parentPath + file.getNomefile();

			File resource = new File(path);
			if (!resource.exists()) {
				throw new AssoException("Richiesto file inesistente: " + path);
			}

			AssoLogger.GetInstance().logInfo("Scaricata risorsa: " + path);

			inputStream = new FileInputStream(resource);

			byte[] b = new byte[1];
			while ((inputStream.read(b)) > 0) {
				out.write(b);
			}

		} catch (Throwable t) {
			throw new AssoException(t);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					AssoLogger.GetInstance().logError(e);
				}
			}
		}
	}

	/*
	 * Recupera file e attributi dalla mappa passato come parametro (non-Javadoc)
	 * 
	 * @see it.asso.upload.FileManager_itf#upload(javax.servlet.http.HttpServletRequest ,
	 * org.cms.jpa.object.impl.Dominio)
	 */
	public File_itf uploadTemporayFile(Map<String, Object> modelMap) throws Exception {

		String parent_id = (String) modelMap.get("parent_id");

		FileItem fileItem = (FileItem) modelMap.get("file");
		String fileName = fileItem.getName();
		String delimiter = "\\\\";
		String[] split = fileName.split(delimiter);
		fileName = split[split.length - 1];
		DBFile dbFile = (DBFile) creaFile(parent_id, fileName);

		Dominio dominio = (Dominio) modelMap.get(AssoConst_itf.DOMINIO);
		dbFile.setDominio(dominio);

		String state = (String) modelMap.get("state");
		if (state == null) {
			state = DominioModel_itf.WORKINPROGRESS;
		}
		dbFile.setState(state);

		String title = (String) modelMap.get("title");
		if (title == null) {
			title = fileName;
		}
		dbFile.setTitle(title);

		String description = (String) modelMap.get("description");
		if (description == null) {
			description = fileName;
		}
		dbFile.setDescription(description);

		String autore = (String) modelMap.get("autore");
		if (autore == null) {
			autore = dominio.getUid();
		}
		dbFile.setAutore(autore);

		save(dbFile);

		// save comment and filename to database
		String parentPath = getRealFullPath(parent_id);
		File container = new File(parentPath);
		if (!container.exists()) {
			container.mkdirs();
		}
		// write the file
		File file = new File(parentPath, fileName);
		fileItem.write(file);

		return dbFile;
	}

	/**
	 * Torna la dimensione del file associato all'oggetto
	 * 
	 * @param fileId
	 * @return
	 * @throws Exception
	 */
	public long getSize(String fileId) throws Exception {

		File_itf file = getFile(fileId);
		return getSize(file);
	}

	/**
	 * @param file
	 * @return
	 * @throws AssoException
	 */
	public long getSize(File_itf file) throws AssoException {

		String parent_id = file.getIdParent();
		String parentPath = getRealFullPath(parent_id);
		String path = parentPath + file.getNomefile();

		File resource = new File(path);
		long length = resource.length();

		return length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cms.jpa.dao.impl.DominioDao#save(java.lang.Object)
	 */
	//@Override
	public void save(Object obj) throws AssoException {

		super.save(obj);

		// EntityManager em = null;
		// EntityTransaction transaction = null;
		// try {
		// em = getEntityManager();
		// transaction = em.getTransaction();
		// transaction.begin();
		// // ((EntityManagerImpl) em).getBroker().setAllowReferenceToSiblingContext(true);
		//
		// DBFile f = (DBFile) obj;
		// Dominio dominio = (Dominio) f.getDominio();
		// dominio = em.merge(dominio);
		// f.setDominio(dominio);
		// em.persist(obj);
		// transaction.commit();
		// } catch (Throwable e) {
		// // Rollback the transaction
		// error(transaction, e);
		// } finally {
		// // Close this EntityManager
		// if (em != null) {
		// close(em);
		// }
		// }
	}

	public List<File_itf> getFilesToBeProcess() {

		EntityManager em = null;
		try {
			em = getEntityManager();
			String sqlQuery = "select obj from DBFile obj where obj.tipo = 'FILE' and obj.state = 'WFB' order by obj.id ";;
			Query query = em.createQuery(sqlQuery);
			@SuppressWarnings("unchecked")
			List<File_itf> resultList = query.getResultList();
			return resultList;
		} catch (Throwable e) {
			AssoLogger.GetInstance().logError("Errore lettura file da processare:", e);
			return null;
		} finally {
			close(em);
		}
	}

	public File_itf getMyFile(String idFile) throws AssoException {

		EntityManager em = null;
		try {
			em = getEntityManager();
			Query query = em.createQuery("select obj from DBFile obj where obj.id=:idFile");
			query.setParameter("idFile", idFile);
			List<File_itf> resultList = query.getResultList();
			if (resultList.isEmpty()) {
				return null;
			}

			File_itf file = resultList.get(0);

			return file;

		} finally {
			close(em);
		}

	}

	public List<File_itf> getFilesToBeZip(String autore, String tipo) {

		EntityManager em = null;
		String tipoFile = tipo + " - XML Generato";
		try {
			em = getEntityManager();
			String sqlQuery = "select obj from DBFile obj where obj.tipo = 'FILE' and obj.state = 'OK' and obj.description like :tipoFile and obj.autore = :autore order by obj.nomefile ";
			Query query = em.createQuery(sqlQuery);
			query.setParameter("tipoFile", tipoFile + "%");
			query.setParameter("autore", autore);
			@SuppressWarnings("unchecked")
			List<File_itf> resultList = query.getResultList();
			return resultList;
		} catch (Throwable e) {
			AssoLogger.GetInstance().logError("Errore lettura file da processare:", e);
			return null;
		} finally {
			close(em);
		}
	}

	public List<File_itf> getAllFilesToBeDelete(String autore, String tipo) {

		return getFilesToBeDelete(autore, tipo, true);

	}

	public List<File_itf> getKOFilesToBeDelete(String autore, String tipo) {

		return getFilesToBeDelete(autore, tipo, false);

	}

	public List<File_itf> getFilesToBeDelete(String autore, String tipo, boolean tutto) {

		EntityManager em = null;
		String tipoFile = tipo + " - Cartella di lavoro";
		try {
			em = getEntityManager();
			String sqlQuery = "select obj from DBFile obj where obj.tipo = 'DIR' ";
			if (!tutto)
				sqlQuery += " and obj.state = 'KO' ";
			sqlQuery += " and obj.description like :tipoFile and obj.autore = :autore order by obj.nomefile ";
			Query query = em.createQuery(sqlQuery);
			query.setParameter("tipoFile", tipoFile + "%");
			query.setParameter("autore", autore);
			@SuppressWarnings("unchecked")
			List<File_itf> resultList = query.getResultList();
			return resultList;
		} catch (Throwable e) {
			AssoLogger.GetInstance().logError("Errore lettura file da processare:", e);
			return null;
		} finally {
			close(em);
		}
	}

	public void saveProtocollo(File_itf file, String idProtocollo, String esito, String stato) throws AssoException {

		// Cancello i figli ricorsivamente
		List<File_itf> filesContenuti = getChildren(file.getIdParent());
		for (Iterator<File_itf> iterator = filesContenuti.iterator(); iterator.hasNext();) {
			DBFile child = (DBFile) iterator.next();
			if (child.isFolder()) {
				saveProtocollo(child, idProtocollo, esito, stato);
			} else {
				child.setIdProtocollo(idProtocollo);
				child.setEsito(esito);
				child.setState(stato);
				this.update(child);
			}
		}
		DBFile root = (DBFile) getMyFile(file.getIdParent());
		root.setIdProtocollo(idProtocollo);
		root.setEsito(esito);
		this.update(root);

	}

};
