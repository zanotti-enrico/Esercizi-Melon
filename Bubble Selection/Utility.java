import java.util.Scanner;
import java.util.Random;
//GOH OGOH GOH GOH 
public class Utility {
    /*Il metodo genera un array di numeri casuali con i parametri specificati */
    public static int[] randomArray(int elementAmount, int maxBound) {
        //Creare l'array di output
        int[] output = new int[elementAmount];
        //Creare il generatore di numeri casuali
        Random generator = new Random();
        //Iterare in ogni elemento nell'array di output generando un nuovo numero casuale
        for(int currentElement = 0; currentElement < elementAmount; currentElement++)
            output[currentElement] = generator.nextInt(maxBound);
        
        //Ritornare l'array generato
        return output;
    }

    /*Il metodo copia un array e ritorna la copia */
    public static int[] copyArray(int[] input) {
        //Creazione dell'array di copia
        int[] output = new int[input.length];
        //Copia degli elementi
        for(int copy = 0; copy < input.length; copy++) {
            output[copy] = input[copy];
        }
        //Ritornare la copia
        return output;
    }

    /*Il metodo prende l'input utente eseguendo un controllo errori e richiedendo il
     * reinserimento del dato in caso di input errato.
     * Permette l'inserimento soltanto di numeri interi positivi.
     */
    public static int getUserInput(Scanner userInput) {
        //Contiene il valore di ritorno
        int output = 0;
        //Contiene se l'input e' errato o meno
        boolean invalidInput;
        do {
            //Reimpostare la variabile di input non valido
            invalidInput = false;
            //Richiedere l'input all'utente
            try {
                output = userInput.nextInt();
            } catch(Exception exception) {
                //Svuotare il buffer
                userInput.nextLine();
                //Stampare un messaggio di errore
                System.out.println("Errore : L'input deve essere di tipo numerico.");
                invalidInput = true;
            }

            //Se l'input non e' errato controllare se il valore e' negativo e dare un errore
            if(!invalidInput && output < 0) {
                //Il valore inserito e' errato
                System.out.println("Errore : il numero inserito deve essere positivo.");
                invalidInput = true;
            }
        } while(invalidInput);

        return output;
    }
}
