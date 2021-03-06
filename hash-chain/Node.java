/*
Basic Node class that stores an id, string, and reference to the next node
in the list. List is singly linked, so no reference to previous nodes
 */
public class Node {
    private int id;
    private String value;

    private Node next;

    //Default constructor for nodes containing data
    public Node(int i, String v) {
        id = i;
        value = v;
    }

    //Empty constructor for head,tail nodes
    public Node() {
    }


    //Basic getters and setters below

    public int getId() {
        return id;
    }

    public String getVal() {
        return value;
    }

    public void setVal(String s) {
        value = s;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node n) {
        next = n;
    }

}