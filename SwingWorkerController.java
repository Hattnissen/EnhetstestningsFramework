import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingWorkerController implements ActionListener {
    private final SwingWorkerModel model;
    private final SwingWorkerView view;

    public SwingWorkerController(SwingWorkerModel model, SwingWorkerView view) {
        this.model = model;
        this.view = view;
    }

    public void actionPerformed(ActionEvent event) {
        SwingUtilities.invokeLater(new Runnable() { public void run() {model.update(view.textField.getText());} });
    }
}