package game;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.GhostlyFixture;
import city.cs.engine.StaticBody;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

/**
 * Indications if you get lost!
 *
 * @author Giuseppe
 */
public class TipsBoard extends GhostlyFixture {

    private static final BodyImage IMAGE = new BodyImage("data/board.png", 10);

    /**
     *
     * @param w the world in which this fixture will exist
     * @param p the position of this fixture
     */
    public TipsBoard(World w, Vec2 p) {
        super(new StaticBody(w), new BoxShape(3, 3));
        this.getBody().setImage(IMAGE);
        this.getBody().setPosition(p);
    }

}
