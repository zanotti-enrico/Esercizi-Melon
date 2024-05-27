import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.Scanner;

public class Rubrica {
    //Vettore di stringhe contenente tutti gli elementi dei due menu
    private final static String[] normalMenu = {
        "Gestione Rubrica",
        "Aggiungi Contatto",
        "Ricerca Contatto",
        "Rimuovi Contatti",
        "Modifica Contatto",
        "Visualizza Contatti",
        "Riordina Contatti",
        "Salva su file",
        "Carica da file",
        "Esci",
        "Sblocca contatti nascosti"
    };
    private final static String[] secretMenu = {
        "Gestione rubrica - Menu nascosto",
        "Aggiungi Contatto",
        "Ricerca Contatto",
        "Rimuovi Contatti",
        "Modifica Contatto",
        "Visualizza Contatti",
        "Riordina Contatti",
        "Salva su file",
        "Carica da file",
        "Modifica password menu nascosto",
        "Blocca contatti nascosti",
        "Esci"
    };
    private static boolean secretMenuEnabled = false; //Determina se siamo nel menu segreto o meno
    private static String secretMenuPsw = "1234"; //Contiene la password per l'accesso al menu segreto
    private static Contact[] contactList = new Contact[16]; //Contiene i contatti salvati
    private static int contactListOccupied = 0; //Fino a quando la rubrica e' stata occupata

