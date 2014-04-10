package game;

import city.cs.engine.UserView;
import static game.moveState.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * This is the KeyboardHandler which calls move() from Player passing a
 * moveState(enum) value.
 *
 * LEFT ARROW = MOVE LEFT RIGHT ARROW = MOVE RIGHT UP ARROW = JUMP E = USE
 *
 * @author Giuseppe  *
 */
public class KeyboardHandler extends KeyAdapter {

    private final Player player;
    private final UserView view;
    private moveState m;
    private final Game game;

    /**
     *
     * @param game the current game
     * @param player the player of the current world
     * @param view the view that shows the simulation
     */
    public KeyboardHandler(Game game, Player player, UserView view) {
        this.player = player;
        this.view = view;
        this.game = game;
        m = MS_STOP;
    }

    /**
     * Detects which key has been pressed and behaves accordingly.
     *
     * @param e a description of the keyevent
     */
    @Override
    public void keyPressed(KeyEvent e) {

        int id = e.getKeyCode();
        switch (id) {
            case KeyEvent.VK_LEFT:
                m = MS_LEFT;
                player.move(m);
                break;
            case KeyEvent.VK_RIGHT:
                m = MS_RIGHT;
                player.move(m);
                break;
            case KeyEvent.VK_UP:
                m = MS_JUMP;
                player.move(m);
                break;
            case KeyEvent.VK_E:
                m = MS_STOP;
                player.use();
                break;
        }
    }

    /**
     * Clear the moving state if the key is released.
     *
     * @param e a description of the keyevent
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_LEFT) {
            player.clearWalker();
        } else if (code == KeyEvent.VK_RIGHT) {
            player.clearWalker();
        }
    }

}
