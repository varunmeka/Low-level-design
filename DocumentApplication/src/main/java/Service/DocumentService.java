package Service;

import model.AccessType;
import model.Document;
import model.User;

public interface DocumentService {

    int createDoument(String documentName, User Author, String content);
    int editDocument(int documentId,User editUser, String content);
    int deleteDocument(int documentId, User editUser );
    boolean grantAccess(AccessType accessType,int documentId, User user,User grantingUser);
    Document readDocument(int documentId, User user );
    void grantPublicAccess(int documentId, User user );
}
