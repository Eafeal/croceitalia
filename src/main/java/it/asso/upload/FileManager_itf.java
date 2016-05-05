package it.asso.upload;

import it.asso.util.AssoException;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.cms.jpa.object.impl.Dominio;

/**
 * @author paolo
 * 
 */
public interface FileManager_itf {

	File_itf creaCartella(String idParent, String fileName, Dominio dominio) throws Exception;

	File_itf creaFile(String idParent, String fileName) throws Exception;

	void delete(File_itf file) throws Exception;

	void fillDocument(File_itf nuovoFile, Blob blob) throws Exception;

	void fillDocument(File_itf nuovoFile, InputStream inputStream) throws Exception;

	File_itf findById(String id);

	List<File_itf> getChildren(String idParent) throws Exception;

	File_itf getFile(String idFile) throws Exception;

	File_itf getDirForUser(String user_id) throws Exception;

	File_itf getFile(String idParent, String nomeFile) throws Exception;

	File_itf getFirstLevel(String dominioId) throws Exception;

	String getFullPath(String idFile) throws Exception;

	InputStream getInputStream(String idFile) throws Exception;

	String getRealFullPath(String folder_id) throws AssoException;

	Map<String, String> getTypeMap(String type, String nomeFile, Dominio dominio) throws AssoException;

	void update(File_itf file) throws AssoException;

	File_itf upload(HttpServletRequest request, Dominio dominio) throws Exception;

	void writeOn(String idFile, OutputStream out) throws Exception;

}
