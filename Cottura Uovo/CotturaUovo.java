import java.util.Scanner;

public class CotturaUovo {
    public static void main(String[] args) {
        /*Istanziamento dell'oggetto keyboard */
        Scanner keyboard = new Scanner(System.in);

        //Minuti necessari per la cottura
        final int minutiCottura = 3;
        //Utilizzato per scegliere l'animazione dell'uovo
        byte animazione = 0;
        
        ClrScr();
        PrintRawEgg();
        System.out.println("Premere INVIO per inziare la cottura (" + minutiCottura + " minuti)");
        String temporary = keyboard.nextLine();

        /*Iterazione dell'animazione di cottura fino al termine dei 3 minuti */
        for(long secondiTrascorsi = 0; secondiTrascorsi < minutiCottura * 60; secondiTrascorsi++){
            /*Eseguire la cancellazione dello schermo */
            ClrScr();

            /*Eseguire l'animazione utilizzando la variabile animazione scambiando tra le due immaigni dell'uovo in cottura */
            if(animazione == 0){
                PrintBoilingEgg1();
                animazione = 1;
            }
            else if(animazione == 1){
                PrintBoilingEgg2();
                animazione = 0;
            }

            /*Stampa del messaggio di cottura in corso */
            System.out.println("Cottura in corso - " + ((minutiCottura * 60) - secondiTrascorsi) + " secondi rimanenti...");

            //Stop del programma per 1 secondo
            sleep();
        }

        /*Stampa del messaggio di termine e dell'uovo cotto */
        ClrScr();
        PrintCookedEgg();
        System.out.println("L'uovo ha terminato di cuocersi, la frittata e' pronta!");
    }

    /*Metodo per la stampa dell'uovo crudo */
    private static void PrintRawEgg() {
        /*Stampa di un uovo crudo in forma ASCII su schermo */
        String uovoCrudo = "     ,'\"`.\n    /     \\\n   :       :\n   :       :\n    `.___,'\n";
        System.out.println(uovoCrudo);
    }
    
    /*Metodo per la stampa dell'uovo che sta bollendo */
    private static void PrintBoilingEgg1() {
        /*Stampa di un uovo che bolle (animazione 1) in forma ASCII su schermo */
        String uovoBollente = "   ~   ~    ~~ \n   |   |     |\n   ||   |   |\n     ,'\"`.  ||\n    /     \\\n   :       :\n   :       :\n    `.___,'\n";
        System.out.println(uovoBollente);
    }

    
    /*Metodo per la stampa dell'uovo che sta bollendo */
    private static void PrintBoilingEgg2() {
        /*Stampa di un uovo che bolle (animazione 2) in forma ASCII su schermo */
        String uovoBollente = "  ~    ~      ~ \n   |   |     |\n   ||   |   ||\n     ,'\"`.  |\n  | /     \\\n   :       :\n   :       :\n    `.___,'\n";
        System.out.println(uovoBollente);
    }
    
    /*Metodo per la stampa dell'uovo cotto */
    private static void PrintCookedEgg() {
        /*Stampa di un uovo cotto in forma ASCII su schermo */
        String frittata = "        . -- ~~~ -- .\n    .-~               ~-.\n   /                     \\\\\n  /      **               \\\n |      *  *               \\\n |       **                 |\n |           ***           |\n  \\         *   *         /\n   \\        *   *        /\n    `-.      ***      .-\n        ~- . ___ . -~\n";
        System.out.println(frittata);
    }

    /*Used for clearing screen data */
    private static void ClrScr(){
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*Used for waiting */
    private static void sleep() {
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}