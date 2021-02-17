/*
ArrayQ class uses the concepts of an Array Queue to enqueue
10 elements at a time. Because we're using this as a FIFO queue,
we only need one add function and one remove function. Here, I went
with using the addFirst and removeLast functions, but addLast and removeFirst
would have worked as well.
 */

public class ArrayQ implements Queue{
    private int size;
    private int enq[];
    private int head, tail;

    public ArrayQ(){
        size = 0;
        enq = new int[10];
        head = -1;
        tail = -1;
    }

    //add function acts the same as addFirst
    public void add(int i) {
        if(size == 10){
            System.out.println("Error");
            return;
        }
        if(size == 0){ //first element of the queue
            enq[0] = i;
            head = 0;
            tail = 0;
        }
        else{ //move head by 1 element, insert value i
            head = (head + 9) % 10; //head - 1 + n, here n always = 10
            enq[head] = i;
        }
        size++;
    }

    //pop function acts the same as remove last
    public int pop() {
        if (size == 0) {
            System.out.println("Error");
            return -1;
        }
        int ret = enq[tail];
        if (size == 1) { //this is last element of array
            head = -1;
            tail = -1;
        } else { //move tail forward one element
            tail = (tail + 9) % 10; //tail - 1 + n, here n always = 10
        }
        size--;
        return ret;
    }

    //basic getters
    public int getSize() {
        return size;
    }

    public int getTail(){
        return enq[tail];
    }
}