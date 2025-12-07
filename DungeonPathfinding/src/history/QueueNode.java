package history;

public class QueueNode {
    public String action;
    public QueueNode next;
    public QueueNode(String action) { this.action = action; this.next = null; }
}