import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClearButtonListener implements ActionListener {
    SwingWorkerView view;

    public ClearButtonListener(SwingWorkerView view) {
        this.view = view;
    }

    public void actionPerformed(ActionEvent event)
    {
        view.textArea.setText(null);
    }
}
