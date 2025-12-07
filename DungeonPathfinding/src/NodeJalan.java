public class NodeJalan {
    String langkah;
    NodeJalan next;
    
    NodeJalan(String langkah) {
        this.langkah = langkah;
        this.next = null;
    }
}

class QueueJalan {
    NodeJalan depan;
    NodeJalan belakang;
    
    QueueJalan() {
        depan = null;
        belakang = null;
    }

    void tambah(String langkah) {
        NodeJalan baru = new NodeJalan(langkah);
        if (belakang == null) {
            depan = belakang = baru;
        } else {
            belakang.next = baru;
            belakang = baru;
        }
    }
    
    String ambil() {
        if (depan == null) {
            return "kosong";
        }
        String hasil = depan.langkah;
        depan = depan.next;
        if (depan == null) {
            belakang = null;
        }
        return hasil;
    }
    
    boolean kosong() {
        return depan == null;
    }
}