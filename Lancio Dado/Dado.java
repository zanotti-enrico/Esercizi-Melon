import java.util.Scanner;
import java.util.Random;

public class Dado {
    public static void main(String[] args) {
        //Istanziamento dello scanner
        Scanner keyboard = new Scanner(System.in);

        //Rotazione del dado per giocatore 1
        System.out.println("Il giocatore 1 prema INVIO per lanciare il dado");
        keyboard.nextLine();
        int giocatore1 = RotazioneDado();
        
        //Rotazione del dado per giocatore 2
        System.out.println("Il giocatore 2 prema INVIO per lanciare il dado");
        keyboard.nextLine();
        int giocatore2 = RotazioneDado();

        //Controllo sul vincitore o parita'
        if(giocatore1 == giocatore2)
            System.out.println("Pari");
        else if(giocatore1 > giocatore2)
            System.out.println("Ha vinto giocatore 1");
        else if(giocatore1 < giocatore2)
            System.out.println("Ha vinto giocatore 2");

        //Chiusura dello scanner
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
                Wait();
                ClrScr(); 
                System.out.println("E' uscito : ");
                output = faccia.nextInt(6) + 1;
            } else {
                Wait();
                ClrScr(); 
                output = i;
            }

            FacceDado(output);
        }
        
        //Stampare la faccia per tempo prolungato e ritornare il valore casuale generato
        WaitLong();
        ClrScr();

        return output;
    }

    /*Cancella lo schermo */
    private static void ClrScr(){
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /*Attende 0,2 secondi durante la rotazione del dado */
    private static void Wait() {
        try{
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    /*Attesa di 1 secondo per tempo prolungato */
    private static void WaitLong() {
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
