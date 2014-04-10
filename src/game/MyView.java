package game;

import city.cs.engine.UserView;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Subclass of UserView with customized background
 *
 * @author Giuseppe
 *
 */
public class MyView extends UserView {

    private static Image BG1 = new ImageIcon("data/background.jpg").getImage();
    private static Image BG2 = new ImageIcon("data/bg2.png").getImage();
    private static Image BG3 = new ImageIcon("data/bg3.png").getImage();
    private static Image BG4 = new ImageIcon("data/bg4.jpg").getImage();

    private Level l;

    /**
     *
     * @param w the world to be visualized
     * @param width width of the view.
     * @param height height of the view.
     */
    public MyView(Level w, int width, int height) {
        super(w, width, height);
        l = w;
        BG1 = BG1.getScaledInstance(width, height, 1);
        BG2 = BG2.getScaledInstance(width, height, 1);
        BG3 = BG3.getScaledInstance(width, height, 1);
        BG4 = BG4.getScaledInstance(width, height, 1);
    }

    @Override
    protected void paintBackground(Graphics2D g) {
        switch (l.getLevelNumber()) {
            case 1:
                g.drawImage(BG1, 0, 0, this);
                break;
            case 2:
                g.drawImage(BG2, 0, 0, this);
                break;
            case 3:
                g.drawImage(BG3, 0, 0, this);
                break;
            case 4:
                g.drawImage(BG4, 0, 0, this);
                break;
            default:
                System.err.println("NO BG SPECIFIED");

        }
    }

    @Override
    protected void paintForeground(Graphics2D g) {

    }

    /**
     *
     * @param l the level shown.
     */
    public void setLevel(Level l) {
        this.l = l;
    }

}
