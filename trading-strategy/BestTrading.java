/*
Sean Fauteaux
1/22/2021
CSCD 300
Prog2: Trading Strategy

This program opens a data file into an unsorted array where each value represents
the price of a stock, and the line number represents the day where the stock was that value.
The search then finds the best days to buy and sell the stock for maximum profit. 

Runtime complexity of O(n log(n))
*/
import java.util.*;
import java.io.*;

public class BestTrading {
    public static void main(String[] args) throws IOException{
        //Reads input file into ArrayList, then a regular Array
        Scanner inFile = new Scanner(new FileInputStream(args[0]));

        ArrayList<Integer> input = new ArrayList<Integer>();
        
        while(inFile.hasNextInt()){ //reads given data into an ArrayList
            input.add(inFile.nextInt());
        } 
        int size = input.size();

        int A[] = new int[size];

        for(int i = 0;i<size;i++){ //reads ArrayList into an Array
            A[i] = input.get(i);
        }
        
        int answer[] = bestProfit(A,0,size-1);
        System.out.printf("[%d,%d,%d]", answer[0],answer[1],answer[2]); //Final Output

        inFile.close(); //close scanner
    }

    //The main recursive function, keeps calling itself until the bottom of the binary tree is reached
    //then begins to return and check each array using "findProfit"
    public static int[] bestProfit(int p[], int low, int high) throws IndexOutOfBoundsException{
        int ret[] = {low,high,0}; //return set to this by default, only returns this when low==high
        
        //error check, should never happen but it is here just in case 
        if(low > high){
            throw new IndexOutOfBoundsException("Low index was greater than High index"); //should never happen
        }

        //this is the recursive check, once we get to the end of the "tree" we begin to return
        else if(low == high){
            return ret;
        }

        int mid = Math.floorDiv((low+high),2); //finds middle of array

        int leftAnswer[] = bestProfit(p,low,mid); //evaluates left side of array
        int rightAnswer[] = bestProfit(p,mid+1,high); //evaluates right side of array
        int midAnswer[] = findProfit(p,low,high); //evaluates best of both sides of the array

        //if the profit of the left side is greater than/equal to the other profits
        if(leftAnswer[2] >= rightAnswer[2] && leftAnswer[2] >= midAnswer[2]){
            return leftAnswer;
        }
        //else if right side is greater/equal to other profits
        else if(rightAnswer[2] >= leftAnswer[2] && rightAnswer[2] >= midAnswer[2]){
            return rightAnswer;
        }
        //else return lowest from left, biggest from right
        else{
            return midAnswer;
        }
    }

    //calculates the lowest value on the left and highest value on the right to find the best trade days
    public static int[] findProfit(int p[],int low,int high){
        int mid = (low+high)/2;
        int a = low; //looking for lowest value
        int b = high; //looking for highest value
        for(int i = low;i<=mid;i++){
            if(p[i] < p[a]){
                a = i;
            }
        }
        for(int i = high;i>=mid;i--){
            if(p[i]>p[b]){
                b = i;
            }
        }
        int[] ret = {a,b, p[b]-p[a]};
        return ret;
    }

}