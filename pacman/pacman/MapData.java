import javax.swing.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

public class MapData {

    public int pacx = 500, pacy = 500;
    public int gx = 500, gy = 500;
    public int width, height;
    public int maze_width, maze_height;
    public boolean[][] maze;
    public ArrayList<ArrayList<Integer> > ghost_marks_x;
    public ArrayList<ArrayList<Integer> > ghost_marks_y;

    public MapData(int id) {

        FileReader mz = loadReader(String.format("maze%d.txt", id + 1));
        File gh = readTextFile(String.format("characters%d.txt", id + 1));

        try {
            readMaze(mz);
            readGhost(gh);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void readMaze(FileReader maze_file) throws Exception {
        BufferedReader br = new BufferedReader(maze_file);

        ArrayList<ArrayList<Boolean> > maze_lst = new ArrayList<>();
        String cur_str;
        while ((cur_str = br.readLine()) != null) {
            maze_lst.add(new ArrayList<>());
            for (int j = 0; j < cur_str.length(); j++) {
                int cid = maze_lst.size() - 1;
                maze_lst.get(cid).add(cur_str.charAt(j) == '1');
            }
        }

        maze_height = maze_lst.size();
        maze_width = maze_lst.get(0).size();
        height = maze_height * 80 + 80;
        width = maze_width * 80;

        maze = new boolean[maze_height][maze_width];
        for (int i = 0; i < maze_height; i++)
            for (int j = 0; j < maze_width; j++)
                maze[i][j] = maze_lst.get(i).get(j);
    }

    private void readGhost(File ghost_file) throws Exception {
        Scanner scanner = new Scanner(ghost_file);

        pacx = scanner.nextInt();
        pacy = scanner.nextInt();

        int num_ghosts = scanner.nextInt();
        ghost_marks_x = new ArrayList<>();
        ghost_marks_y = new ArrayList<>();

        for (int g = 0; g < num_ghosts; g++) {
            int n_points = scanner.nextInt();
            ArrayList<Integer> px = new ArrayList<>();
            ArrayList<Integer> py = new ArrayList<>();

            for (int i = 0; i < n_points; i++) {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                px.add(x);
                py.add(y);
            }

            ghost_marks_x.add(px);
            ghost_marks_y.add(py);
        }
    }

    private File readTextFile(String name) {
        File result = null;

        String[] s = new String[2];
        s[0] = Paths.get(name).toAbsolutePath().toString();
        s[1] = Paths.get("pacman", name).toAbsolutePath().toString();

        if (Files.exists(Paths.get(s[0]))) {
            result = new File(s[0]);
        } else if (Files.exists(Paths.get(s[1]))) {
            result = new File(s[1]);
        }
        return result;
    }

    private FileReader loadReader(String name) {
        FileReader result = null;

        String[] s = new String[2];
        s[0] = Paths.get(name).toAbsolutePath().toString();
        s[1] = Paths.get("pacman", name).toAbsolutePath().toString();

        if (Files.exists(Paths.get(s[0]))) {
            try {
                result = new FileReader(s[0]);
            } catch (FileNotFoundException e) {

            }
        } else if (Files.exists(Paths.get(s[1]))) {
            try {
                result = new FileReader(s[1]);
            } catch (FileNotFoundException e) {

            }
        }
        return result;
    }
}
