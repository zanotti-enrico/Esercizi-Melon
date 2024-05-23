import java.util.Scanner;

public class Contact {
    public Contact(String _firstName, String _lastName, long _phoneNumber, String _type) {
        //Contiene il valore del tipo di contatto che rappresenta la stringa passata
        ContactType typeConverted = null;
        //Array contenente tutti i valori dell'enumeratore, ricercare in questo array il valore rappresentato dalla stringa
        for(ContactType search : ContactType.values())
            //Se viene trovato un valore che eguaglia la stringa
            if(search.toString().equals(_type))
            {
                //Salvare il valore trovato e uscire dal ciclo
                typeConverted = search;
                break;
            }

        setFirstName(_firstName);
        setLastName(_lastName);
        setPhoneNumber(_phoneNumber);
        setType(typeConverted);
    }

    public Contact(String _firstName, String _lastName, long _phoneNumber, String _type, boolean _hidden) {
        //Contiene il valore del tipo di contatto che rappresenta la stringa passata
        ContactType typeConverted = null;
        //Array contenente tutti i valori dell'enumeratore, ricercare in questo array il valore rappresentato dalla stringa
        for(ContactType search : ContactType.values())
            //Se viene trovato un valore che eguaglia la stringa
            if(search.toString().equals(_type))
            {
                //Salvare il valore trovato e uscire dal ciclo
                typeConverted = search;
                break;
            }
            
        setFirstName(_firstName);
        setLastName(_lastName);
        setPhoneNumber(_phoneNumber);
        setType(typeConverted);
        setHidden(_hidden);
    }

    /*Inizializza un oggetto vuoto */
    public Contact() {}

    //Used for the phone number location
    private enum ContactType {
        Casa,
        Cellulare,
        Lavoro
    };

    //Attributi del contatto
    private String firstName;
    private String lastName;
    private long phoneNumber;
    private ContactType contactType;
    private boolean hiddenContact = false; //Il contatto viene inizializzato come non nascosto

