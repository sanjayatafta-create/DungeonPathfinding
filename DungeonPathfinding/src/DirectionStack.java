public class DirectionStack {
    private DirectionNode top;
    public DirectionStack() { 
        top = null; 
    }
    public void push(String room) { 
        DirectionNode n = new DirectionNode(room); 
        n.next = top; 
        top = n; 
    }
    public String pop() { 
        if (top == null) return null; 
        String r = top.roomName; 
        top = top.next; 
        return r; 
    }
    public String peek() { 
        if (top == null) return null; 
        return top.roomName; 
    }
    public boolean isEmpty() { 
        return top == null; 
    }
    
    public int size() {
        int count = 0;
        DirectionNode t = top;
        while (t != null) {
            count++;
            t = t.next;
        }
        return count;
    }
    
    public void printStack() { 
        DirectionNode t = top; 
        if (t == null) { 
            System.out.println("(kosong)"); 
            return; 
        } 
        int i = 1;
        while (t != null) { 
            System.out.println(i++ + ". " + t.roomName); 
            t = t.next; 
        } 
    }
}