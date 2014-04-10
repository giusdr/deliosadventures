package game;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

/**
 * The gate is able to rotate on its edge to open the passage.
 *
 * @author Giuseppe
 *
 */
public class Gate extends Receiver {

    private static final float GATEWIDTH = 2.25f;
    private static final float GATEHEIGHT = 0.25f;
    private static final Shape GATESHAPE = new BoxShape(GATEWIDTH, GATEHEIGHT);
    private static final BodyImage IMAGE = new BodyImage("data/gate_texture.png", 10);
    private final Vec2 position;

    /**
     *
     * @param w the world in which the gate exists
     * @param p the position of this gate
     */
    public Gate(World w, Vec2 p) {
        super(w, p);
        this.setFixture(new SolidFixture(this, GATESHAPE));
        setImg();
        this.position = p;
        this.close();
    }

    /**
     * Change the gate to an open position.
     */
    @Override
    public void open() {
        this.setAngleDegrees(180);
        this.setPosition(new Vec2(this.getPosition().x + GATEWIDTH + GATEHEIGHT, this.getPosition().y + GATEWIDTH - GATEHEIGHT));

    }

    /**
     * Change the gate to a closed position.
     */
    @Override
    public final void close() {
        this.setPosition(position);
        this.setAngleDegrees(90);
    }

    /**
     * Set the image and clips it to the body shape.
     */
    private void setImg() {
        IMAGE.setClipped(true);
        this.setImage(IMAGE);
    }

}
