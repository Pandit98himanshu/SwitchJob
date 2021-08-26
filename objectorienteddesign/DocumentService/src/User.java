public interface User {
    public int create(Document doc);

    public Document read(int id);

    public void edit(int id, Document doc);

    public void share(String user, Accesses accesses, int id);

    public void delete(int id);
}
