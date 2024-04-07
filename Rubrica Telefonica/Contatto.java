import java.util.Scanner;

//Enumeratore contenente i tipi di numero di telefono
enum tipologiaTelefono {
    ABITAZIONE,
    AZIENDALE,
    CELLULARE
};

public class Contatto {
    public String nome; //Nome della persona
    public String cognome; //Cognome della persona
    public String numeroTelefono; //Numero di telefono
    tipologiaTelefono utilizzo; //Tipo del numero di telefono

    /*Ritorna una stringa contenente i dati anagrafici dell'utente*/
    public String anagrafica() {
        return String.format("Nome : %s\nCognome : %s\nNumero di telefono : '%s'\nUtilizzo telefono : %s", nome, cognome, numeroTelefono, utilizzo.toString());
    }

    /*Permette l'inserimento dei dati anagrafici */
    public void inserimentoAnagrafica(Scanner userInput) {
        //Inserimento nome
        System.out.print("Nome : ");
        nome = userInput.next();

        //Inserimento cognome
        System.out.print("Cognome : ");
        cognome = userInput.next();

        //Inserimento numero di telefono
        System.out.print("Numero di telefono : ");
        numeroTelefono = userInput.next();

        //Inserimento utilizzo del numero di telefono
        //Contenuto del menu
        String[] tipologieTelefono = {
            "Inserire una tipologia di numero di telefono",
            "ABITAZIONE",
            "AZIENDALE",
            "CELLULARE"
        };
        //Chiamare il menu
        int userSelection = Tools.menu(tipologieTelefono, userInput);
        //In base all'inserimento dell'utente, impostare la tipologia di numero di telefono
        switch(userSelection) {
            case 1 : utilizzo = tipologiaTelefono.ABITAZIONE;break;
            case 2 : utilizzo = tipologiaTelefono.AZIENDALE;break;
            case 3 : utilizzo = tipologiaTelefono.CELLULARE;break;
        }
    }

    /*Controlla se un valore e' uguale ad un altro */
    public boolean equals(Contatto obj) {
        //Il controllo viene fatto tramite nome e cognome
        return (this.nome.equals(obj.nome) && this.cognome.equals(obj.cognome));
    }
}
