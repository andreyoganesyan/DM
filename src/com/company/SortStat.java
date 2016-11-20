package com.company;

/**
 * Created by andre_000 on 20-Nov-16.
 */
public class SortStat {
    public int numberOfComparisons;
    public int numberOfSwaps;
    public SortStat(int numberOfComparisons, int numberOfSwaps){
        this.numberOfComparisons=numberOfComparisons;
        this.numberOfSwaps=numberOfSwaps;
    }
    public String toString(){
        return "Number of comparisons: " + numberOfComparisons +". Number of swaps: " + this.numberOfSwaps +".";
    }
}
