import java.util.Scanner;

//Enumeratore contenente i tipi di numero di telefono
enum tipologiaTelefono {
    ABITAZIONE,
    AZIENDALE,
    CELLULARE
};

public class Contatto {
    protected String nome; //Nome della persona
    protected String cognome; //Cognome della persona
    protected String numeroTelefono; //Numero di telefono
    protected tipologiaTelefono utilizzo; //Tipo del numero di telefono
    protected double saldo = 0; //Saldo telefonico del contatto

    //Metodi per la modifica e l'accesso ai parametri del contatto
    public String getNome() {
        return this.nome;
    }
    public String getCognome() {
        return this.cognome;
    }
    public String getNumeroTelefono() {
        return this.numeroTelefono;
    }
    public tipologiaTelefono getUtilizzo() {
        return this.utilizzo;
    }
    public double getSaldo() {
        return this.saldo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }
    public void setUtilizzo(tipologiaTelefono utilizzo) {
        this.utilizzo = utilizzo;
    }

    //Permette di incrementare il saldo dell'utente
    public void ricaricaSaldo(double quantita) {
        //Incrementare il saldo della quantita' richiesta
        this.saldo += quantita;
    }
    /*Decrementa il saldo del contatto.
     * Se il valore da decrementare e' maggiore del saldo rimasto allora il saldo rimane nullo
     * Il metodo ritorna la spesa effettuata veramente tenendo conto del salto rimasto precedentemente
     */
    public double effettuaSpesa(double spesa) {
        //Se la spesa non eccede il saldo rimasto allora proseguire con il decremento
        if(spesa < this.saldo)
        {
            this.saldo -= spesa;
            //Ritornare il valore totale della spesa
            return spesa;
        }
        //Altrimenti :
        //Salvare il saldo rimasto
        double saldoRimasto = this.saldo;
        //Il saldo e' nullo
        this.saldo = 0;
        //Ritornare il saldo rimasto
        return saldoRimasto;
    }

    /*Permette la creazione di un contatto vuoto */
    public Contatto () {}
    /*Permette la creazione di un nuovo contatto impostando nome e cognome */
    public Contatto (String _nome, String _cognome)
    {
        this.nome = _nome;
        this.cognome = _cognome;
    }

    @Override
    public String toString() {
        return String.format("Nome : %s\nCognome : %s\nNumero di telefono : '%s'\nUtilizzo telefono : %s", nome, cognome, numeroTelefono, utilizzo.toString());
    }

    /*Ritorna una stringa appropriata per la scrittura del contatto su un file .csv */
    public String toCSVString() {
        return String.format("%s;%s;%s;%s", nome, cognome, numeroTelefono, utilizzo.toString());
    }

    /*Permette l'inserimento dei dati anagrafici
     * Il valore di ritono del metodo determina l'esito dell'inserimento dei dati.
     */
    public boolean inserimentoAnagrafica(Scanner userInput) {
        //Stringa che l'utente deve inserire per uscire dall'inserimento
        String uscitaInserimento = "exit";

        //Permettere l'uscita dal programma utilizzando una stringa specifica
        System.out.println("Digitare '" + uscitaInserimento + "' per uscire dall'inserimento");
        //Inserimento nome
        System.out.print("Nome : ");
        nome = userInput.next();
        //Se l'utente ha inserito la stringa di uscita dall'inserimento ritornare il metodo con un valore che indica inserimento non completato
        if(nome.toLowerCase().equals(uscitaInserimento))
            return false;

        //Inserimento cognome
        System.out.print("Cognome : ");
        cognome = userInput.next();
        //Determinare se l'utente ha inserito la stringa di termine inserimento
        if(cognome.toLowerCase().equals(uscitaInserimento))
            //Ritornare un valore che indica che l'inserimento non e' stato completato
            return false;

        //Inserimento numero di telefono
        System.out.print("Numero di telefono : ");
        numeroTelefono = userInput.next();
        //Determinare se l'utente ha richiesto l'uscita dall'inserimento
        if(numeroTelefono.toLowerCase().equals(uscitaInserimento)) 
            //Ritornare un valore che indica che l'inserimento non e' stato completato
            return false;

        //Inserimento utilizzo del numero di telefono
        //Ritornare il valore di ritorno di questo metodo per indicare se l'inserimento e' stato completato correttamente o meno
        return this.inserimentoTipologiaTelefono("Inserire una tipologia di numero di telefono", userInput);
    }

    /*Permette l'inserimento del singolo dato della tipologia del numero di telefono
     * Se l'inserimento e' stato completato correttamente dall'utente il metodo ritorna un valore vero, altrimenti ritorna falso
     */
    public boolean inserimentoTipologiaTelefono(String ask, Scanner userInput) {
        //Inserimento utilizzo del numero di telefono
        //Costruzione dell'array contenente gli inserimenti del menu
        String[] tipologieTelefono = new String[1 + tipologiaTelefono.values().length + 1];

        //Mettere il titolo inizialmente
        tipologieTelefono[0] = ask;
        //Inserire l'opzione di uscita dal menu al termine
        tipologieTelefono[tipologieTelefono.length - 1] = "Uscire";
        //Dopo il titolo, inserire le opzioni contenute nell'enumeratore
        for(int option = 0; option < tipologiaTelefono.values().length; option++)
            //Inserire il valore corrente nell'enumeratore (convertito a stringa)
            tipologieTelefono[option + 1] = tipologiaTelefono.values()[option].toString();

        //Chiamare il menu
        int userSelection = Tools.menu(tipologieTelefono, userInput);

        //Proseguire con la modifica dell'utilizzo soltanto se l'utente non ha richiesto l'uscita dal menu
        if(userSelection <= tipologiaTelefono.values().length)
        {
            //In base all'inserimento dell'utente, impostare la tipologia di numero di telefono
            this.utilizzo = tipologiaTelefono.values()[userSelection - 1];
            //Ritornare un valore che indica inserimento completato con successo
            return true;
        } else return false; //Se l'inserimento non e' stato completato allora ritornare falso
    }

    /*Controlla se un valore e' uguale ad un altro */
    public boolean equals(Contatto obj) {
        //Il controllo viene fatto tramite nome e cognome
        return (this.nome.equals(obj.nome) && this.cognome.equals(obj.cognome));
    }

    /*Compara due contatti e ritorna il maggiore ed il minore*/
    public int compare(Contatto compare) {
        //Se i cognomi sono uguali ritornare la comparazione
        if(compare.cognome.equals(this.cognome))
            return Tools.compareString(this.nome, compare.nome);
        //Se i cognomi sono differenti allora ritornare il confronto tra i cognomi
        return Tools.compareString(this.cognome, compare.cognome);
    }
}
