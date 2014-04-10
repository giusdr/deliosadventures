package game;

/**
 * Score class for each level. Extends model because the GUI shows the score and
 * it's updated every time it changes.
 *
 * @author Giuseppe
 */
public class Score extends Model {

    private int score;

    /**
     * Creates a new Score
     */
    public Score() {
        super();
        score = 0;
    }

    /**
     *
     * @return the score as an integer.
     */
    public int getScore() {
        return score;
    }

    /**
     * Increase the score and notify the listeners in the GUI.
     *
     * @param s increase the score by this amount
     */
    public void increaseScoreBy(int s) {
        score += s;
        changed();
    }

}
