package org.cms.controller;

import it.asso.upload.DBFile;
import it.asso.upload.FileDao;
import it.asso.upload.FileManager_itf;
import it.asso.upload.File_itf;
import it.asso.util.AssoLogger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cms.jpa.object.impl.Dominio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author paolo
 * 
 */
@Controller
public class FileController extends SiteCmsController {

	/**
	 * 
	 */
	@Autowired(required = true)
	protected FileManager_itf fileDao;

	/**
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "documento/delete/{idFile}", method = RequestMethod.GET)
	protected ModelAndView delete(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("idFile") String idFile) {

		ModelAndView modelAndView = getModelAndView(request);
		try {
			File_itf file = fileDao.getFile(idFile);
			String idParent = file.getIdParent();
			fileDao.delete(file);

			return esploraCartella(request, response, idParent);

		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}
	}

	/**
	 * @param request
	 * @param response
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "documento/doUpdate", method = RequestMethod.POST)
	protected ModelAndView doUpdate(HttpServletRequest request, HttpServletResponse response, DBFile file) {

		ModelAndView modelAndView = getModelAndView(request);
		try {
			fileDao.update(file);

			return update(request, response, file.getId());

		} catch (Exception errore) {
			return error(modelAndView, errore);
		}
	}

	/**
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "documento/get/{idFile}", method = RequestMethod.GET)
	protected ModelAndView download(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("idFile") String idFile) {

		ModelAndView modelAndView = getModelAndView(request);
		try {
			File_itf file = fileDao.getFile(idFile);

			String mimeType = file.getMimeType();
			response.setContentType(mimeType);

			response.addHeader("Content-Disposition",
					"attachment;filename=" + java.net.URLEncoder.encode(file.getNomefile(), "UTF-8"));

			// if (file.isDocument()) {
			// response.addHeader("Content-Disposition", "attachment;filename="
			// + java.net.URLEncoder.encode(file.getNomefile(), "UTF-8"));
			// }

			ServletOutputStream outputStream = response.getOutputStream();
			fileDao.writeOn(idFile, outputStream);
			outputStream.flush();
			outputStream.close();

			// VALORI DI OUTPUT
			return null;

		} catch (Throwable errore) {
			try {
				response.reset();
			} catch (Throwable t) {
				AssoLogger.GetInstance().logInfo("Non sono riuscito a fare la reset del response");
			}
			return error(modelAndView, errore);
		}
	}

	/**
	 * @param request
	 * @param response
	 * @return Versione modificata per visualizzare e non scaricare il file
	 */
	@RequestMapping(value = "documento/view/{idFile}", method = RequestMethod.GET)
	protected ModelAndView visual(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("idFile") String idFile) {

		ModelAndView modelAndView = getModelAndView(request);
		try {
			File_itf file = fileDao.getFile(idFile);

			String mimeType = file.getMimeType();
			response.setContentType(mimeType);

			// if (!mimeType.equals("text/plain"))
			// response.addHeader("Content-Disposition", "attachment;filename="
			// + java.net.URLEncoder.encode(file.getNomefile(), "UTF-8"));

			// if (file.isDocument()) {
			// response.addHeader("Content-Disposition", "attachment;filename="
			// + java.net.URLEncoder.encode(file.getNomefile(), "UTF-8"));
			// }

			ServletOutputStream outputStream = response.getOutputStream();
			fileDao.writeOn(idFile, outputStream);
			outputStream.flush();
			outputStream.close();

			// VALORI DI OUTPUT
			return null;

		} catch (Throwable errore) {
			try {
				response.reset();
			} catch (Throwable t) {
				AssoLogger.GetInstance().logInfo("Non sono riuscito a fare la reset del response");
			}
			return error(modelAndView, errore);
		}
	}

