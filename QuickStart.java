import javax.swing.*;

public class QuickStart {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            Gui gui = new Gui("Tester");
            gui.show();
        });
    }
}