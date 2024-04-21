import java.util.Scanner;

public class Tools {
    /*Compara due stringhe utilizzando l'ordine alfabetico 
     * Ritorna un valore negativo se la prima e' minore della seconda;
     * un valore positivo se la prima e' maggiore della seconda;
     * un valore nullo se le due stringhe sono uguali.
    */
    public static int compareString(String firstCompare, String secondCompare) {
        //Variabile che contiene la dimensione della stringa piu piccola
        int lowerLength;
        if(firstCompare.length() < secondCompare.length())
            lowerLength = firstCompare.length();
        else
            lowerLength = secondCompare.length();

        //Iterare in tutti i caratteri della stringa piu corta
        for(int currentChar = 0; currentChar < lowerLength; currentChar++)
        {
            /* Se viene trovato un carattere diverso, ritornare dal metodo un valore
             * minore di 0 se il primo e' minore del secondo, altrimenti
             * un valore maggiore di 0 se il primo e' maggiore del secondo parametro passato.
            */
            if(firstCompare.charAt(currentChar) < secondCompare.charAt(currentChar))
                return -1;
            else if(firstCompare.charAt(currentChar) > secondCompare.charAt(currentChar))
                return 1;
            //Se i due caratteri sono uguali continuare a cercare fino a quando non si raggiunge un carattere differente
        }

        //Se fino alla lunghezza della stringa piu' corta tutti i caratteri delle due stringhe sono uguali,
        //Se le due stringhe sono di lunghezza diversa:
        if(firstCompare.length() != secondCompare.length())
        {
            /*Ritornare un valore positivo o negativo in base alla stringa di lunghezza maggiore 
                * viene ritenuta maggiore la stringa di dimensione maggiore
            */
            if(firstCompare.length() > secondCompare.length())
                return 1;
            else if(firstCompare.length() < secondCompare.length())
                return -1;
        }

        //Altrimenti se le due stringhe sono di dimensione uguale allora sono uguali
        return 0;
    }

    /*Stampa e gestisce a schermo un menu, ritornando il valore selezionato dall'utente */
    public static int menu(String[] opzioni, Scanner keyboard) {
        //Contiene la selezione
        int selezione = 0;
 
        //Controllo sull'input ricevuto dall'utente
        boolean inputErrato = false;
        do {
            //Resettare la variabile di input errato
            inputErrato = false;

            //Cancellare lo schermo
            ClrScr();

            //Stampare titolo e opzioni su schermo
            System.out.println("=== " + opzioni[0] + " ===\n");
            for (int iteratoreOpzioni = 1; iteratoreOpzioni < opzioni.length; iteratoreOpzioni++)
                System.out.println("[" + iteratoreOpzioni + "] - " + opzioni[iteratoreOpzioni]);

            System.out.print("\n>");

            //Controllare il valore di input inserito dall'utente
            try {
                //Ricevere l'input dall'utente
                selezione = keyboard.nextInt();
            } catch(Exception ex) {
                //Nel caso l'utente abbia inserito un valore di tipo non numerico
                //Stampare un messaggio di errore
                System.out.println("Errore : il dato inserito deve essere di tipo numerico.");
                //Vuotare il buffer
                keyboard.nextLine();
                //Impostare la variabile che determina se il dato inserito non e' valido
                inputErrato = true;
            }

            //Controllo sul dato soltanto se l'input precedente non era errato.
            if(!inputErrato && (selezione <= 0 || selezione > opzioni.length - 1)) 
            {
                //Stampare il messaggio di errore ed impostare la variabile che determina l'input errato
                System.out.println("Errore : Il valore inserito non e' contenuto nel menu.\n");
                inputErrato = true;
            }
        //Continuare il ciclo fino a quando l'input non e' corretto
        } while(inputErrato);

        //Svuotare il buffer di input dopo la lettura di un numero
        keyboard.nextLine();

        return selezione;
    }

    /*Richiede in input da parte dell'utente un valore e controlla che esso sia compreso nel range specificato.
     * Se il valore inserito dall'utente non e' numerico o non e' compreso nel range specificato, allora ripetere l'inserimento.
     * Prima di ogni inserimento stampa una stringa che viene passata tramite parametro prima di ogni inserimento.
     * Altri due parametri booleani definiscono se utilizzare il limite minimo e il limite massimo, impostarli a falsi se i limiti non vanno utilizzati
     * Se il limite minimo supera il limite massimo il limite minimo viene ignorato.
     * Al termine della richiesta dell'utente il metodo stampa una richiesta di uscita, con una stringa che se l'utente immette il programma esce.
     * Se l'utente inserisce la stringa di uscita, il metodo ritorna un valore minore del valore minimo definito.
     */
    public static int getUserInput(int min, int max, String userRequest, Scanner keyboard, boolean limiteMinimo, boolean limiteMassimo) {
        //Stringa da immettere perche' l'inserimento si termini
        final String termineInserimento = "exit";
        //Viene impostata a vero quando il valore inserito non e' valido, porta alla ripetizione dell'inserimento
        boolean valoreNonValido;
        //Se il valore minimo e' maggiore del massimo allora ignorare il limite minimo
        limiteMinimo = min > max;
        
        //Contiene l'input dell'utente nei due formati
        String userInputStr;
        int userInput = 0;
        do {
            //Reimpostare la variabile di valore non valido
            valoreNonValido = false;

            //Richiedere all'utente il valore utilizzando la stringa passata come metodo
            System.out.println("Inserire '" + termineInserimento + "' per uscire.");
            System.out.print(userRequest);

            //Richiedere l'input all'utente
            userInputStr = keyboard.next();
            //Se l'utente ha inserito la stringa di termine del programma allora uscire
            if(userInputStr.toLowerCase().equals(termineInserimento))
                //Ritornare dal metodo il valore che indica la terminazione dell'inserimento
                return min - 1;
            //Converto il valore stringa inserito dall'utente in formato numerico
            try {
                userInput = Integer.valueOf(userInputStr);
            } catch (Exception ex) {
                //In caso di input non numerico inserito
                System.out.println("Errore : il valore inserito deve essere di tipo numerico.");
                //Svuotare il buffer
                keyboard.nextLine();
                //Ripetere l'esecuzione del programma
                valoreNonValido = true;
            }

            //Continuare il controllo se il valore inserito e' di tipo numerico
            //Controllare valore minimo e massimo soltanto se l'utente ha determinato un valore minimo o massimo
            if(!valoreNonValido && ((userInput < min && limiteMinimo) || (userInput > max && limiteMassimo)))
            {
                //Stampare il messaggio di errore
                System.out.println("Errore : il valore inserito deve essere compreso tra " + min + " e " + max);
                //Svuotare il buffer
                keyboard.nextLine();
                //Il valore inserito non e' valido, ripetere il programma
                valoreNonValido = true;
            }
        //Ripetere l'esecuzione nel caso il valore non sia valido
        } while(valoreNonValido);

        //Svuotare il buffer dopo la lettura del numero
        keyboard.nextLine();

        //Se il valore inserito fosse valido, allora ritornare il valore inserito
        return userInput;
    }

    /*Metodo che cancella lo schermo */
    public static void ClrScr(){
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*Metodo che attende un certo periodo di tempo */
    public static void Wait(long ms) {
        try {
            Thread.sleep(ms);
        } catch(InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
