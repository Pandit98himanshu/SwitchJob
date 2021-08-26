/*
Simple Document Service
Design and implement a Simple document Service where users can create documents and  read the same.

A document has a name and associated string content <name{string}, content{string}>

All documents are private when created.
Owners of documents can {grant} {read OR edit} access to other users
Only the owner can delete a document
Username will be just a string. Every action like create/read/edit/delete must be made on behalf of a user
 */
public class Main {

    public static void main(String[] args) {

        UserImpl user1 = new UserImpl("User1");
        UserImpl user2 = new UserImpl("User2");

        Document doc1 = new Document("Doc1", "This is a document.");
        int docId1 = user1.create(doc1);

        Document tempDoc = user1.read(docId1);

        System.out.println(tempDoc.getName());
        System.out.println(tempDoc.getBody());

        user2.read(docId1);

        Accesses accesses1 = new Accesses();
        accesses1.read = true;
        user1.share("user2", accesses1, docId1);

        user2.read(docId1);
/*
        Document doc2 = new Document("Doc2", "This is another document.");
        user2.edit(docId1, doc2);

        accesses1.edit = true;
        user1.share("user2", accesses1, docId1);

        user2.edit(docId1, doc2);
        user2.read(docId1);
*/
    }
}
