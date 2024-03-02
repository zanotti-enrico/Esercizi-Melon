import java.util.Scanner;

public class Esercizio51 {
    public static void main(String[] args) {
        //Istanziamento dello scanner
        Scanner keyboard = new Scanner(System.in);

        //Richiesta di input all'utente di un numero in formato data
        System.out.print("Inserire una data in formato ggmmaaaa :");
        int userInput = keyboard.nextInt();

        //Passare l'input al metodo per la separazione in giorno, mese ed anno
        String[] separatedInput = dateSeparator(userInput);

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
    private static String[] dateSeparator(int date) {
        //L'array di output deve separare la data in 3 valori: giorno, mese ed anno
        String[] output = new String[3];
        //L'array di uscita in formato numerico
        int[] outputNum = new int[3];

        outputNum[2] = (date % 10000); //Salva nell'anno le ultime 4 cifre della data
        date /= 10000; //Rimuovi le ultime 4 cifre della data
        outputNum[1] = (date % 100); //Salva nel mese le ultime 2 cifre della data
        date /= 100; //Rimuovi le ultime 2 cifre
        outputNum[0] = (date % 100); //Salva nel giorno le ultime 2 cifre

        /*Ora che l'array e' stato invertito, salvare il risultato in una stringa convertendo il nome del mese */
        output[0] = String.valueOf(outputNum[0]);
        output[1] = getMonth(outputNum[1]);
        output[2] = String.valueOf(outputNum[2]);

        return output;
    }

    /*Passato un valore intero, ritorna una stringa che contiene il mese corrispondente */
    private static String getMonth(int monthNumber) {
        return switch(monthNumber) {
            case 1 -> "Gennaio";
            case 2 -> "Febbraio";
            case 3 -> "Marzo";
            case 4 -> "Aprile";
            case 5 -> "Maggio";
            case 6 -> "Giugno";
            case 7 -> "Luglio";
            case 8 -> "Agosto";
            case 9 -> "Settembre";
            case 10 -> "Ottobre";
            case 11 -> "Novembre";
            case 12 -> "Dicembre";
            default -> "Mese non esistente";
        };
    }
}