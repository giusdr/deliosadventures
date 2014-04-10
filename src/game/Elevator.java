package game;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.SolidFixture;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import org.jbox2d.common.Vec2;

/**
 * This class is the superclass for all the elevators, static platforms moving
 * up and down.
 *
 * Extends Receiver, since some elevators are going to be activated by user
 * interactions, implements StepListener because it's going to move up and down
 * over time.
 *
 * @author Giuseppe
 *
 */
public abstract class Elevator extends Receiver implements StepListener {

    protected final Vec2 INCREMENT = new Vec2(0, 0.10f);
    private SolidFixture fixture;
    private static final BodyImage IMAGE = new BodyImage("data/elevator.png", 2);
    protected float distance;
    protected Vec2 position;

    /**
     *
     * @param w the world in which this elevator will exist
     * @param p the position of this body in the world.
     */
    public Elevator(Level w, Vec2 p) {
        super(w, p, new BoxShape(4, 0.5f));
        fixture = new SolidFixture(this, new BoxShape(4, 0.5f));
        fixture.setFriction(30);
        this.setImage(IMAGE);
        this.getImage().setClipped(true);
        this.position = p;
    }

    /**
     *
     * @param w the world in which this elevator will exist
     * @param p the position of this body in the world.
     * @param d the distance that the elevator will eventually travel.
     */
    public Elevator(Level w, Vec2 p, int d) {
        this(w, p);
        this.distance = d;
    }

    /**
     * - NOT IMPLEMENTED IN THIS CLASS-.
     */
    @Override
    public abstract void open();

    /**
     * -NOT IMPLEMENTED IN THIS CLASS-.
     */
    @Override
    public abstract void close();

    /**
     * Called immediately before each simulation step.
     *
     * @param se the event descriptor.
     */
    @Override
    public abstract void preStep(StepEvent se);

    /**
     * Called immediately after each simulation step.
     *
     * @param se the event descriptor.
     */
    @Override
    public abstract void postStep(StepEvent se);

}
