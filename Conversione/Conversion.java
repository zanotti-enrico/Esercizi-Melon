import java.util.Scanner;

public class Conversion {
    public static void main(String[] args) {
        /*Creating a new scanner */
        Scanner keyboard = new Scanner(System.in);

        String numberStr; //String input
        int numberBinary = 0; //Number converted in binary
        int numberDecimal = 0; //Number translated in decimal
        int currentDigit; //Current digit in the for loop
        int digitCounter = 0; //Keeps track of what digit in the binary number we're at in the decimal conversion
        boolean isBinary = true; //Flag that gets set to false if an invalid digit is found

        /*User input */
        System.out.print("Inserisci un numero binario : ");
        numberStr = keyboard.next();

        /*Check for binary number */
        for(int iterator = 0; iterator < numberStr.length() && isBinary; iterator++){
            /*Char to int conversion */
            currentDigit = (int)numberStr.charAt(iterator) - '0';

            /*Check for binary number */
            if(currentDigit != 0 && currentDigit != 1)
                isBinary = false; //Exit from for loop
            else
                numberBinary = numberBinary * 10 + currentDigit; //Append the current digit to the final number
        }

        /*If the number doesn't contain any invalid digit convert it in decimal */
        if(isBinary){
            /*Keep executing until every digit of the binary number is consumed */
            while(numberBinary > 0) {
                /*Retrieve last digit of binary number */
                currentDigit = numberBinary % 10;
                numberBinary /= 10;

                /*Retrieve the power of 2 relative to the current digit using digitCounter */
                int numberSignificance = 1;
                for(int iterator = 0; iterator < digitCounter; iterator++)
                    numberSignificance *= 2;

                /*Add the current digit to the decimal number converting it */
                numberDecimal += currentDigit * numberSignificance;

                /*Increase the counter for the current digit */
                digitCounter++;
            }

            /*Print the result */
            System.out.println("Numero convertito : " + numberDecimal);
        } else 
            System.out.println("Errore : il numero inserito non e' un numero binario in quanto contiene cifre diverse da 0 e 1");
    }
}