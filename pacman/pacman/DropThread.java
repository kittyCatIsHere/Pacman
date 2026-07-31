public class DropThread extends Thread {
    private final GameState state;
    private final GPanel gamePanel;

    private final int TIME_STEP = 5000;

    public DropThread(GameState state, GPanel gamePanel) {
        super();
        this.state = state;
        this.gamePanel = gamePanel;
    }

    @Override
    public void run() {
        int cur_time = this.state.passed_millis;
        while (!state.endGame) {
            if (this.state.passed_millis < cur_time + TIME_STEP) {
                continue;
            }

            double chance = Math.random();

            if (chance < 0.25) {

                int ghost_choice = (int)(Math.random() * state.ghosts_x.size());
                int x = state.ghosts_x.get(ghost_choice).get();
                int y = state.ghosts_y.get(ghost_choice).get();

                int bonus_choice = (int)(Math.random() * 5);
                if (bonus_choice == 0) {
                    state.bonus_x[0].set(x);
                    state.bonus_y[0].set(y);
                    state.bonus_open[0].set(1);
                    gamePanel.star.setLocation(x, y);
                    gamePanel.star.setVisible(true);
                } else if (bonus_choice == 1) {
                    state.bonus_x[1].set(x);
                    state.bonus_y[1].set(y);
                    state.bonus_open[1].set(1);
                    gamePanel.forward.setLocation(x, y);
                    gamePanel.forward.setVisible(true);
                } else if (bonus_choice == 2) {
                    state.bonus_x[2].set(x);
                    state.bonus_y[2].set(y);
                    state.bonus_open[2].set(1);
                    gamePanel.heart.setLocation(x, y);
                    gamePanel.heart.setVisible(true);
                } else if (bonus_choice == 3) {
                    state.bonus_x[3].set(x);
                    state.bonus_y[3].set(y);
                    state.bonus_open[3].set(1);
                    gamePanel.reset.setLocation(x, y);
                    gamePanel.reset.setVisible(true);
                } else if (bonus_choice == 4) {
                    state.bonus_x[4].set(x);
                    state.bonus_y[4].set(y);
                    state.bonus_open[4].set(1);
                    gamePanel.x2.setLocation(x, y);
                    gamePanel.x2.setVisible(true);
                }
            }

            cur_time = this.state.passed_millis;
        }
    }
}
