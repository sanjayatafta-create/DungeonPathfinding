package history;

public class PlayerQueue {
    private QueueNode front;
    private QueueNode rear;
    
    public PlayerQueue() { front = null; rear = null; }
    
    public void enqueue(String action) {
        QueueNode n = new QueueNode(action);
        if (rear == null) { 
            front = n; 
            rear = n; 
        } else { 
            rear.next = n; 
            rear = n; 
        }
    }
    
    public String dequeue() {
        if (front == null) return null; 
        String v = front.action; 
        front = front.next; 
        if (front == null) rear = null; 
        return v; 
    }
    
    public void printLast(int n) {
        if (front == null) {
            System.out.println("  (kosong)");
            return;
        }
        
        // Hitung total elemen
        int total = 0;
        QueueNode t = front;
        while (t != null) {
            total++;
            t = t.next;
        }
        
        // Tampilkan n elemen terakhir
        int startIndex = Math.max(0, total - n);
        t = front;
        int currentIndex = 0;
        
        System.out.println("  ┌─[ " + Math.min(n, total) + " AKSI TERAKHIR ]" + "─".repeat(35));
        while (t != null) {
            if (currentIndex >= startIndex) {
                System.out.printf("  │ %2d. %s\n", 
                    (currentIndex - startIndex + 1), t.action);
            }
            currentIndex++;
            t = t.next;
        }
        System.out.println("  └" + "─".repeat(60));
    }
    
    public void printQueue() { 
        QueueNode t = front; 
        if (t == null) { 
            System.out.println("  (kosong)"); 
            return; 
        } 
        int i = 1;
        System.out.println("  ┌─[ LOG AKSI ]" + "─".repeat(45));
        while (t != null) { 
            System.out.printf("  │ %2d. %s\n", i++, t.action);
            t = t.next; 
        }
        System.out.println("  └" + "─".repeat(60));
    }
}