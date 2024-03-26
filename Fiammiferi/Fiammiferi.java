import java.util.Scanner;
import java.util.Random;

public class Fiammiferi {
    public static void main(String[] args) {
        //Istanziamento dello scanner
        Scanner keyboard = new Scanner(System.in);
        //Istanziamento del generatore random
        Random generator = new Random();
        //Contenuto del menu
        final String[] menuContents = {
            "Selezionare il metodo di funzionamento del programma", // Titolo
            "Giocatore VS Giocatore", //Prima opzione
            "Giocatore VS Computer"
        };

        //Contiene quanti fiammiferi puo' l'utente togliere al massimo
        int maxNumFiammiferi = 3;
        //Contiene quali e quanti fiammiferi sono presenti nell'array
        int numFiammiferi = 21;
        //Contiene l'ultimo giocatore che ha rimosso il fiammifero, al termine del programma questo conterra' il vincitore
        int ultimoGiocatore = 0;

        //Richiede all'utente la modalita' di funzionamento del programma tramite il menu
        int funzionamentoProgramma = menu(menuContents, keyboard);

        ClrScr();
        
        //Loop che si esegue fino al termine del programma
        do {
            //Se il numero di fiammiferi rimasti e' minore del numero massimo di fiammiferi da rimuovere, impostare il numero massimo di fiammiferi al numero di fiammiferi rimasti
            if(maxNumFiammiferi > numFiammiferi)
                maxNumFiammiferi = numFiammiferi;

            //Far giocare il primo giocatore
            System.out.println("Turno giocatore 1\n");
            //Stampare i fiammiferi
            System.out.println(disegnaFiammiferi(numFiammiferi));
            //Far scegliere all'utente quanti fiammiferi si vogliono togliere
            numFiammiferi -= getUserInput(1, maxNumFiammiferi, "Digitare quanti fiammiferi si vogliono rimuovere (MAX " + maxNumFiammiferi + ") :", keyboard);
            //L'ultimo giocatore e' stato il giocatore numero 1
            ultimoGiocatore = 1;

            //Cancellare lo schermo
            ClrScr();

            //Se sono rimasti dei fiammiferi, continuare
            if(numFiammiferi > 0)
            {
                //Se il numero di fiammiferi rimasti e' minore del numero massimo di fiammiferi da rimuovere, impostare il numero massimo di fiammiferi al numero di fiammiferi rimasti
                if(maxNumFiammiferi > numFiammiferi)
                maxNumFiammiferi = numFiammiferi;

                //In base alla selezione iniziale dal menu dell'utente, decidere se far scegliere il nuovo valore all'utente o al computer
                if(funzionamentoProgramma == 2)
                {
                    //Stampare il turno
                    System.out.println("Turno computer\n");
                    //Stampare i fiammiferi
                    System.out.println(disegnaFiammiferi(numFiammiferi));
                    //Generare un valore da rimuovere compreso fra 1 e 3, togliendolo dal numero di fiammiferi
                    int numFiammiferiRimuovere = generator.nextInt(1, maxNumFiammiferi + 1);
                    System.out.println("Rimossi " + numFiammiferiRimuovere + " fiammiferi.\n");
                    //Rimuovere i fiammiferi dal numero totale
                    numFiammiferi -= numFiammiferiRimuovere;
                    //Attendere per mostrare all'utente il risultato generato
                    Wait(1000);
                    ClrScr();
                    //Stampare il numero di fiammiferi rimossi
                    System.out.println("Stato fiammiferi : \n\n" + disegnaFiammiferi(numFiammiferi));
                    //Attendere un altro secondo per mostrare all'utente
                    Wait(1000);
                    //L'ultimo giocatore e' stato il computer
                    ultimoGiocatore = 3;
                } else {
                    //Far giocare il secondo giocatore
                    System.out.println("Turno giocatore 2\n");
                    //Stampare i fiammiferi
                    System.out.println(disegnaFiammiferi(numFiammiferi));
                    //Far scegliere all'utente quanti fiammiferi si vogliono togliere
                    numFiammiferi -= getUserInput(1, maxNumFiammiferi, "Digitare quanti fiammiferi si vogliono rimuovere (MAX " + maxNumFiammiferi + ") :", keyboard);
                    //L'ultimo giocatore e' stato il giocatore 2
                    ultimoGiocatore = 2;
                }
                
                //Cancellare lo schermo
                ClrScr();
            }
        //Continuare l'esecuzione del programma finche' il numero di fiammiferi e' positivo
        } while(numFiammiferi > 0);

        //Controllo sull'ultimo giocatore e stampa del vincitore in base a quel numero.
        System.out.print("Ha vinto ");
        System.out.println(
            switch(ultimoGiocatore) {
                case 1 -> "Giocatore 1";
                case 2 -> "Giocatore 2";
                case 3 -> "Il computer";

                default -> "Nessuno";
            }
        );

        //Chiudere l'istanziamento dello scanner
        keyboard.close();
    }

    /*Passato un array di booleani, Scrive i fiammiferi su stringa presenti o non presenti in base ai valori booleani veri nell'array
     * Il numero di fiammiferi stampati e' la dimensione dell'array passato al metodo.
     * La stringa viene poi ritornata dal metodo
     */
    private static String disegnaFiammiferi(int numFiammiferi) {
        //Contiene la stringa con i fiammiferi
        String disegnoFiammiferi = "";

        //Iterare per tutti gli elementi dell'array
        for(int printIterator = 0; printIterator < numFiammiferi; printIterator++)
            //Disegnare l'asterisco
            disegnoFiammiferi += "*";

        //Andare a capo nella stringa
        disegnoFiammiferi += "\n";

        //Ora stampare lo stecchino dei fiammiferi
        //Iterare in tutti gli elementi dell'array passato
        for(int printIterator = 0; printIterator < numFiammiferi; printIterator++)
            //Disegnare la gamba del fiammifero
            disegnoFiammiferi += "|";

        //Ritornare l'oggetto modificato
        return disegnoFiammiferi;
    }

    /*Richiede in input da parte dell'utente un valore e controlla che esso sia compreso nel range specificato.
     * Se il valore inserito dall'utente non e' numerico o non e' compreso nel range specificato, allora ripetere l'inserimento.
     * Prima di ogni inserimento stampa una stringa che viene passata tramite parametro prima di ogni inserimento.
     */
    private static int getUserInput(int min, int max, String userRequest, Scanner keyboard) {
        //Viene impostata a vero quando il valore inserito non e' valido, porta alla ripetizione dell'inserimento
        boolean valoreNonValido;
        //Contiene l'input dell'utente
        int userInput = 0;
        do {
            //Reimpostare la variabile di valore non valido
            valoreNonValido = false;

            //Richiedere all'utente il valore utilizzando la stringa passata come metodo
            System.out.println(userRequest);

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
            if(!valoreNonValido && (userInput < min || userInput > max))
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
    
    /*Stampa e gestisce a schermo un menu, ritornando il valore selezionato dall'utente */
    private static int menu(String[] opzioni, Scanner keyboard) {
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

    /*Metodo che cancella lo schermo */
    private static void ClrScr(){
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*Metodo che attende un certo periodo di tempo */
    private static void Wait(long ms) {
        try {
            Thread.sleep(ms);
        } catch(InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}