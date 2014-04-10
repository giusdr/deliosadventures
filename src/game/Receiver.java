package game;

import city.cs.engine.Fixture;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

/**
 * This is the superclass for every different receiver (gates,elevators, etc)
 * that provides standard open an close methods.
 *
 * @author Giuseppe
 *
 *
 */
public abstract class Receiver extends StaticBody {

    private Fixture fixture;

    /**
     *
     * @param w the world in which this receiver will exist
     * @param p the position of this receiver in the world
     */
    public Receiver(World w, Vec2 p) {
        super(w);
        this.setPosition(p);
    }

    /**
     *
     * @param w the world in which this receiver will exist
     * @param p the position of this receiver in the world
     * @param s the shape of this receiver.
     */
    public Receiver(World w, Vec2 p, Shape s) {
        super(w, s);
        this.setPosition(p);
    }

    /**
     * Implements an opening feature for the receiver
     */
    public abstract void open();

    /**
     * Implements a closing feature for the receiver
     */
    public abstract void close();

    /**
     * Set a new fixture
     *
     * @param fixture a new fixture for this receiver
     */
    public void setFixture(Fixture fixture) {
        this.fixture = fixture;
    }

    /**
     *
     * @return the current fixture.
     */
    public Fixture getFixture() {
        return this.fixture;
    }
}
