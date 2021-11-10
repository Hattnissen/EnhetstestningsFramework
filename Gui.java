import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.geom.Dimension2D;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class Gui {

    private JFrame frame;

    private JButton test1Button;

    private Test1 test = new Test1();

    public Gui(String title)
    {
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextArea textArea = buildTextArea();
        JPanel panel = buildTestPanel(textArea);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(textArea, BorderLayout.CENTER);

        frame.getContentPane();
        frame.pack();
    }

    public void show()
    {
        frame.setVisible(true);
    }

    private JPanel buildTestPanel(JTextArea textArea)
    {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Tester"));
        panel.setLayout(new FlowLayout((FlowLayout.LEFT)));

        test1Button = new JButton("Test 1");
        test1Button.addActionListener(new ButtonListener(test, textArea));

        panel.add(test1Button);

        return panel;
    }

    private JTextArea buildTextArea()
    {
        JTextArea textArea = new JTextArea(15,50);
        //textArea.setPreferredSize(new Dimension(100,50));
        textArea.setBorder(BorderFactory.createTitledBorder("Output"));
        textArea.setLayout(new FlowLayout(FlowLayout.LEFT));

        return textArea;
    }
}
