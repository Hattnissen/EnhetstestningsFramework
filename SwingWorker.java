import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

class SwingWorkerModel implements PropertyChangeListener {
    private int value;
    private ArrayList<ChangeListener> listeners;
    private SwingWorkerTask task;

    public SwingWorkerModel (int value)
    {
        this.value = value;
        this.listeners = listeners;
        // this.task = null;
    }

    public int getValue()
    {
        return value;
    }

    public void addListener (ChangeListener listener)
    {
        listeners.add(listener);
    }

    synchronized public void update()
    {
        if (task != null)  // no concurrent tasks
            return;

        task = new SwingWorkerTask(value);
        task.addPropertyChangeListener(this);
        task.execute();
    }
    public void propertyChange(PropertyChangeEvent event)
    {
        if (task == null)
            return;
        if (task != event.getSource())
            return;

        System.out.println("Det gick");
    }
}

class SwingWorkerTask extends SwingWorker<Integer,Integer>
{
    private int value;

    public SwingWorkerTask (int value)
    {
        this.value = value;
    }

    protected Integer doInBackground()
    {
        System.out.println("In doInBackground");
        return value;
    }
}
/*
class SwingWorkerView extends JPanel implements ChangeListener
{
    private static final long serialVersionUID = 1L;

    private final SwingWorkerModel model;

    public SwingWorkerView(SwingWorkerModel model)
    {
        this.model = model;
        setPreferredSize(new Dimension(800,800));
    }

    public void paint(Graphics g)
    {
        super.paint(g);

    }
}*/