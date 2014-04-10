package game;

import city.cs.engine.BodyImage;
import city.cs.engine.CircleShape;
import city.cs.engine.DynamicBody;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import org.jbox2d.common.Vec2;

/**
 * This is a bouncing fire ball, if it hits the player, he dies.
 *
 * @author Giuseppe
 *
 */
public class Ball extends DynamicBody {

    private final SolidFixture ballFixture;
    private static final Shape BALL_SHAPE = new CircleShape(1.25f);
    private static final BodyImage IMAGE = new BodyImage("data/fireball.png", 3);

    /**
     *
     * @param world The world where the Ball will exist
     * @param player The Player that checks the collision.
     * @param p The position in the World.
     */
    public Ball(Level world, Player player, Vec2 p) {
        super(world, BALL_SHAPE);
        this.setPosition(p);
        this.addCollisionListener(new DamageZoneContact(player));
        this.ballFixture = new SolidFixture(this, BALL_SHAPE);
        this.ballFixture.setRestitution(1);
        this.setImage(IMAGE);

    }

}
