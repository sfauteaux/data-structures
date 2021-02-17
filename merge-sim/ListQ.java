//Singly-linked based Queue. Keeps track of first value and overall size
public class ListQ implements Queue{
    private Node head;
    private int size;

    //Constructor
    public ListQ(){
        size = 0;
    }

    //add method takes int, creates node using int, adds int to the queue
    public void add(int i){
        Node n = new Node(i);
        if(size == 0){ //if first value of the queue
            head = n;
            size = 1;
            return;
        }
        Node temp = head;
        while(temp.getNext() != null){
            temp = temp.getNext();
        }
        temp.setNext(n);
        size++;
    }

    //pop function returns and removes the first value of the queue
    public int pop(){
        int ret = head.getVal();
        if(size == 1){
            head = null;
            size = 0;
            return ret;
        }
        else {
            Node temp = head;
            head = head.getNext(); //move forward in queue
            temp.setNext(null); //old head ready for garbage collection
        }

        size--;

        return ret;
    }

    //Basic getter
    public int getSize(){
        return size;
    }


}