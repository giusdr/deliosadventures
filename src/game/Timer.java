package game;

import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Timer which starts when the Level starts, and is shown in the GUI.
 *
 * @author Giuseppe
 */
public class Timer extends Model implements StepListener {

    private long time;
    private int minutes = 0;
    private int seconds = 0;
    private String currentTime = null;

    /**
     *
     * @param l Level that starts the timer.
     */
    public Timer(Level l) {
        super();
        time = l.getStartingTime();
    }

    /**
     *
     * @return current seconds
     */
    public int getSeconds() {
        return seconds;
    }

    /**
     *
     * @return current minutes
     */
    public int getMinutes() {
        return minutes;
    }

    /**
     * Called immediately before each simulation step.
     *
     * @param se the event descriptor.
     */
    @Override
    public void preStep(StepEvent se) {
        long now = System.currentTimeMillis();
        seconds = (int) (now - time) / 1000;
        if (seconds > 59) {
            time = now;
            minutes++;
        }
        currentTime = (minutes + " : " + seconds);
        changed();
    }

    /**
     *
     * @return a string representing the current time.
     */
    public String getCurrentTime() {
        return currentTime;
    }

    /**
     * -NOT IMPLEMENTED- Called immediately after each simulation step.
     *
     * @param se the event descriptor.
     */
    @Override
    public void postStep(StepEvent se) {
    }

    /**
     * Notifies the change listeners that it's changed.
     */
    @Override
    protected void changed() {
        ChangeEvent e = new ChangeEvent(this);
        for (ChangeListener l : listeners) {
            l.stateChanged(e);
        }
    }

}
