package game;

import city.cs.engine.BodyImage;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import org.jbox2d.common.Vec2;

/**
 * Pickup with its own shape, points and image
 *
 * @author Giuseppe
 */
public class Present extends Collectible {

    private static final Shape SHAPE = new PolygonShape(-0.9f, -0.793f, -0.929f, 0.704f, 0.064f, 1.234f, 0.996f, 0.627f, 0.913f, -0.784f);
    private static final BodyImage IMAGE = new BodyImage("data/present.png", 2.5f);

    /**
     *
     * @param w the world in which the present exists
     * @param p the player that can collect the present
     */
    public Present(Level w, Player p) {
        super(w, p);
        new SolidFixture(this, SHAPE);
        this.setImage(IMAGE);
        this.setPoints(10);
    }

    /**
     *
     * @param w the world in which the present exists
     * @param p the player that can collect the present
     * @param v the position of the present in the world
     */
    public Present(Level w, Player p, Vec2 v) {
        this(w, p);
        this.setPosition(v);
    }

}
