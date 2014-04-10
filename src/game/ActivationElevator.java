package game;

import city.cs.engine.StepEvent;
import org.jbox2d.common.Vec2;

/**
 * A type of Elevator that moves after activation.
 *
 * @author Giuseppe
 */
public class ActivationElevator extends Elevator {

    private final float startingY, maxY;
    private boolean activated;

    /**
     *
     * @param w the world in which this elevator will exist
     * @param p the position of this body in the world.
     * @param d the distance that the elevator will travel on the y-axis.
     */
    public ActivationElevator(Level w, Vec2 p, int d) {
        super(w, p, d);
        activated = false;
        maxY = this.getPosition().y + distance;
        startingY = this.getPosition().y;
    }

    /**
     * Called immediately before each simulation step.
     *
     * @param se the event descriptor.
     */
    @Override
    public void preStep(StepEvent se) {
        if (activated) {
            position = position.add(INCREMENT);
            this.setPosition(position);
            if (this.getPosition().y >= maxY || this.getPosition().y <= startingY) {
                INCREMENT.y = -INCREMENT.y;
            }
        }
    }

    /**
     * Method that activates the elevator.
     */
    @Override
    public void open() {
        if (!activated) {
            activated = true;
        }
    }

    /**
     * -NOT IMPLEMENTED-.
     *
     */
    @Override
    public void close() {
    }

    /**
     * -NOT IMPLEMENTED-.
     *
     */
    @Override
    public void postStep(StepEvent se) {
    }

}
