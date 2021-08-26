public class UserImpl implements User {
    private String username;
    private OperationsImpl performOperation = new OperationsImpl();

    public UserImpl(String username) {
        this.username = username;
    }

    @Override
    public int create(Document doc) {
        return performOperation.create(username, doc);
    }

    @Override
    public Document read(int id) {
        return performOperation.read(username, id);
    }

    @Override
    public void edit(int id, Document doc) {
        performOperation.edit(username, id, doc);
    }

    @Override
    public void share(String user, Accesses accesses, int id) {
        performOperation.share(username, user, accesses, id);
    }

    @Override
    public void delete(int id) {
        performOperation.delete(username, id);
    }
}
