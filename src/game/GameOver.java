package game;

import city.cs.engine.Body;
import city.cs.engine.BodyImage;
import city.cs.engine.GhostlyFixture;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

/**
 * Image appearing when the player dies.
 *
 * @author GIUSEPPE
 */
public class GameOver extends GhostlyFixture {

    private static final BodyImage IMAGE = new BodyImage("data/gameover.png", 15);

    /**
     *
     * @param game the game where everything happens into
     * @param body the body for this class
     * @param shape the shape of this body
     */
    public GameOver(Game game, Body body, Shape shape) {
        super(body, shape);
        body.setImage(IMAGE);
        body.setPosition(game.getViewTracker().getCentre().add(new Vec2(0, 4)));
    }
}
