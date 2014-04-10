package game;

import city.cs.engine.SensorEvent;
import city.cs.engine.SensorListener;

/**
 * This listener is going to switch to the next level if the current is
 * completed.
 *
 * @author Giuseppe
 */
public class SwitchLevelListener implements SensorListener {

    private final Player p;
    private final Level l;
    private final Game g;

    /**
     *
     * @param g the current game
     * @param l the current level
     */
    public SwitchLevelListener(Game g, Level l) {
        this.g = g;
        this.l = l;
        this.p = l.getPlayer();

    }

    /**
     * If there's contact and the current level is completed, it goes to next
     * level.
     *
     * @param e description of the sensor event
     */
    @Override
    public void beginContact(SensorEvent e) {
        if (e.getContactBody() == p && l.isCompleted()) {
            e.getSensor().removeSensorListener(this);
            g.goNextLevel();
        }
    }

    /**
     * -NOT IMPLEMENTED-
     *
     * @param e description of the sensor event.
     */
    @Override
    public void endContact(SensorEvent e) {
    }

}
