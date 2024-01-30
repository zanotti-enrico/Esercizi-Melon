import java.util.Scanner;
import java.util.Random;

public class LottoEstrazioniBoolean {
    public static void main(String[] args) {
        //Istanziamento dello scanner
        Scanner keyboard = new Scanner(System.in);

        //Numero estrazioni
        final int estrazioni = 5;
        //Valore minimo e massimo estrazioni
        final int minimoEstrazioni = 1;
        final int massimoEstrazioni = 90;
        //Contenuto da stampare nel menu 
        final String[] contenutoMenu = {
            "Lotto Estrazioni", //Titolo
            "[1] : Estrazioni ruota di Venezia", //Si estraggono 5 numeri dalla ruota di Venezia
            "[2] : Giocata utente", //Il giocatore punta cinque numeri sulla ruota di Venezia
            "[3] : Verifica vincita", //Verificare la vincita del giocatore
            "[4] : Visualizza Giocate", //Visualizzare i numeri risultati durante le giocate
            "[5] : Reset", //Reimposta il programma
            "[6] : Esci"
        };
        //Contiene la selezione dell'utente
        int selezione = 0;

        //Contiene le estrazioni della ruota di Venezia
        int[] estrazioniRuota = null;
        int[] estrazioniUtente = new int[estrazioni];
        //Array utilizzato per il controllo se il valore inserito dall'utente si trova gia' nell'array
        boolean[] controlloEstrazioniUtente = new boolean[massimoEstrazioni - minimoEstrazioni];

        //Contiene se l'estrazione del giocatore e della ruota di venezia sono state eseguite
        /*Questo viene fatto per impedire l'utente o la ruota di estrarre
         * piu' volte o di visualizzare i valori della ruota prima dell'estrazione
         */
        boolean estrazioneRuotaEseguita = false;
        boolean estrazioneUtenteEseguita = false;

        do {
            //Cancellare lo schermo
            ClrScr();
            
            //Stampare il menu
            selezione = menu(contenutoMenu, keyboard);

            //Controllo sul valore inserito
            switch(selezione) {
                case 1 : { //Estrazioni ruota di venezia
                    //Impedire multiple estrazioni della ruota
                    if(estrazioneRuotaEseguita)
                    {
                        System.out.println("Errore : l'estrazione della ruota puo' essere eseguita una sola volta.\nPremere INVIO per continuare.");
                        keyboard.nextLine();
                        break;
                    }

                    //Generare le estrazioni per la ruota
                    estrazioniRuota = valoreCasualeArray(estrazioni, minimoEstrazioni, massimoEstrazioni);
                    //Salvare l'estrazione della ruota compiuta nella rispettiva variabile globale
                    estrazioneRuotaEseguita = true;

                    System.out.println("Estrazioni eseguite. Premere INVIO per continuare...");
                    keyboard.nextLine();

                    break;
                }

                case 2 : { //Giocata utente
                    //Impedire multiple puntate dell'utente
                    if(estrazioneUtenteEseguita)
                    {
                        System.out.println("Errore : la puntata dell'utente puo' essere eseguita una sola volta.\nPremere INVIO per continuare.");
                        keyboard.nextLine();
                        break;
                    }

                    System.out.println("Puntare i cinque numeri per la ruota\n");

                    //Il valore inserito dall'utente all'interno del ciclo di for
                    int valoreInserito;
                    //Se il valore inserito dall'utente si trova gia' nell'array questa variabile viene impostata a falsa
                    boolean continuareInserimento;

                    //Riempire l'array delle estrazioni dell'utente con i numeri richiesti
                    for (int estrazioneCorrente = 0; estrazioneCorrente < estrazioni; estrazioneCorrente++) {
                        do {
                            //Reimpostare ad ogni iterazione la variabile per continuare l'inserimento
                            continuareInserimento = true;

                            System.out.print("Estrazione " + estrazioneCorrente + ": ");
                            //Salvare nella variabile locale il valore inserito dall'utente
                            valoreInserito = keyboard.nextInt();

                            //Nel caso l'utente inserisca un valore fuori range
                            if(valoreInserito < minimoEstrazioni || valoreInserito > massimoEstrazioni) { 
                                System.out.println("Errore : Il valore inserito deve essere compreso tra " + minimoEstrazioni + " e " + massimoEstrazioni + " per le estrazioni");
                                continuareInserimento = false;
                            }
                            
                            //Eseguire questo controllo soltanto se il numero inserito si trova nel range corretto
                            if(continuareInserimento) {
                                //Se secondo l'array di booleane il valore si trova gia' nell'array allora ripetere l'estrazione
                                if(controlloEstrazioniUtente[valoreInserito - minimoEstrazioni])
                                {
                                    System.out.println("Errore : i valori delle estrazioni non possono ripetersi.");
                                    //Impostando la variabile a falso il ciclo while si ripete
                                    continuareInserimento = false;
                                } else { //Se il valore non era presente:
                                    //Impostare il valore nell'array di booleane a vero in modo ceh non possa essere reinserito nuovamente
                                    controlloEstrazioniUtente[valoreInserito - minimoEstrazioni] = true;
                                }
                            }
                        //Se un numero inserito non e' valido ripetere l'inserimento
                        } while(!continuareInserimento);

                        //Salvare il numero digitato nell'array una volta eseguito il controllo per assicurarsi che sia valido
                        estrazioniUtente[estrazioneCorrente] = valoreInserito;
                    }

                    //Salvare l'esecuzione del puntamento dell'utente nella rispettiva variabile
                    estrazioneUtenteEseguita = true;

                    System.out.println("Puntate completate");
                    break;
                }

                case 3 : { //Verifica vincita
                    //Impedire la ricerca delle vincite nel caso l'utente non abbia puntato o la ruota non abbia estratto
                    if(!estrazioneUtenteEseguita || !estrazioneRuotaEseguita)
                    {
                        System.out.println("Errore : Per la visualizzazione delle vincite sia utente che ruota devono aver estratto.");
                        System.out.println("Premere INVIO per continuare");
                        keyboard.nextLine();
                        break;
                    }

                    //Contiene il numero di estrazioni dell'utente che sono presenti all'interno della ruota
                    int estrazioniUtenteCorrette = nPresentiInArray(estrazioniRuota, estrazioniUtente);

                    //In base alla quantita' di valori trovati presenti in entrambi gli array stampare una vittoria
                    switch(estrazioniUtenteCorrette) {
                        //Nel caso uno o nessun valore siano presenti in entrambe le ruote allora nessuna vincita
                        case 0 : 
                        case 1 : 
                            System.out.println("Nessuna vincita."); break;

                        case 2 : System.out.println("Vincita : ambo"); break;
                        case 3 : System.out.println("Vincita : terna");break;
                        case 4 : System.out.println("Vincita : quaterna");break;
                        case 5 : System.out.println("Vincita : cinquina");break;
                    }

                    //Attendere per dare il tempo all'utente di leggere
                    System.out.println("Premere INVIO per continuare."); 
                    keyboard.nextLine();

                    break;
                }

                case 4 : { //Visualizza giocate
                    //Impedire la visualizzazione delle giocate nel caso l'utente o la ruota non abbiano estratto
                    if(!estrazioneUtenteEseguita || !estrazioneRuotaEseguita)
                    {
                        System.out.println("Errore : Per la visualizzazione delle giocate sia utente che ruota devono aver estratto.");
                        System.out.println("Premere INVIO per continuare");
                        keyboard.nextLine();
                        break;
                    }

                    System.out.println("Visualizzazione delle giocate: ");

                    System.out.println("\nEstrazioni ruota di Venezia: ");
                    //Visualizzare il contenuto degli array delle estrazioni
                    for(int estrazioneCorrente = 0; estrazioneCorrente < estrazioni; estrazioneCorrente++)
                        System.out.println((estrazioneCorrente + 1) + ": " + estrazioniRuota[estrazioneCorrente]);

                    System.out.println("\nEstrazioni utente: ");
                    //Visualizzare il contenuto degli array delle estrazioni
                    for(int estrazioneCorrente = 0; estrazioneCorrente < estrazioni; estrazioneCorrente++)
                        System.out.println((estrazioneCorrente + 1) + ": " + estrazioniUtente[estrazioneCorrente]);

                    //Attendere per dare il tempo all'utente di leggere
                    System.out.println("\nPremere INVIO per continuare."); 
                    keyboard.nextLine();

                    break;
                }

                case 5 : { //Reimpostare il programma
                    //Reimpostare le variabili
                    estrazioneRuotaEseguita = false;
                    estrazioneUtenteEseguita = false;
                    //Svuotare gli array delle estrazioni
                    for(int iteratoreCancellazione = 0; iteratoreCancellazione < estrazioni; iteratoreCancellazione++) {
                        estrazioniRuota[iteratoreCancellazione] = 0;
                        estrazioniUtente[iteratoreCancellazione] = 0;
                    }

                    System.out.println("Gioco resettato.\nPremere INVIO per continuare.");
                    keyboard.nextLine();

                    break;
                }
            }
        } while(selezione != 6); //Se l'utente inserisce 5 allora uscire
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
        //Contiene delle variabili booleane utilizzate per sapere se i numeri sono gia' presenti nell'array
        boolean[] controlloOutput = new boolean[massimo - minimo];
        //Numero corrente generato
        int numeroCorrente;

        //Inizializzazione dell'array di variabili booleane a false
        for(int iteratoreInizializzazione = 0; iteratoreInizializzazione < controlloOutput.length; iteratoreInizializzazione++)
            controlloOutput[iteratoreInizializzazione] = false;

        //Iterazione generazione numeri casuali
        for(int iteratore = 0; iteratore < qta; iteratore++)
        {
            //Viene impostato a vero se il valore era presente nell'array precedentemente
            boolean ripetereGenerazione = false;

            //Ripetere l'estrazione nel caso il numero estratto sia presente nell'array
            do {
                numeroCorrente = generatore.nextInt(minimo, massimo + 1);

                //Se il valore si trova nell'array eseguire nuovamente il ciclo
                if(controlloOutput[numeroCorrente - minimo])
                    ripetereGenerazione = true;
                else {
                    /*Se il valore non e' presente reimpostare la variabile per eseguire nuovamente il ciclo while
                     * e salvare nell'array di booleane il valore presente in modo che non possa comparire nuovamente
                     */
                    ripetereGenerazione = false;
                    controlloOutput[numeroCorrente - minimo] = true;
                }
            //Continuare a ripetere fino a quando la variabile ripetereGenerazione diventa falsa
            } while(ripetereGenerazione);

            //Quando il numero generato non si trova nell'array allora salvarlo nell'array di uscita
            output[iteratore] = numeroCorrente;
        }

        //Ritornare l'output
        return output;
    }

