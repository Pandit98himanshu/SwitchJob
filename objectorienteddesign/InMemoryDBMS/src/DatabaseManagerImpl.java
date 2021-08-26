import java.util.HashMap;

public class DatabaseManagerImpl implements DatabaseManager {
    HashMap<String, Database> databaseHashMap;

    public DatabaseManagerImpl() {
        this.databaseHashMap = new HashMap<>();
    }

    @Override
    public Database createDatabase(String databaseName) {
        if (databaseHashMap.containsKey(databaseName)) {
            System.out.println("Database already exists with this name");
        } else {
            databaseHashMap.put(databaseName, new DatabaseImpl(databaseName));
            System.out.println("Database created.");
        }
        return databaseHashMap.get(databaseName);
    }

    @Override
    public void deleteDatabase(String databaseName) {
        databaseHashMap.remove(databaseName);
        System.out.println("Database successfully removed.");
    }

    public HashMap<String, Database> getDatabaseHashMap() {
        return databaseHashMap;
    }

    public void setDatabaseHashMap(HashMap<String, Database> databaseHashMap) {
        this.databaseHashMap = databaseHashMap;
    }
}
