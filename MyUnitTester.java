import javax.swing.*;
import java.awt.*;

public class MyUnitTester
{
    public static void main(String[] args)
    {
        try
        {
            SwingWorkerModel model = new SwingWorkerModel();
            SwingWorkerView view = new SwingWorkerView(model);
            SwingWorkerController controller = new SwingWorkerController(model, view);

            JFrame frame = new JFrame("MyUnitTester");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            frame.add(view.topPanel, BorderLayout.NORTH);
            frame.add(view.textArea, BorderLayout.CENTER);
            frame.add(view.bottomPanel, BorderLayout.SOUTH);

            frame.getContentPane();
            frame.pack();

            model.addListener(view);
            view.textField.addActionListener(controller);
            view.runButton.addActionListener(new RunButtonListener(view, model));
            view.clearButton.addActionListener(new ClearButtonListener(view));

            frame.setVisible(true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}