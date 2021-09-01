package objectorienteddesign.InMemoryDBMS.src;

public interface DatabaseManager {
    public Database createDatabase(String databaseName);
    public void deleteDatabase(String databaseName);
}
