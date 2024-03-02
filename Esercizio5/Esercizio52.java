import java.util.Scanner;

public class Esercizio52 {
    public static void main(String[] args) {
        //Istanziamento dello scanner
        Scanner keyboard = new Scanner(System.in);

        //Richiesta di input all'utente di un numero in formato data
        System.out.print("Inserire una data in formato ggmmaaaa :");
        int userInput = keyboard.nextInt();

        //Passare l'input al metodo per la separazione in giorno, mese ed anno
        String[] separatedInput = dateSeparator(userInput);

        //Se la data inserita e' valida, stampare il risultato
        if (separatedInput != null)
        {
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
        } else { //Altrimenti stampare un messaggio di errore
            System.out.println("Data non valida.");
        }

        //Chiusura dello scanner
        keyboard.close();
    }

    /*Dato un valore numerico rappresentante la data in formato ggmmaaaa ritorna un array
     * di interi separando la data in giorno, mese, anno.
     * Nel caso la data passata non sia valida, la funzione ritorna null.
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

        /*Passare la data alla funzione che controlla se essa e' corretta */
        if(!checkValidDate(outputNum))
            return null; //Ritornare oggetto nullo indicando che la data passata non e' valida

        /*Ora che l'array e' stato invertito, salvare il risultato in una stringa convertendo il nome del mese */
        output[0] = String.valueOf(outputNum[0]);
        output[1] = getMonth(outputNum[1]);
        output[2] = String.valueOf(outputNum[2]);

        return output;
    }

    /*Passata una data, controlla se essa sia valida o meno
     * la data passata e' formattata da un array a 3 elementi dove 
     * il primo indica il giorno, il secondo il mese ed il terzo l'anno.
     */
    private static boolean checkValidDate(int[] date) {
        /*Definire variabili apposite per la leggibilita' del funzionamento del metodo */
        int day = date[0];
        int month = date[1];
        int year = date[2];
        /*In base al mese, effettuare un determinato controllo sul numero del giorno*/
        if( //Controllo che il giorno non superi il giorno massimo del mese specificato
            switch(month) {
                case 1 -> day > 31;
                case 2 -> day > 29; //Consideriamo il caso in cui l'anno sia bisestile
                case 3 -> day > 31;
                case 4 -> day > 30;
                case 5 -> day > 31;
                case 6 -> day > 30;
                case 7 -> day > 31;
                case 8 -> day > 31;
                case 9 -> day > 30;
                case 10 -> day > 31;
                case 11 -> day > 30;
                case 12 -> day > 31;

                default -> true; //Se il mese non e' in questo range, la data e' sicuramente errata
            } || day < 0 //Controllo inoltre che il giorno non sia negativo
        ) return false;

        //Nel caso il mese sia febbraio ed il giorno sia 29, controllare che l'anno sia bisestile
        if(month == 2 || day == 29)
            if(!( //Neghiamo tutta l'espressione in modo da eseguire l'istruzione soltanto se l'anno NON e' bisestile
                (year % 4 == 0) //L'anno e' bisestile se divisibile per 4
                || //Oppure :
                (year % 100 == 0 && year % 400 == 0) //E' inoltre bisestile se e' secolare (divisibile per 100) e divisibile per 400
            ))
                return false; //Se l'anno non e' bisestile e il giorno e' il 29 febbraio, la data non e' corretta

        //In tutti gli altri casi, la data e' corretta
        return true;
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