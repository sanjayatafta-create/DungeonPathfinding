public class Room {
    private int id;
    private String name;

    public Room north;
    public Room south;
    public Room east;
    public Room west;

    private boolean hasItem1;
    private String itemName1;
    private int itemValue1;
    
    private boolean hasItem2;
    private String itemName2;
    private int itemValue2;
    
    private boolean hasMonster;
    private String monsterName;
    private int monsterDamage;

    public Room(int id, String name) {
        this.id = id;
        this.name = name;
        north = null; 
        south = null; 
        east = null; 
        west = null;
        hasItem1 = false; 
        hasItem2 = false;
        hasMonster = false; 
        monsterName = null; 
        monsterDamage = 0;
    }

    public int getId() { return id; }
    public String getName() { return name; }

    public void placeItem(String name, int value) { 
        if (!hasItem1) {
            hasItem1 = true;
            itemName1 = name;
            itemValue1 = value;
        } else if (!hasItem2) {
            hasItem2 = true;
            itemName2 = name;
            itemValue2 = value;
        }
    }
    
    public boolean hasItem() { 
        return hasItem1 || hasItem2; 
    }
    
    public String getItemName() { 
        if (hasItem1) {
            return itemName1;
        }
        if (hasItem2) {
            return itemName2;
        }
        return null;
    }
    
    public int getItemValue() { 
        if (hasItem1) {
            return itemValue1;
        }
        if (hasItem2) {
            return itemValue2;
        }
        return 0;
    }
    
    // MODIFIKASI: Ambil item yang ada
    public ItemInfo takeItem() {
        if (hasItem1) {
            ItemInfo info = new ItemInfo(itemName1, itemValue1);
            hasItem1 = false;
            itemName1 = null;
            itemValue1 = 0;
            return info;
        }
        
        if (hasItem2) {
            ItemInfo info = new ItemInfo(itemName2, itemValue2);
            hasItem2 = false;
            itemName2 = null;
            itemValue2 = 0;
            return info;
        }
        
        return null;
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

    public String toString() {
        int totalItems = (hasItem1 ? 1 : 0) + (hasItem2 ? 1 : 0);
        return name + " (ID: " + id + ", Items: " + totalItems + ")";
    }
    
    public String getFullInfo() {
        int totalItems = (hasItem1 ? 1 : 0) + (hasItem2 ? 1 : 0);
        return name + " [ID:" + id + "]" + 
               (totalItems > 0 ? " [Items: " + totalItems + "]" : "") + 
               (hasMonster ? " [Monster: " + monsterName + "]" : "");
    }
    
    public static class ItemInfo {
        public final String name;
        public final int value;
        
        public ItemInfo(String name, int value) {
            this.name = name;
            this.value = value;
        }
    }
}