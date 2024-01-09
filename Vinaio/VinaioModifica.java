import java.util.Scanner;

public class VinaioModifica {
    public static void main(String[] args) {
        /*Oggetto per input da tastiera*/
        Scanner keyboard = new Scanner(System.in);

        /*Costanti utilizzate nel programma*/
        final int iva = 21;
        final double capacita = 1.5;
        final int qtaVini = 3;
        final double[] costo = { //Costo al litro
            1.5, //Barbera
            2.0, //Lugana
            1.0  //Merlot
        };
        final String[] nome = { //Nomi dei tipi di vini
            "Barbera",
            "Lugana",
            "Merlot"
        };

        /*Variabili per la gestione di multipli clienti*/
        boolean chiusuraCassa = false;
        int qtaClienti = 0;
        double vinoVenduto = 0;
        double qtaSpesa = 0;
        int tipoVino;

        /*Notifica dell'utente sulle variabili impostate per ogni vino disponibile*/
        System.out.println(
            "Vinaio\nCapacita' bottiglioni : " + capacita + "L\n" +
            "IVA Applicata : " + iva + "%\n"
        );
        for(int vino = 0; vino < qtaVini; vino++)
            System.out.println("Costo/Litro Vino " + nome[vino] + " : " + costo[vino]);

        /*Ciclo continuo fino a chiusura della cassa*/
        while(!chiusuraCassa) {
            /*Incrementare la variabile che determina la quantita' dei clienti*/
            qtaClienti++;

            /*Richiesta del tipo di vino da comprare con controllo sul valore */
            System.out.println("\nCliente numero " + qtaClienti + ":\nSpecificare il tipo di vino da acquistare");
            System.out.print(
                "0 - " + nome[0] + "\n" +
                "1 - " + nome[1] + "\n" +
                "2 - " + nome[2] + "\n" + ":"
            );
            do {
                tipoVino = keyboard.nextInt();

                //Stampa messaggio di errore in caso il valore non sia valido
                if(tipoVino < 0 || tipoVino > 2)
                    System.out.println("Errore il valore inserito deve essere compreso tra 0 e 2");
            } while (tipoVino < 0 || tipoVino > 2);
            
            /*Richiesta del numero di bottiglioni da comprare con controllo sulla positivita'*/
            System.out.println("Specificare la quantita' di bottiglioni da acquistare : ");
            int nBottiglioni;
            do {
                nBottiglioni = keyboard.nextInt();
                /*Stampa di un messaggio di errore nel caso l'utente inserisca un valore non valido*/
                if(nBottiglioni <= 0)
                    System.out.println("Errore : Il numero di bottiglioni non puo' essere negativo o nullo");
            } while(nBottiglioni <= 0);

            /*Calcolo del prezzo, dell'IVA e del prezzo compreso di IVA*/
            double prezzoVendita, calcoloIVA, prezzoTotale;
            prezzoVendita = capacita * costo[tipoVino] * nBottiglioni;
            calcoloIVA = prezzoVendita / (double)iva;
            prezzoTotale = prezzoVendita + calcoloIVA;

            /*Incremento della quantita' totale spesa e della quantita' totale di vino venduto*/
            vinoVenduto += nBottiglioni * capacita;
            qtaSpesa += prezzoTotale;

            /*Stampa dei risultati su schermo*/
            System.out.printf(
                "Scontrino Acquisti - Vino %s : " +
                "\n\tNumero di bottiglioni acquistati : %d" +
                "\n\tPrezzo di vendita : Euro %.2f" +
                "\n\tIVA (%d%%) : Euro %.2f" + 
                "\n\tPrezzo totale : Euro %.2f\n",
                nome[tipoVino], nBottiglioni, prezzoVendita, iva, calcoloIVA, prezzoTotale
            );

            /*Richiesta di chiusura della cassa*/
            System.out.println("Chiudere la cassa? [S]");
            keyboard.nextLine(); //Svuotare il buffer di input (l'ultima richiesta di input prevedeva un numero)
            String userInput = keyboard.nextLine().toUpperCase();
            /*Controllo sull'input dell'utente*/
            if(userInput.equals("S"))
                chiusuraCassa = true; //Terminare il ciclo
        }

        /*Output del resoconto della giornata */
        System.out.printf(
            "\nResoconto : " +
            "\n\tQuantita' di clienti : %d" +
            "\n\tQuantita' totale di vino venduto : %.1fL" +
            "\n\tPrezzo totale speso : Euro %.2f\n",
            qtaClienti, vinoVenduto, qtaSpesa
        );
    }
}