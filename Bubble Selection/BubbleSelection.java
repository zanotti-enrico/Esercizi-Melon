import java.util.Scanner;

public class BubbleSelection {
    public static void main(String[] args) {
        //Istanziamento del lettore per i dati inseriti dall'utente
        Scanner userInput = new Scanner(System.in);

        //Richiesta dei dati dell'array da generare
        System.out.print("Quanti elementi si vogliono creare nell'array da ordinare?\n>");
        int elementAmount = Utility.getUserInput(userInput);

        //Richiesta del range dei dati
        System.out.print("Fino a che valore devono raggiungere i dati casuali creati nell'array?\n>");
        int maxBound = Utility.getUserInput(userInput);

        //Creazione dei due array da ordinare (uno la copia dell'altro)
        int[] generationOutputBubble = Utility.randomArray(elementAmount, maxBound);
        int[] generationOutputSelection = Utility.copyArray(generationOutputBubble);

        //Contiene il numero di operazioni eseguite durante l'ordinamento
        long operationAmount;
        //Ordinamento dell'array con bubble sort
        System.out.println("Ordinamento dell'array con bubble sort...");
        operationAmount = Sort.bubble(generationOutputBubble);
        System.out.println("Operazioni eseguite : " + operationAmount);
        //Ordinamento dell'array con selection sort
        System.out.println("Ordinamento dell'array con selection sort...");
        operationAmount = Sort.selection(generationOutputSelection);
        System.out.println("Operazioni eseguite : " + operationAmount);

        userInput.close();
    }
}