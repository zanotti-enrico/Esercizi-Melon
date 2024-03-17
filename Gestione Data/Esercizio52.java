import java.util.Scanner;

public class Esercizio52 {
    public static void main(String[] args) {
        //Istanziamento dello scanner
        Scanner keyboard = new Scanner(System.in);

        //Richiesta di input all'utente di un numero in formato data
        System.out.print("Inserire una data in formato ggmmaaaa :");
        int userInput = keyboard.nextInt();

        //Passare l'input al metodo per la separazione in giorno, mese ed anno
        int[] separatedDate = dateSeparatorInteger(userInput);

        //Se la data inserita e' valida, stampare il risultato
        if (separatedDate != null)
            printDate(separatedDate);
        else { //Altrimenti stampare un messaggio di errore
            System.out.println("Data non valida.");
        }

        //Variabili di contenuto del menu
        final String[] menuContents = {
            "Selezionare cosa si vuole fare con la data:",
            "Incrementarla",
            "Decrementarla",
            "Uscire"
        };
        //Variabile di termine del programma
        boolean programTermination = false;
        //Valore selezionato dall'utente nel menu
        int userSelection;
        do {
            //Prendi la selezione dell'utente dal menu
            userSelection = menu(menuContents, keyboard);

            //In base alla selezione agisci sulla data
            if(userSelection == 1) { //Se l'utente ha selezionato l'incremento
                System.out.println("Di quanto si vuole incrementare la data?");
                int increment = keyboard.nextInt();
                separatedDate = dataUP(separatedDate, increment);
            } else if(userSelection == 2) { //Se l'utente ha selezionato il decremento
                System.out.println("Di quanto si vuole decrementare la data?");
                int decrement = keyboard.nextInt();
                separatedDate = dataDown(separatedDate, decrement);
            } else //Nel caso l'utente abbia selezionato l'opzione di uscire
                programTermination = true;

            System.out.println("\nOra la data e':");
            printDate(separatedDate);
        } while(!programTermination);

        //Chiusura dello scanner
        keyboard.close();
    }

