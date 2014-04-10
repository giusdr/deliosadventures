package game;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.SolidFixture;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

/**
 * This is the class for the bridge, that can be placed vertically or
 * horizontally. The state of the bridge will be changed upon activation.
 *
 *
 * @author Giuseppe
 *
 */
public class Bridge extends Receiver {

    private Vec2 position;
    private final float width, height;

    /**
     *
     * @param w the world in which this bridge will exist
     * @param width width of the bridge
     * @param height height of the bridge
     * @param p the position of this body in the world
     */
    public Bridge(World w, float width, float height, Vec2 p) {
        super(w, p);
        this.width = width;
        this.height = height;
        this.setPosition(p);
        new SolidFixture(this, new BoxShape(this.width, this.height)).setFriction(20);
        if (width > height) {
            this.setImage(new BodyImage("data/bridge2.png"));
        } else {
            this.setImage(new BodyImage("data/bridge.png", 40));
        }
        this.getImage().setClipped(true);
    }

    /**
     * Change the state of the bridge from closed to open.
     */
    @Override
    public void open() {
        this.setAngleDegrees(90);
        this.setPosition(new Vec2(position.x - height - width, position.y - height - width));
    }

    /**
     * - Not implemented -
     */
    @Override
    public void close() {
    }

    /**
     * Set the position in the world of this body's origin point and assigns it
     * to a private field. The first component of the parameter is x, the second
     * component is y.
     *
     * @param p The new Position
     */
    @Override
    public final void setPosition(Vec2 p) {
        super.setPosition(p);
        this.position = p;
    }

}
