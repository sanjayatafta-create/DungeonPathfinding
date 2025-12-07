package inventory;

public class ItemNode {
    public Item data;
    public ItemNode next;
    public ItemNode(Item data) { this.data = data; this.next = null; }
}