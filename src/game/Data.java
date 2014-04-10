package game;

import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

/**
 * This class is a record that can contain information about bodies.
 *
 * @author Giuseppe
 */
public class Data {

    private Shape shape;
    private Vec2 vec;
    private String name;

    /**
     *
     * @param s the shape retain
     * @param v the position to retain
     * @param n the name to retain
     */
    public Data(Shape s, Vec2 v, String n) {
        this.shape = s;
        this.vec = v;
        this.name = n;

    }

    /**
     *
     * @return the shape.
     */
    public Shape getShape() {
        return shape;
    }

    /**
     * Set a new shape.
     *
     * @param shape a new shape.
     */
    public void setShape(Shape shape) {
        this.shape = shape;
    }

    /**
     *
     * @return the position.
     */
    public Vec2 getVec() {
        return vec;
    }

    /**
     * Set a new position.
     *
     * @param vec a new Position.
     */
    public void setVec(Vec2 vec) {
        this.vec = vec;
    }

    /**
     *
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set a new name.
     *
     * @param name a new name.
     */
    public void setName(String name) {
        this.name = name;
    }

}
