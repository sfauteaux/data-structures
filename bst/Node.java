public class Node {
    protected int key; //node key to find it when searching
    protected Node left; //left child
    protected Node right; //right child
    protected Node parent; //parent node, if node == root then parent == null;

    protected Node next; //used for the Queue class

    //Default constructor only takes key value. Other values will be
    //set by the Tree class during insertion.
    public Node(int k){
        key = k;
        parent = left = right = null;
    }

    /*Basic getters/setters below*/
    public int getKey() {
        return key;
    }

    //No setter for Key, we don't want to change a nodes key value in this assignment

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getNext(){
        return next;
    }

    public void setNext(Node n){
        next = n;
    }

}