package game;

import city.cs.engine.Body;
import city.cs.engine.GhostlyFixture;
import city.cs.engine.Sensor;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

/**
 * Interactions are all the GhostlyFixtures that the player can use.
 *
 * Basically, an interaction is placed in the world and it creates a sensor of
 * the same fixture's shape.
 *
 * Interactions are going to activate Receivers.
 *
 * @author Giuseppe
 *
 *
 */
public abstract class Interaction extends GhostlyFixture {

    private Body body;
    private World world;
    private Receiver target;
    private boolean activated;
    private Sensor sensor;
    private static final Music CLICK = new Music("data/click.wav");

    /**
     *
     * @param w the world in which this fixture exists
     * @param t the receiver to be modified
     * @param s a shape for this fixture
     */
    public Interaction(World w, Receiver t, Shape s) {
        super(new StaticBody(w), s);
        activated = false;
        target = t;
        sensor = new Sensor(this.getBody(), s);
        CLICK.getMusic().setVolume(0.8);
    }

    /**
     *
     * @param w the world in which this fixture exists
     * @param t the receiver to be modified
     * @param s a shape for this fixture
     * @param b boolean value that specifies the state of the activation
     */
    public Interaction(World w, Receiver t, Shape s, boolean b) {
        this(w, t, s);
        activated = b;
    }

    /**
     *
     * @param w the world in which this fixture exists
     * @param t the receiver to be modified
     * @param s a shape for this fixture
     * @param p the position of this fixture in the world
     */
    public Interaction(World w, Receiver t, Shape s, Vec2 p) {
        this(w, t, s);
        this.setPosition(p);
    }

    /**
     *
     * @param w the world in which this fixture exists
     * @param t the receiver to be modified
     * @param s a shape for this fixture
     * @param p the position of this fixture in the world
     * @param b boolean value that specifies the state of the activation
     */
    public Interaction(World w, Receiver t, Shape s, Vec2 p, boolean b) {
        this(w, t, s, b);
        this.setPosition(p);
    }

    /**
     *
     * @return the sensor of the interaction.
     */
    public Sensor getSensor() {
        return sensor;
    }

    /**
     * Initialize the sensor attached to this fixture.
     *
     * @param p the player that will use the interaction.
     */
    public void initializeSensor(Player p) {
        this.sensor.addSensorListener(new Contact(p, this));
    }

    /**
     * Changes the state of the receiver.
     */
    public void changeState() {
       // System.out.println("Before: " + activated);
        activated = !activated;
        CLICK.getMusic().play();
        //System.out.println("After: " + activated);
        if (activated) {
            target.open();
        } else {
            target.close();
        }
    }

    /**
     * Set a new position for this fixture.
     *
     * @param p a new position
     */
    public final void setPosition(Vec2 p) {
        this.getBody().setPosition(p);
    }

    /**
     *
     * @return the position of this fixture in the world.
     */
    public Vec2 getPosition() {
        return this.getBody().getPosition();
    }

    /**
     *
     * @return a boolean value representing the activation state of the
     * interaction.
     */
    public boolean isActivated() {
        return activated;
    }
    /**
     * Set the activation parameter
     * @param a a new value for activation
     */
    public void setActivated(Boolean a) {
        this.activated = a;
    }

    /**
     *
     * @return the world.
     */
    public World getWorld() {
        return world;
    }
    

}
