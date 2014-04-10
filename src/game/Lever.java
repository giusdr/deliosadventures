package game;

import city.cs.engine.BodyImage;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

/**
 * Subclass of Interaction. It opens a gate and it switch image if activated
 *
 * @author Giuseppe
 *
 */
public class Lever extends Interaction {

    private static final Shape SHAPE = new PolygonShape(-0.221f, 0.783f, 0.218f, 0.783f, 1.228f, -1.237f, -1.241f, -1.237f);
    private static final BodyImage LEVER_DISABLED = new BodyImage("data/lever.png", 2.5f);
    private static final BodyImage LEVER_ENABLED = new BodyImage("data/lever_active.png", 2.5f);

    /**
     *
     * @param world the world in which this interaction will exist
     * @param target the receiver that will change state upon activation.
     * @param player the player that interacts with the lever.
     */
    public Lever(World world, Receiver target, Player player) {
        super(world, target, SHAPE);
        this.setImg();
        this.initializeSensor(player);

    }

    /**
     *
     * @param world the world in which this interaction will exist
     * @param target The targeted receiver that will change state upon
     * activation.
     * @param position Position of the lever
     * @param player The player that interacts with the lever.
     */
    public Lever(World world, Receiver target, Player player, Vec2 position) {
        super(world, target, SHAPE, position);
        this.setImg();
        this.initializeSensor(player);
    }

    /**
     * Set the image accordingly to the activation state.
     */
    protected final void setImg() {
        if (this.isActivated()) {
            this.getBody().setImage(LEVER_ENABLED);
        } else {
            this.getBody().setImage(LEVER_DISABLED);
        }

    }

    /**
     * Change the state of the receiver.
     */
    @Override
    public void changeState() {
        super.changeState();
        this.setImg();
    }

}
