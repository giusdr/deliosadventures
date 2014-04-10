package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

/**
 * This listener will respond to collisions between the player and collectible
 * bodies in the simulation.
 *
 * @author GIUSEPPE
 */
public class CollectibleListener implements CollisionListener {

    private final Player p;
    private final Collectible c;
    private final Level l;
    private static final Music COLLECTIBLE_SOUND = new Music("data/present.wav");

    /**
     *
     * @param l the level in which the player and the score exist
     * @param c the collectible where this CollectibleListener is attached
     */
    public CollectibleListener(Level l, Collectible c) {
        this.p = l.getPlayer();
        this.l = l;
        this.c = c;

    }

    /**
     * Receive and respond to a collision event between the player and the
     * collectible. If the collision take place, it increases the score and
     * destroys the pickup.
     *
     * @param e the collision event description
     */
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() == p) {
            l.getScore().increaseScoreBy(c.getPoints());
            e.getReceivingBody().destroy();
            //System.out.println("Score:" + l.getScore().getScore());
            COLLECTIBLE_SOUND.getMusic().setVolume(0.50);
            COLLECTIBLE_SOUND.getMusic().play();
        }
    }

}
