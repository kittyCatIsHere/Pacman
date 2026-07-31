import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class GameState {
    public volatile int passed_millis = 0;

    public volatile boolean mouth_open = false;
    public volatile String pacman_direction = "right";
    public AtomicInteger pacman_x = new AtomicInteger(0);
    public AtomicInteger pacman_y = new AtomicInteger(0);

    public ArrayList<AtomicInteger> ghosts_x = new ArrayList<>();
    public ArrayList<AtomicInteger> ghosts_y = new ArrayList<>();

    public boolean[][] maze_data;
    public AtomicInteger[][] prizes;

    public AtomicInteger[] bonus_x;
    public AtomicInteger[] bonus_y;
    public AtomicInteger[] bonus_open;
    public AtomicInteger[] bonus_active;

    public volatile boolean left = false;
    public volatile boolean right = false;
    public volatile boolean up = false;
    public volatile boolean down = false;

    public AtomicInteger score = new AtomicInteger(0);
    public AtomicInteger life = new AtomicInteger(3);

    public volatile boolean endGame = false;

    public GameState() {
        bonus_x = new AtomicInteger[5];
        bonus_y = new AtomicInteger[5];
        bonus_open = new AtomicInteger[5];
        bonus_active = new AtomicInteger[5];

        for (int i = 0; i < 5; i++) {
            bonus_x[i] = new AtomicInteger(0);
            bonus_y[i] = new AtomicInteger(0);
            bonus_open[i] = new AtomicInteger(0);
            bonus_active[i] = new AtomicInteger(0);
        }
    }

    public void setCharacters(MapData data) {
        pacman_x.set(data.pacx);
        pacman_y.set(data.pacy);

        ghosts_x = new ArrayList<>();
        ghosts_y = new ArrayList<>();

        for (int i = 0; i < data.ghost_marks_x.size(); i++) {
            int fx = data.ghost_marks_x.get(i).get(0);
            int fy = data.ghost_marks_y.get(i).get(0);

            ghosts_x.add(new AtomicInteger((fx - 1) * 80));
            ghosts_y.add(new AtomicInteger((fy - 1) * 80 + 80));
        }
    }

    public void setPrizes(MapData data) {
        int height = data.maze.length;
        int width = data.maze[0].length;

        maze_data = data.maze;
        prizes = new AtomicInteger[height][width];

        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                prizes[i][j] = new AtomicInteger(!maze_data[i][j] ? 1 : 0);
    }

    public void setDirection(String dir) {
        switch (dir) {
            case "up" -> { dropDirections(); up = true; pacman_direction = "up"; }
            case "down" -> { dropDirections(); down = true; pacman_direction = "down"; }
            case "right" -> { dropDirections(); right = true; pacman_direction = "right"; }
            case "left" -> { dropDirections(); left = true; pacman_direction = "left"; }
        }
    }

    private void dropDirections() {
        left = false;
        right = false;
        up = false;
        down = false;
    }

    public int getOrientation() {
        int orientation_id = 0;
        if (pacman_direction.equals("up"))
            orientation_id = 1;
        if (pacman_direction.equals("right"))
            orientation_id = 2;
        if (pacman_direction.equals("down"))
            orientation_id = 3;
        if (pacman_direction.equals("left"))
            orientation_id = 4;

        if (!mouth_open)
            orientation_id = 0;
        return orientation_id;
    }
}
