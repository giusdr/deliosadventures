package game;

import city.cs.engine.StepEvent;
import org.jbox2d.common.Vec2;

/**
 * A type of elevator that moves continuously since it is created in the world.
 *
 * @author Giuseppe
 *
 */
public class AutomaticElevator extends Elevator {

    private final float maxY, minY;

    /**
     *
     * @param w the world in which this elevator will exist
     * @param p the position of this body in the world.
     * @param d the distance in the y-axis from the starting point to the max
     * and minimum height.
     */
    public AutomaticElevator(Level w, Vec2 p, int d) {
        super(w, p, d);
        maxY = this.getPosition().y + distance;
        minY = this.getPosition().y - distance;
        position = this.getPosition();

    }

    /**
     * Called immediately before each simulation step.
     *
     * @param se the event descriptor.
     */
    @Override
    public void preStep(StepEvent se) {
        position = position.add(INCREMENT);
        this.setPosition(position);
        if (this.getPosition().y >= maxY || this.getPosition().y <= minY) {
            INCREMENT.y = -INCREMENT.y;
        }
    }

    /**
     * - NOT IMPLEMENTED - Called immediately after each simulation step.
     *
     * @param se the event descriptor.
     */
    @Override
    public void postStep(StepEvent se) {
    }

    /**
     * -NOT IMPLEMENTED-
     */
    @Override
    public void open() {

    }

    /**
     * -NOT IMPLEMENTED-
     *
     */
    @Override
    public void close() {
    }

}
