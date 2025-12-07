package dungeon;

public class Room {
    private int id;
    private String name;

    public Room north;
    public Room south;
    public Room east;
    public Room west;

    private boolean hasItem;
    private String itemName;
    private int itemValue;

    private boolean hasMonster;
    private String monsterName;
    private int monsterDamage;

    public Room(int id, String name) {
        this.id = id;
        this.name = name;
        north = null; south = null; east = null; west = null;
        hasItem = false; itemName = null; itemValue = 0;
        hasMonster = false; monsterName = null; monsterDamage = 0;
    }

    public int getId() { return id; }
    public String getName() { return name; }

    public void placeItem(String name, int value) { 
        hasItem = true; 
        itemName = name; 
        itemValue = value; 
    }
    
    public boolean hasItem() { return hasItem; }
    
    // FIXED: Mengembalikan nama item TANPA menghapus state
    public String getItemName() { 
        return hasItem ? itemName : null; 
    }
    
    // FIXED: Mengembalikan value item TANPA menghapus state
    public int getItemValue() { 
        return hasItem ? itemValue : 0; 
    }
    
    // FIXED: Method baru untuk mengambil item (menghapus state)
    public ItemInfo takeItem() {
        if (!hasItem) return null;
        
        ItemInfo info = new ItemInfo(itemName, itemValue);
        hasItem = false;
        itemName = null;
        itemValue = 0;
        return info;
    }

    public void placeMonster(String name, int dmg) { 
        hasMonster = true; 
        monsterName = name; 
        monsterDamage = dmg; 
    }
    
    public boolean hasMonster() { return hasMonster; }
    public String monsterName() { return monsterName; }
    public int monsterDamage() { return monsterDamage; }
    
    public void killMonster() { 
        hasMonster = false; 
        monsterName = null; 
        monsterDamage = 0; 
    }

    // UPDATE toString METHOD
    public String toString() {
        return name + " (ID: " + id + ")";
    }
    
    // TAMBAH METHOD BARU UNTUK MENDAPATKAN INFO LENGKAP
    public String getFullInfo() {
        return name + " [ID:" + id + "]" + 
               (hasItem ? " [Item: " + itemName + "]" : "") + 
               (hasMonster ? " [Monster: " + monsterName + "]" : "");
    }
    
    // INNER CLASS untuk menyimpan info item
    public static class ItemInfo {
        public final String name;
        public final int value;
        
        public ItemInfo(String name, int value) {
            this.name = name;
            this.value = value;
        }
    }
}