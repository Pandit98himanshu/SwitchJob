package objectorienteddesign.InMemoryDBMS.src;

public interface Database {
    public Table createTable(String tableName);
    public void dropTable(String tableName);
    public void renameTable(String oldTableName, String newTableName);
}
