import java.util.Scanner;

public class ConversioneMese {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        //Richiedere all'utente il mese
        System.out.print("Inserire un mese, il programma restituisce il numero del mese:");
        String mese = keyboard.nextLine();

        //Mostrare l'output della funzione
        int numMese = intToStrMese(mese);
        System.out.println("Il numero del mese e' : " + numMese);

        //Chiudere lo scanner
        keyboard.close();
    }

    /*
     * Converte il mese nel numero corrispondente.
     * In caso di mese non valido ritorna -1;
     * ESEMPIO:
     * intToStrMese("Gennaio") = 1
     */
    private static int intToStrMese(String mese) {
        //Convertire il mese in tutte minuscole per ignorare le maiuscole/minuscole
        switch(mese.toLowerCase()) {
            case "gennaio" : return 1;
            case "febbraio" : return 2;
            case "marzo" : return 3;
            case "aprile" : return 4;
            case "maggio" : return 5;
            case "giugno" : return 6;
            case "luglio" : return 7;
            case "agosto" : return 8;
            case "settembre" : return 9;
            case "ottobre" : return 10;
            case "novembre" : return 11;
            case "dicembre" : return 12;
            //Nel caso il mese non esista
            default : return -1;
        }
    }
}