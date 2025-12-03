class StackHistory {
    private StepNode  top;  

    public StackHistory() {
        top = null;
    }

    public void push(String step) {
        StepNode newNode = new StepNode(step);
        newNode.next = top;
        top = newNode;
    }

    public String pop() {
        if (top == null) {
            return "kosong";
        }
        String lastStep = top.step;
        top = top.next;
        return lastStep;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public String peek() {
        if (top == null) {
            return "kosong";
        }
        return top.step;
    }
}