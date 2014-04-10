package game;

import city.cs.engine.DynamicBody;
import city.cs.engine.Shape;

/**
 * This subtype of DynamicBody is the superclass of all the pickups that
 * increase the player's score.
 *
 * @author Giuseppe
 *
 */
public abstract class Collectible extends DynamicBody {

    private Player p;
    private int points;

    /**
     *
     * @param w the world in which this bridge will exist
     * @param p the player that will collide with this body
     */
    public Collectible(Level w, Player p) {
        super(w);
        this.p = p;
        this.addCollisionListener(new CollectibleListener(w, this));
    }

    /**
     *
     * @param w the world in which this bridge will exist
     * @param p the player that will collide with this body
     * @param s the initial shape of this body
     *
     */
    public Collectible(Level w, Player p, Shape s) {
        super(w, s);
        this.p = p;
        this.addCollisionListener(new CollectibleListener(w, this));

    }

    /**
     *
     * @return Get how many points is this worth
     */
    public int getPoints() {
        return this.points;
    }

    /**
     *
     * @param points Set a new value for points
     */
    public void setPoints(int points) {
        this.points = points;
    }

}
