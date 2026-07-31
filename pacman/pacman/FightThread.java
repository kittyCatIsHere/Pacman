public class FightThread extends Thread {
    private final GameState state;

    public FightThread(GameState state) {
        super();
        this.state = state;
    }

    @Override
    public void run() {
        int cur_time = state.passed_millis;
        int cur_wait = 10;
        while (!state.endGame) {
            if (state.passed_millis < cur_time + cur_wait) {
                continue;
            }
            cur_wait = 10;

            if (state.bonus_active[0].get() == 1)
                continue;

            int x = state.pacman_x.get();
            int y = state.pacman_y.get();

            for (int i = 0; i < state.ghosts_x.size(); i++) {
                int xg = state.ghosts_x.get(i).get();
                int yg = state.ghosts_y.get(i).get();

                if (Math.abs(xg - x) <= 30 && Math.abs(yg - y) <= 30) {
                    state.life.set(state.life.get() - 1);
                    cur_wait = 300;
                }
            }
            cur_time = state.passed_millis;
        }
    }
}
