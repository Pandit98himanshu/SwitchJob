package objectorienteddesign.InMemoryDBMS.src;

import java.util.*;

public class Row {
    private String rowID;
    private HashMap<String, String> columnValuesMap;
    private Date createdAt;
    private Date updatedAt;

    public Row(String rowID, HashMap<String, String> columnValuesMap, Date createdAt, Date updatedAt) {
        this.rowID = rowID;
        this.columnValuesMap = columnValuesMap;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getRowID() {
        return rowID;
    }

    public void setRowID(String rowID) {
        this.rowID = rowID;
    }

    public HashMap<String, String> getColumnValuesMap() {
        return columnValuesMap;
    }

    public void setColumnValuesMap(HashMap<String, String> columnValuesMap) {
        this.columnValuesMap = columnValuesMap;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
