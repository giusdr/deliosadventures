package game;

import city.cs.engine.BodyImage;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import city.cs.engine.StaticBody;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

/**
 * StaticBody where the player will walk on.
 *
 * @author Giuseppe
 *
 */
public class Platform extends StaticBody {

    private SolidFixture fixture;
    private Shape shape;
    private static final float FRICTION = 20;
    private static final BodyImage IMAGE = new BodyImage("data/vertical.png", 40);

    /**
     *
     * @param w the world in which this platform will exist
     * @param s the shape for this platform
     */
    public Platform(World w, Shape s) {
        super(w);
        this.shape = s;
        fixture = new SolidFixture(this, shape);
        fixture.setFriction(FRICTION);
        this.setImg();
    }

    /**
     *
     * @param w the world in which this platform will exist
     * @param s the shape for this platform
     * @param p the position of this platform in the world
     */
    public Platform(World w, Shape s, Vec2 p) {
        this(w, s);
        this.setPosition(p);

    }

    /**
     *
     * @param w the world in which this platform will exist
     * @param s the shape for this platform
     * @param p the position of this platform in the world
     * @param n the name of this platform
     */
    public Platform(World w, Shape s, Vec2 p, String n) {
        this(w, s, p);
        this.setName(n);
    }

    /**
     *
     * @return get the fixture
     */
    public SolidFixture getFixture() {
        return fixture;
    }

    /**
     * Set a new fixture
     *
     * @param fixture a new solidfixture
     */
    public void setFixture(SolidFixture fixture) {
        this.fixture = fixture;
    }

    /**
     *
     * @return the current shape
     */
    public Shape getShape() {
        return shape;
    }

    /**
     * Set a new shape
     *
     * @param shape a new shape
     */
    public void setShape(Shape shape) {
        this.shape = shape;
    }

    /**
     * Sets the image in the constructor, clipping it to the body.
     */
    public final void setImg() {
        setImage(this.IMAGE);
        this.IMAGE.setClipped(true);
    }

}
