import javax.swing.SwingUtilities;

public class KillerThread extends Thread {

    private final GameState state;
    private final StateManager manager;

    public KillerThread(GameState state, StateManager manager) {
        super();
        this.state = state;
        this.manager = manager;
    }

    @Override
    public void run() {
        while (!state.endGame) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (state.life.get() == 0)
                state.endGame = true;
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                manager.exitGame(state.score.get());
            }
        });
    }
}
