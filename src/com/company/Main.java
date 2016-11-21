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
                    System.out.println("Performed Binary Insertion sort. " + BinaryInsertionSort(array).toString());
                    isSorted = true;
                    System.out.println("The array after sorting:");
                    System.out.println(Arrays.toString(array));
                } else System.out.println("The array is null");
            }
            else if (input.equalsIgnoreCase("shellsort")) {
                if (array != null) {
                    System.out.println("Performed Shell sort. " + ShellSort(array).toString());
                    isSorted = true;
                    System.out.println("The array after sorting:");
                    System.out.println(Arrays.toString(array));
                } else System.out.println("The array is null");
            }
            else if (input.equalsIgnoreCase("quicksort")) {
                if (array != null) {
                    System.out.println("Performed Quick sort. " + QuickSort(array).toString());
                    isSorted = true;
                    System.out.println("The array after sorting:");
                    System.out.println(Arrays.toString(array));
                } else System.out.println("The array is null");
            }
            else if(input.matches("^(?i:getstat)\\s\\d+\\s\\d+(?i:((\\sinsertionsort)|(\\sbinaryinsertionsort)|(\\sshellsort)|(\\squicksort)))+$")){
                String[] inputArray = input.split(" ");
                PrintStats(Integer.parseInt(inputArray[1]),Integer.parseInt(inputArray[2]), Arrays.copyOfRange(inputArray, 3, inputArray.length));
            }
            else System.out.println("Invalid command");

            input = scanner.nextLine();
        }
    }

    static void PrintStats(int arraySize, int numOfIterations, String[] listOfSorts){
        int[] tempArray;
        SortStat tempSortStat = null;
        for(String sort:listOfSorts){
            int worstNumOfSwaps=-1, bestNumOfSwaps=-1, worstNumOfComparisons=-1,
                        bestNumOfComparisons=-1;
            double avgNumOfSwaps, avgNumOfComparisons;
            int[] numsOfSwaps = new int[numOfIterations];
            int[] numsOfComparisons = new int[numOfIterations];
            String name ="";
            for (int i =0; i< numOfIterations; i++){
                tempArray = GetRandomArray(arraySize);
                switch (sort.toLowerCase()) {
                    case "insertionsort" :{
                        tempSortStat = InsertionSort(tempArray);
                        name = "INSERTION SORT";
                        break;
                    }
                    case "binaryinsertionsort" :{
                        tempSortStat = BinaryInsertionSort(tempArray);
                        name = "BINARY INSERTION SORT";
                        break;
                    }
                    case "shellsort" :{
                        tempSortStat = ShellSort(tempArray);
                        name = "SHELL SORT";
                        break;
                    }
                    case "quicksort" :{
                        tempSortStat = QuickSort(tempArray);
                        name = "QUICK SORT";
                        break;
                    }
                }
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
            avgNumOfComparisons= (double)IntStream.of(numsOfComparisons).sum()/numOfIterations;
            avgNumOfSwaps = (double)IntStream.of(numsOfSwaps).sum()/numOfIterations;
            System.out.println(name);
            System.out.println("Number of iterations: " + numOfIterations + ". Array size: " + arraySize);
            System.out.println("NUMBER OF COMPARISONS: best: " + bestNumOfComparisons +" worst: " + worstNumOfComparisons + " average: "+ avgNumOfComparisons);
            System.out.println("NUMBER OF SWAPS: best: " + bestNumOfSwaps +" worst: " + worstNumOfSwaps + " average: "+ avgNumOfSwaps);
            System.out.println();
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
                array[j + 1] = array[j];
                numberOfSwaps++;
            }

            array[left] = temp;
            //numberOfSwaps++; — not sure if this counts
        }
        return new SortStat(numberOfComparisons, numberOfSwaps);
    }
    static SortStat ShellSort(int[] array){

        ArrayList<Integer> gaps = new ArrayList<Integer>();
        int length = array.length;
        int n = 2;
        while (length/n!=1){
            gaps.add(length/n);
            n*=2;
        }
        int numOfComparisons = 0, numOfSwaps =0;

        for(int gap:gaps){
            for(int i = gap; i<length; i++){
                int temp = array[i];
                int j = i;
                numOfComparisons++;
                for(; j>=gap&&array[j-gap]>temp; j-=gap){
                    numOfComparisons++;
                    numOfSwaps++;
                    array[j] = array[j-gap];
                }
                array[j]=temp;
            }
        }
        return new SortStat(numOfComparisons, numOfSwaps);



    }

    static SortStat QuickSort(int[]array){
        SortStat sortStat = new SortStat(0,0);
        QuickSortRec(array,0, array.length-1,sortStat);
        return sortStat;
    }
    static void QuickSortRec(int[] array, int low, int high, SortStat sortStat){
        if (low < high) {
            int p = QuickSortPart(array, low, high, sortStat);
            QuickSortRec(array, low, p-1, sortStat);
            QuickSortRec(array, p + 1, high, sortStat);
        }
    }
    static int QuickSortPart(int[] array, int low, int high, SortStat sortStat){
        int pivot = array[high];
        int i = low;

        for (int  j = low; j <= high - 1; j++) {
            sortStat.numberOfComparisons++;
            if (array[j] <= pivot) {
                sortStat.numberOfSwaps++;
                int temp = array[i];
                array[i]=array[j];
                array[j]=temp;
                i++;
            }
        }
        if(array[i]!=array[high]){sortStat.numberOfSwaps++;}
        int temp = array[i];
        array[i]=array[high];
        array[high]=temp;
        return i;
    }




    void TopDownMergeSort(int[] A, int[] B, int n)
    {
        B = Arrays.copyOf(A, n);          // duplicate array A[] into B[]
        TopDownSplitMerge(B, 0, n, A);   // sort data from B[] into A[]
    }
    void TopDownSplitMerge(int [] B, int iBegin, int iEnd, int[] A)
    {
        if(iEnd - iBegin < 2)
            return;
        int iMiddle = (iEnd + iBegin) / 2;
        TopDownSplitMerge(A, iBegin,  iMiddle, B);
        TopDownSplitMerge(A, iMiddle,    iEnd, B);  // sort the right run
        // merge the resulting runs from array B[] into A[]
        TopDownMerge(B, iBegin, iMiddle, iEnd, A);
    }

    //  Left source half is A[ iBegin:iMiddle-1].
