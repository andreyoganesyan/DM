package com.company;


import java.util.*;
import java.util.stream.IntStream;

public class Main {


    public static void main(String[] args) {

        int[] array = null;
        boolean isSorted = false;
        Scanner scanner = new Scanner( System.in );
        String input = scanner.nextLine();
        while(!input.equalsIgnoreCase("exit")){
            if (input.matches("^(?i:random)\\s\\d+$")){
                array = GetRandomArray(Integer.parseInt(input.split("\\s")[1]));
                isSorted = false;
            }
            else if (input.matches("^(?i:set)(\\s-?\\d+)+\\s?$")){
                array = ParseSet(input);
                isSorted = false;
            }
            else if (input.equalsIgnoreCase("print")){
                if (array!=null) {
                    System.out.println(Arrays.toString(array));
                }
                else System.out.println("The array is null");
            }
            else if (input.matches("^(?i:linsearch)\\s\\d+$")){
                if (array!=null) {
                    System.out.println(LinearSearch(array, Integer.parseInt(input.split("\\s")[1])));
                }
                else System.out.println("The array is null");
            }
            else if (input.matches("^(?i:binsearch)\\s\\d+$")){
                if (array!=null) {
                    if (!isSorted){
                        System.out.println("The array has been sorted:");
                        Arrays.sort(array);
                        isSorted = true;
                        System.out.println(Arrays.toString(array));
                    }
                    System.out.println(BinarySearch(array, Integer.parseInt(input.split("\\s")[1])));
                }
                else System.out.println("The array is null");
            }
            else if (input.equalsIgnoreCase("sort")){
                if (array!=null){
                    Arrays.sort(array);
                    isSorted = true;
                    System.out.println("The array has been sorted:");
                    System.out.println(Arrays.toString(array));
                }
                else System.out.println("The array is null");
            } else if (input.equalsIgnoreCase("insertionsort")){
                if (array!=null){
                    System.out.println("Performed Insertion sort. "+ InsertionSort(array).toString());
                    isSorted = true;
                    System.out.println("The array after sorting:");
                    System.out.println(Arrays.toString(array));
                }
                else System.out.println("The array is null");
            } else if (input.equalsIgnoreCase("binaryinsertionsort")) {
                if (array != null) {
                    System.out.println("Performed Insertion sort. " + BinaryInsertionSort(array).toString());
                    isSorted = true;
                    System.out.println("The array after sorting:");
                    System.out.println(Arrays.toString(array));
                } else System.out.println("The array is null");
            } else if(input.matches("^(?i:getstat)\\s\\d+\\s\\d+(?i:((\\sinsertionsort)|(\\sbinaryinsertionsort)))+$")){
                String[] inputArray = input.split(" ");
                PrintStats(Integer.parseInt(inputArray[1]),Integer.parseInt(inputArray[2]), Arrays.copyOfRange(inputArray, 3, inputArray.length));
            }
            else System.out.println("Invalid command");

            input = scanner.nextLine();
        }
    }

