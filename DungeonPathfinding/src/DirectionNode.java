public class DirectionNode {
    public String roomName;
    public DirectionNode next;

    public DirectionNode(String roomName) { 
        this.roomName = roomName; 
        this.next = null; 
    }
}