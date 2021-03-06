import java.util.*;
import java.io.*;
/*
Sean Fauteaux
3/7/2021
CSCD 300
Program 7: Hash Table with Chains using Singly Linked Lists

This program uses a hash table with only 5 buckets to store data. Because the table is so small,
collisions are guaranteed to happen with data sets greater than 5. These collisions are handled with
singly linked lists (SLL).

Each bucket of the hash table stores a reference to an SSL, and each node of the list contains
a 4 digit student ID (int) and the student name (String).

Hash table functionality includes insert, update, search, delete
 */
public class HashChain {
    public static void main(String[] args) throws IOException {
        //Open file scanner, use scanner to read data into the hashChain
        Scanner inFile = new Scanner(new FileInputStream(args[0]));
        SLList[] hashChain = readData(inFile);

        //Keyboard scanner for inputting choices
        Scanner input = new Scanner(System.in);

        boolean running = true;
        while (running) {
            optionsMenu(); //Print options menu
            int choice = input.nextInt();
            switch (choice) {

                //Case 1: Insert/Update student information
                case (1):
                    //Read in the student id and name
                    System.out.println("Input the student id: ");
                    int id = input.nextInt();
                    System.out.println("Input the student name: ");
                    String val = input.next();
                    int hashId = hash(id);

                    //Search for name in Hash Chain. If exists, update.
                    //else if doesn't exist, insert as new node
                    Node n = hashChain[hashId].search(id);
                    if (n != null) {
                        n.getNext().setVal(val);
                        System.out.println("The student was existing and the record has been updated.");
                    } else {
                        n = new Node(id, val);
                        hashChain[hashId].add(n);
                        System.out.println("The new student has been added successfully.");
                    }
                    break;

                //Case 2: Delete student record
                case (2):
                    System.out.println("Input the student id: ");
                    int del = input.nextInt();
                    hashChain[hash(del)].remove(del);
                    //The linked list remove function calls the search function for the given ID.
                    //If id not found, print "No such a student"
                    //Else if found, remove
                    break;

                //Case 3: Search for a student
                case (3):
                    System.out.println("Input the student id: ");
                    int target = input.nextInt();
                    Node d = hashChain[hash(target)].search(target);
                    //Linked list search function return null node if not found
                    if (d == null) {
                        System.out.println("No such a student.");
                    } else { //Returns the previous node if it is found
                        d = d.getNext();
                        System.out.printf("Student id:%d. Student Name:%s. ", d.getId(), d.getVal());
                    }
                    break;

                //Case 4: Print all student records
                case (4):
                    printChain(hashChain);
                    break;

                //Case 5: quit program
                case (5):
                    running = false;
                    break;
            }
            System.out.println(); //for clear output formatting
        }

        input.close();
        inFile.close();

    }

    //Reads the text file into separate int, string values
    //Creates Node with those values, hashes id value and adds to hashChain[]
    public static SLList[] readData(Scanner inFile) {
        SLList[] hashChain = new SLList[5];
        for (int i = 0; i < 5; i++) {
            hashChain[i] = new SLList();
        }
        while (inFile.hasNext()) {
            String val = inFile.nextLine();
            int id = Integer.parseInt(val.substring(0, val.indexOf(',')));
            val = val.substring(val.indexOf(',') + 1);
            Node n = new Node(id, val);
            hashChain[hash(id)].add(n);
        }
        return hashChain;
    }


    //hashing method for re-usability
    public static int hash(int i) {
        return (7 * i + 29) % 5;
    }

    //Prints all elements in the Hash Chain
    public static void printChain(SLList[] chain) {
        for (SLList list : chain) {
            list.printList();
        }
    }

    //Prints options based on assignment specification
    public static void optionsMenu() {
        System.out.println("Choose one of the following options.");
        System.out.println("====================================");
        System.out.println("1) insert/update a new student record");
        System.out.println("2) delete a student record");
        System.out.println("3) search for a student record");
        System.out.println("4) print all the student records");
        System.out.println("5) quit");
        System.out.println("");
        System.out.println("Your choice: ");
    }
}
