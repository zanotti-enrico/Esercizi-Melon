import java.util.Scanner;

public class PresenzaCaratteri {
    public static void main(String[] args) {
        //User input
        Scanner keyboard = new Scanner(System.in);

        //Boolean variable containing wether each letter got used
        boolean[] usedLetters = new boolean[26];
        //Contains the current letter of the string in the alphabet, going from 1 to 26
        int currentLetter;
        
        //Retrieve user input
        System.out.println("Inserire una stringa : ");
        String userInput = keyboard.nextLine().toUpperCase();

        //For each character in the string
        for(int userInputIterator = 0; userInputIterator < userInput.length(); userInputIterator++) {
            //Transform the char type to the number of a letter in the alphabet
            currentLetter = userInput.charAt(userInputIterator) - 64;

            if(currentLetter > 0 && currentLetter <= 26) //If the current character is a valid letter
            {
                //If it hasn't been already, set the usage of this letter in the string to true
                if(!usedLetters[currentLetter])
                    usedLetters[currentLetter] = true;
            }
        }

        //Now iterate trough the boolean values to show used letters
        for(int letterIterator = 0; letterIterator < usedLetters.length; letterIterator++)
        {
            if(usedLetters[letterIterator])
                System.out.println("Lettera " + (char)(letterIterator + 64) + " utilizzata.");
        }

        //Avoid memory leak
        keyboard.close();
    }
}