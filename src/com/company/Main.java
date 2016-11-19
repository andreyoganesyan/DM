package com.company;


import java.util.*;

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
            } else System.out.println("Invalid command");

            input = scanner.nextLine();
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

}