    /*Stampa su schermo una data passata */
    private static void printDate(int[] date)
    {
        //Iterare in tutte le posizioni della data
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
            //In caso la posizione corrente sia il mese, allora stampare il nome del mese
            if(dateIterator == 1)
                System.out.println(dateSection + " : " + getMonth(date[dateIterator]));
            else
                System.out.println(dateSection + " : " + date[dateIterator]);
        }
    }
    
    /*Stampa e gestisce a schermo un menu, ritornando il valore selezionato dall'utente */
    private static int menu(String[] opzioni, Scanner keyboard) {
        //Contiene la selezione
        int selezione;

        //Controllo sull'input ricevuto dall'utente
        do {

            //Stampare titolo e opzioni su schermo
            System.out.println("=== " + opzioni[0] + " ===\n");
            for (int iteratoreOpzioni = 1; iteratoreOpzioni < opzioni.length; iteratoreOpzioni++)
                System.out.println("[" + iteratoreOpzioni + "] - " + opzioni[iteratoreOpzioni]);

            System.out.print("\n>");

            //Ricevere l'input dall'utente
            selezione = keyboard.nextInt();

            //Controllo errori
            if(selezione <= 0 || selezione > opzioni.length - 1) { //Stampare il messaggio di errore e attendere
                System.out.println("Errore : Il valore inserito non e' contenuto nel menu.\n");
            }
        } while(selezione <= 0 || selezione > opzioni.length - 1);

        //Svuotare il buffer di input dopo la lettura di un numero
        keyboard.nextLine();

        return selezione;
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
    /*Uguale alla funzione precedente, ritorna un array di interi invece che stringhe */
    private static int[] dateSeparatorInteger (int date) {
        //L'array di output deve separare la data in 3 valori: giorno, mese ed anno
        int[] output = new int[3];

        output[2] = (date % 10000); //Salva nell'anno le ultime 4 cifre della data
        date /= 10000; //Rimuovi le ultime 4 cifre della data
        output[1] = (date % 100); //Salva nel mese le ultime 2 cifre della data
        date /= 100; //Rimuovi le ultime 2 cifre
        output[0] = (date % 100); //Salva nel giorno le ultime 2 cifre

        /*Passare la data alla funzione che controlla se essa e' corretta */
        if(!checkValidDate(output))
            return null; //Ritornare oggetto nullo indicando che la data passata non e' valida
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
            if(!isLeap(year)) //Neghiamo tutta l'espressione in modo da eseguire l'istruzione soltanto se l'anno NON e' bisestile
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
    
    /*Given a date, it increments it by the amount of days passed to the method */
    private static int[] dataUP (int[] date, int increment) {
        //Create a copy of the array date
        int[] dateCopy = new int[date.length];
        //Fill the copy with the date
        for(int arrCopy = 0; arrCopy < date.length; arrCopy++)
            dateCopy[arrCopy] = date[arrCopy];

        //Do a cycle that executes as many times as the increment
        for(int incrementIterator = 0; incrementIterator < increment; incrementIterator++) {
            //If the date is the last of the year, increment the year
            if(dateCopy[0] == 31 && dateCopy[1] == 12)
            {
                //Reset day and month
                dateCopy[0] = 1;
                dateCopy[1] = 1;
                //Increment year
                dateCopy[2]++;
            //If the day is the last day of the month
            } else if(
                //Condition of the if statement based on the month and, in case of february, the year
                switch(dateCopy[1]) {
                    case 1 -> dateCopy[0] == 31;
                    case 2 -> (isLeap(dateCopy[2]) && dateCopy[0] == 29) || (!isLeap(dateCopy[2]) && dateCopy[0] == 28);
                    case 3 -> dateCopy[0] == 31;
                    case 4 -> dateCopy[0] == 30; 
                    case 5 -> dateCopy[0] == 31;
                    case 6 -> dateCopy[0] == 30;
                    case 7 -> dateCopy[0] == 31;
                    case 8 -> dateCopy[0] == 31;
                    case 9 -> dateCopy[0] == 30;
                    case 10 -> dateCopy[0] == 31;
                    case 11 -> dateCopy[0] == 30;
                    case 12 -> dateCopy[0] == 31;

                    default -> false; //If the option is not valid then skip the if statement
                }
            ) {
                //Reset the day
                dateCopy[0] = 1;
                //Increment the month
                dateCopy[1]++;
            } else //If it's all good, then increment the day
                dateCopy[0]++;
        }

        //Return the modified date
        return dateCopy;
    }

    /*Given a date, it decrements it by the amount of days passed to the method */
    private static int[] dataDown(int[] date, int decrement) {
        //Create a copy of the array date
        int[] dateCopy = new int[date.length];
        //Fill the copy with the date
        for(int arrCopy = 0; arrCopy < date.length; arrCopy++)
            dateCopy[arrCopy] = date[arrCopy];

        //Do a cycle that executes as many times as the decrement
        for(int decrementIterator = 0; decrementIterator < decrement; decrementIterator++) {
            //If the day is the first of the year:
            if(dateCopy[0] == 1 && dateCopy[1] == 1)
            {
                //Set day and month to the last of the year
                dateCopy[0] = 31;
                dateCopy[1] = 12;
                //Decrement the year
                dateCopy[2]--;
            //But if the day is the first of the month:
            } else if(dateCopy[0] == 1) {
                //Decrement the month
                dateCopy[1]--;
                //Set the day to the last of the decremented month
                dateCopy[0] = switch(dateCopy[1]) {
                    case 1 -> 31;
                    case 2 -> isLeap(dateCopy[2]) ? 29 : 28;
                    case 3 -> 31;
                    case 4 -> 30;
                    case 5 -> 31;
                    case 6 -> 30;
                    case 7 -> 31;
                    case 8 -> 31;
                    case 9 -> 30;
                    case 10 -> 31;
                    case 11 -> 30;
                    case 12 -> 31;

                    default -> 0;
                };
            } else //Else decrement the day
                dateCopy[0]--;
        }

        //Return the modified date
        return dateCopy;
    }

    /*Given a year, it returns if it's a leap year or not */
    private static boolean isLeap(int year) {
        return ( 
            (year % 4 == 0) //L'anno e' bisestile se divisibile per 4
            || //Oppure :
            (year % 100 == 0 && year % 400 == 0) //E' inoltre bisestile se e' secolare (divisibile per 100) e divisibile per 400
        );
    }
}