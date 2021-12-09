import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class SwingWorkerModel implements PropertyChangeListener {
    private ArrayList<ChangeListener> listeners;
    public ArrayList<String> resultsMessages;
    public ArrayList<String> results;
    public ArrayList<String> exceptions;
    private SwingWorkerTask task;
    public String test = null;

    public SwingWorkerModel() {
        this.listeners = new ArrayList<>();
        this.resultsMessages = new ArrayList<>();
        this.results = new ArrayList<>();
        this.exceptions = new ArrayList<>();
        this.task = null;
        this.test = test;
    }

    public void addListener(ChangeListener listener) {
        listeners.add(listener);
    }

    synchronized public void update(String test) {
        if (task != null)
            return;

        System.out.println("model.update");
        this.test = test;
        task = new SwingWorkerTask(this);
        task.addPropertyChangeListener(this);
        task.execute();
        System.out.println("Kört model.update");
    }

    public void propertyChange(PropertyChangeEvent event) {
        System.out.println("In propertyChange");
        if (task == null)
            return;
        if (task != event.getSource())
            return;

        if (task.isDone()) {
            System.out.println("I isDone");
            task = null;
        }

        ChangeEvent e = new ChangeEvent(this);
        for (ChangeListener listener : listeners)
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    listener.stateChanged(e);
                }
            });
    }
}