import java.util.Scanner;

public class Esercizio3 {
    public static void main(String[] args) {
        //Instantiate the Scanner
        Scanner keyboard = new Scanner(System.in);

        //Initialising an array which keeps track of where are the letters present in the string
        int[] letterPosition = new int[26]; //The size of the array is the amount of letters (26)
        //Declare the user input string
        String userInput;

        //Invalid values check
        do {
            System.out.print("Inserire una stringa :");
            //Ask user to input a string and save it
            userInput = keyboard.nextLine();

            //Check for repeating values
            if(valuesRepeating(userInput))
                System.out.println("Le lettere nella stringa inserita non si possono ripetere");
            //Check for special characters
            if(!lettersOnly(userInput))
                System.out.println("La stringa non deve contenere caratteri speciali");
        } while(valuesRepeating(userInput) || !lettersOnly(userInput));

        //Iterate trough all the elements of the input
        for(int characterIterator = 0; characterIterator < userInput.length(); characterIterator++) {
            //Retrieve the number of the current letter in the alphabet
            int currentLetter = userInput.toUpperCase().charAt(characterIterator) - 'A';
            //Save the position into that element in the array, the position has index 1
            letterPosition[currentLetter] = characterIterator + 1;
        }

        //Iterate trough the array of letters to know what letters appear in the string and print them
        System.out.println("Lettere che compaiono nella stringa : ");
        for(int currentLetter = 0; currentLetter < letterPosition.length; currentLetter++)
        {
            //Print the letter only if it's present in the string
            if(letterPosition[currentLetter] > 0)
                System.out.println(
                    "Lettera " +
                    (char)(currentLetter + 'A') +
                    " presente."
                );
        }

        System.out.println("\nPremere INVIO per continuare");
        keyboard.nextLine();

        //Reconstructing the string from the starting array, for now we treat this as a char array
        char[] userInputReconstructed = new char[userInput.length()];
        //Iterate trough all the letters of the alphabet and reconstruct the string
        for(int reconstructionIterator = 0; reconstructionIterator < letterPosition.length; reconstructionIterator++)
        {
            //Current letter position
            int currentLetterPosition = letterPosition[reconstructionIterator];
            //Add the element to the array only if it's actually present
            if(currentLetterPosition > 0)
                userInputReconstructed[currentLetterPosition - 1] = (char)(reconstructionIterator + 'A');
        }

        //Cast the array to a string type
        String reconstructedStringCast = String.valueOf(userInputReconstructed);
        //Print the reconstructed string
        System.out.println("Stringa ricostruita : " + reconstructedStringCast);
    }

    /*Given a String, it returns wether there are letters repeating or not */
    private static boolean valuesRepeating(String input) {
        //Method output
        boolean repeatingValueFound = false;

        //Iterate trough string starting from second element
        //The for loop should terminate when either the iterator reached end of string or a repeating value was found
        for(int i = 1; i < input.length() && !repeatingValueFound; i++) {
            if(input.charAt(i) == input.charAt(i - 1))
                repeatingValueFound = true;
        }

        return repeatingValueFound;
    }

    /*Given a String, it returns wether it only contains letters and no special characters */
    private static boolean lettersOnly(String input) {
        //Method output
        boolean containsOnlyLetters = true;

        //Iterate trough all the characters of the string
        for(int i = 0; i < input.length() && containsOnlyLetters; i++) {
            //If the input string contains a character outside the A-Z range, then it's a special character and the loop should close returning from the function
            if(
                input.toUpperCase().charAt(i) < 'A' || input.toUpperCase().charAt(i) > 'Z'
            ) containsOnlyLetters = false;
        }

        return containsOnlyLetters;
    }
}