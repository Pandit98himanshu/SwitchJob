package objectorienteddesign.DocumentService.src;

public interface Operations {
    public int create(String username, Document doc);

    public Document read(String user, int id);

    public void edit(String user, int id, Document doc);

    public void share(String ownerName, String user, Accesses acceses, int id);

    public void delete(String user, int id);
}
