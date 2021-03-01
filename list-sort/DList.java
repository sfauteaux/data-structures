/*

 */

public class DList {
    protected Node head = new Node(null); //dummy nodes
    protected Node tail = new Node(null);

    protected int size;

    public DList() {
        size = 0;
    }

    //Adds nodes to the end of the list, adjusting size value and pointers at the end of the list
    public void addNode(Node n) {
        if (size == 0) {
            head.setNext(n);
            tail.setPrev(n);
            size = 1;
        } else {
            tail.getPrev().setNext(n);
            n.setPrev(tail.getPrev());
            n.setNext(tail);
            tail.setPrev(n);
            size++;
        }
    }

    //Swaps the nodes that contain the two values given
    public void swapNodes(Node a, Node b) {
        //If neighbors, a left of b
        if(a.getNext() == b){
            if(head.getNext() == a){ //and a is front of list
                head.setNext(b);
            }
            else{
                a.getPrev().setNext(b);
            }
            b.getNext().setPrev(a);
            a.setPrev(b);
            b.setNext(a);
        }
        //neighbors, b left of a
        else if(b.getNext() == a){
            if(head.getNext() == b){
                head.setNext(a);
            }
            else{
                b.getPrev().setNext(a);
            }
            a.getNext().setPrev(b);
            b.setPrev(a);
            a.setNext(b);
        }
        //Cases where a, b not neighbors
        else{
            //get prev, next nodes of a and b
            Node p1 = a.getPrev();
            Node n1 = a.getNext();
            Node p2 = b.getPrev();
            Node n2 = b.getNext();

            //set a and b's next, prev nodes to each others
            a.setNext(n2);
            a.setPrev(p2);
            b.setNext(n1);
            b.setPrev(p1);

            //Case: a is head, p1 is null
            if(head.getNext() == a){
                head.setNext(b);
                n1.setPrev(b);
                p2.setNext(a);
                n2.setPrev(a);
            }

            //Case: b is head, p2 is null
            else if(head.getNext() == b){
                head.setNext(a);
                p1.setNext(b);
                n1.setPrev(b);
                n2.setPrev(a);
            }

            else {
                //set the surrounding nodes to point at a and b
                p1.setNext(b);
                n1.setPrev(b);
                p2.setNext(a);
                n2.setPrev(a);
            }
        }
    }

    //Prints the list
    public void printList() {
        Node temp = head.getNext();
        for (int i = 0; i < size; i++) {
            System.out.print(temp.getVal() + " ");
            temp = temp.getNext();
        }
        System.out.println("");
    }

    //getters for first, last elements of the list that are NOT the dummy head/tail
    public Node getFirst(){
        return head.getNext();
    }

    public Node getLast(){
        return tail.getPrev();
    }

}