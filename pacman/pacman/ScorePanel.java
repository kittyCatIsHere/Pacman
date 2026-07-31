import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public class ScorePanel extends JPanel {
    public JLabel timeLabel;
    public JLabel heartLabel1;
    public JLabel heartLabel2;
    public JLabel heartLabel3;
    public JLabel boostLabel;
    public JLabel scoreLabel;

    private GameState state;

    public ScorePanel(GameState state) {
        this.state = state;

        setLayout(new GridBagLayout());
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));

        timeLabel = new JLabel("Time: 0");
        heartLabel1 = new JLabel("♥");
        heartLabel2 = new JLabel("♥");
        heartLabel3 = new JLabel("♥");
        boostLabel = new JLabel("Boost: none");
        scoreLabel = new JLabel("Score: 0");

        Font font = new Font("Arial", Font.BOLD, 24);
        timeLabel.setFont(font);
        heartLabel1.setFont(font);
        heartLabel2.setFont(font);
        heartLabel3.setFont(font);
        boostLabel.setFont(font);
        scoreLabel.setFont(font);

        timeLabel.setForeground(Color.BLACK);
        heartLabel1.setForeground(Color.RED);
        heartLabel2.setForeground(Color.RED);
        heartLabel3.setForeground(Color.RED);
        boostLabel.setForeground(Color.BLACK);
        scoreLabel.setForeground(Color.BLACK);

        timeLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 30));
        heartLabel1.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 10));
        heartLabel2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        heartLabel3.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 30));
        boostLabel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        scoreLabel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 20));

        centerPanel.add(timeLabel);
        centerPanel.add(heartLabel1);
        centerPanel.add(heartLabel2);
        centerPanel.add(heartLabel3);
        centerPanel.add(boostLabel);
        centerPanel.add(scoreLabel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.NONE;
        add(centerPanel, gbc);
    }

    public void updateScore(int val) {
        scoreLabel.setText(String.format("Score: %d", val));
    }

    public void updateTime(int val) {
        timeLabel.setText(String.format("Time: %ds", val));
    }

    public void updateBoost(int[] boost_activated) {
        int num = 5;
        for (int i = 0; i < 5; i++) {
            if (boost_activated[i] == 1) num = i;
        }

        if (num == 5)
            boostLabel.setText("Boost: none");
        else if (num == 0)
            boostLabel.setText("Boost: star");
        else if (num == 1)
            boostLabel.setText("Boost: forward");
        else if (num == 2)
            boostLabel.setText("Boost: heart");
        else if (num == 3)
            boostLabel.setText("Boost: reset");
        else if (num == 4)
            boostLabel.setText("Boost: x2");
    }

    public void updateLife(int val) {
        if (val == 3) {
            heartLabel1.setVisible(true);
            heartLabel2.setVisible(true);
            heartLabel3.setVisible(true);
        } else if (val == 2) {
            heartLabel1.setVisible(true);
            heartLabel2.setVisible(true);
            heartLabel3.setVisible(false);
        } else if (val == 1) {
            heartLabel1.setVisible(true);
            heartLabel2.setVisible(false);
            heartLabel3.setVisible(false);
        } else {
            heartLabel1.setVisible(false);
            heartLabel2.setVisible(false);
            heartLabel3.setVisible(false);
        }
    }
}
