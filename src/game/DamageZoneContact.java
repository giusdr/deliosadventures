package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.SensorEvent;
import city.cs.engine.SensorListener;

/**
 * This class provides collision and sensor detectors for the various
 * adversities in the game.
 *
 * @author Giuseppe
 */
public class DamageZoneContact implements SensorListener, CollisionListener {

    private final Player p;

    /**
     *
     * @param p the player that reacts to the contact.
     */
    public DamageZoneContact(Player p) {
        this.p = p;
    }

    /**
     * Respond to a solid fixture ceasing to overlap with a sensor.
     *
     * @param se description of the contact
     */
    @Override
    public void beginContact(SensorEvent se) {
        p.die();
    }

    /**
     * -NOT IMPLEMENTED IN THIS CLASS - Respond to a solid fixture ceasing to
     * overlap with a sensor.
     *
     * @param se description of the contact
     */
    @Override
    public void endContact(SensorEvent se) {
    }

    /**
     * Receives and responds to a collision between the player and the hostile
     * body.
     *
     * @param ce the collision event description
     */
    @Override
    public void collide(CollisionEvent ce) {
        if (ce.getOtherBody() == p) {
            p.die();
        }
    }

}
