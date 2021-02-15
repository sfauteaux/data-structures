/*
Stack based on singly linked list. Keeps reference to top node in the stack
and stores the size of the list.
 */
public class ListStack{
    protected int size;
    protected Node top;

    //constructor for List takes initial node
    public ListStack(){
        top = null;
        size = 0;
    }

    //creates new node given value of double, adds to stack
    public void push(double i){
        Node n = new Node(i, top);
        top = n;
        size++;
    }

    //returns top node value while removing top node from stack
    public double pop(){
        if(size == 0){
            System.out.println("Error");
            return -1;
        }
        double i = top.getVal();

        //getting current top node ready for garbage collection
        Node n = top;
        top = n.getPrev();
        n.setPrev(null);

        size--;
        return i;
    }

    //used in testing
    public double peek(){
        return top.getVal();
    }

    public int getSize(){
        return size;
    }

}