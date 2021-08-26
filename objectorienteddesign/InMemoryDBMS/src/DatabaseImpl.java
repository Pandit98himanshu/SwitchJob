import java.util.*;

public class DatabaseImpl implements Database {
    private String tableName;
    private HashMap<String, Table> tableHashMap;
    private Date createdAt;

    public DatabaseImpl(String tableName) {
        this.tableName = tableName;
        tableHashMap = new HashMap<>();
        createdAt = new Date();
    }

    @Override
    public Table createTable(String tableName) {
        if (tableHashMap.containsKey(tableName)) {
            System.out.println("Table with name " + tableName + " already exists.");
        } else {
            TableImpl table = new TableImpl(tableName);
            tableHashMap.put(tableName, table);
            System.out.println("Table successfully created.");
        }
        return tableHashMap.get(tableName);
    }

    @Override
    public void dropTable(String tableName) {
        tableHashMap.remove(tableName);
        System.out.println("Table successfully deleted.");
    }

    @Override
    public void renameTable(String oldTableName, String newTableName) {
        Table table = tableHashMap.get(oldTableName);
        dropTable(oldTableName);
        tableHashMap.put(newTableName, table);
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public HashMap<String, Table> getTableHashMap() {
        return tableHashMap;
    }

    public void setTableHashMap(HashMap<String, Table> tableHashMap) {
        this.tableHashMap = tableHashMap;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
