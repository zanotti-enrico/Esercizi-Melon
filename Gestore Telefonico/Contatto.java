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
        this.inserimentoTipologiaTelefono("Inserire una tipologia di numero di telefono", userInput);
    }

    /*Permette l'inserimento del singolo dato della tipologia del numero di telefono */
    public void inserimentoTipologiaTelefono(String ask, Scanner userInput) {
        //Inserimento utilizzo del numero di telefono
        //Costruzione dell'array contenente gli inserimenti del menu
        String[] tipologieTelefono = new String[1 + tipologiaTelefono.values().length];
        //Mettere il titolo inizialmente
        tipologieTelefono[0] = ask;
        //Dopo il titolo, inserire le opzioni contenute nell'enumeratore
        for(int option = 0; option < tipologiaTelefono.values().length; option++)
            //Inserire il valore corrente nell'enumeratore (convertito a stringa)
            tipologieTelefono[option + 1] = tipologiaTelefono.values()[option].toString();

        //Chiamare il menu
        int userSelection = Tools.menu(tipologieTelefono, userInput);
        //In base all'inserimento dell'utente, impostare la tipologia di numero di telefono
        this.utilizzo = tipologiaTelefono.values()[userSelection - 1];
    }

    /*Controlla se un valore e' uguale ad un altro */
    public boolean equals(Contatto obj) {
        //Il controllo viene fatto tramite nome e cognome
        return (this.nome.equals(obj.nome) && this.cognome.equals(obj.cognome));
    }
}
