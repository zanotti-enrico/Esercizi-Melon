/*Estrarre 5 numeri nell'intervallo da 1 a 90
 * Realizzare un programma che permetta di estrarre questi numeri in modo casuale.
 * Le ruote sono 11, vogliamo un metodo che ritorni un array di valori per una delle ruote
 */

import java.util.Random;

public class Ruota {
    public static void main(String[] args) {
        final int estrazioniInizio = 1; //Inizio range estrazioni
        final int estrazioniFine = 90; //Fine range estrazioni
        //Quantita' di estrazioni
        final int qtaEstrazioni = 5;
        //Quantita' di ruote da estrarre
        final int qtaRuote = 11;

        //Iterare le estrazioni per ognuna delle ruote e stampare il risultato
        for(int ruotaCorrente = 1; ruotaCorrente <= qtaRuote; ruotaCorrente++) {
            //Estrarre i numeri e salvarli in un array
            int[] estrazioni = estrarreNumeri(qtaEstrazioni, estrazioniInizio, estrazioniFine);   

            //Stampare in output il numero della ruota e il risultato delle estrazioni
            System.out.println("Ruota estrazioni numero " + ruotaCorrente + " : ");
            System.out.println("\nEsito Estrazioni : ");
            for(int i = 0; i < estrazioni.length; i++)
                System.out.println("\t" + i + " : " + estrazioni[i]);
            System.out.println("\n");
        }
    }

    /*Ritorna un array di valori corrispondenti alle estrazioni
     * La lunghezza dell'array viene specificata dall'argomento assieme al range dei numeri da estrarre
     * Le estrazioni vengono eseguite con un controllo per evitare ripetizioni di numeri
     */
    private static int[] estrarreNumeri(int nValori, int valoreMinimo, int valoreMassimo) {
        //Viene ritornato dalla funzione
        int[] outputEstrazione = new int[nValori];

        //Riempire tutti i valori dell'array con controllo per evitare ripetizioni di valori uguali
        int valoreCasualeCorrente;
        for(int nEstrazione = 0; nEstrazione < nValori; nEstrazione++) {
            //Viene impostata a vero quando si necessita della ripetizione dell'estrazione
            boolean ripetereEstrazione = false;
            //Ciclo while che ripete l'estrazione nel caso il numero estratto sia gia' uscito
            do {
                //Genera un numero casuale
                valoreCasualeCorrente = valoreCasuale(valoreMinimo, valoreMassimo);

                //Controlla se il numero e' presente nell'array controllando soltanto la sua parte popolata, nel caso lo sia ripetere l'estrazione
                if(nContenutoInArray(valoreCasualeCorrente, outputEstrazione, nEstrazione))
                    ripetereEstrazione = true;
                else //Resettare la variabile ripetereEstrazione se era precedentemente vera
                    ripetereEstrazione = false;
            } while(ripetereEstrazione);
            
            //Salvare l'esito dell'estrazione nell'array di output
            outputEstrazione[nEstrazione] = valoreCasualeCorrente;
        }

        //Ora che abbiamo popolato l'array con le estrazioni esso puo' essere ritornato
        return outputEstrazione;
    }

    /*Ritorna un numero casuale compreso tra il minimo ed il massimo
     * passati come parametri
     */
    private static int valoreCasuale(int minimo, int massimo) {
        Random generatore = new Random();
        
        return generatore.nextInt(minimo, massimo + 1);
    }

    /*Controlla se un numero si trova all'interno di un array
     * la funzione cerca la presenza del numero nell'array fino al raggiungimento dell'indice
     * indicato nell'argomento
     */
    private static boolean nContenutoInArray(int input, int[] ricerca, int fineRicerca) {
        //Si presuppone che il numero non sia contenuto nell'array
        boolean nContenuto = false;

        //Iterare dall'inizio dell'array al termine della ricerca specificato
        for(int iteratoreRicerca = 0; iteratoreRicerca < fineRicerca && !nContenuto; iteratoreRicerca++)
            //Se si trova il numero nell'array uscire dal loop e terminare la funzione
            if(ricerca[iteratoreRicerca] == input)
                nContenuto = true;

        return nContenuto;
    }
}