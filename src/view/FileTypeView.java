package view;

public class FileTypeView {
    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayError(String error) {
        System.err.println(error);
    }

    public void displayExecutionTime(long time) {
        System.out.printf("It took %.3f seconds%n", time / 1e9);
    }
}
