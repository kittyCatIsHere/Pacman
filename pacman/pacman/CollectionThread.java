import javax.swing.*;

public class CollectionThread extends Thread {
    private final GameState state;
    private final GPanel gamePanel;

    public CollectionThread(GameState state, GPanel gamePanel) {
        super();
        this.state = state;
        this.gamePanel = gamePanel;
    }

    @Override
    public void run() {
        int cur_time = state.passed_millis;
        while (!state.endGame) {
            if (state.passed_millis < cur_time + 10) {
                continue;
            }

            int x = (state.pacman_x.get() + 40) / 80;
            int y = (state.pacman_y.get() + 40 - 80)/ 80;

            int multiplier = 1;
            if (state.bonus_active[4].get() == 1)
                multiplier = 2;

            if (state.prizes[y][x].get() == 1) {
                state.prizes[y][x].set(0);
                state.score.set(state.score.get() + multiplier);
                gamePanel.maze_labels[y][x].setVisible(false);
            }

            cur_time = state.passed_millis;
        }
    }
}
