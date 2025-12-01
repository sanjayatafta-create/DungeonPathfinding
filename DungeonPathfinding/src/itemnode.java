public class itemnode {
    String namaitem;
    String tipe;
    int value;
    itemnode next;

    public itemnode(String namaitem, String tipe, int value) {
        this.namaitem = namaitem;
        this.tipe = tipe;
        this.value = value;
        this.next = null;
    }
}