    static void PrintStats(int arraySize, int numOfIterations, String[] listOfSorts){
        int[] tempArray;
        SortStat tempSortStat;
        for(String sort:listOfSorts){
            switch (sort.toLowerCase()) {
                case "insertionsort" :{
                    int worstNumOfSwaps=-1, bestNumOfSwaps=-1, worstNumOfComparisons=-1,
                            bestNumOfComparisons=-1;
                    double avgNumOfSwaps, avgNumOfComparisons;
                    int[] numsOfSwaps = new int[numOfIterations];
                    int[] numsOfComparisons = new int[numOfIterations];
                    for (int i =0; i< numOfIterations; i++){
                        tempArray = GetRandomArray(arraySize);
                        tempSortStat = InsertionSort(tempArray);
                        numsOfComparisons[i] = tempSortStat.numberOfComparisons;
                        numsOfSwaps[i] = tempSortStat.numberOfSwaps;
                        if(tempSortStat.numberOfComparisons>worstNumOfComparisons){
                            worstNumOfComparisons=tempSortStat.numberOfComparisons;
                        }
                        if(tempSortStat.numberOfComparisons<bestNumOfComparisons||bestNumOfComparisons==-1){
                            bestNumOfComparisons=tempSortStat.numberOfComparisons;
                        }
                        if(tempSortStat.numberOfSwaps>worstNumOfSwaps){
                            worstNumOfSwaps=tempSortStat.numberOfSwaps;
                        }
                        if(tempSortStat.numberOfSwaps<bestNumOfSwaps||bestNumOfSwaps==-1){
                            bestNumOfSwaps=tempSortStat.numberOfSwaps;
                        }
                        
                        
                    }
                    avgNumOfComparisons= IntStream.of(numsOfComparisons).sum()/numOfIterations;
                    avgNumOfSwaps = IntStream.of(numsOfSwaps).sum()/numOfIterations;

                    System.out.println("INSERTIONS SORT");
                    System.out.println("Number of iterations: " + numOfIterations + ". Array size: " + arraySize);
                    System.out.println("NUMBER OF COMPARISONS: best: " + bestNumOfComparisons +" worst: " + worstNumOfComparisons + " average: "+ avgNumOfComparisons);
                    System.out.println("NUMBER OF SWAPS: best: " + bestNumOfSwaps +" worst: " + worstNumOfSwaps + " average: "+ avgNumOfSwaps);
                    System.out.println();
                    break;
                }
                case "binaryinsertionsort" :{
                    int worstNumOfSwaps=-1, bestNumOfSwaps=-1, worstNumOfComparisons=-1,
                            bestNumOfComparisons=-1;
                    double avgNumOfSwaps, avgNumOfComparisons;
                    int[] numsOfSwaps = new int[numOfIterations];
                    int[] numsOfComparisons = new int[numOfIterations];
                    for (int i =0; i< numOfIterations; i++){
                        tempArray = GetRandomArray(arraySize);
                        tempSortStat = BinaryInsertionSort(tempArray);
                        numsOfComparisons[i] = tempSortStat.numberOfComparisons;
                        numsOfSwaps[i] = tempSortStat.numberOfSwaps;
                        if(tempSortStat.numberOfComparisons>worstNumOfComparisons){
                            worstNumOfComparisons=tempSortStat.numberOfComparisons;
                        }
                        if(tempSortStat.numberOfComparisons<bestNumOfComparisons||bestNumOfComparisons==-1){
                            bestNumOfComparisons=tempSortStat.numberOfComparisons;
                        }
                        if(tempSortStat.numberOfSwaps>worstNumOfSwaps){
                            worstNumOfSwaps=tempSortStat.numberOfSwaps;
                        }
                        if(tempSortStat.numberOfSwaps<bestNumOfSwaps||bestNumOfSwaps==-1){
                            bestNumOfSwaps=tempSortStat.numberOfSwaps;
                        }


                    }
                    avgNumOfComparisons= IntStream.of(numsOfComparisons).sum()/numOfIterations;
                    avgNumOfSwaps = IntStream.of(numsOfSwaps).sum()/numOfIterations;

                    System.out.println("BINARY INSERTIONS SORT");
                    System.out.println("Number of iterations: " + numOfIterations + ". Array size: " + arraySize);
                    System.out.println("NUMBER OF COMPARISONS: best: " + bestNumOfComparisons +" worst: " + worstNumOfComparisons + " average: "+ avgNumOfComparisons);
                    System.out.println("NUMBER OF SWAPS: best: " + bestNumOfSwaps +" worst: " + worstNumOfSwaps + " average: "+ avgNumOfSwaps);
                    System.out.println();
                    break;
                }
            }
        }
    }

    static int[] GetRandomArray(int length){
        int[] array = new int[length];
        Random rand = new Random();
        for (int i = 0; i<length; i++){
            array[i] = rand.nextInt(100);
        }
        return array;
    }
    static int[] ParseSet(String input){
        String[] inputSplit = input.split(" ");
        int[] array = new int[inputSplit.length-1];
        for (int i = 1; i<inputSplit.length; i++){
            array[i-1] = Integer.parseInt(inputSplit[i]);
        }
        return  array;
    }
    static String LinearSearch(int[] array, int target){
        String result ="";
        int counter = 0;
        for (int i =0;i<array.length; i++){
            counter++;
            if (array[i] == target){
                result+="Pos: "+i + ". ";
                break;
            }

        }
        if(result.isEmpty()){
            result+="Not found. ";
        }
        result+="Number of comparisons: " + counter + ".";
        return  result;
    }

    static String BinarySearch(int[] sortedArray, int target){
        int leftBorder = 0;
        int rightBorder = sortedArray.length-1;
        int middle;
        int i = -1;
        int counter = 0;
        String result = "";
        while (leftBorder<=rightBorder){
            middle = (leftBorder+rightBorder)/2;
            counter++;
            if (sortedArray[middle]<target){
                leftBorder=middle+1;
            }
            else {
                counter++;
                if (sortedArray[middle]>target){
                    rightBorder = middle - 1;
                } else {
                    i = middle;
                    break;
                }
            }

        }
        if (i==-1){
            result+="Not found. ";
        }
        else {
            result+="Pos: "+i+". ";
        }
        result+="Number of comparisons: " + counter +".";
        return result;
    }

    static SortStat InsertionSort(int[] array){
        int numberOfComparisions = 0;
        int numberOfSwaps = 0;
        for (int i = 1; i<array.length; i++){
            int j = i;
            numberOfComparisions++;
            while (j>0&&array[j-1]>array[j]){
                numberOfComparisions++;
                numberOfSwaps++;
                int temp = array[j];
                array[j] = array[j-1];
                array[j-1] = temp;
                j--;
            }

        }
        return new SortStat(numberOfComparisions, numberOfSwaps);

    }

    static SortStat BinaryInsertionSort(int[] array)
    {
        int numberOfComparisons = 0;
        int numberOfSwaps = 0;
        for (int i = 0; i < array.length; i++)
        {
            int temp = array[i]; int left = 0; int right = i - 1;
            while (left <= right)
            {
                int m = (left + right) / 2;
                if (temp < array[m])
                    right = m - 1;
                else left = m + 1;
                numberOfComparisons++;
            }

            for (int j = i - 1; j >= left; j--)
            {
                array[j + 1] = array[j]; // сдвиг элементов
                numberOfSwaps++;
            }

            array[left] = temp;
            numberOfSwaps++;
        }
        return new SortStat(numberOfComparisons, numberOfSwaps);
    }

}