    /*Dati due array di valori interi non ripetuti ritorna la quantita' di numeri che
     * si trovano in entrambi gli array.
     */
    private static int nPresentiInArray(int[] primoArray, int[] secondoArray) {
        //Conta gli elementi present in entrambi gli array
        int contatoreElementiPresenti = 0;
        //Viene impostata a vero quando il valore del primo array viene trovato nel secondo per saltare il resto della ricerca
        boolean valoreUgualeTrovato;

        //Iterare per ogni elemento del primo array alla ricerca di un elemento uguale nel secondo array
        for(int ricercaPrimoArray = 0; ricercaPrimoArray < primoArray.length; ricercaPrimoArray++) {
            //Impostato a vero quando un valore uguale viene trovato tra i due array
            valoreUgualeTrovato = false;

            //Iterare nel secondo array fino a quando un valore uguale tra i due non viene trovato o l'array si termina
            for(int ricercaSecondoArray = 0; ricercaSecondoArray < secondoArray.length && !valoreUgualeTrovato; ricercaSecondoArray++)
                //Controllare se il valore corrente del primo e del secondo array sono uguali
                if(primoArray[ricercaPrimoArray] == secondoArray[ricercaSecondoArray])
                { //Se i due valori sono uguali incrementare il contatore e ricercare un nuovo valore
                    contatoreElementiPresenti++;
                    valoreUgualeTrovato = true;
                }
        }

        return contatoreElementiPresenti;
    }
}