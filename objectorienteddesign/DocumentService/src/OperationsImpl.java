import java.util.*;

public class OperationsImpl implements Operations {
    int id = 0;
    private HashMap<Integer, Document> documentHash;
    private HashMap<Integer, String> owner;
    private HashMap<Integer, HashSet<String>> sharedUsersRead;
    private HashMap<Integer, HashSet<String>> sharedUsersEdit;
    private Document document;

    public OperationsImpl() {
        this.documentHash = new HashMap<>();
        this.document = new Document();
        this.owner = new HashMap<>();
        this.sharedUsersRead = new HashMap<>();
        this.sharedUsersEdit = new HashMap<>();
    }

    @Override
    public int create(String username, Document doc) {
        owner.put(id, username);
        documentHash.put(id, doc);
        return id++;
    }

    @Override
    public Document read(String user, int id) {
        String ownerName = owner.get(id);
        HashSet<String> sharedUsers = sharedUsersRead.get(id);
        if ((ownerName != null && ownerName.equals(user))
                || (sharedUsers != null && sharedUsers.contains(user))) {
            return documentHash.get(id);
        }
        System.out.println("You don't have read access.");
        return null;
    }

    @Override
    public void edit(String user, int id, Document doc) {
        String ownerName = owner.get(id);
        HashSet<String> sharedUsers = sharedUsersEdit.get(id);
        if ((ownerName != null && ownerName.equals(user))
                || (sharedUsers != null && sharedUsers.contains(user))) {
            documentHash.put(id, doc);
        } else {
            System.out.println("You don't have edit access.");
        }
    }

    @Override
    public void share(String ownerName, String user, Accesses accesses, int id) {
        String userName = owner.get(id);
        if (userName == null || !userName.equals(ownerName)) {
            System.out.println("You are not the owner.");
            return;
        }
        if (accesses.read) {
            HashSet<String> users = sharedUsersRead.get(id);
            if (users == null) {
                users = new HashSet<>();
            }
            users.add(user);
            sharedUsersRead.put(id, users);
        }
        if (accesses.edit) {
            HashSet<String> users = sharedUsersEdit.get(id);
            if (users == null) {
                users = new HashSet<>();
            }
            users.add(user);
            sharedUsersEdit.put(id, users);
        }
    }

    @Override
    public void delete(String user, int id) {
        String userName = owner.get(id);
        if (userName != null && userName.equals(user)) {
            documentHash.remove(id);
        } else {
            System.out.println("You don't have delete access.");
        }
    }
}
