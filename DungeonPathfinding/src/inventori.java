public class inventori{
    itemnode head;

    public inventori() {
        this.head = null;
    }

    public void tambahitem(String namaitem, String tipe, int value) {
        itemnode newItem = new itemnode(namaitem, tipe, value);
        if (head == null) {
            head = newItem;
        } else {
            itemnode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newItem;
        }
    }

    public void tampilkanitem() {
        if (head == null) {
            System.out.println("Inventori kosong.");
            return;
        }
        itemnode current = head;
        int count = 0;
        while (current != null) {
            count++;
            System.out.println(count + ". " + current.namaitem + " [" + current.tipe + "] " + current.value +" gold");
            current = current.next;
        }
    }

    public int hitungitem() {
        int count = 0;
        itemnode current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public itemnode getHead() {
        return head;
    }

    public boolean hapusitem(String namaitem) {
        if (head == null) {
            return false;
        }
        if (head.namaitem.equals(namaitem)) {
            head = head.next;
            return true;
        }
        itemnode current = head;
        while (current.next != null) {
            if (current.next.namaitem.equals(namaitem)) {
                current.next = current.next.next;
                return true;
            }
            current = current.next;
        }
        return false;
    }
}