package pt.example.bootstrap;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.apache.chemistry.opencmis.commons.enums.VersioningState;
import org.apache.chemistry.opencmis.commons.exceptions.CmisObjectNotFoundException;
import org.apache.chemistry.opencmis.commons.impl.dataobjects.ContentStreamImpl;

public class AlfrescoApi {
	public static void main(String[] args) {
		File initialFile = new File("C:\\sgiid\\relatorios\\icon.png");
		InputStream targetStream = null;
		try {
			targetStream = new FileInputStream(initialFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(uploadFile(targetStream, "icon.png"));
		// System.out.println(deleteFile("4db23f0f-b529-47e8-86e7-68f3d6a5e93e"));

	}

	public static String[] uploadFile(InputStream uploadedInputStream, String filename) {

		Session session = getSession();
		Folder root = session.getRootFolder();

		String targetFolderPath = "/Shared/Teste"; // Path to the existing folder
		// Navigate to the target folder using the path
		// Navigate to the target folder using the path
		Folder targetFolder = null;
		try {
			targetFolder = (Folder) session.getObjectByPath(targetFolderPath);
		} catch (CmisObjectNotFoundException e) {
			// Folder does not exist, create it
			String[] pathSegments = targetFolderPath.split("/");
			Folder parentFolder = session.getRootFolder();
			for (String segment : pathSegments) {
				if (segment.isEmpty())
					continue;
				try {
					parentFolder = (Folder) session.getObjectByPath(parentFolder.getPath() + "/" + segment);
				} catch (CmisObjectNotFoundException ex) {
					// Check if the folder already exists before creating it
					boolean folderExists = false;
					for (CmisObject cmisObject : parentFolder.getChildren()) {
						if (cmisObject instanceof Folder && segment.equals(cmisObject.getName())) {
							parentFolder = (Folder) cmisObject;
							folderExists = true;
							break;
						}
					}
					if (!folderExists) {
						Map<String, Object> folderProps = new HashMap<>();
						folderProps.put(PropertyIds.OBJECT_TYPE_ID, "cmis:folder");
						folderProps.put(PropertyIds.NAME, segment);
						parentFolder = parentFolder.createFolder(folderProps);
					}
				}
			}
			targetFolder = parentFolder;
		}

		// Check if the document already exists and rename if necessary
		String newFilename = filename;
		boolean documentExists = false;
		for (CmisObject cmisObject : targetFolder.getChildren()) {
			if (cmisObject instanceof Document && newFilename.equals(cmisObject.getName())) {
				documentExists = true;
				break;
			}
		}

		if (documentExists) {
			String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			int dotIndex = filename.lastIndexOf('.');
			if (dotIndex != -1) {
				newFilename = filename.substring(0, dotIndex) + "_" + timestamp + filename.substring(dotIndex);
			} else {
				newFilename = filename + "_" + timestamp;
			}
		}

		// Document Creation
		Map<String, Object> documentProps = new HashMap<>();
		documentProps.put(PropertyIds.OBJECT_TYPE_ID, "cmis:document");
		documentProps.put(PropertyIds.NAME, newFilename);

		// Document read/write
		ContentStream contentStream = new ContentStreamImpl(newFilename, null, "application/octet-stream",
				uploadedInputStream);

		Document doc = targetFolder.createDocument(documentProps, contentStream, VersioningState.MAJOR);

		String pathValue = doc.getId();
		String fileId = pathValue.split(";")[0];
		String parentId = targetFolder.getId();

		/*
		 * System.out.println("Document has been created"); System.out.println(fileId);
		 * System.out.println(parentId);
		 */

		String fileUrl = "http://192.168.40.107:8080/share/proxy/alfresco/slingshot/node/content/workspace/SpacesStore/"
				+ fileId + "/" + newFilename;
		System.out.println("File URL: " + fileUrl);
		return new String[] { fileId, parentId, newFilename, fileUrl };
	}

	public static String deleteFile(String documentId) {
		Session session = getSession();
		try {
			CmisObject cmisObject = session.getObject(documentId);
			if (cmisObject instanceof Document) {
				Document document = (Document) cmisObject;
				document.delete(true);
				// System.out.println("Document with ID " + documentId + " has been removed.");
				return "OK";
			} else {
				// System.out.println("The object with ID " + documentId + " is not a
				// document.");
				return "ERROR";
			}
		} catch (Exception e) {
			// System.out.println("Failed to remove the document with ID " + documentId);
			return "ERROR";
		}

	}

	public static Session getSession() {

		// ALFRESCO INTEGRATION
		SessionFactory factory = SessionFactoryImpl.newInstance();
		Map<String, String> parameter = new HashMap<String, String>();

		// Credentials setup
		parameter.put(SessionParameter.USER, "admin");
		parameter.put(SessionParameter.PASSWORD, "admin");

		// Connection to Alfresco
		parameter.put(SessionParameter.ATOMPUB_URL,
				"http://192.168.40.107:8080/alfresco/api/-default-/public/cmis/versions/1.0/atom");
		parameter.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());

		Session session = factory.getRepositories(parameter).get(0).createSession();
		return session;
	}
}