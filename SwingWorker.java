import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

class SwingWorkerModel implements PropertyChangeListener
{
    private int value;
    private ArrayList<ChangeListener> listeners;
    public ArrayList<String> resultsMessages;
    public ArrayList<String> results;
    public ArrayList<String> exceptions;
    private int progress;
    private SwingWorkerUpdateTask task;
    String test = null;

    public SwingWorkerModel ()
    {
        this.value = value;
        this.listeners = new ArrayList<>();
        this.resultsMessages = new ArrayList<>();
        this.results = new ArrayList<>();
        this.exceptions = new ArrayList<>();
        this.progress = -1;
        this.task = null;
        this.test = test;
    }

    public int getValue()
    {
        return value;
    }

    public int getProgress()
    {
        return progress;
    }

    public void addListener (ChangeListener listener)
    {
        listeners.add(listener);
    }

    synchronized public void update(String test)
    {
        if(task != null)
            return;

        System.out.println("model.update");
        this.test = test;
        task = new SwingWorkerUpdateTask(value);
        task.addPropertyChangeListener(this);
        task.execute();
        System.out.println("Kört model.update");
    }

    public void propertyChange(PropertyChangeEvent event)
    {
        System.out.println("In propertyChange");
        if (task == null)
            return;
        if (task != event.getSource())
            return;


        if(task.isDone())
        {
            System.out.println("I isDone");
            runTest1();
            /*try
            {
                runTest1();
            }
            catch(InterruptedException | ExecutionException e)
            {
                String exception;
                if(Thread.interrupted())
                {
                    exception = "Thread was interrupted while running test";
                    exceptions.add(exception);
                    e.printStackTrace();
                }

            }*/
            System.out.println("Kört runTest1");
            task = null;
            progress = -1;
        }

        ChangeEvent e = new ChangeEvent(this);
        for (ChangeListener listener : listeners)
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    listener.stateChanged(e);
                }
            });
    }

    public void runTest1()
    {
        Test1 test = new Test1();
        int success = 0;
        int fail = 0;
        int failByException = 0;

        test.setUp();
        if (test.testInitialisation()) {
            resultsMessages.add("testInitialisation: SUCCESS\n");
            success++;
        }
        test.tearDown();

        test.setUp();
        if (test.testIncrement()) {
            resultsMessages.add("testIncrement: SUCCESS\n");
            success++;
        }
        test.tearDown();

        test.setUp();
        if (test.testDecrement()) {
            resultsMessages.add("testDecrement: SUCCESS\n");
            success++;
        }
        test.tearDown();

        test.setUp();
        try {
            if (test.testFailingByException()) {
                resultsMessages.add("testFailingByException: SUCCESS\n");
            } else {
                resultsMessages.add("testFailingByException: FAIL\n");
            }
        } catch (NullPointerException exception) {
            resultsMessages.add("testFailingByException: FAIL Generated a Java.lang.NullPointerException\n");
            failByException++;
        } finally {
            test.tearDown();
        }

        test.setUp();
        if (!test.testFailing()) {
            resultsMessages.add("testFailing: FAIL\n");
            fail++;
        }
        test.tearDown();

        resultsMessages.add("\n");
        results.add(Integer.toString(success));
        results.add(" tests succeeded\n");
        results.add(Integer.toString(fail));
        results.add(" tests failed\n");
        results.add(Integer.toString(failByException));
        results.add(" tests failed because of an exception\n");
    }
}

class SwingWorkerUpdateTask extends SwingWorker<Integer,Integer>
{
    private int value;

    //Constructor
    public SwingWorkerUpdateTask (int value)
    {
        this.value = value;
    }

    protected Integer doInBackground()
    {
        System.out.println("In doInBackground");
        for (int progress=0; progress<100; progress++)
        {
            try
            {
                Thread.sleep(20);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            value++;
            setProgress(progress);
        }
        System.out.println("Kört doInBackground");
        return value;
    }
}

class SwingWorkerView extends JPanel implements ChangeListener
{
    private static final long serialVersionUID = 1L;

    public JPanel panel;
    public JTextField textField;
    public JButton runButton;
    public JTextArea textArea;

    private Test1 test = new Test1();

    private final SwingWorkerModel model;

    public SwingWorkerView(SwingWorkerModel model)
    {
        this.model = model;

        textArea = new JTextArea(15,50);
        textArea.setBorder(BorderFactory.createTitledBorder("Output"));
        textArea.setLayout(new FlowLayout(FlowLayout.CENTER));

        panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Tester"));
        panel.setLayout(new FlowLayout((FlowLayout.LEFT)));

        textField = new JTextField("", 30);
        textField.setLayout(new FlowLayout(FlowLayout.RIGHT));

        runButton = new JButton("Run");

        panel.add(textField);
        panel.add(runButton);
    }

    public void stateChanged(ChangeEvent event)
    {
        System.out.println("I stateChanged");
        for(String resultMessage : model.resultsMessages)
        {
            textArea.append(resultMessage);
        }
        for(String result : model.results)
        {
            textArea.append(result);
        }
        System.out.println("Kört stateChanged");
    }

}

class SwingWorkerController implements ActionListener {
    private final SwingWorkerModel model;
    private final SwingWorkerView view;

    public SwingWorkerController(SwingWorkerModel model, SwingWorkerView view) {
        this.model = model;
        this.view = view;
    }

    public void actionPerformed(ActionEvent event) {
        String test = view.textField.getText();
        System.out.println("test= " + test);

        if (test.equals("Test1")) {
            System.out.println("controller event: model update requested");
            SwingUtilities.invokeLater(new Runnable() { public void run() {model.update(test);} });
        } else if (test == "Test2") {
            System.out.println("controller event: model update requested");
            SwingUtilities.invokeLater(new Runnable() { public void run() {model.update(test);} });
        } else if (test == "Test3") {
            System.out.println("controller event: model update requested");
            SwingUtilities.invokeLater(new Runnable() { public void run() {model.update(test);} });
        }
    }
}