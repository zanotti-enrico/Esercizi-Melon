import java.util.Scanner;

public class Utility {
    /*The method prints a menu on screen and asks for user input, it then returns the
     * option selected by the user.
     * The contents string passed contains as the first element the title of the menu.
     * The method prints in the system output stream.
     * The last parameter of the menu allows to specify a hidden entry of the menu,
     * to avoid specifying one, set a number that's outside of the contents string vector.
     */
    public static int menu(String[] contents, Scanner input, int hiddenEntry) {
        //Check if the passed string array is valid
        if(contents.length < 1) //If the passed array is empty
            return 0; //Return null to indicate error

        //Is true when the input is valid and the menu should close
        boolean inputIsValid;

        int currentUserInput = 0;

        //Keep executing the loop if the input is not valid
        do {
            //Reset the variable saving if the input is valid or not
            inputIsValid = true;

            clearScreen();

            //Print the title (first element of the contents array)
            System.out.println("=== " + contents[0] + " ===");

            //Print all menu elements
            for(int currentMenuElement = 1; currentMenuElement < contents.length; currentMenuElement++)
                //If the current entry of the menu was requested to be hidden, don't print it.
                if(currentMenuElement != hiddenEntry)
                    System.out.println(currentMenuElement + " : " + contents[currentMenuElement]);
            System.out.print("\n>");

            //Do an error check on user input
            try {
                //Retrieve user input
                currentUserInput = input.nextInt();
            } catch (Exception exception) {
                //If the input is not a number
                inputIsValid = false;
                System.out.println("Errore : il numero inserito deve essere un valore numerico");
            }
            //Clear the buffer
            input.nextLine();

            //Check user input, continue the check only if the value is valid
            if(inputIsValid && (currentUserInput < 0 || currentUserInput > contents.length - 1))
            { //If the input is out of range then print an error message and reexecute the menu
                inputIsValid = false;
                System.out.println("Errore : il numero inserito deve essere contenuto nel menu");

            }
        } while(!inputIsValid);
        
        return currentUserInput;
    }
    
    /*Given a string array of contents where the first element is the title,
     * it prints a menu which allows the user to select multiple things on a list.
     * The method returns a boolean array containing the selections
     * The scanner is passed to read user input.
     * The method adds at the end a final option to select, which if inputted by the user makes the menu stop the selection.
     * The user has to select an element before exiting from the menu.
     * The method prints in the system output stream.
     */
    public static boolean[] multipleSelectionMenuBool(String[] contents, Scanner input) {
        //Check if the passed value is valid
        if(contents.length < 1) //If the contents array is empty
            return null; //Return null from the function to indicate an error

        //Gets printed at the end as the option to select to exit from the menu
        final String exitOption = "Terminare Selezione";
        //This is the number the user has to type to exit from the menu, hence the number of the last option
        int exitOptionNumber = contents.length;

        /*This array contains wether every element in the menu contents is selected or not 
         * it will be returned by the function.
         * The size is the contents - 1 because the contents array contains also the title
        */
        boolean[] selections = new boolean[contents.length - 1];
        //Determines wether user selection ended
        boolean menuFinished = false;
        //User input
        int userInput;


        //Initialise the boolean array at false state
        for(int initialisationIterator = 0; initialisationIterator < selections.length; initialisationIterator++)
            selections[initialisationIterator] = false;

        //Keep executing until menu selection is finished
        do {
            clearScreen();

            //Print the title
            System.out.println("=== " + contents[0] + " ===");

            //Print all the menu elements
            for(int menuElement = 1; menuElement < contents.length; menuElement++) {
                System.out.print(menuElement + " : [");

                //Print an "X" if the selection is made
                if(selections[menuElement - 1])
                    System.out.print("X");
                else
                    System.out.print(" ");

                System.out.println("] " + contents[menuElement]);
            }
            
            //Print the option to select to exit from the menu which gets selected if the user input equals contents.length
            System.out.println(exitOptionNumber + " : " + exitOption);

            //Get user input
            System.out.print("\n>");
            //Error check on user input
            try {
                userInput = input.nextInt();
            } catch(Exception exception) { //In case the user didn't input a numeric value
                //Print an error
                System.out.println("Errore : il numero inserito deve essere in formato numerico\nPremere INVIO per continuare...");
                input.nextLine();
                input.nextLine();
                //Skip this loop
                continue;
            }
            //Clear the buffer after reading a number
            input.nextLine();

            //Check user input
            if(userInput > 0 && userInput < contents.length) { //IF the input is an option
                //Use the NOT operator to invert the value contained in the array at the position specified by the user
                selections[userInput - 1] = !selections[userInput - 1];
            } else if(userInput == exitOptionNumber) { //IF the user input is the exit option
                //Execute a check if any element got selected before exiting
                // for(boolean selectionElement : selections) //Iterate trough all the booleans in the array
                //     if(selectionElement) //If a boolean is true exit from the loop, otherwise ignore user input
                menuFinished = true; //Exit from the do-while loop to make the function return the selections
            } else { //If the user input is invalid
                //Print an error message
                System.out.println("Errore : il numero inserito deve essere compreso nella lista.\nPremere INVIO per continuare.");
                //Wait for ENTER to continue program execution
                input.nextLine();
            }
        } while(!menuFinished);

        //Return selection indexes
        return selections;
    }

