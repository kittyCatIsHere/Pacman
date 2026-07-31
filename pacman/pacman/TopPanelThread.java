public class TopPanelThread extends Thread {
    private final GameState state;
    private final GPanel gamePanel;

    public TopPanelThread(GameState state, GPanel gamePanel) {
        super();
        this.state = state;
        this.gamePanel = gamePanel;
    }

    @Override
    public void run() {
        int cur_time = state.passed_millis;
        while (!state.endGame) {
            if (state.passed_millis < cur_time + 100) {
                continue;
            }

            gamePanel.updateScore(state.score.get());
            gamePanel.updateTime(state.passed_millis / 1000);
            gamePanel.updateLife(state.life.get());

            int[] boost_activated = new int[5];
            for (int i = 0; i < 5; i++)
                boost_activated[i] = state.bonus_active[i].get();
            gamePanel.updateBoost(boost_activated);

            cur_time = state.passed_millis;
        }
    }
}
