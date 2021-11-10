import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener
{
    private Test1 test;
    private JTextArea textArea;

    public ButtonListener(Test1 test, JTextArea textArea)
    {
        this.test = test;
        this.textArea = textArea;
    }

    public void actionPerformed(ActionEvent event)
    {
        test.setUp();
        textArea.append("SetUp completed\n");
        if(test.testInitialisation())
        {
            textArea.append("testInitialisation: SUCCESS\n");
        }
        if(test.testIncrement())
        {
            textArea.append("testIncrement: SUCCESS\n");
        }
        if(test.testDecrement())
        {
            textArea.append("testDecrement: SUCCESS\n");
        }
        try {
            if (!test.testFailingByException()) {
                textArea.append("FAIL Generated a Java.lang.NullPointerException\n");
            }
            else
            {
                textArea.append("testFailingByException: SUCCESS\n");
            }
        }
        catch (NullPointerException exception)
            {
                textArea.append("FAIL Generated a Java.lang.NullPointerException\n");
            }

        if (!test.testFailing()) {
            textArea.append("FAIL\n");
        }
        test.tearDown();
        textArea.append("TearDown completed\n");
    }
}