    /*Given a string array of contents where the first element is the title,
     * it prints a menu which allows the user to select multiple things on a list.
     * The method returns an integer array containing the index of the selected elements in the contents string
     * The scanner is passed to read user input.
     * The method adds at the end a final option to select, which if inputted by the user makes the menu stop the selection.
     * The user has to select an element before exiting from the menu.
     * The method prints in the system output stream.
     */
    public static int[] multipleSelectionMenuInt(String[] contents, Scanner input) {
        //Check if the passed value is valid
        if(contents.length < 1) //If the contents array is empty
            return null; //Return null from the function to indicate an error

        //Gets printed at the end as the option to select to exit from the menu
        final String exitOption = "Terminare Selezione";
        //This is the number the user has to type to exit from the menu, hence the number of the last option
        int exitOptionNumber = contents.length;

        /*This array contains wether every element in the menu contents is selected or not 
         * it will be returned by the function.
         * The size is the contents - 1 because the contents array contains also the title
        */
        boolean[] selections = new boolean[contents.length - 1];
        //Determines wether user selection ended
        boolean menuFinished = false;
        //User input
        int userInput;


        //Initialise the boolean array at false state
        for(int initialisationIterator = 0; initialisationIterator < selections.length; initialisationIterator++)
            selections[initialisationIterator] = false;

        //Keep executing until menu selection is finished
        do {
            clearScreen();

            //Print the title
            System.out.println("=== " + contents[0] + " ===");

            //Print all the menu elements
            for(int menuElement = 1; menuElement < contents.length; menuElement++) {
                System.out.print(menuElement + " : [");

                //Print an "X" if the selection is made
                if(selections[menuElement - 1])
                    System.out.print("X");
                else
                    System.out.print(" ");

                System.out.println("] " + contents[menuElement]);
            }
            
            //Print the option to select to exit from the menu which gets selected if the user input equals contents.length
            System.out.println(exitOptionNumber + " : " + exitOption);

            //Get user input
            System.out.print("\n>");
            //Error check on user input
            try {
                userInput = input.nextInt();
            } catch(Exception exception) { //In case the user didn't input a numeric value
                //Print an error
                System.out.println("Errore : il numero inserito deve essere in formato numerico\nPremere INVIO per continuare...");
                input.nextLine();
                input.nextLine();
                //Skip this loop
                continue;
            }
            //Clear the buffer after reading a number
            input.nextLine();

            //Check user input
            if(userInput > 0 && userInput < contents.length) { //IF the input is an option
                //Use the NOT operator to invert the value contained in the array at the position specified by the user
                selections[userInput - 1] = !selections[userInput - 1];
            } else if(userInput == exitOptionNumber) { //IF the user input is the exit option
                //Execute a check if any element got selected before exiting
                // for(boolean selectionElement : selections) //Iterate trough all the booleans in the array
                //     if(selectionElement) //If a boolean is true exit from the loop, otherwise ignore user input
                menuFinished = true; //Exit from the do-while loop to make the function return the selections
            } else { //If the user input is invalid
                //Print an error message
                System.out.println("Errore : il numero inserito deve essere compreso nella lista.\nPremere INVIO per continuare.");
                //Wait for ENTER to continue program execution
                input.nextLine();
            }
        } while(!menuFinished);

        //Convert the boolean array of selections into an integer array containing the indexes of the selected elements
        //Count the amount of booleans set to true
        int selectionAmount = 0;
        for(boolean currentSelection : selections)
            if(currentSelection)
                selectionAmount++;
        //Create a new integer array with the booleans set to true
        int[] selectionIndexes = new int[selectionAmount];
        int selectionIndexIterator = 0; //Iterator indicating where the array is filled
        //Iterate trough the array of booleans, each index of each element set to true will be saved in the output array
        for(int currentSelection = 0; currentSelection < selections.length; currentSelection++)
            if(selections[currentSelection]) //If the element is selected
                //Save the index of the selection;
                selectionIndexes[selectionIndexIterator++] = currentSelection;

        //Return selection indexes
        return selectionIndexes;
    }

    //Clears the terminal screen by creating a new process calling the "cls" command from the command line
    private static void clearScreen() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
