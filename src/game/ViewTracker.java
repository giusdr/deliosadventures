package game;

import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.UserView;
import org.jbox2d.common.Vec2;

/**
 * Tracks the player, making him always in the centre of the screen. It is also
 * updating the platforms to be displayed according to the position of the
 * player in the world.
 *
 * @author Giuseppe
 *
 *
 *
 */
public class ViewTracker implements StepListener {

    private final UserView view;
    private Player player;

    /**
     *
     * @param view current view on the world
     * @param player the player to be placed in the middle.
     */
    public ViewTracker(UserView view, Player player) {
        this.view = view;
        this.player = player;

    }

    /**
     * Called immediately before each simulation step.
     *
     * @param e the event descriptor.
     */
    @Override
    public void preStep(StepEvent e) {
        player.getLevel().update();
        if (player.getPosition().y < -25) {
            player.die();
        }
    }

    /**
     * Called immediately after each simulation step.
     *
     * @param e the event descriptor.
     */
    @Override
    public void postStep(StepEvent e) {
        view.setCentre(player.getPosition());
        player.getLevel().displayPlatforms();

    }

    /**
     * Set a new player in the tracker
     *
     * @param p the player to be set
     */
    public void setPlayer(Player p) {
        this.player = p;
    }

    /**
     *
     * @return the centre of the screen.
     */
    public Vec2 getCentre() {
        return view.getCentre();
    }

}
