import java.util.Scanner;

public class Vinaio {
    public static void main(String[] args) {
        //Oggetto per input da tastiera
        Scanner keyboard = new Scanner(System.in);

        //Costanti utilizzate nel programma
        final int iva = 21;
        final double capacita = 1.5F;
        final double costo = 1.0F;

        //Notifica dell'utente sulle variabili impostate
        System.out.println(
            "Vinaio Barbera\nCapacita' singolo bottiglione : " + capacita + 
            "L\nCosto/Litro : Euro " + costo + 
            "\nIVA applicata : " + iva + "%"
        );

        //Richiesta del numero di bottiglioni da comprare con controllo sulla positivita'
        System.out.print("Specificare la quantita' di bottiglioni da acquistare : ");
        int nBottiglioni;
        do {
            nBottiglioni = keyboard.nextInt();
            //Stampa di un messaggio di errore nel caso l'utente inserisca un valore non valido
            if(nBottiglioni <= 0)
                System.out.println("Errore : Il numero di bottiglioni non puo' essere negativo o nullo");
        } while(nBottiglioni <= 0);

        //Calcolo del prezzo, dell'IVA e del prezzo compreso di IVA
        double prezzoVendita, calcoloIVA, prezzoTotale;
        prezzoVendita = capacita * costo * nBottiglioni;
        calcoloIVA = prezzoVendita / (double)iva;
        prezzoTotale = prezzoVendita + calcoloIVA;

        //Stampa dei calcoli su schermo
        System.out.println(
            "Scontrino Acquisti : \n\tNumero bottiglioni acquistati : " + nBottiglioni +
            "\n\tPrezzo di vendita : Euro " + prezzoVendita + 
            "\n\tIVA (" + iva + "%) : Euro " + calcoloIVA +
            "\n\tPrezzo totale : Euro " + prezzoTotale
        );
    }
}