    public static void main(String[] args) {
        //Istanziamento del lettore di input utente
        Scanner userInput = new Scanner(System.in);

        //Impostata a vero quando il programma deve terminarsi
        boolean terminate = false;
        //Contiene l'elemento selezionato dall'utente nel menu
        int userSelection;
        do {
            //In base alla variabile che determina se mostrare il menu nascosto o meno
            if(!secretMenuEnabled)
                userSelection = Utility.menu(normalMenu, userInput, 10); //Nascondere l'elemento 10 del menu
            else    
                userSelection = Utility.menu(secretMenu, userInput, -1); //Non nascondere nessun elemento del menu

            //In base al tipo di menu, variare il tipo di azioni eseguite a seconda dell'input utente
            switch(userSelection) {
                //Aggiungere un nuovo contatto
                case 1 : {
                    //Creare un nuovo oggetto contatto
                    Contact newContact = new Contact();
                    /* Riempire il nuovo oggetto con l'input utente, richiedere anche se l'utente
                        * Le flag vanno passate al metodo tramite un array di booleane.
                        * Richiedere all'utente se nascondere il contatto soltanto se le funzioni nascoste sono abilitate
                    */
                    newContact.edit(userInput, new boolean[]{true, true, true, true, secretMenuEnabled});
                    //Se si trova nella rubrica, stampare un messaggio di errore e uscire
                    if(exists(newContact))
                    {
                        System.out.println("Errore : il contatto e' gia' presente nella rubrica.");
                        break;
                    }
                    //Altrimenti salvarlo nella rubrica
                    addContact(newContact);

                    //Stamparlo
                    System.out.println("Nuovo contatto :");
                    System.out.println(newContact.toString(secretMenuEnabled));
                    System.out.println("Premere INVIO per continuare...");
                    userInput.nextLine();
                    break;
                }

                //Trovare un contatto
                case 2 : {
                    //Se non ci sono contatti da trovare, uscire
                    if(contactListOccupied <= 0)
                    {
                        System.out.println("Errore : nessun contatto salvato.");
                        System.out.println("Premere INVIO per continuare...");
                        userInput.nextLine();
                        break;
                    }

                    //Variabili da utilizzare per la ricerca
                    String findFirstName, findLastName, findPhoneNumber;

                    System.out.println("Lasciare il campo vuoto per non utilizzarlo durante la ricerca.");
                    //Richiedere il nome
                    System.out.print("Inserire il nome: ");
                    findFirstName = userInput.nextLine();
                    //Richiedere il cognome
                    System.out.print("Inserire il cognome: ");
                    findLastName = userInput.nextLine();
                    //Richiedere il numero di telefono
                    System.out.print("Inserire il numero di telefono: ");
                    findPhoneNumber = userInput.nextLine();

                    //Effettuare la ricerca
                    Contact[] contactsFound = findContacts(findFirstName, findLastName, findPhoneNumber);
                    //Stampare il risultato della ricerca
                    System.out.println("Contatti trovati :");
                    printContactArray(contactsFound);
                    //Mettere il programma in pausa fino a quando l'utente non preme INVIO
                    System.out.println("Premere INVIO per continuare...");
                    userInput.nextLine();

                    break;
                }

                //Eliminare una serie di contatti
                case 3 : {
                    //Se non ci sono contatti da eliminare, uscire
                    if(contactListOccupied <= 0)
                    {
                        System.out.println("Errore : nessun contatto salvato.");
                        System.out.println("Premere INVIO per continuare...");
                        userInput.nextLine();
                        break;
                    }
                    //Creare un array contenente il titolo del menu e i contatti da stampare, appendere all'inizio di questo array il titolo
                    String[] contactListString = contactListStringArray(contactList, contactListOccupied, "Selezionare i contatti da eliminare");
                    //Aprire un menu a selezione multipla per far scegliere all'utente i contatti da eliminare
                    int[] deletionUserInput = Utility.multipleSelectionMenuInt(contactListString, userInput);
                    //Utilizzare le selezioni dell'utente per eliminare i contatti richiesti
                    removeContact(deletionUserInput);

                    break;
                }

                //Modificare un contatto
                case 4 : {
                    //Se non ci sono contatti da mostrare, uscire
                    if(contactListOccupied <= 0)
                    {
                        System.out.println("Errore : nessun contatto salvato.");
                        System.out.println("Premere INVIO per continuare...");
                        userInput.nextLine();
                        break;
                    }
                    //Creare un array contenente il titolo del menu e i contatti da stampare, appendere all'inizio di questo array il titolo
                    String[] contactListString = contactListStringArray(contactList, contactListOccupied, "Selezionare il contatto da modificare");
                    //Aggiungere una stringa alla fine per permettere l'uscita dal menu
                    String[] contactListExpand = new String[contactListString.length + 1];
                    //Copiare i dati nella stringa
                    for(int copy = 0; copy < contactListString.length; copy++)
                        contactListExpand[copy] = contactListString[copy];
                    //Nell'ultimo elemento, salvare l'opzione per uscire
                    contactListExpand[contactListExpand.length - 1] = "Esci";

                    //Aprire un menu per la selezione del contatto da modificare
                    int editContactIndex = Utility.menu(contactListExpand, userInput, -1) - 1; //Decrementare di 1 perche' il menu inizia a contare dall'1 e l'array dallo 0

                    //Se e' stato selezionato un elemento fuori dall'array allora uscire, e' stata selezionata l'uscita dal menu
                    if(editContactIndex >= contactListOccupied)
                        break;

                    //Creare un nuovo array di stringhe contenente i dati da modificare del contatto
                    final String[] editMenuContents;
                    //I dati di cui permettere la modifica variano in base al menu selezionato
                    if(secretMenuEnabled) {
                        editMenuContents = new String[]{
                            "Selezionare quali elementi modificare", //Titolo
                            "Nome",
                            "Cognome",
                            "Numero di telefono",
                            "Tipologia numero di telefono",
                            "Visibilita'" //Mostrato soltanto se il menu nascosto e' abilitato
                        };
                    } else {
                        editMenuContents = new String[]{
                            "Selezionare quali elementi modificare", //Titolo
                            "Nome",
                            "Cognome",
                            "Numero di telefono",
                            "Tipologia numero di telefono"
                        };
                    }

                    //Richiedere all'utente la selezione multipla di uno o piu' elementi da modificare
                    boolean[] editElements = Utility.multipleSelectionMenuBool(editMenuContents, userInput);

                    //Se il menu nascosto non e' abilitato e' necessario aggiungere alla fine il flag per non permettere la modifica del contatto nascosto
                    if (!secretMenuEnabled) {
                        //Aggiungere alla fine del vettore un elemento
                        boolean[] flags = new boolean[editElements.length + 1];
                        //Copiare i dati nel nuovo array
                        for(int copy = 0; copy < editElements.length; copy++)
                            flags[copy] = editElements[copy];
                        //Non siamo nel menu nascosto, l'ultima flag indica se va richiesto se il contatto e' nascosto o meno, impostarla sempre falsa
                        flags[flags.length - 1] = false;
                        //Passare queste flag e lo scanner per l'input utente al metodo edit del contatto selezionato per permettere la modifica delle impostazioni richieste
                        contactList[editContactIndex].edit(userInput, flags);
                    } else //Altrimenti utilizzare direttamente l'array delle selezioni dell'utente
                        contactList[editContactIndex].edit(userInput, editElements);
                    //Stampare il nuovo contatto
                    System.out.println("Contatto modificato:");
                    System.out.println(contactList[editContactIndex].toString(secretMenuEnabled));
                    System.out.println("Premere INVIO per continuare...");
                    userInput.nextLine();

                    break;
                }

                //Visualizzare i contatti
                case 5 : {
                    //Stampa tutti i contatti e attendi l'input utente
                    System.out.println("Stampa di tutti i contatti:");
                    printContacts();
                    System.out.println("Premere INVIO per continuare...");
                    userInput.nextLine();
                    break;
                }

                //Riordinare i contatti
                case 6 : {
                    //Se non ci sono contatti da ordinare, uscire
                    if(contactListOccupied <= 0)
                    {
                        System.out.println("Errore : nessun contatto salvato.");
                        System.out.println("Premere INVIO per continuare...");
                        userInput.nextLine();
                        break;
                    }
                    //Riordina i contatti e attendi l'input utente
                    sortContacts();
                    System.out.println("Contatti riordinati.\nPremere INVIO per continuare...");
                    userInput.nextLine();
                    break;
                }
                
                //Salvare su file
                case 7 : {
                    //Richiedere la posizione
                    System.out.print("Inserire il nome del file in cui salvare i dati: ");
                    String fileName = userInput.nextLine();
                    //Appendere al file l'estensione
                    fileName += ".csv";
                    //Salvare il file
                    try {
                        saveToFile(fileName);
                    } catch(IOException exception) {
                        //Stampare un errore e uscire
                        System.out.println("Errore durante il salvataggio su file :"); 
                        exception.printStackTrace();
                        break;
                    }
                    //Informare l'utente del completamento
                    System.out.println("Salvataggio completato con successo.\nPremere INVIO per continuare...");
                    userInput.nextLine();
                    break;
                }

                //Caricare da file
                case 8 : {
                    //Richiedere la posizione del file da cui caricare
                    System.out.print("Inserire il nome del file da cui caricare i dati: ");
                    String filePath = userInput.nextLine();
                    //Caricare i dati dal file
                    try {
                        loadFromFile(filePath);
                    } catch(IOException exception) {
                        //Stampare un errore e uscire
                        System.out.println("Errore durante il caricamento da file :");
                        exception.printStackTrace();
                        break;
                    }
                    //Informare l'utente del completamento
                    System.out.println("Caricamento completato con successo.\nPremere INVIO per continuare...");
                    userInput.nextLine();
                    break;
                }

                //Uscire/Modificare password menu segreto
                case 9 : {
                    //Se il menu segreto non e' stato abilitato, e' stata selezionata l'opzione per uscire allora uscire
                    if(!secretMenuEnabled)
                    {
                        System.out.println("Uscita dal programma...");
                        terminate = true;
                        break;
                    } else { //Altrimenti, modificare la password del menu segreto
                        System.out.print("Inserire la nuova password per il menu segreto: ");
                        String newSecretMenuPsw = userInput.nextLine();
                        System.out.print("Reinserirla per confermare: ");
                        String newInput = userInput.nextLine();

                        //Confrontare le password
                        if(newSecretMenuPsw.equals(newInput)) {
                            secretMenuPsw = newSecretMenuPsw; //Modificare la password d'accesso
                            System.out.println("Password modificata con successo");
                        } else
                            System.out.println("Errore: le due password inserite non sono uguali. La password non verra' cambiata");

                        //Attendere l'input utente prima di continuare
                        System.out.println("Premere INVIO per continuare...");
                        userInput.nextLine();

                        break;
                    }
                }

                //Entrare/uscire dal menu segreto
                case 10 : {
                    //Se il menu segreto non e' abilitato
                    if(!secretMenuEnabled)
                    {
                        //Richiedere all'utente la password
                        System.out.print("Inserire password: ");
                        String passwordInput = userInput.nextLine();
                        /*Controllare che sia corretta e salvare il risultato nella variabile booleana che 
                            * determina se il menu segreto e' abilitato o meno.
                            */
                        secretMenuEnabled = passwordInput.equals(secretMenuPsw);
                        if(secretMenuEnabled)
                            System.out.println("Password Corretta");
                        else
                            System.out.println("Password errata");
                        System.out.println("Premere INVIO per continuare...");
                        userInput.nextLine();
                    } else //Altrimenti, uscire dal menu segreto
                    {
                        //Richiedere conferma all'utente
                        System.out.print("Uscire dal menu segreto? [S]: ");
                        String input = userInput.nextLine();

                        //Se la conferma viene data, allora uscire, altrimenti rimanere nel menu
                        if(input.toLowerCase().equals("s"))
                            secretMenuEnabled = false;
                    }
                    break;
                }

                //Uscire dal programma
                default : {
                    System.out.println("Uscita dal programma...");
                    terminate = true;
                    break;
                }
            }
        } while(!terminate);
    }

