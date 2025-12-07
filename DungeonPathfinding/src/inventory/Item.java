package inventory;

public class Item {
    private String name;
    private String desc;
    private int value;

    public Item(String name, String desc, int value) {
        this.name = name; this.desc = desc; this.value = value;
    }

    public String getName() { return name; }
    public int getValue() { return value; }

    public String toString() { return name + " (" + desc + ", value=" + value + ")"; }
}