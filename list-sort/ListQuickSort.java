/*
Sean Fauteaux
2/28/2021
CSCD 300
Prog 6: Quick Sort on Doubly Linked List

I could not get the partition function to work properly with the linked list. With just swapping the
values of the nodes, I could get it to work, but not with swapping the physical nodes.
 */

import java.util.*;
import java.io.*;

public class ListQuickSort {
    public static void main(String[] args) throws IOException {
        DList list = new DList();
        Scanner inFile = new Scanner(new FileInputStream(args[0]));
        while (inFile.hasNext()) {
            String t = inFile.next();
            int j = Integer.parseInt(t);
            Node n = new Node(j);
            list.addNode(n);
        }

        inFile.close();

        QuickSort(list.getFirst(), list.getLast(), list);
        list.printList();
    }

    //Recursive function that keeps partitioning the list until the list has been sorted
    public static void QuickSort(Node l, Node h, DList list) {
        //Recursion stops when low == high, low == high.getNext, or high == null
        if (l != h && h != null && l != h.getNext()) { //Recursive check. When this fails the recursion stops
            Node l1 = l.getNext();
            Node h1 = h.getNext();
            Node piv = partition(l, h, list);
            QuickSort(l1.getPrev(), piv.getPrev(), list); //lower bound up to pivot -1
            QuickSort(piv.getNext(), h1.getPrev(), list); //pivot + 1 up to the upper bound
        }
    }

    //Partition takes the pivot value and finds where it belongs in the array,
    //then swaps the two physical nodes using the swap function from DList.java
    public static Node partition(Node l, Node h, DList list) {
        Node piv = h;
        Node index = l;
        for (Node i = l; i != h; i = i.getNext()) {
            if (i.getVal() <= piv.getVal()) {
                if (i == null) {
                    i = l;
                }
                if (index != i) {
                    list.swapNodes(index, i);
                }
                index = i.getNext();
            }
        }
        Node temp = index.getNext();
        list.swapNodes(index, piv);

        return temp.getPrev();
    }
}