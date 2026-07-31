import javax.swing.JFrame;
import java.awt.Insets;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class StateManager {

    private JFrame frame = null;
    private ScoreLoader scoreLoader;
    private ArrayList<Thread> threads;

    public StateManager(JFrame frame) {
        this.frame = frame;
        scoreLoader = new ScoreLoader();
        setMenu();
    }

    private void setMenu() {
        frame.getContentPane().removeAll();
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        scoreLoader = new ScoreLoader();

        MainMenuPanel mainMenuPanel = new MainMenuPanel();
        mainMenuPanel.newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setBoard();
            }
        });

        mainMenuPanel.scoreboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setScoreboard();
            }
        });

        frame.add(mainMenuPanel);
        frame.revalidate();
        frame.repaint();
    }

    private void setScoreboard() {
        frame.getContentPane().removeAll();
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        List<String> names = scoreLoader.getNames();
        List<Integer> scores = scoreLoader.getScores();

        ScoreboardPanel scoreboardPanel = new ScoreboardPanel(names, scores);
        scoreboardPanel.actionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setMenu();
            }
        });
        scoreboardPanel.setFocusable(true);
        scoreboardPanel.requestFocus();
        frame.add(scoreboardPanel);
        frame.revalidate();
        frame.repaint();
    }

    private void setSavePanel(int final_score) {
        frame.getContentPane().removeAll();
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        SavePanel savePanel = new SavePanel(final_score);
        savePanel.exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setMenu();
            }
        });
        savePanel.saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeSave(final_score, savePanel.name);
                setMenu();
            }
        });
        frame.add(savePanel);
        frame.revalidate();
        frame.repaint();
    }

    private void makeSave(int score, String name) {
        scoreLoader.makeSave(score, name);
    }

    private void setBoard() {
        frame.getContentPane().removeAll();

        BoardSelectionPanel boardSelectionPanel = new BoardSelectionPanel();

        for (int i = 0; i < 5; i++) {
            final int index = i;
            boardSelectionPanel.boardButton[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setGame(index);
                }
            });
        }

        frame.add(boardSelectionPanel);
        frame.revalidate();
        frame.repaint();
    }

    private void setGame(int id) {
        frame.getContentPane().removeAll();

        MapData data = null;
        try {
            data = new MapData(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Insets insets = this.frame.getInsets();
        frame.setSize(data.width + insets.left + insets.right, data.height + insets.bottom + insets.top);
        frame.setLocationRelativeTo(null);

        GameState state = new GameState();
        GPanel gamePanel = new GPanel(data, state);

        frame.getContentPane().add(gamePanel);
        frame.revalidate();
        frame.repaint();

        state.setCharacters(data);
        state.setPrizes(data);
        Controls controls = new Controls(frame, state);

        threads = new ArrayList<>();
        threads.add(new TimerThread(state, gamePanel));
        threads.add(new PacmanAnimationThread(state, gamePanel, data));
        threads.add(new GhostAnimationThread(state, gamePanel, data));
        threads.add(new PacmanMouthThread(state));
        threads.add(new FightThread(state));
        threads.add(new CollectionThread(state, gamePanel));
        threads.add(new BoostThread(state, gamePanel));
        threads.add(new DropThread(state, gamePanel));
        threads.add(new TopPanelThread(state, gamePanel));
        threads.add(new KillerThread(state, this));

        for (Thread thread : threads)
            thread.start();
    }

    public void exitGame(int final_score) {
        try {
            for (Thread thread : threads)
                thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        setSavePanel(final_score);
    }
}
