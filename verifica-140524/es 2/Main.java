import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        //Creare l'array che contiene i dati
        int[] userInput = new int[11]; //Max 11 elementi
        //Richiedere l'inserimento all'utente dei primi dieci valori
        for(int currentElement = 0; currentElement < userInput.length - 1; currentElement++)
        {
            //Impostata a vero quando il valore inserito dall'utente non è valido
            boolean invalidValue;
            //Nel caso un valore inserito non sia valido allora reinserirlo
            do {
                //Reimpostare la variabile di inserimento errato
                invalidValue = false;
                //Richiedere l'inserimento all'utente
                System.out.print("Inserire l'elemento " + currentElement + " :");
                try {
                    userInput[currentElement] = keyboard.nextInt();
                } catch(Exception e) {
                    System.err.println("Errore: il valore inserito deve essere numerico.");
                    invalidValue = true;
                }
                //Se il valore inserito è fuori range, allora non e' valido
    
                if(userInput[currentElement] < 1 || userInput[currentElement] > 30)
                {
                    System.out.println("Errore: il valore inserito deve essere compreso tra 1 e 30");
                    invalidValue = true;
                }
            } while(invalidValue);
        }
        //Richiedere l'inserimento dell'ultimo valore (opzionale)
        System.out.println("Inserire un valore non numerico per omettere l'ultimo valore");
        //Impostata a vero quando il valore inserito non e' valido
        boolean invalidValue;
        do {
            invalidValue = false;
            System.out.print("Inserire il valore " + (userInput.length - 1) + " :");
            try {
                userInput[userInput.length - 1] = keyboard.nextInt();
            } catch(Exception e) { //Nel caso un valore non venga inserito allora omettere quest'ultimo elemento dall'array
                //Creare un nuovo array con dimensione diminuita
                int[] newArray = new int[userInput.length - 1];
                //Copiare gli elementi nel nuovo array
                for(int copy = 0; copy < newArray.length; copy++){
                    newArray[copy] = userInput[copy];
                    //Impostare l'array a quello nuovamente creato con dimensione decrementata
                    userInput = newArray;
                }
            }
            //Se il valore inserito e' fuori range allora richiedere l'inserimento
            if(userInput[userInput.length - 1] < 1 || userInput[userInput.length - 1] > 30)
            {
                System.out.println("Errore: il valore inserito deve essere compreso tra 1 e 30");

                invalidValue = true;
            }
        } while(invalidValue);
    
        //Passare l'array al metodo che lo ordina
        sort(userInput);

        //Stampare tutti i valori
        System.out.println("Array ordinato :");
        for(int currentElement : userInput)
            System.out.println("\t" + currentElement);
    }
    
    public static void sort(int[] input) {
        //Contiene l'indice in cui deve essere sostituito l'elemento minore trovato nell'array
        int substituteIndex = -1;
        //Impostata a vero quando l'array e' ordinato
        boolean sorted = false;
        do {
            //Ricercare la nuova posizione dell'indice
            int newIndex = -1;
            //Iterazione dalla posizione dell'indice + 1 al penultimo elemento dell'array per la ricerca del prossimo elemento pari
            for(int newIndexSearch = substituteIndex + 1; newIndexSearch < input.length - 1; newIndexSearch++)
            {
                if(input[newIndexSearch] % 2 == 0) //Se viene trovato un elemento pari
                {
                    newIndex = newIndexSearch; //Salvare la posizione del nuovo indice
                    break; //Uscire dal ciclo
                }
            }
            //Se non e' stato trovato l'elemento, l'array e' ordinato
            if(newIndex == -1)
                sorted = true;
            else //Altrimenti salvare l'elemento trovato
                substituteIndex = newIndex;
    
            //Se l'array non è stato ordinato, procedere con la ricerca
            if(!sorted)
            {
                //Contiene il valore più piccolo nell'array disordinato e la sua posizione
                int minValue = input[substituteIndex];
                int minValuePosition = substituteIndex;
                //Ricerca del valore più piccolo pari, fermandosi al penultimo elemento dell'array
                for(int search = substituteIndex; search < input.length - 1; search++)
                {
                    //Se viene trovato un nuovo valore minimo pari
                    if(input[search] < minValue && input[search] % 2 == 0) {
                        minValue = input[search]; //Aggiornare il valore minimo
                        minValuePosition = search; //Aggiornare la posizione
                    }
                }
                //Ora sostituire il valore alla posizione dell'indice con il valore minimo trovato
                int swapTemp = input[substituteIndex];
                input[substituteIndex] = input[minValuePosition];
                input[minValuePosition] = swapTemp;
            }
        } while(!sorted); //Continuare il ciclo fino a quando l'array non e' ordinato
    }
}