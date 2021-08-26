import java.util.*;

public class TableImpl implements Table {
    private String tableName;
    private HashMap<String, Row> rows;
    private Date createdAt;

    public TableImpl(String tableName) {
        this.tableName = tableName;
        rows = new HashMap<>();
        createdAt = new Date();
    }

    @Override
    public void insert(String rowId, HashMap<String, String> columnsMap) {
        if (rows.containsKey(rowId)) {
            System.out.println("Duplicate primary key. Insertion failed.");
        } else {
            Row row = new Row(rowId, columnsMap, new Date(), new Date());
            rows.put(rowId, row);
            System.out.println("Successfully added a row");
        }
    }

    @Override
    public void update(String rowId, HashMap<String, String> columnValuesMap) {
        Row row = rows.get(rowId);
        if (row == null) {
            System.out.println("No such row found.");
        } else {
            columnValuesMap.forEach((K, V) -> row.getColumnValuesMap().put(K, V));
            System.out.println("Successfully updated.");
            row.setUpdatedAt(new Date());
        }
    }

    @Override
    public void delete(String rowId) {
        rows.remove(rowId);
        System.out.println("Successfully deleted.");
    }

    @Override
    public Row select(String rowId) {
        return rows.get(rowId);
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public HashMap<String, Row> getRows() {
        return rows;
    }

    public void setRows(HashMap<String, Row> rows) {
        this.rows = rows;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
