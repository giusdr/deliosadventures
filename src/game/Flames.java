package game;

import city.cs.engine.BodyImage;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

/**
 * This class is an extension of DieZone. Flames that kill the player.
 *
 * @author GIUSEPPE
 */
public class Flames extends DieZone {

    private static final BodyImage IMAGE = new BodyImage("data/flames.png", 10);

    /**
     *
     * @param w the world in which this fixture will exist
     * @param s a shape for this fixture
     * @param p the position of this fixture in the world
     */
    public Flames(Level w, Shape s, Vec2 p) {
        super(w, s, p);
        this.getBody().setImage(IMAGE);

    }

}
