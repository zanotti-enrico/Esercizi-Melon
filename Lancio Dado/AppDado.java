import java.util.Scanner;
import java.util.Random;

public class AppDado {
    public static void main(String[] args) {
        //Istanziamento dello scanner
        Scanner keyboard = new Scanner(System.in);

        //Contiene il testo da mostrare per il menu
        final String testoMenu = "=== APP Gara Dadi ===\n[1]: Sfida tra due giocatori\n[2]: Sfida contro il computer\n[3]: Fine\n";
        //Viene impostata a vero all'uscita dal programma
        boolean uscitaProgramma = false;

        do {
            //Stampa del menu
            System.out.println(testoMenu);

            System.out.print("Digitare la propria scelta : ");
            //Richiesta di input dall'utente con controllo
            switch(keyboard.nextInt()) { //Controllo sull'input inserito dall'utente
                case 1 : 
                    keyboard.nextLine(); //Svuotare l'input dopo la lettura del numero
                    giocatoreVSGiocatore(keyboard); 
                    break;
                case 2 : 
                    keyboard.nextLine(); //Svuotare l'input dopo la lettura del numero
                    giocatoreVSComputer(keyboard); 
                    break;
                case 3 : 
                    uscitaProgramma = true; 
                    System.out.println("Uscita dal programma...");    
                    break; //Uscire dal programma
                
                //Stampare messaggio di errore in caso di input non valido
                default : System.out.println("Errore : il valore deve essere compreso nella lista.");
            }

            //Cancellare lo schermo
            ClrScr();
        //Ripetere fino a richiesta di fine programma
        } while(!uscitaProgramma);

        keyboard.close();
    }

    /*Il metodo accetta come parametro lo scanner in modo da riuscire a
     * leggere l'input dall'utente
     */
    private static void giocatoreVSGiocatore(Scanner keyboard) {
        //Contiene il valore lanciato dai giocatori
        int giocatore1;
        int giocatore2;

        //Stampare il messaggio
        ClrScr();
        System.out.println("Giocatore 1 - Premere INVIO Per lanciare il dado.");

        //Attendere l'invio dell'utente
        keyboard.nextLine();
        //Ruotare il dado e salvare il risultato
        giocatore1 = RotazioneDado();

        //Stampare il messaggio
        System.out.println("Giocatore 2 - Premere INVIO Per lanciare il dado.");
        
        //Attendere l'invio dell'utente
        keyboard.nextLine();
        //Ruotare il dado
        giocatore2 = RotazioneDado();

        if(giocatore1 == giocatore2)
            System.out.println("Dadi pari.");
        else
            if(giocatore1 > giocatore2)
                System.out.println("Ha vinto giocatore 1");
            else
                System.out.println("Ha vinto giocatore 2");
    }

    private static void giocatoreVSComputer(Scanner keyboard) {
        int giocatore;
        int computer;

        //Stampare il messaggio
        System.out.println("Premere INVIO Per lanciare il dado.");
        //Attendere l'invio dell'utente
        keyboard.nextLine();

        //Ruotare il dado per il giocatore
        giocatore = RotazioneDado();

        System.out.println("Gioco computer : ");
        //Attendere un secondo prima di tirare
        Wait(1000);

        //Ruotare il dado per il computer
        computer = RotazioneDado();

        if(giocatore == computer)
            System.out.println("Dadi pari.");
        else
            if(giocatore > computer)
                System.out.println("Ha vinto il giocatore");
            else
                System.out.println("Ha vinto il computer");
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
        /*
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
    }
}