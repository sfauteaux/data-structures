/*
Sean Fauteaux
2/9/2021
CSCD 300
Prog 4: Postfix Expression Solver

This program reads a text file into a stack that is based on a singly-linked list.
When a math operand is encountered, the top two values of the stack are popped and the math
operand is used on the two values, then pushed back onto the stack. This process repeats
until the data file is empty. The final value is then popped from the stack and printed to console.
 */

import java.io.*;
import java.util.*;

public class Postfix{
    //open scanner, create stack, read file into stack and perform
    //the math operations as they are encountered
    public static void main(String[] args) throws IOException{
        //Open scanner, create stack, read file into stack
        Scanner inFile = new Scanner(new FileInputStream(args[0]));
        ListStack stack = new ListStack();

        while(inFile.hasNext()){
            String s = inFile.next();
            if(checkOp(s)){
                double k = (mathOp(s,stack.pop(),stack.pop())); //performs math operand on top 2 values
                stack.push(k); //then puts that value at the top of the stack
            }
            else{
                stack.push(Double.parseDouble(s)); //if not an operand, must be a number. Push number to stack
            }
        }

        double fin = stack.pop(); //by popping the last value, all nodes from the list are ready for garbage collection
        System.out.println(fin); //return final value

        inFile.close();
    }

    //finds which operand is being used, returns the result of the operation
    public static double mathOp(String s, double i, double j){
        switch(s){
            case "*":
                return j * i;
            case "/":
                return j / i;
            case "+":
                return j + i;
            default:
                return j - i;
        }
    }

    //checks if the given string is one of the four math operands we're using
    public static boolean checkOp(String s){
        switch (s) {
            case "*":
            case "+":
            case "/":
                return true;
            default:
                return s.equals("-");
        }
    }
}