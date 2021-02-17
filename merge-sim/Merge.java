/*
Sean Fauteaux
2/16/2021
CSCD 300
Prog 5: Merge

This program simulates the process of externally merging data files into one whenever the amount
of data is too large to be loaded directly onto the main memory of a system. Program can take a variable
amount of file inputs. The first 10 elements of each file are enqueued an Array-based Queue with
a set size of 10 in order to simulate the lack of space available on the system memory.

After the enqueue, the lowest value across all queues is loaded into the sorted List-based queue,
simulating loading the sorted data onto the system storage.
 */
import java.io.*;
import java.util.*;

public class Merge{
    public static void main(String[] args) throws IOException {

        int fSize = args.length;
        //Create scanner for each data file sent to
        Scanner[] inFiles = new Scanner[fSize];
        for(int i = 0; i < fSize; i++) {
            inFiles[i] = new Scanner(new FileInputStream(args[i]));
        }

        //create a FIFO array for each of the input files
        ArrayQ[] enq = new ArrayQ[fSize];

        int tSize = 0; //Keeps track of the size of all array queue's combined

        //Enqueue the first 10 elements into the Array queues
        for (int i = 0; i < fSize; i++) {
            enq[i] = new ArrayQ();
            //enArrayQ the first 10 elements
            while (inFiles[i].hasNext() && enq[i].getSize() < 10) {
                String t = inFiles[i].next(); //read line into string
                int j = Integer.parseInt(t); //convert to int
                enq[i].add(j); //add value to enq
            }
            tSize += enq[i].getSize();
        }
        ListQ sorted = new ListQ();

        //if element removed from an array queue and nothing is added in it's place from scanner
        //subtract 1 from tSize and nullify it's spot in the enq array

        //while Array Queues are not empty, go thru them all and find lowest values for each loop
        while (tSize != 0) {
            Integer lowest = null;
            int index = 0; //needed to be initialized for compiler
            for(int i = 0; i < fSize; i++){
                if(enq[i] != null){ //enq is not empty, still in set
                    if(lowest == null || enq[i].getTail() < lowest){ //enq[i] is lower than our current lowest
                        lowest = enq[i].getTail();
                        index = i;
                    }
                }
            }
            sorted.add(enq[index].pop());//dequeue lowest value into our sorted ListQ

            //Reload the array queue if possible. If not, close scanner
            if(inFiles[index] == null){
                tSize--;
            }
            else{
                if(inFiles[index].hasNext()){
                    String t = inFiles[index].next(); //read line into string
                    int j = Integer.parseInt(t); //convert to int
                    enq[index].add(j); //add value to enq
                }
                else{
                    tSize--;
                }
            }

            //Check for empty array, ready it for garbage collection if empty
            if(enq[index].getSize() == 0){
                enq[index] = null;
            }
        }

        //Close all scanners
        for(int i = 0;i<fSize;i++){
            inFiles[i].close();
        }

        //Print final List Queue
        while(sorted.getSize() != 0){
            System.out.println(sorted.pop());
        }

    }
}