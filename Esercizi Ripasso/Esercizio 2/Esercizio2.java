public class Esercizio2 {
    public static void main(String[] args) {
        //The array which the program uses to check for consecutive values
        int[] input = {45, 54, 14, 13, 12, 65, 33, 33, 33, 34, 15, 19};

        //Check if there are three consecutive numbers in the input array
        int consecutives = 0;

        //Used during for loop
        int lastValue;
        int currentValue;
        //Iterate trough all the elements of the array until 3 or more consecutive values are found
        for(int i = 1; i < input.length && consecutives < 3; i++) {
            //Update currentValue and lastValue
            currentValue = input[i];
            lastValue = input[i - 1];

            //Check for consecutive values
            if(lastValue == currentValue && consecutives == 0) //If it's the first match the consecutive values are two not one
                consecutives += 2;
            else if(lastValue == currentValue)
                consecutives++;
            else //If they are not reset the amount fo consecutive values
                consecutives = 0;
        }

        if(consecutives >= 3)   
            System.out.println("3 Valori consecutivi trovati.");
        else
            System.out.println("Non sono stati trovati 3 valori consecutivi.");
    }
}