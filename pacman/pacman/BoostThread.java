public class BoostThread extends Thread {
    private final GameState state;
    private final GPanel gamePanel;

    private final int TIME_STEP = 5000;

    public BoostThread(GameState state, GPanel gamePanel) {
        super();
        this.state = state;
        this.gamePanel = gamePanel;
    }

    @Override
    public void run() {
        int cur_time = state.passed_millis;
        int ground_millis = cur_time;
        while (!state.endGame) {
            if (state.passed_millis < cur_time + 10) {
                continue;
            }

            if (state.passed_millis > ground_millis + TIME_STEP) {
                for (int j = 0; j < 5; j++)
                    state.bonus_active[j].set(0);
            }

            int x = state.pacman_x.get();
            int y = state.pacman_y.get();

            for (int i = 0; i < 5; i++) {
                int xb = state.bonus_x[i].get();
                int yb = state.bonus_y[i].get();
                int open = state.bonus_open[i].get();

                if (open == 0)
                    continue;

                if (Math.abs(xb - x) <= 40 && Math.abs(yb - y) <= 40) {
                    for (int j = 0; j < 5; j++)
                        state.bonus_active[j].set(0);
                    state.bonus_active[i].set(1);

                    if (i == 2) {
                        state.life.set(Math.min(3, state.life.get() + 1));
                        state.bonus_active[i].set(0);
                    }

                    if (i == 3) {
                        for (int p1 = 0; p1 < state.prizes.length; p1++) {
                            for (int p2 = 0; p2 < state.prizes[0].length; p2++) {
                                state.prizes[p1][p2].set(!state.maze_data[p1][p2] ? 1 : 0);
                                gamePanel.maze_labels[p1][p2].setVisible(true);
                            }
                        }
                        state.bonus_active[i].set(0);
                    }

                    state.bonus_open[i].set(0);
                    gamePanel.hideBonus(i);

                    ground_millis = state.passed_millis;
                }
            }
            cur_time = state.passed_millis;
        }
    }
}