    //Metodi per la modifica dei contatti nella rubrica
    public void setFirstName(String newFirstName) {
        if(newFirstName != null)
            this.firstName = newFirstName;
    }
    public void setLastName(String newLastName) {
        if(newLastName != null)
            this.lastName = newLastName;
    }
    public void setPhoneNumber(long newPhoneNumber) {
        if(newPhoneNumber > 0 && newPhoneNumber < 9999999999l)
            this.phoneNumber = newPhoneNumber;
    }
    public void setType(ContactType newType) {
        if(newType != null)
            this.contactType = newType;
    }
    public void setHidden(boolean isHidden) {
        this.hiddenContact = isHidden;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public long getPhoneNumber() {
        return phoneNumber;
    }
    public ContactType getType() {
        return contactType;
    }
    public boolean isHidden() {
        return hiddenContact;
    }

    /*Permette la modifica del contatto attraverso l'input utente, modificando
     * soltanto i campi indicati attraverso le flag passate tramite l'array di booleane
     * Il metodo stampa in output.
     */
    public void edit(Scanner userInput, boolean[] flags)
    {
        //Ricavo delle singole flag dall'array di booleane
        boolean firstName = flags[0];
        boolean lastName = flags[1];
        boolean phoneNumber = flags[2];
        boolean phoneNumberType = flags[3];
        boolean hidden = flags[4];

        //Se richiesto, far inserire il nome
        if(firstName)
        {
            System.out.print("Inserire il nome: ");
            this.firstName = userInput.nextLine();
        }

        //Far inserire il cognome
        if(lastName) {
            System.out.print("Inserire il cognome: ");
            this.lastName = userInput.nextLine();
        }

        //Far inserire il numero di telefono
        if(phoneNumber) {
            //Variabile impostata quando il valore inserito non e' validos
            boolean invalid;
            do {
                invalid = false; //Reimpostare la variabile;

                System.out.print("Inserire il numero di telefono: ");

                //Richiedere l'inserimento di un numero di telefono
                try {
                    this.phoneNumber = userInput.nextLong();
                } catch(Exception exception) {
                    userInput.nextLine(); //Svuotare il buffer che contiene ancora la lettura
                    System.out.println("Errore : il numero di telefono deve essere numerico");
                    invalid = true; //E' stato inserito un dato non valido
                }

                //Controllare che il valore inserito sia positivo
                if(this.phoneNumber < 0) {
                    invalid = true;
                    System.out.println("Il valore inserito non puo' essere negativo");
                }
            } while(invalid); //Continuare il ciclo fino a quando il valore inserito non e' corretto

            //Svuotare il buffer
            userInput.nextLine();
        }

        //Far inserire la tipologia di numero di telefono
        if(phoneNumberType) {
            //Variabile impostata se il valore inserito dall'utente non e' valido
            boolean invalid;
            //Ciclo continuo fino a quando il valore inserito e' valido
            do {
                //Reimpostare la variabile per il valore non valido
                invalid = false;

                //Array di valori validi per l'inserimento
                ContactType[] contactTypes = ContactType.values();
                ContactType selectedType = null; //Contiene il valore inserito dall'utente

                System.out.println("Inserire l'utilizzo del numero di telefono:");
                //Stampare l'array delle opzioni
                for(ContactType print : contactTypes)
                    System.out.println("- " + print.toString());
                System.out.print(">");
                //Richiedere l'input all'utente
                String userInputData = userInput.nextLine();

                //Trovare il valore nell'array di valori validi
                for(ContactType search : contactTypes)
                {
                    //Se la rappresentazione stringa del tipo di contatto corrente eguaglia l'input il valore e' stato trovato
                    //Durante la ricerca ignorare maiuscole/minuscole
                    if(search.toString().toLowerCase().equals(userInputData.toLowerCase()))
                    {
                        //Salvare il contatto selezionato
                        selectedType = search;
                        //Uscire dal ciclo
                        break;
                    }
                }

                //Se il valore non e' stato trovato mostrare un errore e richiedere l'input
                if(selectedType == null)
                {
                    System.out.println("Errore : il valore inserito non e' presente nella lista.");
                    invalid = true;
                }

                //Se un valore e' stato inserito:
                if(!invalid)
                    //Salvare il valore selezionato
                    this.contactType = selectedType;
            } while(invalid);
        }

        //Determinare se il valore va nascosto o meno
        if(hidden)
        {
            System.out.print("Il contatto deve essere nascosto? [S]: ");
            //Richiedere un input, ignorando maiuscole/minuscole, controllando se e' uguale a "S"
            //Se risulta uguale, il contatto va nascosto
            this.hiddenContact = userInput.nextLine().toUpperCase().equals("S");
        }
    }

    public int compareTo(Contact compare) {
        //Il contatto nascosto e' sempre maggiore
        if(this.hiddenContact != compare.hiddenContact)
        {
            if(this.hiddenContact)
                return 1;
            else
                return -1;
        } else 
        {

            if(compare.lastName.equals(this.lastName)) //Se i due cognomi sono uguali comparare i nomi
                return this.firstName.compareTo(compare.firstName);
            else //Altrimenti comparare i cognomi se non sono uguali
                return this.lastName.compareTo(compare.lastName);
        }
    }

    @Override
    public boolean equals(Object compare) {
        return (
            this.firstName.equals(((Contact)compare).firstName) && this.lastName.equals(((Contact)compare).lastName)
        );
    }

    /*Se la flag passata come parametro e' impostata a vero, il metodo stampa 
     * se il contatto corrente e' nascosto o meno.
     */
    public String toString(boolean hidden) {
        String output = String.format("Nome : %s\nCognome : %s\nNumero di telefono : %s\nTipologia telefono : %s\n",
            this.firstName, this.lastName, String.valueOf(this.phoneNumber), this.contactType.toString()
        );

        //Se e' stato richiesto di mostrare se il contatto e' nascosto o meno, includere questa informazione al termine della stringa
        if(hidden)
        {
            output += "Nasosto : ";
            if(this.hiddenContact)
                output += "Si\n";
            else
                output += "No\n";
        }
        
        return output;
    }

    /*Converts the object to a compact, single line string*/
    public String toCompactString() {
        return String.format("Nome : %s; Cognome : %s; Numero di telefono : %s (%s)\n", this.firstName, this.lastName, String.valueOf(this.phoneNumber), this.contactType.toString());
    }

    /*Converts the object to a comma separated string compatible with CSV */
    public String toCSVString(boolean hidden) {
        //If the hidden property has to be shown
        if(hidden)
            return String.format("%s;%s;%s;%s;%s", this.firstName, this.lastName, String.valueOf(this.phoneNumber), this.contactType.toString(), String.valueOf(this.hiddenContact));
        //Otherwise
        return String.format("%s;%s;%s;%s", this.firstName, this.lastName, String.valueOf(this.phoneNumber), this.contactType.toString());
    }
}