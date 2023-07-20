package Service.impl;

import Service.DocumentService;
import model.AccessType;
import model.Document;
import model.User;

import java.util.*;

public class ColdTierDocumentService implements DocumentService {
    private static Map<Integer, Document> documentMap = new HashMap<>();

    @Override
    public int createDoument(String documentName, User author, String content) {
        Document document =new Document(documentName,content,author);
        documentMap.put(document.getDocumentId(),document);
        return document.getDocumentId();
    }

    @Override
    public int editDocument(int documentId, User editUser, String content) {
        if(hasWritePermissions(documentId,editUser)){
            documentMap.get(documentId).setContent(content);
        }
        else{
            throw new RuntimeException("Permission denied");
        }
        return documentId;
    }

    @Override
    public int deleteDocument(int documentId, User editUser) {
        Document document = documentMap.get(documentId);
        if(document.getAuthor().getUserName() ==  editUser.getUserName()){
            documentMap.remove(document.getDocumentId(),document);
        }
        else{
            throw new RuntimeException("Permission denied");
        }
        return 0;
    }

    @Override
    public boolean grantAccess(AccessType accessType, int documentId, User user, User grantingUser) {
        if(documentMap.containsKey(documentId)){
            Document document = documentMap.get(documentId);
            if(document.getAuthor().getUserName() != grantingUser.getUserName()){
                throw new RuntimeException("Permission denied");
            }
            Map<AccessType, List<User>> accessMap=   document.getAccessUsers();
            if(accessMap.containsKey(accessType)){
                accessMap.get(accessType).add(user);
            }
            else{
                accessMap.put(accessType, Arrays.asList(user));
            }
        }
        return false;
    }

    @Override
    public Document readDocument(int documentId, User user) {
        if(documentMap.containsKey(documentId)) {
            Document document = documentMap.get(documentId);
            if(document.getAuthor().getUserName() == user.getUserName() || !document.getIsPrivate()){
                return document;
            }
            else if(document.getAccessUsers().getOrDefault(AccessType.WRITE, new ArrayList<>()).contains(user)){
                return document;
            }
            else if(document.getAccessUsers().getOrDefault(AccessType.READ,new ArrayList<>()).contains(user)){
                return document;
            }
            else{
                throw new RuntimeException("Permission denied");
            }
        }
        return null;
    }

    @Override
    public void grantPublicAccess(int documentId, User user) {
        Document document= documentMap.get(documentId);
        if(document.getAuthor().getUserName() == user.getUserName()) {
            documentMap.get(documentId).setIsPrivate(false);
        }
        else{
            throw new RuntimeException("Permission denied");
        }
    }


    private boolean hasWritePermissions(int documentId, User user){
        Document currentDocument = documentMap.get(documentId);
        boolean hasPermissions=  currentDocument.getAccessUsers().getOrDefault(AccessType.WRITE, new ArrayList<>()).contains(user);
        return hasPermissions || !currentDocument.getIsPrivate();
    }

}
