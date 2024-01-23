import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner tastiera = new Scanner(System.in);

        String[] contenutoMenu = {
            "APP Gara dadi",
            "[1] : Sfida tra due giocatori",
            "[2] : Sfida contro il computer",
            "[3] : Uscita dal programma"
        };

        int selezione = menu(contenutoMenu, tastiera);

        System.out.println("Valore ritornato dal menu : " + selezione);

        tastiera.close();
    }

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

        return selezione;
    }

    private static void ClrScr(){
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void Wait(int ms){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }
}