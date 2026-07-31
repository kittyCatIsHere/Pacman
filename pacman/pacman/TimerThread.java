import javax.swing.*;

public class TimerThread extends Thread {

    private final GameState state;
    private final GPanel gamePanel;

    public TimerThread(GameState state, GPanel gamePanel) {
        super();
        this.state = state;
        this.gamePanel = gamePanel;
    }

    @Override
    public void run() {
        int prev_millis = state.passed_millis;
        while (!state.endGame) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            state.passed_millis += 10;

            if (state.passed_millis >= prev_millis + 1000) {
                prev_millis = state.passed_millis;
            }
        }
    }
}
