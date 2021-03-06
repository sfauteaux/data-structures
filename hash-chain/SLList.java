public class SLList{
    private Node head;
    private Node tail;

    private int size;

    //Constructor initializes empty list
    public SLList(){
        head = new Node();
        tail = new Node();
        size = 0;

        head.setNext(tail);
    }

    //Adds node to the end of the linked list
    public void add(Node n){
        if(size==0){
            head.setNext(n);
            n.setNext(tail);
            size = 1;
        }
        else{
            Node temp = head;
            for(int i = 0;i<size;i++){ //Loop runs until temp.getNext() == tail
                temp = temp.getNext();
            }
            temp.setNext(n);
            n.setNext(tail);
            size++;
        }
    }

    /*
    Searches the list to see if provided iD exists within the list.
    If node exists, return PREVIOUS node. Else, return null.
    Because this is a SINGLY linked list, this returns the previous node so that
    the remove function can easily remove a node that we are searching for.
    */
    public Node search(int id){
        Node temp = head;
        for(int i = 0;i<size;i++){ //traverse list
            if(temp.getNext().getId() == id){ //if next node id == id we're searching for
                return temp; //Returns the node BEFORE the node we're searching for
            }
            else{ //If not found, go to next node
                temp = temp.getNext();
            }
        }
        return null; //only returns null if the loop never returned another node
    }

    //Calls list.search for the node to be removed, then removes it if found.
    //If node not found, do nothing.
    public void remove(int id){
        Node n = search(id);
        if(n==null){ //if search didn't fund student
            System.out.println("No such a student.\n");
            return; //do nothing
        }

        Node rem = n.getNext(); //rem node == node we are removing
        n.setNext(n.getNext().getNext()); //skip over temp node
        size--;
        rem.setNext(null);//prepare rem for garbage collection
        System.out.println("The student has been deleted successfully.\n");
    }

    //Prints all ID's in the list
    public void printList(){
        Node temp = head;
        for(int i = 0;i<size;i++){
            temp=temp.getNext();
            System.out.printf("(%d,%s) ",temp.getId(),temp.getVal());
        }
        System.out.println("");
    }
}