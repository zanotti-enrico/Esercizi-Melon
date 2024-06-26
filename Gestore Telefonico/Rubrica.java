import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class Rubrica {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        //Contiene i dati da stampare nel menu
        String[] menuContent = {
            "Rubrica Telefonica",
            "Inserimento",
            "Visualizzazione",
            "Modifica telefono",
            "Modifica tipologia telefono",
            "Elimina contatto",
            "Ordina contatti",
            "Effettua ricarica",
            "Effettua chiamata",
            "Salva File",
            "Carica da File",
            "Uscita"
        };
        //Contiene i contatti
        Contatto[] rubrica = new Contatto[16];
        //Registro chiamate
        Chiamata[] registroChiamate = new Chiamata[16];
        int rubricaOccupied = 0; //Fino a che punto l'array rubrica e' stato riempito

        //Continuare l'esecuzione fino al termine del programma
        boolean progEnd = false;
        int userSelection; //Contiene quale elemento l'utente ha inserito
        do {
            //Prendere il dato dall'utente
            userSelection = Tools.menu(menuContent, keyboard);

            //Switch in base all'inserimento dell'utente
            switch(userSelection) {
                //Se e' stato selezionato l'inserimento
                case 1 : {
                    //Aggiungere un contatto nella rubrica e salvare il risultato del metodo
                    int output = aggiungiContatto(rubrica, rubricaOccupied, keyboard);
                    //Se il valore ritornato e' negativo allora c'e' stato un errore, uscire dallo switch
                    if(output < 0)
                        break;
                    //Altrimenti l'output e' il nuovo valore di quanto e' occupata la rubrica
                    rubricaOccupied = output;
                    break;
                }

                //Se e' stata selezionata la visualizzazione
                case 2 : {
                    //Visualizzare i contatti
                    visualizzaContatti(rubrica, rubricaOccupied, keyboard);
                    break;
                }

                //Se e' stata selezionata una modifica ad un contatto
                case 3 :
                case 4 :
                {
                    //Modificare il contatto specificato
                    modificaContatto(rubrica, rubricaOccupied, userSelection - 3, keyboard);
                    break;
                }

                //Se e' stata selezionata la cancellazione
                case 5 : {
                    //Eliminare un contatto e salvare il risultato
                    int output = eliminazioneContatto(rubrica, rubricaOccupied, keyboard);
                    //Se c'e' stato un errore uscire dalla condizione dello switch
                    if(output < 0)
                        break;
                    //Altrimenti salvare il risultato nell'occupazione della rubrica
                    rubricaOccupied = output;
                    break;
                }
                //Se l'utente ha richiesto l'ordinamento dei contatti
                case 6 : {
                    //Richiedere all'utente l'ordinamento dei contatti
                    ordinamentoContatti(rubrica, rubricaOccupied, keyboard);
                    break;
                }
                //Se l'utente ha richiesto la ricarica
                case 7 : {
                    ricarica(rubrica, rubricaOccupied, keyboard);
                    break;
                }
                //Se l'utente ha richiesto la chiamata
                case 8 : {
                    effettuaChiamata(rubrica, rubricaOccupied, keyboard);
                    break;
                }

                //Se l'utente ha richiesto il salvataggio dei file
                case 9 : {
                    try {
                        salvaFile("rubrica.csv", rubrica, rubricaOccupied);
                    } catch(IOException exception) {
                        System.out.println("Errore durante la scrittura su file");
                        exception.printStackTrace();
                    }
                    
                    System.out.println("Premere INVIO per continuare...");
                    keyboard.nextLine();
                    break;
                }

                //Se l'utente ha richiesto il caricamento da file
                case 10 : {
                    //Richiedere all'utente la posizione del file da caricare
                    System.out.println("Inserire la posizione del file da cui caricare i dati:");
                    String path = keyboard.nextLine();
                    //Caricare nell'array della rubrica i dati presi da file
                    try {
                        rubrica = caricaFile(path);
                        rubricaOccupied = rubrica.length;
                    } catch(IOException exception) {
                        //Nel caso ci sia un errore durante la lettura da file stamparlo
                        System.out.println("Errore durante la lettura da file:");
                        exception.printStackTrace();
                    }

                    System.out.println("Premere INVIO per continuare...");
                    keyboard.nextLine();
                    break;
                }

                default : {
                    //Impostare la variabile per l'uscita dal ciclo
                    System.out.println("Chiusura programma");
                    progEnd = true;
                    break;
                }
            }
        } while(!progEnd);
    }

    /*Il metodo ordina utilizzando l'algoritmo di ordinamento bubble sort la rubrica, basandosi sull'ordine alfabetico
     * dei nomi e dei cognomi nella rubrica.
     * Modifica direttamente l'array passato al metodo stesso.
     * La dimensione dell'array non viene modificata.
     * Il metodo stampa in output e richiede input dall'utente.
     */
    private static void ordinamentoContatti(Contatto[] rubrica, int rubricaOccupied, Scanner keyboard) {
        //Richiedere all'utente un input per permettergli di annullare l'ordinamento
        System.out.println("La rubrica verra' ordinata.\nPremere INVIO per continuare o digitare 'exit' per uscire.");
        String userInput = keyboard.nextLine();
        //Se l'utente ha inserito la stringa apposita allora uscire
        if(userInput.toLowerCase().equals("exit"))
            return;

        //Contiene se l'array e' ordinato
        boolean arrayOrdinato;
        //Continuare l'iterazione per l'array fino a quando e' ordinato
        do {
            //Presupporre che l'array ora sia ordinato
            arrayOrdinato = true;
            //Iterare in tutti gli elementi dell'array da ordinare partendo dal primo e finendo al penultimo
            for(int contattoCorrente = 1; contattoCorrente < rubricaOccupied; contattoCorrente++)
            {
                //Se il valore corrente e' minore di quello precedente scambiarli
                if(rubrica[contattoCorrente].compare(rubrica[contattoCorrente - 1]) < 0)
                {
                    //L'array sicuramente non e' ordinato se sono stati trovati due valori da scambiare
                    arrayOrdinato = false;
                    //Oggetto temporaneo per lo swap
                    Contatto swap = rubrica[contattoCorrente];
                    //Effettuare lo scambio delle due variabili
                    rubrica[contattoCorrente] = rubrica[contattoCorrente - 1];
                    rubrica[contattoCorrente - 1] = swap;
                }
            }
        } while(!arrayOrdinato);
    }

    /*Il metodo chiede all'utente che contatto vuole essere eliminato, lo elimina rimuovendo lo spazio vuoto.
     * Il metodo ritorna il nuovo valore della dimensione dell'array ora decrementato dato che e' stato eliminato un elemento.
     * Il metodo ritorna -1 nel caso l'utente sia uscito senza eliminare contatti o nel caso la rubrica non fosse occupata da elementi.
     */
    private static int eliminazioneContatto(Contatto[] rubrica, int rubricaOccupied, Scanner keyboard) {
        //Se non ci sono contatti eliminare un messaggio di errore dicendo questo
        if(rubricaOccupied == 0)
        {
            System.out.println("Errore : rubrica vuota.");

            //Attendere che l'utente prema INVIO
            System.out.println("\nPremere INVIO per continuare.");
            keyboard.nextLine();

            //Uscire dal metodo con codice errore
            return -1;
        }
        
        //Viene impostata a vera quando l'eliminazione e' terminata
        boolean termineEliminazione = false;
        do {
            //Salvare se va effettuata la stampa di tutti i contatti
            boolean stampaContatti = false;

            //Richiedere nome e cognome del contatto da eliminare
            String ricercaNome, ricercaCognome = null;
            System.out.println("Inserire 'EXIT' per terminare l'inserimento");
            System.out.println("Inserire 'STAMPA' per stampare tutti i contatti");
            System.out.print("Inserire il nome del contatto da eliminare :");
            ricercaNome = keyboard.next();
            //Se l'utente ha richiesto una stampa impostare la variabile apposita
            if(ricercaNome.toLowerCase().equals("stampa"))
                stampaContatti = true;
            else if(ricercaNome.toLowerCase().equals("exit")) //Se l'utente ha richiesto l'uscita dall'inserimento
                return -1; //Uscire dal metodo
            else { //Se l'utente non ha richiesto la stampa procedere con la lettura del cognome
                System.out.print("Inserire il cognome del contatto da eliminare :");
                ricercaCognome = keyboard.next();
                //Controllare se nel cognome l'utente richiede la stampa
                if(ricercaCognome.toLowerCase().equals("stampa"))
                    stampaContatti = true;
                else if(ricercaCognome.toLowerCase().equals("exit"))
                    return -1; //Uscire dal metodo
            }

            //Svuotare il buffer dopo l'utilizzo di next()
            keyboard.nextLine();

            //Se l'utente ha richiesto la stampa dei contatti allora stampare ed eseguire una nuova iterazione del ciclo while
            if(stampaContatti)
            {
                System.out.println("Stampa di tutti i contatti :");
                //Iterare in tutti i contatti per iterare
                for(int contatto = 0; contatto < rubricaOccupied; contatto++)
                    System.out.println("=== Contatto numero " + (contatto + 1) + " ===\n" + rubrica[contatto].toString());
                System.out.println("=== TERMINE LISTA ===");
            } else { //Altrimenti procedere con l'eliminazione
                //Creare un nuovo contatto contenente nome e cognome specificati per la comparazione
                Contatto ricercaContatto = new Contatto(ricercaNome, ricercaCognome);
                //Contiene il numero del contatto da eliminare
                int eliminazione = -1;
                //Effettuare una ricerca nella rubrica per trovare il contatto con nome e cognome specificati
                for(int ricerca = 0; ricerca < rubricaOccupied && eliminazione < 0; ricerca++)
                    //Se viene trovato un contatto uguale a quello da eliminare
                    if(rubrica[ricerca].equals(ricercaContatto))
                        //Salvare il numero del contatto da eliminare, questo comporta l'uscita dal ciclo for
                        eliminazione = ricerca;

                //Nel caso non sia mai stato trovato un contatto con il nome e cognome specificati allora dare un messaggio di errore
                if(eliminazione < 0)
                    System.out.println("Errore : contatto non trovato nella lista.");
                //Se il contatto e' stato trovato allora procedere con l'eliminazione
                else {
                    System.out.println("Procedimento all'eliminazione del contatto numero " + (eliminazione + 1));
                    //Decrementare la dimensione della rubrica
                    rubricaOccupied--;
                    //Spostare tutti i contatti successivi indietro in modo da eliminare quello corrente
                    for(int contatto = eliminazione; contatto < rubricaOccupied; contatto++)
                        rubrica[contatto] = rubrica[contatto + 1];
                    //L'eliminazione e' terminata con successo
                    System.out.println("Eliminazione terminata con successo");
                    //Attendere la pressione di INVIO
                    System.out.println("Premere INVIO per continuare.");
                    keyboard.nextLine();
                    //Terminare l'eliminazione
                    termineEliminazione = true;
                }
            }
        } while(!termineEliminazione);

        //Ritornare il nuovo valore modificato della modifica della dimensione della rubrica
        return rubricaOccupied;
    }

    /*Passata la rubrica al metodo, esso richiede all'utente che dato e che contatto modificare e permette la modifica.
     * Il metodo modifica direttamente l'array della rubrica passato. La dimensione dell'array non viene modificata.
     * Il parametro della modalita' permette la modifica del numero di telefono se e' 0, altrimenti permette la modifica della tipologia
     * di numero di telefono se e' 1.
     */
    private static void modificaContatto(Contatto[] rubrica, int rubricaOccupied, int mode, Scanner keyboard) {
        //Continuare l'inserimento fino a quando la modifica non viene completata con successo
        boolean modificaCompletata = false;
        do {
            //Richiedere all'utente il contatto da modificare
            int contactNumber = Tools.getUserInput(0, rubricaOccupied, "(Inserire 0 per mostrare tutti i contatti)\nInserire il numero del contatto da modificare : ", keyboard, true, true);

            //Se l'utente ha richiesto l'uscita dall'inserimento allora uscire dalla condizione dello switch
            if(contactNumber < 0)
                return;

            //Se e' stato richiesto che venissero mostrati tutti i contatti salvati allora stamparli
            if(contactNumber == 0)
            {
                System.out.println("Stampa di tutti i contatti :");
                //Iterare in tutti i contatti per iterare
                for(int contatto = 0; contatto < rubricaOccupied; contatto++)
                    System.out.println("=== Contatto numero " + (contatto + 1) + " ===\n" + rubrica[contatto].toString());
                System.out.println("=== TERMINE LISTA ===");
            } else { //Se e' stato selezionato un contatto da visualizzare
                //Se la modalita' specificata e' quella della modifica del numero di telefono
                if(mode == 0)
                {
                    //Richiedere all'utente il nuovo numero di telefono
                    System.out.println("Inserire il nuovo numero di telefono :");
                    String nuovoTelefono = keyboard.nextLine();
                    //Impostare il nuovo numero di telefono nella rubrica
                    rubrica[contactNumber - 1].setNumeroTelefono(nuovoTelefono);
                } else if(mode == 1) //Se la modalita' specificata e' quella di modifica della tipologia del numero di telefono 
                {
                    //Richiedere l'inserimento della tipologia del numero di telefono
                    rubrica[contactNumber - 1].inserimentoTipologiaTelefono("Inserire la nuova tipologia di numero di telefono", keyboard);
                }

                //Il contatto e' stato modificato con successo
                System.out.println("Modifica avvenuta con successo");
                System.out.println("Premere INVIO per continuare.");
                keyboard.nextLine();
                //Impostare la variabile per l'uscita dal ciclo
                modificaCompletata = true;
            }
        } while(!modificaCompletata);
    }

    /*Il metodo, passata la rubrica, richiede all'utente un elemento della rubrica da visualizzare e lo mostra in output.
     * Nessuno degli elementi passati al metodo viene modificato
    */
    private static void visualizzaContatti(Contatto[] rubrica, int rubricaOccupied, Scanner keyboard) {
        //Se non ci sono contatti stampare un messaggio di errore dicendo questo
        if(rubricaOccupied == 0)
        {
            System.out.println("Errore : rubrica vuota.");

            //Attendere che l'utente prema INVIO
            System.out.println("\nPremere INVIO per continuare.");
            keyboard.nextLine();

            //Uscire dal metodo
            return;
        }

        //Richiedere all'utente quale deve essere il contatto da visualizzare
        int contactNumber = Tools.getUserInput(0, rubricaOccupied, "(Inserire 0 per mostrare tutti i contatti)\nInserire il numero del contatto da visualizzare : ", keyboard, true, true);

        //Se l'utente ha richiesto l'uscita dall'inserimento allora uscire dal metodo
        if(contactNumber < 0)
            return;
        
        //Se l'utente ha richiesto che venissero mostrati tutti i contatti
        if(contactNumber == 0)
        {
            System.out.println("Stampa di tutti i contatti salvati :");
            //Iterare in tutti i contatti per iterare
            for(int contatto = 0; contatto < rubricaOccupied; contatto++)
                System.out.println("=== Contatto numero " + (contatto + 1) + " ===\n" + rubrica[contatto].toString());
            System.out.println("=== TERMINE LISTA ===");
        } else { //Altrimenti se e' stato selezionato un contatto specifico
            //Stampare in output il contenuto dell'elemento richiesto
            System.out.println("Anagrafica utente numero " + contactNumber + ":");
            System.out.println(rubrica[contactNumber - 1].toString());
        }

        //Attendere per fare in modo che l'utente possa leggere i dati
        System.out.println("Premere INVIO per continuare");
        keyboard.nextLine();
    }

    /*Il metodo richiede all'utente l'inserimento dell'anagrafica e aggiunge un nuovo contatto alla rubrica passata.
     * Il metodo ritorna il nuovo valore di rubricaOccupied dopo l'aggiungimento del nuovo contatto.
     * Se rubricaOccupied raggiunge il termine dell'array passato, la dimensione dell'array viene raddoppiata.
     * Se il metodo ritorna -1, allora l'utente e' uscito dall'inserimento o il contatto inserito era gia' esistente.
     * La funzione stampa in output e richiede input attraverso lo scanner.
     */
    private static int aggiungiContatto(Contatto[] rubrica, int rubricaOccupied, Scanner keyboard) {
        //Creare un nuovo contatto
        Contatto newContact = new Contatto();
        //Popolamento del contatto
        if (!newContact.inserimentoAnagrafica(keyboard))
            //Se l'inserimento non e' stato completato, allora uscire dalla condizione dello switch senza proseguire
            return -1;
        //Prima di salvare il contatto nell'array controllare che non esista gia' in esso
        boolean contactExists = false; //Vera se il contatto esiste gia'
        for(int check = 0; check < rubricaOccupied && !contactExists; check++)
            contactExists = newContact.equals(rubrica[check]); //Salvare nella variabile di controllo il risultato dell'eguaglianza fra i due oggetti

        //Proseguire con il salvataggio soltanto se il contatto non esiste gia'
        if(contactExists)
        {
            //Stampare un errore per notificare l'utente
            System.out.println("Errore : il contatto specificato esiste gia'");

            //Attendere che l'utente prema INVIO
            System.out.println("\nPremere INVIO per continuare.");
            keyboard.nextLine();

            //Ritornare -1 dalla funzione per indicare un errore
            return -1;
        }

        //Incrementare l'utilizzo dell'array
        rubricaOccupied++;
        //Se abbiamo raggiunto la fine dell'array raddoppiare la dimensione dell'array
        if(rubricaOccupied == rubrica.length)
        {
            //Definire un nuovo array di dimensione doppia
            Contatto[] newRubrica = new Contatto[rubrica.length * 2];
            //Copiare il contenuto dell'array vecchio nel nuovo array
            for(int copyIterator = 0; copyIterator < rubricaOccupied; copyIterator++)
                newRubrica[copyIterator] = rubrica[copyIterator];
            //Impostare il nuovo array creato al posto di quello precedente
            rubrica = newRubrica;
        }
        //Salvataggio del contatto nell'ultimo elemento dell'array
        rubrica[rubricaOccupied - 1] = newContact;

        //Ritornare il nuovo valore di rubricaOccupied
        return rubricaOccupied;
    }

    /*Il metodo richiede all'utente quanti soldi inserire nel contatto ed effettua una ricarica sul contatto determinato 
     * La modifica viene fatta direttamente sull'array di uscita
    */
    private static void ricarica(Contatto[] rubrica, int rubricaOccupied, Scanner keyboard) {
        int numeroUtente = 0;
        boolean numeroContattoInserito = false;
        do {
            //Richiedere all'utente il numero di contatto
            numeroUtente = Tools.getUserInput(0, rubricaOccupied, "(Inserire 0 per mostrare tutti i contatti)\nInserire il numero del contatto a cui effettuare la ricarica : ", keyboard, true, true);
            //Se il numero inserito e' nullo allora stampare tutti i contatti
            if(numeroUtente == 0)
            {
                System.out.println("Stampa di tutti i contatti salvati :");
                //Iterare in tutti i contatti per iterare
                for(int contatto = 0; contatto < rubricaOccupied; contatto++)
                    System.out.println("=== Contatto numero " + (contatto + 1) + " ===\n" + rubrica[contatto].toString());
                System.out.println("=== TERMINE LISTA ===");
            } else numeroContattoInserito = true; //Altrimenti uscire dal ciclo
        } while(!numeroContattoInserito);

        //Richiedere all'utente il valore di ricarica
        double ricarica = 0;
        boolean valoreErrato = false;
        do {
            valoreErrato = false;

            System.out.println("Credito corrente : " + rubrica[numeroUtente - 1].getSaldo() + " Euro");
            System.out.println("Inserire il valore di ricarica : ");
            //Controllo errori per l'inserimento di soltanto numeri
            try {
                ricarica = keyboard.nextDouble();
            } catch (Exception exception) {
                System.out.println("Inserire un numero valido");
                valoreErrato = true;
            }
            
            //Cancellare il buffer dopo la lettura di un numero
            keyboard.nextLine();

            //Proseguire con il controllo soltanto se il valore inserito non era errato
            if(!valoreErrato && ricarica < 0)
            {
                valoreErrato = true;
                System.out.println("Errore : il valore di ricarica deve essere positivo");
            }
        } while(valoreErrato);
        //Incrementare il saldo di quel valore
        rubrica[numeroUtente - 1].ricaricaSaldo(ricarica);
    }
    
    /*Il metodo richiede all'utente il credito di spesa nella chiamata mostrando anche quello rimasto e il credito
     * effettivamente speso tenendo cont di quello rimasto.
    */
    private static void effettuaChiamata(Contatto[] rubrica, int rubricaOccupied, Scanner keyboard) {
        int numeroUtente = 0;
        boolean numeroContattoInserito = false;
        do {
            //Richiedere all'utente il numero di contatto
            numeroUtente = Tools.getUserInput(0, rubricaOccupied, "(Inserire 0 per mostrare tutti i contatti)\nInserire il numero del contatto a cui effettuare la ricarica : ", keyboard, true, true);
            //Se il numero inserito e' nullo allora stampare tutti i contatti
            if(numeroUtente == 0)
            {
                System.out.println("Stampa di tutti i contatti salvati :");
                //Iterare in tutti i contatti per iterare
                for(int contatto = 0; contatto < rubricaOccupied; contatto++)
                    System.out.println("=== Contatto numero " + (contatto + 1) + " ===\n" + rubrica[contatto].toString());
                System.out.println("=== TERMINE LISTA ===");
            } else numeroContattoInserito = true; //Altrimenti uscire dal ciclo
        } while(!numeroContattoInserito);

        //Richiedere all'utente il valore di spesa
        double spesa = 0;
        boolean valoreErrato = false;
        do {
            valoreErrato = false;

            System.out.println("Credito corrente : " + rubrica[numeroUtente - 1].getSaldo() + " Euro");
            System.out.println("Inserire la spesa della chiamata : ");
            //Controllo errori per l'inserimento di soltanto numeri
            try {
                spesa = keyboard.nextDouble();
            } catch (Exception exception) {
                System.out.println("Inserire un numero valido");
                valoreErrato = true;
            }

            //Cancellare il buffer dopo la lettura di un numero
            keyboard.nextLine();

            //Proseguire con il controllo soltanto se il valore inserito non era errato
            if(!valoreErrato && spesa < 0)
            {
                valoreErrato = true;
                System.out.println("Errore : il valore di ricarica deve essere positivo");
            }
        } while(valoreErrato);
        //Effettuare la spesa
        double quantitaSpesa = rubrica[numeroUtente - 1].effettuaSpesa(spesa);
        //Mostrare all'utente la quantita' di soldi effettivamente spesi
        System.out.println("Soldi spesi : " + quantitaSpesa + " Euro");
        //Attendere per dare il tempo all'utente di visualizzare i dati
        System.out.println("Premere INVIO per continuare");
        keyboard.nextLine();
    }

    /*Il metodo salva la rubrica su un file la cui posizione viene specificata
     * dalla stringa passata al metodo.
     */
    private static void salvaFile(String path, Contatto[] rubrica, int rubricaSize) throws IOException {
        //Istanziare la stream per la scrittura in output dei dati su file
        FileWriter fileOutput = new FileWriter(path);

        try {
            //Nella prima riga, scrivere i nomi delle variabili
            fileOutput.write(rubricaSize + " Contatti;Nome;Cognome;Numero di telefono;Utilizzo telefono;Saldo contatto\r\n");
        } catch(IOException exception) {
            fileOutput.close();
            throw exception;
        }

        //Iterare in tutti gli elementi occupati della rubrica
        for(int currentElement = 0; currentElement < rubricaSize; currentElement++) {
            try { //Scrivere il numero del contatto e i suoi dati
                fileOutput.write(currentElement + ";" + rubrica[currentElement].toCSVString() + "\r\n");
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

    /*Passata al metodo la stringa contenente la posizione del file da caricare, ritorna l'array di contatti
     * contenuti nel file richiesto, l'array ritornato e' sempre pieno.
     * Nel caso venga incontrato un errore durante la lettura, viene lanciata un'eccezione.
     */
    private static Contatto[] caricaFile(String path) throws IOException {
        //Oggetto per la rappresentazione del file
        FileReader file = new FileReader(path);
        //Istanziamento dello scanner per la lettura dal file
        Scanner reader = new Scanner(file);
        //Leggere la prima riga del file e salvare il primo numero in esso (contiene il numero di elementi) e convertirlo in intero
        int elementAmount = Integer.valueOf(reader.nextLine().split(" ")[0]);
        //Nuovo array che contiene i dati letti da file
        Contatto[] fileReading = new Contatto[elementAmount];
        int currentElement = 0; //Contiene l'elemento corrente letto dal file

        //Utilizzo dello scanner per la lettura da file
        String currentLine; //Contiene la stringa corrente letta da file
        while(reader.hasNextLine()) {
            //Leggere la prossima riga da file
            currentLine = reader.nextLine();

            //Separare la stringa in un vettore separato da virgole
            String[] singleElements = currentLine.split(";");
            //Creare un nuovo oggetto contatto che contiene i dati ricavati da file
            Contatto newContact = new Contatto(singleElements[1], singleElements[2], singleElements[3], singleElements[4], singleElements[5]);
            //Salvare il nuovo contatto nell'elemento della rubrica, incremntando l'iteratore
            fileReading[currentElement++] = newContact;
        }

        //Chiudere il lettore del file
        reader.close();

        return fileReading;
    }
}
 