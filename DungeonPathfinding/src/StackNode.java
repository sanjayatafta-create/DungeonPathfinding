public class StackNode {
    public String info;
    public StackNode next;
    public StackNode(String info) { 
        this.info = info; 
        this.next = null; 
    }
}
class PlayerStack {
    private StackNode top;
    public PlayerStack() { 
        top = null; 
    }
    public void push(String info) { 
        StackNode n = new StackNode(info); 
        n.next = top; 
        top = n; 
    }
    public String pop() { 
        if (top == null) return null; 
        String v = top.info; 
        top = top.next; 
        return v; 
    }
    public String peek() { 
        if (top == null) return null; 
        return top.info; 
    }
    public boolean isEmpty() { 
        return top == null; 
    }
    public int size() {
        int count = 0;
        StackNode t = top;
        while (t != null) {
            count++;
            t = t.next;
        }
        return count;
    }
    public void printHistory() { 
        StackNode t = top; 
        if (t == null) { 
            System.out.println("  (kosong)"); 
            return; 
        } 
        int i = 1;
        System.out.println("  ┌─[ HISTORY ]" + "─".repeat(45));
        while (t != null) { 
            System.out.printf("  │ %2d. %s\n", i++, t.info);
            t = t.next; 
        }
        System.out.println("  └" + "─".repeat(60));
    }
}