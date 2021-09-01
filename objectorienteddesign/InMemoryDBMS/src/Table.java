package objectorienteddesign.InMemoryDBMS.src;

import java.util.*;

public interface Table {
    public void insert(String rowId, HashMap<String, String> columnsMap);
    public void update(String rowId, HashMap<String, String> columnsMap);
    public void delete(String rowId);
    public Row select(String rowId);
}
