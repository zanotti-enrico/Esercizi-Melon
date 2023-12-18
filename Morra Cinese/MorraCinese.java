import java.util.Scanner;

public class MorraCinese {
    public static void main(String[] args) {
        /*Creazione dell'oggetto per la lettura di input */
        Scanner keyboard = new Scanner(System.in);

        /*Costanti utilizzate per l'impostazione dei tasti */
        final char giocatore1Carta = 'A';
        final char giocatore1Sasso = 'S';
        final char giocatore1Forbice = 'D';
        final char giocatore2Carta = 'J';
        final char giocatore2Sasso = 'K';
        final char giocatore2Forbice = 'L';
        final char terminareGiocoTasto = 'X';
        /*Stringa stampata ogni cancellazione dello schermo */
        final String schermataPrincipale = "Morra Cinese\nGiocatore 1 : " + giocatore1Carta + " = carta; " + giocatore1Forbice + " = forbice; " + giocatore1Sasso + " = sasso\n" + "Giocatore 2 : " + giocatore2Carta + " = carta; " + giocatore2Forbice + " = forbice; " + giocatore2Sasso + " = sasso\n";
        /*Variaibli per la definizione di CARTA, FORBICE, SASSO e NON DEFINITO in forma numerica */
        final int nonDefinito = 0;
        final int sasso = 1;
        final int carta = 2;
        final int forbice = 3;

        /*Variabili utilizzate nel programma */
        boolean terminareGioco = false; //Viene impostata a vera quando il programma deve terminare la sua esecuzione
        char input; //Contiene il carattere inserito dall'utente
        /*Contiene il dato inserito dai due giocatori (carta, forbice, sasso o non definito) */
        int giocatore1 = nonDefinito;
        int giocatore2 = nonDefinito;

        //Ciclo continuo fino a richiesta di chiusura del programma
        while(!terminareGioco){
            //Riscrivere la schermata principale
            ClrScr();
            System.out.println(schermataPrincipale);

            //Leggere un singolo carattere di input dall'utente ignorando maiuscole/minuscole
            input = keyboard.nextLine().toUpperCase().charAt(0);

            //Controllo sull'input inserito dall'utente
            if(input == giocatore1Carta || input == giocatore1Forbice || input == giocatore1Sasso && giocatore1 == nonDefinito) {
                /*Riempimento della variabile giocatore1 in base al valore inserito su tastiera */
                if(input == giocatore1Carta)
                    giocatore1 = carta;
                else if(input == giocatore1Forbice)
                    giocatore1 = forbice;
                else if(input == giocatore1Sasso)
                    giocatore1 = sasso;
            } else if(input == giocatore2Carta || input == giocatore2Forbice || input == giocatore2Sasso && giocatore2 == nonDefinito) {
                /*Riempimento della variabile giocatore2 in base al valore inserito su tastiera */
                if(input == giocatore2Carta)
                    giocatore2 = carta;
                else if(input == giocatore2Forbice)
                    giocatore2 = forbice;
                else if(input == giocatore2Sasso)
                    giocatore2 = sasso;
            } else if(input == terminareGiocoTasto) {
                //Terminare il gioco in caso richiesto
                System.out.println("Terminazione del gioco.");
                terminareGioco = true;
            } else {
                //In caso un valore non valido venisse inserito terminare il ciclo
                System.out.println("Errore : Valore inserito non valido.");
                terminareGioco = true;
            }
            
            //Se entrambi i giocatori hanno inserito un numero
            if(giocatore1 != nonDefinito && giocatore2 != nonDefinito)
            {
                //Controllo sul vincitore
                if(giocatore1 == giocatore2)
                    System.out.println("Gioco Pari");
                else if(
                    giocatore1 == sasso && giocatore2 == carta ||
                    giocatore1 == carta && giocatore2 == forbice ||
                    giocatore1 == forbice && giocatore2 == sasso
                ) System.out.println("Giocatore 2 Vincente");
                else if(
                    giocatore2 == sasso && giocatore1 == carta ||
                    giocatore2 == carta && giocatore1 == forbice ||
                    giocatore2 == forbice && giocatore1 == sasso
                ) System.out.println("Giocatore 1 Vincente");

                /*Fermare l'esecuzione del programma fino a quando l'utente non preme INVIO
                 * Questo per mostrare i risultati all'utente prima di cancellare lo schermo
                 */
                System.out.println("Premere INVIO per continuare");
                String temporary = keyboard.nextLine();

                //Reset delle variabili per permettere il reinserimento dei dati
                giocatore1 = nonDefinito;
                giocatore2 = nonDefinito;
            }
        }

        //Chiudere l'oggetto per la lettura di input
        keyboard.close();
    }

    private static void ClrScr(){
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}