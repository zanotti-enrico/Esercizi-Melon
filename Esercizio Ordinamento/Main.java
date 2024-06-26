import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Instantiating user input scanner
        Scanner userInput = new Scanner(System.in);

        //Get the array to sort from the user input
        int[] array = readArray(userInput);

        //Sort the array
        specialSort(array);

        //Print the array
        printArray("Sorted Array:", array);

        //Remove the duplicates from the array
        array = removeDuplicates(array);

        //Now, print the array once more
        printArray("Sorted array with duplicates removes:", array);
    }

    /*Given an array, it removes duplicates.
     * The method returns the new array without the duplicates.
     */
    private static int[] removeDuplicates(int[] input) {
        //Create a copy of the initial array
        int[] inputCopy = new int[input.length];
        //Copy the data from the input
        for(int copy = 0; copy < input.length; copy++)
            inputCopy[copy] = input[copy];
        
        //Iterate trough the first compare
        for(int firstSearch = 0; firstSearch < inputCopy.length; firstSearch++) {
            //Iterate trough the second compare
            for(int secondSearch = firstSearch + 1; secondSearch < inputCopy.length; secondSearch++)
            {
                //If the array gets shrinked, the for loop must repeat without incrementing
                boolean shrinked;
                do {
                    //Reset the shrinked variable
                    shrinked = false;
                    //If a duplicate is found, remove it and shrink the array
                    if(inputCopy[firstSearch] == inputCopy[secondSearch]) {
                        //Remove the element contained in secondSearch
                        //Move all the elements back of one
                        for(int remove = secondSearch; remove < inputCopy.length - 1; remove++)
                            inputCopy[remove] = inputCopy[remove + 1];
                        //Create a new array with decremented size
                        int[] shrink = new int[inputCopy.length - 1];
                        //Copyd data in new array
                        for(int copy = 0; copy < shrink.length; copy++)
                            shrink[copy] = inputCopy[copy];
                        //Move the shrinked array into the input
                        inputCopy = shrink;
                        //The array got shrinked, so the for loop must reiterate without incrementing
                        shrinked = true;
                    }
                } while(shrinked);
            }
        }

        //Return the new length of the input array
        return inputCopy;
    }
    
    /* Passed the array to sort, it sorts the even numbers and
     * the odd numbers one after the other using a modified version of
     * the selection sort.
     * The method directly sorts the passed array
    */
    private static void specialSort(int[] sort) {
        //Iterator to keep track where the sort was completed
        int startIterator = 0;
        int stopIterator = sort.length - 1;

        //Cycle until sorted
        do {
            //Contains the minimum odd value
            int minOddValue = 0;
            int maxEvenValue = 0;
            int minOddValuePosition = 0;
            int maxEvenValuePosition = 0;
            //Contains wether odd or even values were found
            boolean minOddValueFound = false;
            boolean maxEvenValueFound = false;

            //Search the values in the array, stop when both values are found
            for(int sortElement = startIterator; sortElement <= stopIterator; sortElement++)
            {
                //If current element is even and the biggest even element so far or the first even element, save it
                if(
                    maxEvenValueFound && sort[sortElement] % 2 == 0 && sort[sortElement] > maxEvenValue
                    ||
                    !maxEvenValueFound && sort[sortElement] % 2 == 0
                )
                {
                    maxEvenValue = sort[sortElement];
                    //Also save the position
                    maxEvenValuePosition = sortElement;
                    //We found a maximum even value
                    maxEvenValueFound = true;
                }
                //If the current element is odd and the lowest odd element so far or the first odd element, save it
                else if(
                    minOddValueFound && sort[sortElement] % 2 != 0 && sort[sortElement] < minOddValue
                    ||
                    !minOddValueFound && sort[sortElement] % 2 != 0
                )
                {
                    minOddValue = sort[sortElement];
                    //Also save the position
                    minOddValuePosition = sortElement;
                    //We found a minimum odd value
                    minOddValueFound = true;
                }
            }

            //Move the elements at start and stop of array
            int swapTemp;
            //If a minimum odd value is found
            if(minOddValueFound)
            {
                if(minOddValuePosition != startIterator)
                {
                    //If the element we are going to swap is the max even value, save the max even value in the position we're swapping to
                    if(maxEvenValuePosition == startIterator)
                        maxEvenValuePosition = minOddValuePosition;

                    //Swap the minimum odd value at the start
                    swapTemp = sort[minOddValuePosition];
                    sort[minOddValuePosition] = sort[startIterator];
                    sort[startIterator] = swapTemp;
                }
                //Increment the start
                startIterator++;
            }
            //If a maximum even value is found and we have to replace it
            if(maxEvenValueFound)
            {
                if(maxEvenValuePosition != stopIterator)
                {
                    //Swap the maximum even value at the end
                    swapTemp = sort[maxEvenValuePosition];
                    sort[maxEvenValuePosition] = sort[stopIterator];
                    sort[stopIterator] = swapTemp;
                }
                //Decrement the stop
                stopIterator--;
            }
        //When start and stop iterators match or pass each other, the array is sorted as requested
        } while (startIterator < stopIterator);
    }

    /*Passed an array, it prints the initial string passed as parameter and 
     * it prints the array out to System.out
     */
    private static void printArray(String initialPrint, int[] input) {
        //Print the initial string
        System.out.println(initialPrint);
        //Iterate trough all elements and print them
        for(int element : input)
            System.out.println("\t" + element);
    }
    
    /* Passed the scanner, it fills an integer array with the values
     * from the user input and returns it.
     * Method prints in system output stream to ask the user input.
    */
    private static int[] readArray(Scanner keyboard) {
        //Buffer to save input data before returning
        int[] userInputBuffer = new int[16];
        int userInputIterator = 0; //Array iterator
        //Set to true when the input terminates
        boolean terminate = false;
        //Keep asking user input until user input stops
        do {
            //Ask the user a new input
            System.out.println("Inserire un valore non intero per terminare l'inserimento.");
            System.out.print("Inserire un nuovo valore :");
            try {
                //Get the user input and increment its iterator
                userInputBuffer[userInputIterator++] = keyboard.nextInt();
            } catch(Exception exception) {
                //Decrement the array size since the last element wasn't occupied
                userInputIterator--;
                terminate = true;
            }
            //If the iterator reached the end of the array double its length
            if(userInputIterator >= userInputBuffer.length) {
                //Create a new array with the length doubled
                int[] newArray = new int[userInputBuffer.length * 2];
                //Copy the data in the new array
                for(int copyIterator = 0; copyIterator < userInputBuffer.length; copyIterator++)
                    newArray[copyIterator] = userInputBuffer[copyIterator];
                //Set the buffer array as the new array
                userInputBuffer = newArray;
            } 
        } while(!terminate);
        
        //Empty the system output stream buffer after number input
        keyboard.nextLine();
        
        int[] newFilledArray = null;
        //Adjust the array length to make it full in case it's not
        if(userInputIterator < userInputBuffer.length) {
            //Create a new array to the length of the iterator
            newFilledArray = new int[userInputIterator];
            //Copy the data to the new array
            for(int copyIterator = 0; copyIterator < userInputIterator; copyIterator++)
                newFilledArray[copyIterator] = userInputBuffer[copyIterator];
            //Set the user input buffer as the filled array
            userInputBuffer = newFilledArray;
        }
        
        //Return the filled array
        return newFilledArray;
    }
}
