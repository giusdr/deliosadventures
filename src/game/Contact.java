package game;

import city.cs.engine.SensorEvent;
import city.cs.engine.SensorListener;

/**
 * This listener is applied to each interaction in the game. When the player is
 * on the Sensor, the interaction is given to player, so that by pressing the E
 * button, it can be used.
 *
 * If the player leaves the Sensor, the value of the player's interaction will
 * be set to null.
 *
 * @author Giuseppe
 *
 *
 */
public class Contact implements SensorListener {

    private final Player player;
    private final Interaction interaction;

    /**
     *
     * @param player the player that reacts to the contact.
     * @param i the interaction that will be used by the player.
     */
    public Contact(Player player, Interaction i) {
        this.player = player;
        this.interaction = i;
    }

    /**
     * Respond to solid a fixture beginning to overlap with a sensor.
     *
     * @param e description of the contact
     */
    @Override
    public void beginContact(SensorEvent e) {
        player.isOn(interaction);

    }

    /**
     * Respond to a solid fixture ceasing to overlap with a sensor.
     *
     * @param e description of the contact
     */
    @Override
    public void endContact(SensorEvent e) {
        player.isOn(null);
    }

}
