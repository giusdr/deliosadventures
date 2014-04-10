package game;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

/**
 * This sensor enables ActivationElevators.
 *
 * @author Giuseppe
 *
 */
public class ElevatorSensor extends Interaction {

    private static final Shape SHAPE = new BoxShape(0.5f, 0.5f);

    /**
     *
     * @param w the world in which this sensor will exist
     * @param t the elevator to be enabled
     * @param v the position of this sensor in the world
     * @param p the player of the current world
     */
    public ElevatorSensor(World w, Receiver t, Vec2 v, Player p) {
        super(w, t, SHAPE, v);
        this.getBody().setImage(new BodyImage("data/button.png"));
        this.initializeSensor(p);
    }

}
