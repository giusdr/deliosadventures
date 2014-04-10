package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * Class to make a body walk at a given speed.
 */
public class Walker implements StepListener {

    private final DynamicBody body;
    private final float speed;
    private final Vec2 position;

    /**
     * Create a walker for the given body.
     *
     * @param body the body to be made to walk
     * @param speed the speed at which it is to walk
     */
    public Walker(DynamicBody body, float speed) {
        this.position = new Vec2();
        this.body = body;
        this.speed = speed;
    }

    /**
     * This method is called before each physics step, and sets the horizontal
     * component of the body's velocity to the required speed.
     *
     * @param e description of the step
     */
    @Override
    public void preStep(StepEvent e) {
        Vec2 v = body.getLinearVelocity();
        position.x = speed;
        position.y = v.y;
        body.setLinearVelocity(position);
    }

    /**
     * This method is called after each physics step. There is nothing to do
     * here.
     *
     * @param e description of the step
     */
    @Override
    public void postStep(StepEvent e) {
    }
}
