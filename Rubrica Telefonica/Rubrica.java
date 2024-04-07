import java.util.Scanner;

public class Rubrica {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        //Contiene i dati da stampare nel menu
        String[] menuContent = {
            "Rubrica Telefonica",
            "Inserimento",
            "Visualizzazione",
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
                    boolean invalidData;
                    int contactNumber = 0;
                    do {
                        invalidData = false;
                        //Richiedere all'utente il numero del contatto
                        System.out.println("Inserire il numero del contatto da visualizzare:");
                        try {
                            contactNumber = keyboard.nextInt();
                        } catch(Exception error) {
                            //Svuoare il buffer
                            keyboard.nextLine();
                            //Se il valore non e' numerico dare un errore
                            invalidData = true;
                            System.out.println("Errore : il valore inserito deve essere un numero");
                        }

                        //Continuare con il controllo soltanto se il valore e' numerico
                        if(!invalidData && (contactNumber < 0 || contactNumber >= rubricaOccupied))
                        {
                            //Se il numero inserito non e' corretto allora stampare un errore e ripetere la richiesta
                            System.out.println("Errore : il numero inserito deve essere compreso tra 0 e " + (rubricaOccupied - 1));
                            invalidData = true;
                        }
                    } while(invalidData);

                    //Svuotare il buffer di input dopo la lettura di un numero
                    keyboard.nextLine();

                    //Stampare in output il contenuto dell'elemento richiesto
                    System.out.println("Anagrafica utente numero " + contactNumber + ":");
                    System.out.println(rubrica[contactNumber].anagrafica());

                    //Attendere per fare in modo che l'utente possa leggere i dati
                    System.out.println("Premere INVIO per continuare");
                    keyboard.nextLine();
                    
                    //Uscire dallo switch
                    break;
                }
                case 3 : {
                    //Impostare la variabile per l'uscita dal ciclo
                    System.out.println("Chiusura programma");
                    progEnd = true;
                    break;
                }
            }
        } while(!progEnd);
    }
}