    /*Aggiunge al termine della lista dei contatti un nuovo contatto.
     * Se non c'e' spazio nell'array, raddoppia la sua dimensione.
     */
    private static void addContact (Contact newContact) {
        //Controllare se la rubrica e' piena, in caso affermativo, raddoppiare la sua dimensione
        if(contactListOccupied == contactList.length)
        {
            //Creare un nuovo array di dimensione doppia all'originale
            Contact[] expand = new Contact[contactList.length * 2];
            //Copiare i dati nel nuovo array
            for(int copy = 0; copy < contactList.length; copy++)
                expand[copy] = contactList[copy];
            //Salvare il nuovo array come rubrica
            contactList = expand;
        }

        //Salvare nell'ultimo elemento della rubrica il contatto richiesto, incrementando l'iteratore
        contactList[contactListOccupied++] = newContact;
    }

    /*Rimuove una lista di contatti tramite iteratori all'interno della rubrica.
     * Decrementa l'iteratore della quantita' di elementi nella rubrica
     */
    private static void removeContact (int[] indexes) {
        //Iterare in tutti gli elementi da rimuovere
        for(int currentIndex : indexes)
        {
            //Partendo dall'indice corrente, spostare tutti gli elementi indietro di 1 per rimuovere quello corrente
            for(int move = currentIndex; move < contactListOccupied; move++)
                contactList[currentIndex] = contactList[currentIndex + 1];
            //Decrementare quanto e' occupata la rubrica
            contactListOccupied--;
        }
    }

