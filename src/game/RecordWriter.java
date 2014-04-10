package game;

import java.io.FileWriter;
import java.io.IOException;

/**
 * This class writes to a .txt file the scores for each level that the player
 * completes.
 *
 * @author GIUSEPPE
 */
public class RecordWriter {

    private final static String FILE_NAME = "data/rank.txt";
    private static boolean written = false;

    /**
     * Writes a new score line on the file.
     *
     * @param l level which gives the current time
     * @param time is the total time for the level
     * @param score current score to write
     * @throws IOException if file not exists
     */
    public static void write(Level l, Timer time, Score score) throws IOException {
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            writer.write("Level: " + l.getLevelNumber() + "\tTime: " + time.getCurrentTime() + "\tScore: " + score.getScore() / 10 + "/3" + "\r\n");
            written = true;
        }
    }

    /**
     * Writes an empty line to finish the session when the game is closed.
     *
     * @throws IOException if file not exists
     */
    public static void sessionFinished() throws IOException {
        if (written) {
            try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
                writer.write("\r\n");
            }
        }
    }

}
