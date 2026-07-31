public class PacmanAnimationThread extends Thread {
    private final GameState state;
    private final GPanel gamePanel;
    private final MapData data;

    public PacmanAnimationThread(GameState state, GPanel gamePanel, MapData data) {
        super();
        this.state = state;
        this.gamePanel = gamePanel;
        this.data = data;
    }

    private boolean isCollision(int x, int y) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                int px = (x + 50 * i) / 80;
                int py = (y + 50 * j - 80) / 80;

                if (data.maze[py][px]) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void run() {
        int cur_time = this.state.passed_millis;
        while (!state.endGame) {
            if (this.state.passed_millis < cur_time + 10) {
                continue;
            }

            int x0 = this.state.pacman_x.get(), y0 = this.state.pacman_y.get();
            int x = x0, y = y0;

            int multiplier = 1;
            if (state.bonus_active[1].get() == 1)
                multiplier = 2;

            if (this.state.right) {
                x += 3 * multiplier;
            } else if (this.state.left) {
                x -= 3 * multiplier;
            } else if (this.state.up) {
                y -= 3 * multiplier;
            } else if (this.state.down) {
                y += 3 * multiplier;
            }

            if (isCollision(x, y)) {
                x = x0;
                y = y0;
            }

            this.state.pacman_x.set(x);
            this.state.pacman_y.set(y);
            this.gamePanel.updatePacman(this.state.getOrientation());
            for (int i = 0; i < 5; i++)
                gamePanel.pacman_labels[i].setLocation(x, y);

            cur_time = this.state.passed_millis;
        }
    }
}
