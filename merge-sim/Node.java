//Basic node class used for the List-based queue
public class Node{
    private Node next;
    private int val;

    public Node(int v){
        val = v;
        next = null;
    }


    public Node getNext(){
        return next;
    }

    public void setNext(Node n){
        next = n;
    }

    public int getVal(){
        return val;
    }
}