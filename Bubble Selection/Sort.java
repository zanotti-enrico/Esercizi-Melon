public class Sort {
    /*Il metodo ordina l'array passato utilizzando il bubble sort
     * Viene ordinato direttamente l'array passato e viene ritornato il numero di operazioni eseguite.
     */
    public static long bubble(int[] array) {
        //Salva il numero di operazioni
        long operationAmount = 0;

        //Eseguire l'ordinamento tante volte quanti sono gli elementi dell'array
        for(int operations = 0; operations < array.length; operations++)
        {
            //Ricercare in tutti gli elementi della parte dell'array non ordinato
            for(int search = 0; search < array.length - operations - 1; search++)
            {
                //Il controllo esegue due operazioni di lettura sull'array
                operationAmount += 2;
                //Se due elementi non sono ordinati, scambiarli
                if(array[search] > array[search + 1])
                {
                    //Incrementare il numero di operazioni
                    operationAmount += 4; //2 operazioni in quanto c'e' uno scambio tra due variabili nell'array (2 operazioni scrittura e 2 lettura)
                    //Scambiare i due elementi
                    int swap = array[search];
                    array[search] = array[search + 1];
                    array[search + 1] = swap;
                }
            }
        }

        return operationAmount;
    }

    /*Il metodo ordina l'array passato utilizzando il selection sort.
     * Viene ritornato il numero di operazioni eseguite sull'array.
     */
    public static long selection(int[] array) {
        //Salva il numero di operazioni eseguite sull'array da ritornare
        long operationAmount = 0;

        //Eseguire tante iterazioni quanti sono gli elementi dell'array
        for(int currentIteration = 0; currentIteration < array.length; currentIteration++)
        {
            //Contiene il valore massimo trovato nell'array e la sua posizione
            int max = array[0];
            int maxPosition = 0;
            operationAmount++;
            //Ricercare il numero maggiore nell'array non ordinato
            for(int currentElement = 0; currentElement < array.length - currentIteration; currentElement++)
            {
                //L'if esegue un'operazione di lettura sull'array
                operationAmount++;
                //Se viene trovato un nuovo valore massimo, salvarlo
                if(array[currentElement] > max)
                {
                    operationAmount++; //Eseguita una nuova operazione di lettura
                    max = array[currentElement];
                    maxPosition = currentElement;
                }
            }

            //Incrementare il numero di operazioni
            operationAmount += 4; //2 operazioni in quanto c'e' uno scambio tra due variabili nell'array (2 operazioni scrittura e 2 lettura)
            //Scambiare il valore massimo torvato al termine dell'array
            int swap = array[maxPosition];
            array[maxPosition] = array[array.length - currentIteration - 1];
            array[array.length - currentIteration - 1] = swap;
        }

        return operationAmount;
    }
}
