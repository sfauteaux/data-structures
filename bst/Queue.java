/*
Queue class uses the existing node class to create a FIFO queue that is
used for the level-order traversal.
 */
public class Queue{
    private Node head;
    private int size;

    public Queue(){
        head = null;
        size = 0;
    }

    public void enqueue(Node n){
        if(size == 0){
            head = n;
            size++;
            return;
        }
        Node temp = head;
        while(temp.getNext() != null){
            temp = temp.getNext();
        }
        temp.setNext(n);
        size++;
    }

    //returns the first node in the queue
    public Node dequeue(){
        Node ret = head;
        if(size == 1){
            size = 0;
        }
        else {
            head = head.getNext();
            size--;
        }
        ret.setNext(null);
        return ret;
    }

    public int getSize(){
        return size;
    }
}