public class ItemLinkedList {
    private ItemNode head;

    public ItemLinkedList() { 
        head = null; 
    }

    public void add(Item item) {
        ItemNode n = new ItemNode(item);
        if (head == null) { head = n; return; }
        ItemNode t = head;
        while (t.next != null) t = t.next;
        t.next = n;
    }

    public Item searchByName(String name) {
        ItemNode t = head;
        while (t != null) {
            if (t.data.getName().equalsIgnoreCase(name)) return t.data;
            t = t.next;
        }
        return null;
    }
    
    public boolean hasItem(String name) {
        return searchByName(name) != null;
    }

    public void sortByValueDesc() {
        if (head == null) return;
        boolean swapped;
        do {
            swapped = false;
            ItemNode cur = head;
            while (cur.next != null) {
                if (cur.data.getValue() < cur.next.data.getValue()) {
                    Item tmp = cur.data; 
                    cur.data = cur.next.data; 
                    cur.next.data = tmp; 
                    swapped = true;
                }
                cur = cur.next;
            }
        } while (swapped);
    }
    
    public boolean remove(String name) {
        if (head == null) return false;
        
        if (head.data.getName().equalsIgnoreCase(name)) {
            head = head.next;
            return true;
        }
        
        ItemNode prev = head;
        ItemNode current = head.next;
        
        while (current != null) {
            if (current.data.getName().equalsIgnoreCase(name)) {
                prev.next = current.next;
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false;
    }
    
    public Item removeAndGet(String name) {
        if (head == null) return null;
        
        if (head.data.getName().equalsIgnoreCase(name)) {
            Item removed = head.data;
            head = head.next;
            return removed;
        }
        
        ItemNode prev = head;
        ItemNode current = head.next;
        
        while (current != null) {
            if (current.data.getName().equalsIgnoreCase(name)) {
                prev.next = current.next;
                return current.data;
            }
            prev = current;
            current = current.next;
        }
        return null;
    }
    
    public boolean isEmpty() {
        return head == null;
    }
    
    public int count() {
        int count = 0;
        ItemNode t = head;
        while (t != null) {
            count++;
            t = t.next;
        }
        return count;
    }
    
    public int totalValue() {
        int total = 0;
        ItemNode t = head;
        while (t != null) {
            total += t.data.getValue();
            t = t.next;
        }
        return total;
    }

    public void printAll() {
        ItemNode t = head;
        if (t == null) { 
            System.out.println("  (kosong)"); 
            return; 
        }
        int i = 1;
        while (t != null) { 
            System.out.printf("  %2d. %-25s Value: %4d gold\n", 
                i++, t.data.getName(), t.data.getValue());
            t = t.next; 
        }
    }
    
    // Metode baru untuk print dengan format lebih baik
    public void printFormatted() {
        ItemNode t = head;
        if (t == null) { 
            System.out.println("  └─ (Tidak ada item)"); 
            return; 
        }
        int i = 1;
        System.out.println("  ┌─[ INVENTORY ]" + "─".repeat(35));
        while (t != null) { 
            System.out.printf("  │ %2d. %-20s  %6d gold\n", 
                i++, t.data.getName(), t.data.getValue());
            t = t.next; 
        }
        System.out.println("  └" + "─".repeat(52));
    }
}