    /*Passata una serie di criteri, il metodo ritorna i contatti trovati. 
     * Ricerca anche i contatti nascosti se il menu segreto e' abilitato.
     * I dati possono essere inseriti non per intero, se viene inserita una parte del nome o una parte
     * del cognome di un contatto, i contatti contenenti quella parte di nome o di cognome
     * vengono ritornati comunque. Questo non vale per il numero di telefono.
     * Lasciare vuoto uno dei parametri per non includerli nella ricerca.
     * Durante la ricerca, le maiuscole e le minuscole vengono ignorate
    */
    private static Contact[] findContacts (String firstName, String lastName, String phoneNumber) {
        //Array di contatti trovati da ritornare
        Contact[] output = new Contact[0]; //Inizializzarlo con lunghezza 0

        //Iterare in tutti i contatti
        for(int currentSearch = 0; currentSearch < contactListOccupied; currentSearch++)
        {
            /*Controllare se le stringhe passate sono contenute nel relativo parametro
             * del contatto che si sta controllando.
             * Escludere il controllo per i contatti nascosti.
             * Se uno dei parametri e' vuoto, allora non verra' incluso nella ricerca in quanto 
             * una stringa vuota e' contenuta in qualsiasi stringa.
            */
            if(
                contactList[currentSearch].getFirstName().toLowerCase().contains(firstName.toLowerCase())
                &&
                contactList[currentSearch].getLastName().toLowerCase().contains(lastName.toLowerCase())
                &&
                //Effettuare il controllo sul numero di telefono soltanto se esso e' stato specificato come parametro
                (
                    phoneNumber == "" //Se il numero di telefono per la ricerca non e' stato specificato
                    || //Oppure
                    phoneNumber.equals(String.valueOf(contactList[currentSearch].getPhoneNumber())) //Se la ricerca contiene completamente il numero di telefono passato.
                )
                && //Mostrare il contatto:
                (!secretMenuEnabled && !contactList[currentSearch].isHidden()) //Se bisogna escludere i contatti nascosti ed il contatto non e' nascosto
                || //Oppure
                secretMenuEnabled //Se non bisogna escludere i contatti nascosti
            ) { //Se viene trovato un contatto, aggiungerlo all'array di contatti trovati da ritornare
                //Inizializzare un nuovo array con dimensione incrementata
                Contact[] expand = new Contact[output.length + 1];
                //Copiare i dati nel nuovo array
                for(int copy = 0; copy < output.length; copy++)
                    expand[copy] = output[copy];
                //Salvare, nell'ultimo elemento, il contatto trovato
                expand[expand.length - 1] = contactList[currentSearch];
                //Impostare come array di output l'array espanso
                output = expand;
            }
        }

        //Ritornare l'array di contatti trovati
        return output;
    }

