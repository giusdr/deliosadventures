package game;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

/**
 * Body that teleports the player to a TeleportOut object.
 *
 * @author Giuseppe
 */
public class TeleportIn extends Interaction {

    private static final BodyImage IMAGE = new BodyImage("data/teleportin.png", 2.5f);
    private static final Shape SHAPE = new BoxShape(1, 0.5f);

    /**
     *
     * @param world the world in which this body will exist
     * @param target targeted teleport
     * @param pl the player to be teleported.
     */
    public TeleportIn(World world, Receiver target, Player pl) {
        super(world, target, SHAPE);
        this.getBody().setImage(IMAGE);
        this.initializeSensor(pl);
    }

    /**
     *
     * @param world the world in which this body will exist
     * @param target targeted teleport
     * @param pl the player to be teleported.
     * @param p the position of this teleport
     */
    public TeleportIn(World world, Receiver target, Player pl, Vec2 p) {
        super(world, target, SHAPE, p);
        this.initializeSensor(pl);
        this.getBody().setImage(IMAGE);
    }
    /**
     * Reset the activation state after the player is teleported away.
     */
    @Override
    public void changeState() {
        super.changeState();
        this.setActivated(false);
    }

}
