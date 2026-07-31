public class GhostAnimationThread extends Thread {
    private final GameState state;
    private final GPanel gamePanel;
    private final MapData data;

    private final int GHOST_SPEED = 4;

    public GhostAnimationThread(GameState state, GPanel gamePanel, MapData data) {
        super();
        this.state = state;
        this.gamePanel = gamePanel;
        this.data = data;
    }

    @Override
    public void run() {
        int cur_time = this.state.passed_millis;

        int num_ghosts = data.ghost_marks_x.size();
        int[] current_id = new int[num_ghosts];
        for (int i = 0; i < num_ghosts; i++)
            current_id[i] = 0;

        while (!state.endGame) {
            if (this.state.passed_millis < cur_time + 5) {
                continue;
            }

            for (int i = 0; i < num_ghosts; i++) {
                int id0 = current_id[i];
                int id1 = (id0 + 1) % data.ghost_marks_x.get(i).size();

                int x1 = data.ghost_marks_x.get(i).get(id1), y1 = data.ghost_marks_y.get(i).get(id1);
                int x0 = data.ghost_marks_x.get(i).get(id0), y0 = data.ghost_marks_y.get(i).get(id0);

                int xdiff = x1 - x0;
                int ydiff = y1 - y0;
                if (xdiff != 0)
                    xdiff /= Math.abs(xdiff);
                if (ydiff != 0)
                    ydiff /= Math.abs(ydiff);

                xdiff *= GHOST_SPEED;
                ydiff *= GHOST_SPEED;

                int x = state.ghosts_x.get(i).get();
                int y = state.ghosts_y.get(i).get();
                x += xdiff;
                y += ydiff;

                state.ghosts_x.get(i).set(x);
                state.ghosts_y.get(i).set(y);

                gamePanel.setGhost(i, x, y);

                int x_next = (x1 - 1) * 80;
                int y_next = 80 + (y1 - 1) * 80;

                if (x == x_next && y == y_next)
                    current_id[i] = (current_id[i] + 1) % data.ghost_marks_x.get(i).size();
            }
            cur_time = this.state.passed_millis;
        }
    }
}
