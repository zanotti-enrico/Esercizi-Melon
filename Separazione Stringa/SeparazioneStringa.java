/* Data una stringa di input con delle parole separate da spazio, ritorna
 * un array di stringhe che contiene le parole separate.
 */

import java.util.Scanner;

public class SeparazioneStringa {
    public static void main(String[] args) {
        //Istanziamento dello scnner
        Scanner keyboard = new Scanner(System.in);
        //Posizione dello split
        final int splitPosition = 40;

        //Richiesta di input stringa
        System.out.println("Inserire una stringa da splittare in posizione " + splitPosition + " senza troncare parole:");
        String userInput = keyboard.nextLine();

        //Splittare la stringa in posizione 40 senza troncare parole
        String[] userInputSplitted = splitString(userInput, splitPosition);
        
        //Stampare il contenuto del vettore
        System.out.println("Input splittato:");
        //Iterare in ogni elemento del vettore
        for(String currentInput : userInputSplitted)
            System.out.println("\t" + currentInput);

        //Chiusura dello scanner
        keyboard.close();
    }

    /* Passata una stringa come input, la separa in due stringhe alla posizione piu vicina
     * a quella passata come parametro senza troncare parole.
     * La posizione passata e' la posizione massima, se c'e' il mezzo di una parola a quella posizione
     * la stringa viene troncata all'inizio di quella parola.
    */
    private static String[] splitString(String input, int position) {
        //Ritornare array nullo se l'input e' vuoto
        if(input.length() == 0)
            return null;
        //Ritornare la stringa se la posizione si trova all'esterno della stringa passata
        if(position >= input.length())
            //Ritornare un nuovo array contenente soltanto la stringa passata
            return new String[]{input};
            
        //Contiene l'ultima posizione contenente uno spazio prima della posizione definita dal parametro
        int lastSpace = 0;
        //Iterare nella stringa fino al raggiungimento della posizione richiesta
        for(int search = 0; search <= position; search++)
        {
            if(input.charAt(search) == ' ')
                lastSpace = search;
        }

        //Troncare la stringa in due parti alla posizione dell'ultimo spazio trovato prima della posizione definita
        String[] output = new String[2];
        //Inizializzare le due stringhe a caratteri vuoti
        output[0] = "";
        output[1] = "";
        
        //Copiare i dati dell'input nel nuovo array
        for(int copyData = 0; copyData < input.length(); copyData++)
        {
            if(copyData < lastSpace)
                output[0] += input.charAt(copyData);
            else if(copyData > lastSpace)
                output[1] += input.charAt(copyData);
        }

        //Ritornare la stringa separata
        return output;
    }
}

