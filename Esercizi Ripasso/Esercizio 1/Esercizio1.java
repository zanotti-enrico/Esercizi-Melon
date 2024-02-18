import java.util.Scanner;

public class Esercizio1 {
    public static void main(String[] args) {
        //Instantiate the scanner
        Scanner keyboard = new Scanner(System.in);

        //Ask the user how big the array should be
        int userInputSize = 0;
        //Keep executing loop if user inputs an invalid number
        do {
            System.out.print("Quante stringhe si vogliono inserire? ");
            
            //Retrieve user input
            userInputSize = keyboard.nextInt();

            //Check for invalid value
            if(userInputSize <= 0)
                System.out.println("Errore : il numero inserito deve essere positivo");
        } while(userInputSize <= 0);

        //Last user input was a number so flush the input buffer
        keyboard.nextLine();

        //Initialise the array to the specified value
        String[] userInput = new String[userInputSize];

        //Cycle trough all the elements of the array asking the user to fill them
        for(int inputIterator = 0; inputIterator < userInputSize; inputIterator++)
        {
            System.out.println("Inserire la stringa " + (inputIterator + 1) + ":");
            userInput[inputIterator] = keyboard.nextLine();
        }

        //Reiterate trough all the strings in array checking which ones start with a capital letter
        System.out.println("Stampa delle stringhe che iniziano con la maiuscola :");
        for(String currentString : userInput) {
            //If the first character of the string is an ASCII capital letter then print the string
            if(currentString.charAt(0) >= 65 && currentString.charAt(0) <= 90)
                System.out.println("> " + currentString);
        }
    }
}