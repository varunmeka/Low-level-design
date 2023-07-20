package Service;

import Service.impl.ColdTierDocumentService;
import Service.impl.HotTierDocumentService;
import model.AccessType;
import model.Document;
import model.DocumentType;
import model.User;

import java.util.HashMap;
import java.util.Map;

public class DocumentServiceFactory {
    private static Map<Integer, DocumentType> documentTypeMap = new HashMap<>();

    private DocumentService getDocumentService(DocumentType documentType){
        if(documentType == DocumentType.COLD_TIER)
            return new ColdTierDocumentService();
        else if(documentType == DocumentType.HOT_TIER)
            return new HotTierDocumentService();
        return null;
    }

    private int createDocument(DocumentType documentType,String documentName, User Author, String content){
        DocumentService documentService = getDocumentService(documentType);
        if(getDocumentService(documentType)!=null){
            int documentId = documentService.createDoument(documentName, Author,content);
            documentTypeMap.put(documentId,documentType);
            return documentId;
        }
        else{
            throw new RuntimeException("Unrecogniised document type");
        }
    }

    private int editDocument(int documentId,User editUser, String content){
        return getDocumentService(documentTypeMap.get(documentId)).editDocument(documentId,editUser,content);
    }

//    private int deleteDocument
//    int deleteDocument(int documentId, User editUser );
//    boolean grantAccess(AccessType accessType, int documentId, User user, User grantingUser);
//    Document readDocument(int documentId, User user );
//    void grantPublicAccess(int documentId, User user );

}