    /*Stampa nella stream di output tutti i contatti salvati. */
    private static void printContacts() {
        //Se non ci sono contatti da mostrare, uscire
        if(contactListOccupied <= 0)
        {
            System.out.println("Errore : nessun contatto salvato.");
            return;
        }

        //Iterare in tutti i contatti salvati e chiamare il metodo per la stampa
        int currentContact = 0;
        for(int contactIterator = 0; contactIterator < contactListOccupied; contactIterator++) {
            //Se bisogna escludere i contatti nascosti e il contatto corrente e' nascosto, passare al contatto successivo
            while (!secretMenuEnabled && contactList[currentContact].isHidden())
                currentContact++; //Passa al contatto successivo ed effettua di nuovo il controllo

            //Altrimenti, stampare il contatti corrente
            System.out.println("==== Contatto " + (contactIterator + 1) + " ====");
            System.out.println(contactList[currentContact].toString(secretMenuEnabled));

            //Passare al contatto successivo
            currentContact++;
        }
   }

    /*Stampa nella stream di output un array di contatti specifico */
    private static void printContactArray(Contact[] input) {
        //Se l'array e' vuoto, stampare un errore e ritornare
        if(input.length == 0) {
            System.out.println("Nessun contatto.");
            return;
        }
        
        //Iterare in tutti gli elementi dell'array
        for(int currentContact = 0; currentContact < input.length; currentContact++)
        {
            //Se il menu segreto non e' abilitato e il contatto corrente e' nascosto, non stamparlo
            if(!secretMenuEnabled && input[currentContact].isHidden())
                continue; //Saltare l'iterazione del ciclo for

            //Stampare il contatto utilizzando il metodo per convertire i suoi dati in stringa
            System.out.println("=== Contatto " + (currentContact + 1) + " ===");
            System.out.println(input[currentContact].toString(secretMenuEnabled));
        }
    }

    /*Controlla se un contatto si trova nella rubrica, ritorna il risultato */
    private static boolean exists(Contact search) {
        //Iterare nella rubrica
        for(int currentElement = 0; currentElement < contactListOccupied; currentElement++)
            //Se viene trovato l'elemento, ritornare il risultato
            if(contactList[currentElement].equals(search))
                return true;

        //Se non vengono trovati contatti, ritornare falso
        return false;
    }

    /*Viene utilizzato il bubble sort per ordinare la rubrica */
    private static void sortContacts() {
        //Ogni iterazione dell'algoritmo ordina un elemento, iterare fino a che tutti gli elementi sono ordinati
        for(int sortedElements = 0; sortedElements < contactListOccupied; sortedElements++) {
            //Iterare per tutti gli elementi della rubrica, fermandosi dove inizia la parte di array ordinato
            for(int currentElement = 0; currentElement < contactListOccupied - sortedElements - 1; currentElement++) {
                //Se vengono trovati due elementi non ordinati, scambiarli
                if(contactList[currentElement].compareTo(contactList[currentElement + 1]) > 0)
                {
                    //Scambiare i due contatti
                    Contact tempSwap = contactList[currentElement + 1];
                    contactList[currentElement + 1] = contactList[currentElement];
                    contactList[currentElement] = tempSwap;
                }
            }
        }
    }

