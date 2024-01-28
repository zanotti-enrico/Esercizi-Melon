import java.util.Scanner;
import java.util.Random;

public class LottoEstrazioni {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        //Numero estrazioni
        final int estrazioni = 5;
        //Valore minimo e massimo estrazioni
        final int minimoEstrazioni = 0;
        final int massimoEstrazioni = 90;
        //Contenuto da stampare nel menu 
        final String[] contenutoMenu = {
            "Lotto Estrazioni", //Titolo
            "[1] : Estrazioni ruota di Venezia", //Si estraggono 5 numeri dalla ruota di Venezia
            "[2] : Giocata utente", //Il giocatore punta cinque numeri sulla ruota di Venezia
            "[3] : Verifica vincita", //Verificare la vincita del giocatore
            "[4] : Visualizza Giocate", //Visualizzare i numeri risultati durante le giocate
            "[5] : Esci"
        };
        //Contiene la selezione dell'utente
        int selezione = 0;

        //Contiene le estrazioni della ruota di Venezia
        int[] estrazioniRuota = new int[estrazioni];
        int[] estrazioniUtente = new int[estrazioni];

        do {
            //Stampare il menu
            selezione = menu(contenutoMenu, keyboard);

            ClrScr();

            //Controllo sul valore inserito
            switch(selezione) {
                case 1 : { //Estrazioni ruota di venezia
                    //Generare le estrazioni per la ruota
                    estrazioniRuota = valoreCasualeArray(estrazioni, minimoEstrazioni, massimoEstrazioni);
                    break;
                }

                case 2 : { //Giocata utente
                    System.out.println("Puntare i cinque numeri per la ruota\n");

                    //Riempire l'array delle estrazioni dell'utente con i numeri richiesti
                    for (int estrazioneCorrente = 0; estrazioneCorrente < estrazioni; estrazioneCorrente++) {
                        System.out.print("Estrazione " + estrazioneCorrente + ": ");
                        //Salvare nell'array il numero inserito dall'utente
                        estrazioniUtente[estrazioneCorrente] = keyboard.nextInt();
                    }

                    System.out.println("Estrazioni completate");
                    break;
                }

                case 3 : { //Verifica vincita
                    
                }

                case 4 : { //Visualizza giocate
                    System.out.println("Visualizzazione delle giocate: ");

                    System.out.println("Estrazioni ruota di Venezia: ");
                    //Visualizzare il contenuto degli array delle estrazioni
                    for(int estrazioneCorrente = 0; estrazioneCorrente < estrazioni; estrazioneCorrente++) {
                        System.out.println((estrazioneCorrente + 1) + ": " + estrazioniUtente[estrazioneCorrente]);
                    }

                    System.out.println("Estrazioni utente: ");
                    //Visualizzare il contenuto degli array delle estrazioni
                    for(int estrazioneCorrente = 0; estrazioneCorrente < estrazioni; estrazioneCorrente++) {
                        System.out.println((estrazioneCorrente + 1) + ": " + estrazioniUtente[estrazioneCorrente]);
                    }
                }
            }
        } while(selezione != 5);

        /*Necessario per Giocata utente :
         * Array di 5 elementi contenente i numeri inseriti
         * Controllo dei numeri inseriti per evitare che si ripetano
         * Controllo che i numeri inseriti si trovino sulla ruota di Venezia
        */

        /*Necessario per Visualizza giocate :
         * Menu che chiede se visualizzare le giocate di Venezia o del giocatore
        */
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
                System.out.println(opzioni[iteratoreOpzioni]);

            System.out.print("\n>");

            //Ricevere l'input dall'utente
            selezione = keyboard.nextInt();

            //Cancellare lo schermo
            ClrScr();

            //Controllo errori
            if(selezione <= 0 || selezione > opzioni.length - 1) { //Stampare il messaggio di errore e attendere
                System.out.println("Errore : Il valore inserito non e' contenuto nel menu.\n");   
                Wait(1000);
            }
        } while(selezione <= 0 || selezione > opzioni.length - 1);

        //Svuotare il buffer di input dopo la lettura di un numero
        keyboard.nextLine();

        return selezione;
    }
    
    /*Attende il tempo specificato dal suo parametro in millisecondi */
    private static void Wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    /*Il metodo cancella lo schermo */
    private static void ClrScr(){
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*Ritorna un array di numeri casuali interi generati
     * la dimensione dell'array dipende dal parametro quantita'
     * i valori minimi e massimi sono compresi
     * i valori non si ripetono nell'array
     */
    private static int[] valoreCasualeArray(int qta, int minimo, int massimo) {
        //Generatore random
        Random generatore = new Random();
        //Array di output
        int[] output = new int[qta];
        //Numero corrente generato
        int numeroCorrente;

        //Iterazione generazione numeri casuali
        for(int iteratore = 0; iteratore < qta; iteratore++)
        {
            //Ripetere l'estrazione nel caso il numero estratto sia presente nell'array
            do {
                numeroCorrente = generatore.nextInt(minimo, massimo + 1);
            } while(!valorePresenteInArray(output, numeroCorrente, iteratore));

            //Quando il numero generato non si trova nell'array allora salvarlo nell'array di uscita
            output[iteratore] = numeroCorrente;
        }

        //Ritornare l'output
        return output;
    }

    /*Controlla se un valore si trova all'interno di un array 
     * termina la ricerca al raggiungimento dell'indice termineRicerca o al termine dell'array
    */
    private static boolean valorePresenteInArray (int[] array, int valore, int termineRicerca) {
        //Presuppone che il numero non sia presente nell'array
        boolean presente = false;

        //Effettua una ricerca che si termina se trova il numero nell'array
        for(int ricerca = 0; ricerca < termineRicerca && ricerca < array.length && !presente; ricerca++)
            if(array[ricerca] == valore)
                presente = false;

        //Ritorna l'esito della ricerca
        return presente;
    }
}