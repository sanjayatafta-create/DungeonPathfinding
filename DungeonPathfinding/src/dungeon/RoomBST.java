package dungeon;

public class RoomBST {
    private BSTNode root;

    public void insert(Room room) { 
        root = insertRec(root, room); 
    }
    private BSTNode insertRec(BSTNode node, Room room) {
        if (node == null) return new BSTNode(room);
        if (room.getId() < node.data.getId()) node.left = insertRec(node.left, room);
        else if (room.getId() > node.data.getId()) node.right = insertRec(node.right, room);
        return node;
    }

    public Room search(int id) {
        BSTNode tmp = root;
        while (tmp != null) {
            if (id == tmp.data.getId()) return tmp.data;
            else if (id < tmp.data.getId()) tmp = tmp.left;
            else tmp = tmp.right;
        }
        return null;
    }

    public void inOrderPrint() { 
        inOrderRec(root); 
    }
    
    private void inOrderRec(BSTNode node) {
        if (node != null) {
            inOrderRec(node.left);
            System.out.println(node.data);
            inOrderRec(node.right);
        }
    }
}
