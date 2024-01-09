import java.util.Scanner;

public class Secondi {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        /*Definizione variabili */
        int secondi; //Input variabile
        //Risultato:
        int oreRisultato;
        int minutiRisultato;
        int secondiRisultato;
        /*Variabili utilizzate per arrotondare il numero di secondi
        * totale all'ora o al minuto più vicino.*/
        int secondiArrotondatiOra;
        int secondiArrotondatiMinuto;

        /*Controllo su input utente*/
        System.out.print("Inserire il numero di secondi trascorsi da inizio giornata:");
        do {
            /*Input Utente */
            secondi = keyboard.nextInt();

            /*Controllo negativita'*/
            if(secondi <= 0)
                System.out.print("Errore : il numero di secondi non puo' essere negativo o nullo.\nReinserire il numero : ");
            /*Controllo che non superi la giornata*/
            else if(secondi >= 60 * 60 * 24)
                System.out.println("Errore : il numero di secondi inseriti non deve superare una giornata.\nReinserire il numero : ");
        } while(secondi <= 0 || secondi >= 60 * 60 * 24); //Il numero di secondi non deve essere negativo e non deve superare una giornata

        /*Calcolo Arrotondato */
        oreRisultato = secondi / 60 / 60;
        /*Sottrazione del numero di secondi arrotondato all'ora
        * al numero totale di secondi per il calcolo dei minuti*/
        secondiArrotondatiOra = oreRisultato * 60 * 60;
        /*Calcolo del numero di minuti passati dall'ora calcolata*/
        minutiRisultato = (secondi - secondiArrotondatiOra) / 60;
        /*Sottrazione del numero di secondi arrotondato al minuto
         * al numero totale di secondi per il calcolo dei secondi
         * passati da quel minuto*/
        secondiArrotondatiMinuto = minutiRisultato * 60;
        /*Calcolo dei secondi passati dal minuto calcolato*/
        secondiRisultato = secondi - secondiArrotondatiOra - secondiArrotondatiMinuto;

        //Stampa dei risultati
        System.out.println("Calcolo orario : \n" + oreRisultato + "(ore) : " + minutiRisultato + "(minuti) : " + secondiRisultato + "(secondi)");
    }
}
