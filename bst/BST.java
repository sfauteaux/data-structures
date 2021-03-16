/*
Sean Fauteaux
CSCD 300
Program 8: Implementing a Full Package of a Binary Search Tree.

This project has us make a binary search tree from scratch with full functionality,
including In-Order, Pre-Order, Post-Order, and Level-Order traversals from any given
subtree root within the tree. BST.java is the driver file for the tree, with
BSTree.java and Node.java providing the actual Tree/Tree Node objects and use methods.
 */

import java.util.*;
import java.io.*;

public class BST {
    public static void main(String[] args) throws IOException {


        Scanner inFile = new Scanner(new FileInputStream(args[0]));
        BSTree tree = new BSTree();
        readData(tree,inFile);


        Scanner input = new Scanner(System.in);

        boolean running = true;

        while (running) {
            printMenu();
            int choice = getChoice(input);
            int key = 0;

            //If choice is one of the input options that requires the user to input a given key
            if ((choice > 0 && choice < 4) || choice == 10 || choice == 11) {
                System.out.println("Input the key: ");
                key = Integer.parseInt(input.next());
            }

            //Used for cases 4-9
            Node root = tree.getRoot();

            Node n;

            switch (choice) {
                //case -1 == user input x, end program
                case (-1):
                    running = false;
                    break;
                //case 1 == search for key
                case (1):
                    n = tree.search(key);
                    if (n == null) {
                        System.out.println("The given key does not exist.");
                    } else {
                        System.out.println("The given key exist.");
                    }
                    break;
                //case 2 == insert key
                case (2):
                    n = tree.insert(key);
                    if (n != null) {
                        System.out.println("The given key has been inserted successfully.");
                    }
                    break;
                //case 3 == delete key
                case (3):
                    tree.delete(key);
                    break;
                //case 4 == In-Order traversal
                case (4):
                    tree.inOrder(root);
                    break;
                //case 5 == Pre-Order traversal
                case (5):
                    tree.preOrder(root);
                    break;
                //case 6 == Post-Order Traversal
                case (6):
                    tree.postOrder(root);
                    break;
                //case 7 == Level-Order traversal
                case (7):
                    tree.levelOrder(root);
                    break;
                //case 8 == Find smallest key
                case (8):
                    if (root != null) {
                        System.out.println("Smallest key value: "+ tree.min(root).getKey());
                    }
                    else {
                        System.out.println("The tree is empty");
                    }
                    break;
                    //case 9 == Find largest key
                case(9):
                    if (root != null) {
                        System.out.println("Largest key value: " + tree.max(root).getKey());
                    }
                    else {
                        System.out.println("The tree is empty");
                    }
                    break;
                    //case 10 == Find successor of key
                case(10):
                    n = tree.search(key);
                    if(n == null){
                        System.out.println("No such a key.");
                    }
                    else{
                        n = tree.successor(n);
                        if(n==null){
                            System.out.println("The given key exists but does not have a successor.");
                        }
                        else{
                            System.out.printf("Successor to key %d = %d\n",key,n.getKey());
                        }
                    }
                    break;
                    //case 11 = Find predecessor of key
                case(11):
                    n = tree.search(key);
                    if(n == null){
                        System.out.println("No such a key.");
                    }
                    else{
                        n = tree.predecessor(n);
                        if(n==null){
                            System.out.println("The given key exists but does not have a predecessor.");
                        }
                        else{
                            System.out.printf("Predecessor to key %d = %d\n",key,n.getKey());
                        }
                    }
                    break;
            }
        }

        //inFile.close();
        input.close();

    }

    public static void readData(BSTree tree, Scanner inFile) {
        while (inFile.hasNext()) {
            int n = Integer.parseInt(inFile.next()); //convert string to integer, store as n
            tree.insert(n);
        }
    }

    public static void printMenu() {
        System.out.println("""

                Choose one of the following options.
                ====================================
                1) Search for a key
                2) Insert a new key
                3) Delete an existing key
                4) Inorder traversal of the BST
                5) Preorder traversal of the BST
                6) Postorder traversal of the BST
                7) Level-order traversal of the BST
                8) Find the smallest key
                9) Find the largest key
                a) Find the successor of a given key
                b) Find the predecessor of a given key
                x) quit""");
    }

    //Gets input for what the user wants to do and returns integer value
    //First checks if input was a, b, or x, then defaults to returning
    //char.
    public static int getChoice(Scanner input) {
        char choice = input.next().charAt(0);
        switch (choice) {
            case ('x'):
            case ('X'):
                return -1;
            case ('a'):
            case ('A'):
                return 10;
            case ('b'):
            case ('B'):
                return 11;
            default:
                return choice - 48; //uses ascii value to convert char to int
        }
    }

}