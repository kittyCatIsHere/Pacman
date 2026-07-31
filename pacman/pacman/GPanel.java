import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GPanel extends JPanel {

    public JLabel[][] maze_labels;
    public ScorePanel topPanel;

    public JLabel[] pacman_labels;
    public JLabel[] ghosts;

    public JLabel star;
    public JLabel heart;
    public JLabel x2;
    public JLabel forward;
    public JLabel reset;

    private GameState state;

    public GPanel(MapData data, GameState state) {
        this.state = state;

        setLayout(null);

        ImageIcon wall_icon = readImage("wall.png");
        ImageIcon prize_icon = readImage("prize.png");
        ImageIcon star_icon = readImage("star.png");
        ImageIcon forward_icon = readImage("forward.png");
        ImageIcon heart_icon = readImage("heart.png");
        ImageIcon x2_icon = readImage("x2.png");
        ImageIcon reset_icon = readImage("reset.png");


        ImageIcon[] pacman_icons = new ImageIcon[5];
        pacman_icons[0] = readImage("pacman_closed.png");
        pacman_icons[1] = readImage("pacman_north.png");
        pacman_icons[2] = readImage("pacman_east.png");
        pacman_icons[3] = readImage("pacman_south.png");
        pacman_icons[4] = readImage("pacman_west.png");
        ImageIcon ghost_icon = readImage("ghost.png");

        star = new JLabel(star_icon);
        star.setBounds(0, 0, 80, 80);
        star.setVisible(false);

        forward = new JLabel(forward_icon);
        forward.setBounds(0, 0, 80, 80);
        forward.setVisible(false);

        heart = new JLabel(heart_icon);
        heart.setBounds(0, 0, 80, 80);
        heart.setVisible(false);

        x2 = new JLabel(x2_icon);
        x2.setBounds(0, 0, 80, 80);
        x2.setVisible(false);

        reset = new JLabel(reset_icon);
        reset.setBounds(0, 0, 80, 80);
        reset.setVisible(false);

        int maze_width = data.maze_width;
        int maze_height = data.maze_height;

        topPanel = new ScorePanel(state);
        topPanel.setBounds(0, 0, maze_width * 80, 80);

        maze_labels = new JLabel[maze_height][maze_width];

        int current_x = 0, current_y = 80;
        for (int i = 0; i < maze_height; i++) {
            for (int j = 0; j < maze_width; j++) {
                JLabel cur_label = new JLabel(data.maze[i][j] ? wall_icon : prize_icon);
                cur_label.setBounds(current_x, current_y, 80, 80);
                maze_labels[i][j] = cur_label;
                current_x += 80;
            }
            current_x = 0;
            current_y += 80;
        }

        JLabel[] pacman_labels = new JLabel[5];
        for (int i = 0; i < 5; i++) {
            pacman_labels[i] = new JLabel(pacman_icons[i]);
            pacman_labels[i].setVisible(false);
            pacman_labels[i].setBounds(0, 0, 50, 50);
            add(pacman_labels[i]);
        }
        pacman_labels[1].setVisible(true);

        int num_ghosts = data.ghost_marks_x.size();
        this.ghosts = new JLabel[num_ghosts];

        for (int i = 0; i < num_ghosts; i++) {
            this.ghosts[i] = new JLabel(ghost_icon);
            this.ghosts[i].setBounds(0, 0, 75, 75);
            add(this.ghosts[i]);
        }

        add(star);
        add(forward);
        add(heart);
        add(x2);
        add(reset);

        add(topPanel);

        for (int i = 0; i < maze_height; i++)
            for (int j = 0; j < maze_width; j++)
                add(maze_labels[i][j]);

        this.pacman_labels = pacman_labels;
    }

    private ImageIcon readImage(String name) {
        ImageIcon result = null;

        String[] s = new String[2];
        s[0] = Paths.get(name).toAbsolutePath().toString();
        s[1] = Paths.get("pacman", name).toAbsolutePath().toString();

        if (Files.exists(Paths.get(s[0]))) {
            result = new ImageIcon(s[0]);
        } else if (Files.exists(Paths.get(s[1]))) {
            result = new ImageIcon(s[1]);
        }
        return result;
    }

    public void updateTime(int val) {
        topPanel.updateTime(val);
    }

    public void updateScore(int val) {
        topPanel.updateScore(val);
    }

    public void updateLife(int val) {
        topPanel.updateLife(val);
    }

    public void updateBoost(int[] boost_activated) {
        topPanel.updateBoost(boost_activated);
    }

    private ImageIcon loadImage(String filename) {
        ImageIcon maze_icon = null;
        try {
            maze_icon = new ImageIcon(filename);
        } catch (Exception e) {
            System.out.println("maze file not found");
        }
        return maze_icon;
    }

    public void setGhost(int i, int x, int y) {
        this.ghosts[i].setLocation(x, y);
    }

    public void updatePacman(int orientation_id) {
        for (int i = 0; i < 5; i++)
            this.pacman_labels[i].setVisible(false);
        this.pacman_labels[orientation_id].setVisible(true);
    }

    public void hideBonus(int id) {
        switch (id) {
            case 0 -> star.setVisible(false);
            case 1 -> forward.setVisible(false);
            case 2 -> heart.setVisible(false);
            case 3 -> reset.setVisible(false);
            case 4 -> x2.setVisible(false);
        }
    }
}