// Right source half is A[iMiddle:iEnd-1   ].
// Result is            B[ iBegin:iEnd-1   ].
    void TopDownMerge(int[] A, int iBegin, int iMiddle, int iEnd, int[]B)
    {
        int i = iBegin, j = iMiddle;

        // While there are elements in the left or right runs...
        for (int k = iBegin; k < iEnd; k++) {
            // If left run head exists and is <= existing right run head.
            if (i < iMiddle && (j >= iEnd || A[i] <= A[j])) {
                B[k] = A[i];
                i = i + 1;
            } else {
                B[k] = A[j];
                j = j + 1;
            }
        }
    }


    static void mergeSortRec(int[] array, SortStat sortStat)
    {
        if (array.length <= 1) {
            return;
        }

        int[] first = new int[array.length / 2];
        int[] second = new int[array.length - first.length];
        System.arraycopy(array, 0, first, 0, first.length);
        System.arraycopy(array, first.length, second, 0, second.length);

        mergeSortRec(first, sortStat);
        mergeSortRec(second, sortStat);

        merge(first, second, array, sortStat);
        return;
    }

    private static void merge(int[] first, int[] second, int[] result, SortStat sortStat)
    {
        int iFirst = 0, iSecond = 0, iMerged = 0;

        //Compare elements at iFirst and iSecond,
        //and move smaller element at iMerged
        while (iFirst < first.length && iSecond < second.length)
        {
            sortStat.numberOfComparisons++;
            if (first[iFirst]<second[iSecond])
            {

                result[iMerged] = first[iFirst];
                iFirst++;
            }
            else
            {
                result[iMerged] = second[iSecond];
                iSecond++;
            }
            iMerged++;
        }
        //copy remaining elements from both halves - each half will have already sorted elements
        System.arraycopy(first, iFirst, result, iMerged, first.length - iFirst);
        System.arraycopy(second, iSecond, result, iMerged, second.length - iSecond);
    }


}

