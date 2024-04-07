import java.util.Scanner;

public class Tools {
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
     * Se il valore massimo e' minore del valore minimo, non viene posto nessun limite minimo.
     * Se il valore massimo ed il valore minimo coincidono non viene posto alcun limite massimo
     */
    public static int getUserInput(int min, int max, String userRequest, Scanner keyboard) {
        //Viene impostata a vero quando il valore inserito non e' valido, porta alla ripetizione dell'inserimento
        boolean valoreNonValido;
        //Determinare se ci vuole un limite massimo o minimo
        boolean limiteMinimo = max < min;
        boolean limiteMassimo = max == min;
        //Contiene l'input dell'utente
        int userInput = 0;
        do {
            //Reimpostare la variabile di valore non valido
            valoreNonValido = false;

            //Richiedere all'utente il valore utilizzando la stringa passata come metodo
            System.out.print(userRequest);

            //Richiedere l'input
            try {
                userInput = keyboard.nextInt();
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
                //Il valore inserito non e' valido, ripetere il programma
                valoreNonValido = true;
            }
        //Ripetere l'esecuzione nel caso il valore non sia valido
        } while(valoreNonValido);

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
