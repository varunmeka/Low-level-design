import Service.DocumentService;
import Service.impl.HotTierDocumentService;
import model.AccessType;
import model.User;

public class DocumentApplication {
    public static void main(String[] args){
        System.out.println("DocumentApplication");
        //creating users
        User user1 = new User("user1");
        User user2 = new User("user2");
        User user3 = new User("user3");

        DocumentService documentService = new HotTierDocumentService();

        int documentId1 = documentService.createDoument("document1",user1,"document1 content");
        int documentId2 = documentService.createDoument("document2",user2,"document2 content");

        System.out.println("Created Document with id: "+documentId1);
        documentService.grantAccess(AccessType.WRITE,documentId1,user2,user1);
        System.out.println(documentService.readDocument(1,user2).getContent());
        documentService.editDocument(1,user2,"edited document1");
        System.out.println(documentService.readDocument(1,user2).getContent());

        documentService.grantPublicAccess(documentId2,user2);
        System.out.println(documentService.readDocument(documentId2,user3).getContent());
        System.out.println(documentService.readDocument(documentId2,user1).getContent());
        documentService.grantPublicAccess(documentId1,user1);
        System.out.println(documentService.readDocument(documentId1,user3).getContent());
        documentService.editDocument(1,user3,"edited document by user3");
        System.out.println(documentService.readDocument(documentId1,user3).getContent());
    }
}
