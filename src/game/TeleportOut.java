package game;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

/**
 * Body where the player will be teleported onto by the TeleportIn.
 *
 * @author Giuseppe
 */
public class TeleportOut extends Receiver {

    private static final BodyImage IMAGE = new BodyImage("data/teleportout.png", 2.5f);
    private static final Shape SHAPE = new BoxShape(1.25f, 0.5f);
    private final Player p;

    /**
     *
     * @param w the world in which this body will exist
     * @param p the player to be teleported.
     * @param position the position of the teleport.
     */
    public TeleportOut(World w, Player p, Vec2 position) {
        super(w, position, SHAPE);
        this.p = p;
        this.setImage(IMAGE);
    }

    /**
     * Put the player on this body.
     */
    @Override
    public void open() {
        this.p.putOn(this);
    }

    /**
     * -NOT IMPLEMENTED-
     */
    @Override
    public void close() {
    }

}