	/**
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "documento/list/{idFile}", method = RequestMethod.GET)
	protected ModelAndView esploraCartella(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("idFile") String idFile) {

		ModelAndView modelAndView = getModelAndView(request);
		try {
			File_itf file = fileDao.getFile(idFile);

			// VALORI DI OUTPUT
			if (file.isFolder()) {
				String fullPath = fileDao.getFullPath(file.getId());

				List<File_itf> children = fileDao.getChildren(file.getId());
				int numFiles = children.size();

				modelAndView.addObject("cartellaCorrente", file);
				modelAndView.addObject("fullPath", fullPath);
				modelAndView.addObject("children", children);
				modelAndView.addObject("numFiles", String.valueOf(numFiles));

				modelAndView.setViewName("upload/esploraFile");

				return modelAndView;

			} else {
				// Sono un file
				return download(request, response, idFile);
			}

		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}
	}

	@RequestMapping(value = "documento/listForUser/{user_id}", method = RequestMethod.GET)
	protected ModelAndView esploraCartellaUtente(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("user_id") String user_id) {

		ModelAndView modelAndView = getModelAndView(request);
		try {
			File_itf file = fileDao.getDirForUser(user_id);

			// VALORI DI OUTPUT
			if (file.isFolder()) {
				String fullPath = fileDao.getFullPath(file.getId());

				List<File_itf> children = fileDao.getChildren(file.getId());
				int numFiles = children.size();

				modelAndView.addObject("cartellaCorrente", file);
				modelAndView.addObject("fullPath", fullPath);
				modelAndView.addObject("children", children);
				modelAndView.addObject("numFiles", String.valueOf(numFiles));

				modelAndView.setViewName("upload/esploraFile");

			}
			return modelAndView;

		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}
	}

	/**
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "documento/list", method = RequestMethod.GET)
	protected ModelAndView esploraRisorse(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = getModelAndView(request);
		try {
			Dominio dominio = (Dominio) modelAndView.getModelMap().get(DOMINIO);
			String dominioId = dominio.getUid();
			// Vado sulla root - e' la cartella corrente
			File_itf file = fileDao.getFirstLevel(dominioId);
			String idFile = file.getId();

			return esploraCartella(request, response, idFile);

		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}
	}

	// http://sportlandia.local/edit/documento/nuovaCartella/036456/1
	@RequestMapping(value = "documento/nuovaCartella/{idParent}/{folderName}", method = RequestMethod.GET)
	protected ModelAndView nuovaCartella(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("idParent") String idParent, @PathVariable("folderName") String folderName) {

		ModelAndView modelAndView = getModelAndView(request);
		try {
			Dominio dominio = getDominio(request);

			// ELABORAZIONE DA PARTE DEL MODELLO
			fileDao.creaCartella(idParent, folderName, dominio);

			// VALORI DI OUTPUT
			request.setAttribute("idFile", idParent);

			return esploraCartella(request, response, idParent);

		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}
	}

	/**
	 * @param request
	 * @param response
	 * @param idFile
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "documento/unzip/{idFile}", method = RequestMethod.GET)
	protected ModelAndView unzip(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("idFile") String idFile) throws Exception {

		ModelAndView modelAndView = getModelAndView(request);
		try {
			Dominio dominio = getDominio(request);

			// INIZIO AD UNZIPPARE
			File_itf fileZip = fileDao.getFile(idFile);
			String idParent = fileZip.getIdParent();

			// creo la cartella con il nome dello zip meno l'estensione non uso la cartella corrente
			String folderName = fileZip.getNomefile().substring(0,
					fileZip.getNomefile().length() - (fileZip.getExt().length() + 1));

			File_itf destDir = fileDao.creaCartella(idParent, folderName, dominio);
			File_itf workDir = fileDao.getFile(idParent);

			String root = fileDao.getRealFullPath(workDir.getId());

			File fileZippato = new File(root, fileZip.getNomefile());
			InputStream fin = new FileInputStream(fileZippato);
			ZipInputStream zipInputStream = new ZipInputStream(fin);
			ZipEntry zipEntry = null;
			while ((zipEntry = zipInputStream.getNextEntry()) != null) {
				String pathNameFile = zipEntry.getName();
				// System.out.println("Unzipping " + pathNameFile);

				// Cerco la directory giusta
				workDir = destDir;
				String[] pathArray = pathNameFile.split("/");

				String fileName = pathArray[pathArray.length - 1];
				// escludo l'ultimo elemento perchè è quello che mi interessa
				for (int i = 0; i < pathArray.length - 1; i++) {
					String workDirName = pathArray[i];
					File_itf unaCartella = fileDao.getFile(workDir.getId(), workDirName);
					if (unaCartella == null) {
						// La directory non esiste, la creo
						workDir = fileDao.creaCartella(workDir.getId(), workDirName, dominio);
					} else {
						workDir = unaCartella;
					}
				}
				// adesso sono nella cartella giusta
				// System.out.println("workDir=" + workDir.getNomefile());

				if (zipEntry.isDirectory()) {
					String nomeCartella = fileName;
					// System.out.println("Trovata Cartella: " + nomeCartella);
					fileDao.creaCartella(workDir.getId(), nomeCartella, dominio);
				} else {
					// E' un file
					File_itf nuovoFile = fileDao.creaFile(workDir.getId(), fileName);
					((DBFile) nuovoFile).setDominio(dominio);
					((FileDao) fileDao).save(nuovoFile);

					String path = fileDao.getRealFullPath(workDir.getId());
					File container = new File(path);
					if (!container.exists()) {
						container.mkdirs();
					}

					File file = new File(path, fileName);
					FileOutputStream fileOutputStream = new FileOutputStream(file);

					int size;
					byte[] buffer = new byte[2048];

					while ((size = zipInputStream.read(buffer, 0, buffer.length)) != -1) {
						fileOutputStream.write(buffer, 0, size);
					}
					fileOutputStream.flush();
					fileOutputStream.close();

				}
				zipInputStream.closeEntry();
			}
			zipInputStream.close();

			return esploraCartella(request, response, destDir.getId());

		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}

	}

	/**
	 * @param request
	 * @param response
	 * @param idFile
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "documento/unzipSameFolder/{idFile}", method = RequestMethod.GET)
	protected ModelAndView unzipSameFolder(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("idFile") String idFile) throws Exception {

		ModelAndView modelAndView = getModelAndView(request);
		try {
			Dominio dominio = getDominio(request);

			// INIZIO AD UNZIPPARE
			File_itf fileZip = fileDao.getFile(idFile);
			String idParent = fileZip.getIdParent();

			// non creo la cartella con il nome dello zip meno l'estensione ma uso la cartella corrente
			// String folderName = fileZip.getNomefile().substring(0,
			// fileZip.getNomefile().length() - (fileZip.getExt().length() + 1));
			// File_itf workDir = fileManager.creaCartella(fileZip.getIdParent(), folderName);

			File_itf baseDir = fileDao.getFile(idParent);
			File_itf workDir = baseDir;

			String folderId = baseDir.getId();
			String root = fileDao.getRealFullPath(folderId);

			File fileZippato = new File(root, fileZip.getNomefile());
			InputStream fin = new FileInputStream(fileZippato);
			ZipInputStream zipInputStream = new ZipInputStream(fin);
			ZipEntry zipEntry = null;
			while ((zipEntry = zipInputStream.getNextEntry()) != null) {
				String pathNameFile = zipEntry.getName();
				// System.out.println("Unzipping " + pathNameFile);

				// Cerco la directory giusta
				workDir = baseDir;
				String[] pathArray = pathNameFile.split("/");

				String fileName = pathArray[pathArray.length - 1];
				// escludo l'ultimo elemento perchè è quello che mi interessa
				for (int i = 0; i < pathArray.length - 1; i++) {
					String workDirName = pathArray[i];
					File_itf unaCartella = fileDao.getFile(workDir.getId(), workDirName);
					if (unaCartella == null) {
						// La directory non esiste, la creo
						workDir = fileDao.creaCartella(workDir.getId(), workDirName, dominio);
					} else {
						workDir = unaCartella;
					}
				}
				// adesso sono nella cartella giusta
				// System.out.println("workDir=" + workDir.getNomefile());

				if (zipEntry.isDirectory()) {
					String nomeCartella = fileName;
					// System.out.println("Trovata Cartella: " + nomeCartella);
					fileDao.creaCartella(workDir.getId(), nomeCartella, dominio);
				} else {
					// E' un file
					File_itf nuovoFile = fileDao.creaFile(workDir.getId(), fileName);
					((DBFile) nuovoFile).setDominio(dominio);
					((FileDao) fileDao).save(nuovoFile);

					String path = ((FileDao) fileDao).getRealFullPath(workDir.getId());
					File file = new File(path, fileName);
					FileOutputStream fileOutputStream = new FileOutputStream(file);

					int size;
					byte[] buffer = new byte[2048];

					while ((size = zipInputStream.read(buffer, 0, buffer.length)) != -1) {
						fileOutputStream.write(buffer, 0, size);
					}
					fileOutputStream.flush();
					fileOutputStream.close();

				}
				zipInputStream.closeEntry();
			}
			zipInputStream.close();

			request.setAttribute("idFile", baseDir.getId());
			request.setAttribute("cartellaCorrente", baseDir.getId());

			return esploraCartella(request, response, idParent);

		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}

	}

	/**
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "documento/update/{id}", method = RequestMethod.GET)
	protected ModelAndView update(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") String id) {

		ModelAndView modelAndView = getModelAndView(request);

		try {
			DBFile file = (DBFile) fileDao.findById(id);
			modelAndView.addObject("documento", file);

			modelAndView.setViewName("upload/update");
			return modelAndView;

		} catch (Exception errore) {
			return error(modelAndView, errore);
		}
	}

	/**
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "documento/upload", method = RequestMethod.POST)
	protected ModelAndView upload(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = getModelAndView(request);
		try {
			Dominio dominio = getDominio(request);
			File_itf file = fileDao.upload(request, dominio);
			// VALORI DI OUTPUT
			return esploraCartella(request, response, file.getIdParent());

		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}
	}

	@RequestMapping(value = "x/y/z", method = RequestMethod.GET)
	protected ModelAndView xyx(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = getModelAndView(request);

		String viewName = "edit/utente/list";
		modelAndView.setViewName(viewName);

		return modelAndView;
	}

}
