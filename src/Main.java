import controller.FileTypeController;
import view.FileTypeView;

public class Main {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java Main <algorithm> <folder_path> <patterns_file>");
            return;
        }

        String algorithm = args[0];
        String folderPath = args[1];
        String patternsFile = args[2];

        FileTypeView view = new FileTypeView();
        FileTypeController controller = new FileTypeController(view, algorithm, patternsFile);
        controller.checkFileTypes(folderPath);
    }
}