/*
Sean Fauteaux
1/12/2021
CSCD 300
Prog1: Binary Search Range

This program opens a data file into an array and searches
for the range of values within the array that fall between
the two given target values. 

*/
import java.util.*;
import java.io.*;

public class BinarySearchRange {

    //Gets data file and two target numbers, checks edge cases, then calls
    //the two search methods to find the INDICES of the target values
    public static void main(String[] args) throws IOException{

        Scanner inFile = new Scanner(new FileInputStream(args[0]));
        int lowerTarget = Integer.valueOf(args[1]);
        int upperTarget = Integer.valueOf(args[2]);

        ArrayList<Integer> input = new ArrayList<Integer>();
        
        while(inFile.hasNextInt()){ //reads given data into an ArrayList
            input.add(inFile.nextInt());
        } 
        int j = input.size();
        int A[] = new int[j];

        for(int i = 0;i<j;i++){ //reads ArrayList into an Array
            A[i] = input.get(i);
        }

        if(lowerTarget>upperTarget || upperTarget<A[0] || lowerTarget > A[j-1]){ //edge cases
            System.out.println("null");
        }
        else if(lowerTarget <= A[0] && upperTarget >= A[j-1]){ //easiest case
            System.out.printf("A[%d..%d]", 0, j-1);
        }
        else{
            int lb = lowerBound(0,j-1,lowerTarget,A);
            int ub = upperBound(0,j-1,upperTarget,A);
            if(lb>ub){ //this occurs when there are no values between the two target numbers
                System.out.println("null");
            }
            else{
                System.out.printf("A[%d..%d]", lb, ub);
            }
        }
        inFile.close();
    }
    
    //Binary Search (Recursion Based) that finds the furthest left target value
    //and returns the INDEX of where that target value is at
    public static int lowerBound(int lower, int upper, int target, int A[]){
        int curr = (upper - lower) /2 +lower;
        if(curr == 0){ //if we've made it down to the very left bound of the array
            return 0; //this is the lower bound
        }
        if(A[curr] == target){
            if(A[curr-1] != A[curr]){
                return curr;
            }
            else{ //set curr as new upper bound
                return lowerBound(lower, curr, target, A);
            }
        }
        else if(A[curr] > target){ //if number is bigger than target
            if(A[curr-1] < target){ //but the number to the left is less than target
                return curr; //then we've found our lower value
            } 
            else{ //else we have new upper bound 
                return lowerBound(lower, curr, target, A);
            }
        }
        else{ //if A[curr] < target, set lower bound = current location
            return lowerBound(curr, upper, target, A);
        } 

    }

    //Same as lower bound, but finds the furthest right value of the second target
    //and returns the INDEX of where that target value is at
    public static int upperBound(int lower, int upper, int target, int A[]){
        int curr = (upper-lower)/2 + lower;
        if(curr == (A.length-1)){ //if we've made it to the furthest right possible
            return curr;
        }
        if(A[curr] == target){ //if we've found number
            if(A[curr+1] != target){ //and the number to the right is different
                return curr; 
            }
            else{
                return upperBound(curr, upper, target, A);
            }
        }
        else if(A[curr] < target){ //if lower than target
            if(A[curr+1] > target){ //but next value is greater than target
                return curr; //we've found number
            }
            else{ //else we have new lower
                return upperBound(curr, upper, target, A);
            }
        }
        else{ //if A[curr] > target, new upper bound
            return upperBound(lower, curr, target, A);
        }
    }
}