    /*This method converts an array of contacts to an array of single line strings.
     * The last parameter is an optional string to be added at the start of the array to be used with menus.
    */
    private static String[] contactListStringArray(Contact[] input, int inputLength, String appendToStart) {
        //If the string to add at the start wasn't specified then ignore it
        if(appendToStart == null)
        {
            //Create a new array of size equal to the amount of contacts passed
            String[] output = new String[inputLength];
            //Iterate trough all contacts and convert them to a single line string
            for(int currentContact = 0; currentContact < inputLength; currentContact++)
            {
                //In case the hidden menu isn't enabled and the current contact is hidden, don't show it
                if(input[currentContact].isHidden() && !secretMenuEnabled)
                    continue;
                output[currentContact] = input[currentContact].toCompactString();
            }
            //Return the result
            return output;
        } else { //Otherwise append it
            //Create a new array of size equal to the amount of contacts passed
            String[] output = new String[inputLength + 1];
            //Append the string at the start
            output[0] = appendToStart;
            //Iterate trough all contacts and convert them to a single line string
            for(int currentContact = 1; currentContact < output.length; currentContact++)
            {
                //In case the hidden menu isn't enabled and the current contact is hidden, don't show it
                if(input[currentContact - 1].isHidden() && !secretMenuEnabled)
                    continue;
                output[currentContact] = input[currentContact - 1].toCompactString();
            }
            //Return the result
            return output;
        }
    }

    /*This method takes the contact list and saves it into a .csv file in the current directory
     * The filename is specified by the parameter.
     * It throws any exceptions that occur during the save
     */
    private static void saveToFile(String path) throws IOException {
        //Istanziare la stream per la scrittura in output dei dati su file
        FileWriter fileOutput = new FileWriter(path);

        try {
            //Nella prima riga, scrivere i nomi delle variabili
            //Se il menu nascosto e' abilitato, aggiungere una colonna per il flag dei contatti nascosti
            if(secretMenuEnabled)
                fileOutput.write(contactListOccupied + " Contatti;Nome;Cognome;Numero di telefono;Utilizzo telefono;Nascosto\r\n");
            else
                fileOutput.write(contactListOccupied + " Contatti;Nome;Cognome;Numero di telefono;Utilizzo telefono\r\n");
        } catch(IOException exception) {
            fileOutput.close();
            throw exception;
        }

        //Iterare in tutti gli elementi occupati della rubrica
        for(int currentElement = 0; currentElement < contactListOccupied; currentElement++) {
            //In case the hidden menu isn't enabled and the current contact is hidden, don't save it
            if(contactList[currentElement].isHidden() && !secretMenuEnabled)
                continue;

            try { //Scrivere il numero del contatto e i suoi dati
                fileOutput.write(currentElement + ";" + contactList[currentElement].toCSVString(secretMenuEnabled) + "\r\n");
            } catch(IOException exception) {
                fileOutput.close();
                throw exception;
            }
        }

        //Svuotare il buffer
        fileOutput.flush();

        //Chiudere il file
        fileOutput.close();
    }

    /*This method reads the contents of the file in the path specified by the input, it then
     * saves them into the contactList array.
     * It throws any exceptions that occur during the opening.
     */
    private static void loadFromFile(String file) throws IOException {
        //Oggetto per la rappresentazione del file
        FileReader fileIn = new FileReader(file);
        //Istanziamento dello scanner per la lettura dal file
        Scanner reader = new Scanner(fileIn);
        //Leggere la prima riga del file e salvare il primo numero in esso (contiene il numero di elementi) e convertirlo in intero
        int elementAmount = Integer.valueOf(reader.nextLine().split(" ")[0]);
        //Nuovo array che contiene i dati letti da file
        Contact[] fileReading = new Contact[elementAmount];
        int currentElement = 0; //Contiene l'elemento corrente letto dal file

        //Utilizzo dello scanner per la lettura da file
        String currentLine; //Contiene la stringa corrente letta da file
        while(reader.hasNextLine()) {
            //Leggere la prossima riga da file
            currentLine = reader.nextLine();

            //Separare la stringa in un vettore separato da virgole
            String[] singleElements = currentLine.split(";");

            //Creare un nuovo oggetto contatto che contiene i dati ricavati da file
            Contact newContact;
            //In base al numero di elementi ricavati, creare un nuovo contatto
            if(singleElements.length == 5) 
                newContact = new Contact(singleElements[1], singleElements[2], Long.valueOf(singleElements[3]), singleElements[4]);
            else    
                newContact = new Contact(singleElements[1], singleElements[2], Long.valueOf(singleElements[3]), singleElements[4], Boolean.valueOf(singleElements[5]));
            //Salvare il nuovo contatto nell'elemento della rubrica, incremntando l'iteratore
            fileReading[currentElement++] = newContact;
        }

        //Chiudere il lettore del file
        reader.close();

        contactList = fileReading;
        contactListOccupied = elementAmount;
    }
}

