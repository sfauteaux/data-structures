/*
Node class for singly linked lists that stores an int value
and a reference to the previous node in the list.

Stores previous value because we keeping reference to the top node
at all times and we need to start from the top and go backwards.
 */
public class Node{
    private double val;

    private Node prev;

    //constructor takes int and node values. If first node,
    //must set prev = null when creating node
    public Node(double i, Node p){
        val = i;
        prev = p;
    }

    //basic getters and setters
    public double getVal(){
        return val;
    }

    public Node getPrev(){
        return prev;
    }

    public void setPrev(Node p){
        prev = p;
    }

    //don't need a setter for val, the int values of the nodes don't change
}