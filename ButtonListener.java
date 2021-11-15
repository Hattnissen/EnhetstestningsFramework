import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonListener implements ActionListener
{
    SwingWorkerView view;
    public ButtonListener(SwingWorkerView view)
    {
        this.view = view;
    }

    public void actionPerformed(ActionEvent event)
    {
        view.textField.setText("\r");
        /*
        String text = textField.getText();

        if(text == "Test1") {
            //new Worker().execute();
            Test1 test = new Test1();
            int success = 0;
            int fail = 0;
            int failByException = 0;

            test.setUp();
            if (test.testInitialisation()) {
                textArea.append("testInitialisation: SUCCESS\n");
                success++;
            }
            test.tearDown();

            test.setUp();
            if (test.testIncrement()) {
                textArea.append("testIncrement: SUCCESS\n");
                success++;
            }
            test.tearDown();

            test.setUp();
            if (test.testDecrement()) {
                textArea.append("testDecrement: SUCCESS\n");
                success++;
            }
            test.tearDown();

            test.setUp();
            try {
                if (test.testFailingByException()) {
                    textArea.append("testFailingByException: SUCCESS\n");
                } else {
                    textArea.append("testFailingByException: FAIL\n");
                }
            } catch (NullPointerException exception) {
                textArea.append("testFailingByException: FAIL Generated a Java.lang.NullPointerException\n");
                failByException++;
            } finally {
                test.tearDown();
            }

            test.setUp();
            if (!test.testFailing()) {
                textArea.append("testFailing: FAIL\n");
                fail++;
            }
            test.tearDown();

            textArea.append("\n");
            textArea.append(Integer.toString(success) + " tests succeeded\n");
            textArea.append(Integer.toString(fail) + " tests failed\n");
            textArea.append(Integer.toString(failByException) + " tests failed because of an exception\n");
        }*/
    }
}
