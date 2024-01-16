/*Estrarre 5 numeri nell'intervallo da 1 a 90
 * Realizzare un programma che permetta di estrarre questi numeri in modo casuale.
 * Le ruote sono 11, vogliamo un metodo che ritorni un array di valori per una delle ruote
 */

import java.util.Random;

public class Ruota {
    public static void main(String[] args) {
        final int estrazioniInizio = 1; //Estrazioni bound minimo
        final int estrazioniFine = 90; //Estrazioni bound massimo
        //Quantita' di estrazioni
        final int qtaEstrazioni = 5;
        //Contiene le estrazioni eseguite
        int[] estrazioni = new int[qtaEstrazioni];

        //Esecuzione delle estrazioni
        for (int estrazione = 0; estrazione < qtaEstrazioni; estrazione++) {
            //Salvare l'estrazione nell'array
            estrazioni[estrazione] = valoreCasuale(estrazioniInizio, estrazioniFine);
        }

        //Stampa delle estrazioni
        System.out.println("Estrazioni Completate\nRisultati :");
        for (int i = 0; i < qtaEstrazioni; i++)
            System.out.println("Estrazione numero " + (i + 1) + " = " + estrazioni[i]);
    }

    /*Ritorna un numero casuale compreso tra il minimo ed il massimo
     * passati come parametri
     */
    private static int valoreCasuale(int minimo, int massimo) {
        Random generatore = new Random();
        
        return generatore.nextInt(minimo, massimo + 1);
    }
}