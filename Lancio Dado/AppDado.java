import java.util.Scanner;
import java.util.Random;

public class AppDado {
    public static void main(String[] args) {
        //Istanziamento dello scanner
        Scanner keyboard = new Scanner(System.in);

        //Contiene il testo da mostrare per il menu
        final String[] contenutoMenu = {
            "APP Lancio Dadi", // Titolo
            "[1] Giocatore contro giocatore", //Contenuto
            "[2] Giocatore contro computer",
            "[3] Uscire dal programma"
        };
        //Salva la selezione dell'utente nel menu
        int selezioneUtente;
        //Variabili associate ai risultati di entrambi i giocatori
        int giocatore1;
        int giocatore2; //COMPUTER se viene selezionato giocatore contro computer

        //Ripetere fino a richiesta di fine programma
        do {
            //Visualizzare il menu salvando la selezione
            selezioneUtente = menu(contenutoMenu, keyboard);

            if(selezioneUtente == 1)
                System.out.print("Giocatore 1 - ");
            System.out.println("Premere INVIO per lanciare il dado.");

            //Attendere INVIO da parte dell'utente
            keyboard.nextLine();
            //Salvare il risultato della rotazione del dado
            giocatore1 = RotazioneDado(); //giocatore1 sarebbe il giocatore

            if(selezioneUtente == 1) //Se giocatore2 non e' il computer attendere un'altro INVIO dell'utente
            {
                System.out.println("Giocatore 2 - Premere INVIO per lanciare il dado");
                keyboard.nextLine();
            } else {
                System.out.println("Gioco computer :");
            }
            //Effettuare una nuova rotazione salvando il risultato
            giocatore2 = RotazioneDado();

            //Stampare vincitore o parita'
            if(giocatore1 == giocatore2)
                System.out.println("Pari");
            else 
                if(giocatore1 > giocatore2)
                    System.out.println("Vince giocatore 1");
                else
                    System.out.println("Vince giocatore 2");
        //Ripetere fino a richiesta di fine programma
        } while(selezioneUtente != 3);

        keyboard.close();
    }
    
    /*Il metodo stampa su schermo la faccia del dado relativa al parametro passato
     * se viene passato un valore fuori dal range 1-6 non stampa nulla
     */
    private static void FacceDado(int valoreFaccia) {
        //ALT + 201 ╔
        //ALT + 188 ╝
        //ALT + 187 ╗
        //ALT + 200 ╚
        //ALT + 205 ═
        //ALT + 186 ║

        switch (valoreFaccia) {
            case 1 : {
                System.out.println("\t\t\t\t ╔═════════╗"); 
                System.out.println("\t\t\t\t ║         ║");
                System.out.println("\t\t\t\t ║    O    ║");
                System.out.println("\t\t\t\t ║         ║");
                System.out.println("\t\t\t\t ╚═════════╝");
                break;
            }

            case 2 : {
                System.out.println("\t\t\t\t ╔═════════╗");
                System.out.println("\t\t\t\t ║  O      ║");
                System.out.println("\t\t\t\t ║         ║");
                System.out.println("\t\t\t\t ║      O  ║");
                System.out.println("\t\t\t\t ╚═════════╝");
                break;
            }
            
            case 3 : {
                System.out.println("\t\t\t\t ╔═════════╗");
                System.out.println("\t\t\t\t ║  O      ║");
                System.out.println("\t\t\t\t ║    O    ║");
                System.out.println("\t\t\t\t ║      O  ║");
                System.out.println("\t\t\t\t ╚═════════╝");
                break;
            }
            
            case 4 : {
                System.out.println("\t\t\t\t ╔═════════╗");
                System.out.println("\t\t\t\t ║  O   O  ║");
                System.out.println("\t\t\t\t ║         ║");
                System.out.println("\t\t\t\t ║  O   O  ║");
                System.out.println("\t\t\t\t ╚═════════╝");
                break;
            }
            
            case 5 : {
                System.out.println("\t\t\t\t ╔═════════╗");
                System.out.println("\t\t\t\t ║  O   O  ║");
                System.out.println("\t\t\t\t ║    O    ║");
                System.out.println("\t\t\t\t ║  O   O  ║");
                System.out.println("\t\t\t\t ╚═════════╝");
                break;
            }
            
            case 6 : {
                System.out.println("\t\t\t\t ╔═════════╗");
                System.out.println("\t\t\t\t ║  O   O  ║");
                System.out.println("\t\t\t\t ║  O   O  ║");
                System.out.println("\t\t\t\t ║  O   O  ║");
                System.out.println("\t\t\t\t ╚═════════╝");
                break;
            }
        }
    }
    
    /*Esegue una rotazione del dado stampando le facce su schermo
     * e ritorna il numero casuale riportato
     */
    private static int RotazioneDado() {
        //Tempo da attendere durante la rotazione del dado
        final int rotazioneDadoTempo = 200; //millisecondi
        //Tempo da attendere per mostrare il risultato
        final int risultatoTempo = 2000; //millisecondi

        //Istanziamento di un generatore casuale
        Random faccia = new Random();
        //Inizializzazione della variabile di ritorno
        int output = 0;

        /*Il ciclo si esegue da 1 a 6 stampando le facce del dado,
         * inizialmente mostra tutte e 6 le facce per dare il senso di rotazione
         * poi stampa la faccia ritornata dalla funzione random per un tempo prolungato
         */
        for(int i = 1; i <= 7; i++){
            if (i == 7) {
                Wait(rotazioneDadoTempo);
                ClrScr(); 
                System.out.println("E' uscito : ");
                output = faccia.nextInt(6) + 1;
            } else {
                Wait(rotazioneDadoTempo);
                ClrScr(); 
                output = i;
            }

            FacceDado(output);
        }
        
        //Stampare la faccia per tempo prolungato e ritornare il valore casuale generato
        Wait(risultatoTempo);
        ClrScr();

        return output;
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
}