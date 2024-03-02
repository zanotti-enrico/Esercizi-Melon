import java.util.Scanner;

public class Esercizio50 {
    public static void main(String[] args) {
        //Istanziamento dello scanner
        Scanner keyboard = new Scanner(System.in);

        //Richiesta di input all'utente di un numero in formato data
        System.out.print("Inserire una data in formato ggmmaaaa :");
        int userInput = keyboard.nextInt();

        //Passare l'input al metodo per la separazione in giorno, mese ed anno
        int[] separatedInput = dateSeparator(userInput);

        //Stampare il risultato
        for(int dateIterator = 0; dateIterator < 3; dateIterator++)
        {
            //In base alla posizione nell'array di output, la stringa avra' il valore relativo a quella posizione
            String dateSection = switch(dateIterator) {
                case 0 -> "Giorno";
                case 1 -> "Mese";
                case 2 -> "Anno";
                default -> "";
            };

            //Stampare la posizione corrente nell'array
            System.out.println(dateSection + " : " + separatedInput[dateIterator]);
        }

        //Chiusura dello scanner
        keyboard.close();
    }

    /*Dato un valore numerico rappresentante la data in formato ggmmaaaa ritorna un array
     * di interi separando la data in giorno, mese, anno
     */
    private static int[] dateSeparator(int date) {
        //L'array di uscita
        int[] output = new int[3];

        output[2] = (date % 10000); //Salva nell'anno le ultime 4 cifre della data
        date /= 10000; //Rimuovi le ultime 4 cifre della data
        output[1] = (date % 100); //Salva nel mese le ultime 2 cifre della data
        date /= 100; //Rimuovi le ultime 2 cifre
        output[0] = (date % 100); //Salva nel giorno le ultime 2 cifre

        return output;
    }
}