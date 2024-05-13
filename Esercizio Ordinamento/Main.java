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
            int minOddValue = sort[startIterator];
            int maxEvenValue = sort[startIterator];
            int minOddValuePosition = startIterator;
            int maxEvenValuePosition = startIterator;
            //Search the values in the array
            for(int sortElement = startIterator; sortElement <= stopIterator; sortElement++)
            {
                //If current element is even and the biggest even element so far, save it
                if(sort[sortElement] % 2 == 0 && sort[sortElement] > maxEvenValue)
                {
                    maxEvenValue = sort[sortElement];
                    //Also save the position
                    maxEvenValuePosition = sortElement;
                }
                //If the current element is odd and the lowest odd element so far, save it
                if(sort[sortElement] % 2 != 0 && sort[sortElement] < minOddValue)
                {
                    minOddValue = sort[sortElement];
                    //Also save the position
                    minOddValuePosition = sortElement;
                }
            }

            //Move the elements at start and stop of array
            int swapTemp;
            //Swap the minimum odd value at the start
            swapTemp = sort[minOddValuePosition];
            sort[minOddValuePosition] = sort[startIterator];
            sort[startIterator] = swapTemp;
            //Swap the maximum even value at the end
            swapTemp = sort[maxEvenValuePosition];
            sort[maxEvenValuePosition] = sort[stopIterator];
            sort[stopIterator] = swapTemp;

            //Increment the start and decrement the stop
            startIterator++;
            stopIterator--;
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

