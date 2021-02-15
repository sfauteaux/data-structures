/*
CircularList class that keeps track of its size and 1 cursor node.
Each node is a doubly linked node, and the nodes get added in ascending order
based on the PID value of each node.

As a node is removed, all references to that node are nullified so that
the node can be picked up by the garbage collector.
 */

public class CircularList{
    protected Node cursor;
    protected int size;

    //list constructor
    public CircularList(){
        cursor = null;
        size = 0;
    }

    //Method to add nodes into the list
    public void addNode(Node a){
        //if list is empty, this new node is the cursor
        if(size == 0){
            cursor = a;
            size++;
            return;
        }
        //set current node to cursor
        Node curr = cursor;

        for(int i = 0; i < size; i++){ //parse through entire list
            if(a.getPid() > curr.getPid() ){ //if new pid is greater than current pid
                curr = curr.getNext(); //move to next node
            }
            else { //else the pid of A is less than our current pointer
                i = size + 1; //break loop
            }
        }
        a.setNext(curr);
        a.setPrev(curr.getPrev()); //set the pointers for node a
        a.getNext().setPrev(a);
        a.getPrev().setNext(a); //make the prev and next nodes point AT a

        if(a.getPid() < cursor.getPid()){
            cursor = a; //need to keep the cursor pointing at lowest value
        }
        size ++;
    }

    public void removeNode(Node a){
        if(size == 1){
            a.setNext(null);
            a.setPrev(null); //remove all pointers from a
            cursor = null; //removes last pointer to a, a is ready for garbage
            size = 0;
        }
        else{
            if(a == cursor){
                cursor = a.getNext(); //change where our cursor is pointing
            }
            a.getPrev().setNext(a.getNext()); //set a prev to a next
            a.getNext().setPrev(a.getPrev()); //set a next to a prev
            a.setNext(null);
            a.setPrev(null); //let a be collected by garbage
            size --;
        }
    }

    //prints the PID and time of each element of the list
    public void printList(){
        Node temp = cursor;
        for(int i = 0; i<size;i++){
            temp.print();
            temp = temp.getNext();
        }
    }

    //getter method for size of list
    public int getSize(){
        return size;
    }

    //getter method to get to the cursor of the list
    public Node getCursor(){
        return cursor;
    }
}