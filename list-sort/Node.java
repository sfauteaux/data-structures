public class Node {

    private Integer val;

    private Node prev = null;
    private Node next = null;

    //constructor
    public Node(Integer val) {
        this.val = val;
    }

    public void setNext(Node n) {
        next = n;
    }

    public void setPrev(Node p) {
        prev = p;
    }

    public Node getNext() {
        return next;
    }

    public Node getPrev() {
        return prev;
    }

    public Integer getVal(){
        return val;
    }

    public void setVal(int t) {
        val = t;
    }

}