import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.LinkedList;

public class HashTable {
    private final ArrayList<LinkedList<String>> table;
    private final int m;  // size of table

    public HashTable(int m) {
        this.m = m;
        this.table = new ArrayList<>();
        for(int i = 0; i < m; i++) {
            this.table.add(new LinkedList<>());
        }
    }

    public ArrayList<LinkedList<String>> getTable() {
        return table;
    }

    public int getSize() {
        return m;
    }

    public int hash_function(String key) {
        int hashVal = 0;
        int p = 31;
        for (int i = 0; i < key.length(); i++) {
            hashVal += key.charAt(i) * Math.pow(p, i);
        }
        return hashVal % m;
    }

    public boolean contains(String key) {
        // checks if the key exists at the hashcode position in the table
        int hashcode = hash_function(key);
        return table.get(hashcode).contains(key);
    }

    public int put(String key) {
        int hashcode = hash_function(key);

        // if the symbol does not exist in the table, it adds it to the linked list
        if (!this.contains(key)) {
            LinkedList<String> list = table.get(hashcode);
            list.add(key);
        }
        return table.get(hashcode).indexOf(key);
    }

    public AbstractMap.SimpleImmutableEntry<Integer, Integer> getPositionInTable(String key) throws Exception {
        if (!this.contains(key)) {
            throw new Exception("Key was not found in the hashtable!");
        }
        int arrayPosition = hash_function(key);
        int linkedListPosition = table.get(arrayPosition).indexOf(key);
        return new AbstractMap.SimpleImmutableEntry<>(arrayPosition, linkedListPosition);
    }

    @Override
    public String toString() {
        StringBuilder table = new StringBuilder();
        for (int i = 0; i < m; i++) {
            table.append(this.table.get(i));
            table.append("\n");
        }
        return table.toString();
    }
}
