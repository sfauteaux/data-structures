/*
Basic doubly linked Node class. Stores int values for pid and time,
as well as pointers to previous and next nodes. By default, points
at itself in both directions.

All the methods are very simple getter/setter methods.
Print method that prints the PID and time values for this node.
*/
public class Node{

    private int pid;
    private int time;

    private Node prev = this;
    private Node next = this;

    //constructor
    public Node(int pid, int time){
        this.pid = pid;
        this.time = time;
    }

    public void setNext(Node n){
        next = n;
    }

    public void setPrev(Node p){
        prev = p;
    }

    public Node getNext(){
        return next;
    }

    public Node getPrev(){
        return prev;
    }

    public int getPid(){
        return pid;
    }

    public int getTime(){
        return time;
    }
    public void setTime(int t){
        time = t;
    }

    public void print(){
        System.out.println(getPid() + "," + getTime());
    }
}