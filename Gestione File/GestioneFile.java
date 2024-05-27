import java.util.Scanner;
import java.io.File;
import java.io.FilenameFilter;

public class GestioneFile {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        //Chiedere all'utente il nome del file da cui prendere i dati
        System.out.print("Inserire la directory da cui listare i file: ");
        File directory = new File(keyboard.nextLine());

        //Controllare che l'oggetto sia una directory
        if(!directory.isDirectory())
        {
            System.out.println("Errore: la posizione inserita non si riferisce a una directory valida.");
            keyboard.close();
            return;
        }

        //Chiedere che tipo di nomi file da escludere
        System.out.print("Inserire le estensioni di file da mostrare: ");
        String[] acceptedExtensions = keyboard.nextLine().split(" ");
        FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String fileName) {
                //Accettare il file se e' una directory
                File check = new File(directory, fileName);
                //Se il file e' una directory, accettarlo
                if(check.isDirectory())
                    return true;
                //Scorri nell'array delle estensioni accettate
                for(String currentExtension : acceptedExtensions)
                    if(fileName.endsWith(currentExtension))
                        return true;
                //Se nessuna condizione e' verificata, allora non accettare il file
                return false;
            }
        };

        //Carica il listato dei file nella directory
        File[] files = directory.listFiles(filter);
        //Stampa i file
        System.out.println("Listato file di '" + directory.getPath() + "': ");
        for(File currentFile : files)
        {
            //Stampa nome file
            System.out.print("- " + currentFile.getName());
            //Stampa se e' directory
            if(currentFile.isDirectory())
                System.out.print(" [DIR]");
            //Vai a capo
            System.out.println();
        }
        System.out.println("=== Fine listato ===");

        keyboard.close();
    }
}
