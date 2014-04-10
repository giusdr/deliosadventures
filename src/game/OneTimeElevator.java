package game;

import city.cs.engine.StepEvent;
import org.jbox2d.common.Vec2;

/**
 * Elevator that works only once.
 *
 * @author Giuseppe
 */
public class OneTimeElevator extends Elevator {

    private final float finishPositionY;

    private boolean activated = false;

    /**
     *
     * @param w the world in which this elevator will exist
     * @param p the position of this body in the world.
     * @param y the distance that the elevator will eventually travel.
     */
    public OneTimeElevator(Level w, Vec2 p, float y) {
        super(w, p);
        finishPositionY = y;

    }

    /**
     * Activates the elevator
     */
    @Override
    public void open() {
        activated = true;
    }

    /**
     * -NOT IMPLEMENTED-
     */
    @Override
    public void close() {
    }

    /**
     *
     * @return the activation state
     */
    public boolean isActivated() {
        return activated;
    }

    /**
     * Called immediately before each simulation step. Moves the elevator if it
     * has been activated.
     *
     * @param se the event descriptor.
     */
    @Override
    public void preStep(StepEvent se) {
        if (isActivated()) {
            position = position.add(INCREMENT);
            super.setPosition(position);
            if (super.getPosition().y >= finishPositionY) {
                this.activated = false;
            }
        }
    }

    /**
     * -NOT IMPLEMENTED - Called immediately after each simulation step.
     *
     * @param se the event descriptor.
     */
    @Override
    public void postStep(StepEvent se) {
    }

}
