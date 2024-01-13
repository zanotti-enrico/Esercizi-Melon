import java.util.Random;

public class Statistica {
    public static void main(String[] args)
    {
        final int esecuzioniTotali = 100; //Contiene il numero di esecuzioni da compiere del tiro del dado

        int[] frequenze  = new int[6]; //Contiene le frequenze per le quali ogni faccia del dado viene scelta casualmente

        //Contiene il valore corrente ritornato dalla funzione random
        int valoreCorrente;
        for( int i = 0; i < esecuzioniTotali; i++) {
            //Genera un valore a random
            valoreCorrente = valoreRandom(0, 6);
            //Stampa la faccia dado relativa al valore generato
            System.out.println("Esecuzione numero " + i + "\nTiro Dado : \n" + facciaDado(valoreCorrente));
            /*valoreCorrente ha un range che varia da 1 a 6, mentre l'indice dell'array
             * va da 0 a 5, dobbiamo quindi sottrarre 1 prima di passare l'indice all'array
             */
            frequenze[valoreCorrente - 1]++;

            //Stampa le frequenze delle facce
            for(int j = 0; j < frequenze.length; j++)
                System.out.println("Faccia" + (j + 1) + "=" + frequenze[j]);

            //Separa l'output corrente da quello della prossima esecuzione del ciclo
            System.out.println("\n");
        }
    }

    /*Restituisce un valore casuale compreso tra
     * primo e secondo parametro passato
     */
    private static int valoreRandom(int minValue, int maxValue)
    {
        Random casuale = new Random();

        return casuale.nextInt(minValue,maxValue) + 1;
    }

    /*Ritorna una stringa contenente la faccia del dado relativa al valore passato.
     * Nel caso il valore passato non sia valido ritorna una stringa vuota
     */
    private static String facciaDado(int valoreFaccia)
    {
        String output = ""; //Contiene l'output da ritornare

        //Riempie la stringa output in base al parametro passato
        switch (valoreFaccia) {
            case 1 : {
                output += "\t\t\t\t ╔═════════╗\n";
                output += "\t\t\t\t ║         ║\n";
                output += "\t\t\t\t ║    O    ║\n";
                output += "\t\t\t\t ║         ║\n";
                output += "\t\t\t\t ╚═════════╝\n";
                break;
            }

            case 2 : {
                output += "\t\t\t\t ╔═════════╗\n";
                output += "\t\t\t\t ║  O      ║\n";
                output += "\t\t\t\t ║         ║\n";
                output += "\t\t\t\t ║      O  ║\n";
                output += "\t\t\t\t ╚═════════╝\n";
                break;
            }

            case 3 : {
                output += "\t\t\t\t ╔═════════╗\n";
                output += "\t\t\t\t ║  O      ║\n";
                output += "\t\t\t\t ║    O    ║\n";
                output += "\t\t\t\t ║      O  ║\n";
                output += "\t\t\t\t ╚═════════╝\n";
                break;
            }

            case 4 : {
                output += "\t\t\t\t ╔═════════╗\n";
                output += "\t\t\t\t ║  O   O  ║\n";
                output += "\t\t\t\t ║         ║\n";
                output += "\t\t\t\t ║  O   O  ║\n";
                output += "\t\t\t\t ╚═════════╝\n";
                break;
            }

            case 5 : {
                output += "\t\t\t\t ╔═════════╗\n";
                output += "\t\t\t\t ║  O   O  ║\n";
                output += "\t\t\t\t ║    O    ║\n";
                output += "\t\t\t\t ║  O   O  ║\n";
                output += "\t\t\t\t ╚═════════╝\n";
                break;
            }

            case 6 : {
                output += "\t\t\t\t ╔═════════╗\n";
                output += "\t\t\t\t ║  O   O  ║\n";
                output += "\t\t\t\t ║  O   O  ║\n";
                output += "\t\t\t\t ║  O   O  ║\n";
                output += "\t\t\t\t ╚═════════╝\n";
                break;
            }
        }

        /*Ritorna la stringa modificata.
         * Nel caso il valore passato per input non sia valido l'output sara' vuoto
         */
        return output;
    }
}
