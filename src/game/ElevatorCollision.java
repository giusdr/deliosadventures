package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

/**
 * This class aims to fix a bug where the Player was pushed into the floor by
 * the elevator if he was standing below it. * Now the player will be
 * automatically positioned on the Elevator.
 *
 * @author GIUSEPPE
 */
public class ElevatorCollision implements CollisionListener {

    private final Elevator elevator;
    private final Player player;

    /**
     *
     * @param e the elevator that will collide to the player
     * @param p the Player of the current world
     */
    public ElevatorCollision(Elevator e, Player p) {
        elevator = e;
        player = p;
    }

    /**
     * Respond to collisions between the elevator and the player.
     *
     * @param e the collision event description
     */
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() == player && player.getPosition().y < elevator.getPosition().y) {
            player.putOn(elevator);
        }
    }
}
