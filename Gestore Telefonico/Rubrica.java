import java.util.Scanner;

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
            "Uscita"
        };
        //Contiene i contatti
        Contatto[] rubrica = new Contatto[16];
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
                    //Creare un nuovo contatto
                    Contatto newContact = new Contatto();
                    //Popolamento del contatto
                    newContact.inserimentoAnagrafica(keyboard);
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
                    } else {
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
                    }
                    //Uscire dallo switch
                    break;
                }

                //Se e' stata selezionata la visualizzazione
                case 2 : {
                    //Se non ci sono contatti stampare un messaggio di errore dicendo questo
                    if(rubricaOccupied == 0)
                    {
                        System.out.println("Errore : rubrica vuota.");

                        //Attendere che l'utente prema INVIO
                        System.out.println("\nPremere INVIO per continuare.");
                        keyboard.nextLine();

                        //Uscire dalla condizione dello switch
                        break;
                    }

                    //Richiedere all'utente quale deve essere il contatto da visualizzare
                    int contactNumber = Tools.getUserInput(0, rubricaOccupied, "(Inserire 0 per mostrare tutti i contatti)\nInserire il numero del contatto da visualizzare : ", keyboard, true, true);

                    //Se l'utente ha richiesto che venissero mostrati tutti gli utenti
                    if(contactNumber == 0)
                    {
                        System.out.println("Stampa di tutti i contatti salvati :");
                        //Iterare in tutti i contatti per iterare
                        for(int contatto = 0; contatto < rubricaOccupied; contatto++)
                            System.out.println("=== Contatto numero " + (contatto + 1) + " ===\n" + rubrica[contatto].anagrafica());
                        System.out.println("=== TERMINE LISTA ===");
                    } else {
                        //Stampare in output il contenuto dell'elemento richiesto
                        System.out.println("Anagrafica utente numero " + contactNumber + ":");
                        System.out.println(rubrica[contactNumber - 1].anagrafica());
                    }

                    //Attendere per fare in modo che l'utente possa leggere i dati
                    System.out.println("Premere INVIO per continuare");
                    keyboard.nextLine();
                    
                    //Uscire dallo switch
                    break;
                }

                //Se e' stata selezionata una modifica ad un contatto
                case 3 :
                case 4 :
                {
                    //Continuare l'inserimento fino a quando la modifica non viene completata con successo
                    boolean modificaCompletata = false;
                    do {
                        //Richiedere all'utente il contatto da modificare
                        int contactNumber = Tools.getUserInput(0, rubricaOccupied, "(Inserire 0 per mostrare tutti i contatti)\nInserire il numero del contatto da modificare : ", keyboard, true, true);

                        //Se e' stato richiesto che venissero mostrati tutti i contatti salvati allora stamparli
                        if(contactNumber == 0)
                        {
                            System.out.println("Stampa di tutti i contatti :");
                            //Iterare in tutti i contatti per iterare
                            for(int contatto = 0; contatto < rubricaOccupied; contatto++)
                                System.out.println("=== Contatto numero " + (contatto + 1) + " ===\n" + rubrica[contatto].anagrafica());
                            System.out.println("=== TERMINE LISTA ===");
                        } else {
                            //In base alla selezione del menu dell'utente
                            if(userSelection == 3)
                            {
                                //Richiedere all'utente il nuovo numero di telefono
                                System.out.println("Inserire il nuovo numero di telefono :");
                                String nuovoTelefono = keyboard.nextLine();
                                //Impostare il nuovo numero di telefono nella rubrica
                                rubrica[contactNumber - 1].numeroTelefono = nuovoTelefono;
                            } else if(userSelection == 4) 
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

                    //Uscire dalla condizione dello switch
                    break;
                }

                //Se e' stata selezionata la cancellazione
                case 5 : {
                    //Se non ci sono contatti eliminare un messaggio di errore dicendo questo
                    if(rubricaOccupied == 0)
                    {
                        System.out.println("Errore : rubrica vuota.");

                        //Attendere che l'utente prema INVIO
                        System.out.println("\nPremere INVIO per continuare.");
                        keyboard.nextLine();

                        //Uscire dalla condizione dello switch
                        break;
                    }
                    
                    //Viene impostata a vera quando l'eliminazione e' terminata
                    boolean termineEliminazione = false;
                    do {
                        //Salvare se va effettuata la stampa di tutti i contatti
                        boolean stampaContatti = false;

                        //Richiedere nome e cognome del contatto da eliminare
                        String ricercaNome, ricercaCognome = null;
                        System.out.println("Inserire 'STAMPA' per stampare tutti i contatti");
                        System.out.print("Inserire il nome del contatto da eliminare :");
                        ricercaNome = keyboard.next();
                        //Se l'utente ha richiesto una stampa impostare la variabile apposita
                        if(ricercaNome.toLowerCase().equals("stampa"))
                            stampaContatti = true;
                        else { //Se l'utente non ha richiesto la stampa procedere con la lettura del cognome
                            System.out.print("Inserire il cognome del contatto da eliminare :");
                            ricercaCognome = keyboard.next();
                            //Controllare se nel cognome l'utente richiede la stampa
                            if(ricercaCognome.toLowerCase().equals("stampa"))
                                stampaContatti = true;
                        }

                        //Svuotare il buffer dopo l'utilizzo di next()
                        keyboard.nextLine();

                        //Se l'utente ha richiesto la stampa dei contatti allora stampare ed eseguire una nuova iterazione del ciclo while
                        if(stampaContatti)
                        {
                            System.out.println("Stampa di tutti i contatti :");
                            //Iterare in tutti i contatti per iterare
                            for(int contatto = 0; contatto < rubricaOccupied; contatto++)
                                System.out.println("=== Contatto numero " + (contatto + 1) + " ===\n" + rubrica[contatto].anagrafica());
                            System.out.println("=== TERMINE LISTA ===");
                        } else { //Altrimenti procedere con l'eliminazione
                            //Creare un nuovo contatto contenente nome e cognome specificati per la comparazione
                            Contatto ricercaContatto = new Contatto();
                            ricercaContatto.nome = ricercaNome;
                            ricercaContatto.cognome = ricercaCognome;
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
}
