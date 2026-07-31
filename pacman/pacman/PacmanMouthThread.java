public class PacmanMouthThread extends Thread {
    private final GameState state;

    public PacmanMouthThread(GameState state) {
        super();
        this.state = state;
    }

    @Override
    public void run() {
        int cur_time = this.state.passed_millis;
        boolean flag = false;
        while (!state.endGame) {
            if (this.state.passed_millis < cur_time + 250) {
                continue;
            }

            this.state.mouth_open = flag;
            flag ^= true;
            cur_time = this.state.passed_millis;
        }
    }
}
