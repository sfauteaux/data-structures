/*
Sean Fauteaux
2/2/2021
CSCD300
Prog 3: Round Robin

This program reads in an unsorted list of PID's paired with a time requirement, sorts the input pairs
into a doubly-linked circular list, then cycles through the list subtracting the CPU time from the
PID time requirement. When a PID has no more time, it is removed from the list. Cycles until list is empty.
args[0] must be the data file, args[1] must be the CPU time. 
 */
import java.util.*;
import java.io.*;

public class RoundRobin {

    //Reads the elements of the .txt doc into the circular doubly linked list
    public static void main(String[] args) throws IOException{
        //Open data file, set cpuTime
        Scanner inFile = new Scanner(new FileInputStream(args[0]));
        int cpuTime = Integer.valueOf(args[1]);

        //create empty list
        CircularList list = new CircularList();

        //read the data file into the list
        while(inFile.hasNext()){
            String line = inFile.next(); //separates each line into just the integers
            String first = line.substring(0,line.indexOf(','));
            String second = line.substring(line.indexOf(',')+1);
            int pid = Integer.valueOf(first); //converts the substrings into integers
            int time = Integer.valueOf(second);
            Node a = new Node(pid, time); //creates node from integer values
            list.addNode(a); //adds node to list, goes to next line in file
        }

        //send list to the cycle
        cycle(list, cpuTime);

        //close scanner
        inFile.close();
    }


    //go through all nodes in list until list is empty
    //if node time <= cpuTime, print the node PID and remove node
    //else, node time = node time - cpuTime
    public static void cycle(CircularList list, int cpuTime){
        Node curr = list.getCursor();//starts at the front of the list

        while(list.getSize() != 0){
            if(curr.getTime() <= cpuTime){ //process will be finished after CPU service time
                System.out.print(curr.getPid()); //print process ID
                if(list.getSize() > 1){ //if this isn't the last node
                    System.out.print(","); //prints a comma after the ID if there are more ID's afterwards
                    curr= curr.getNext(); //goes to next node
                    list.removeNode(curr.getPrev()); //remove the node that just ran out of time
                }
                else{
                    list.removeNode(curr); //end of list; just remove node
                }
            }
            else{
                curr.setTime(curr.getTime() - cpuTime); //subtract the time that has elapsed
                curr = curr.getNext(); //go to next node
            }
        }
    }
}
