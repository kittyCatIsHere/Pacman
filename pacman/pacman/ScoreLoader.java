import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ScoreLoader {

    private List<Integer> scores = new ArrayList<>();
    private List<String> names = new ArrayList<>();
    private HighScores serScores = null;

    public ScoreLoader() {
        try (FileInputStream fileIn = new FileInputStream(System.getProperty("user.dir") + File.separator + "scores.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn)) {
            serScores = (HighScores)in.readObject();
            scores = serScores.getScores();
            names = serScores.getNames();
        } catch (Exception e) {
            serScores = new HighScores();
        }
    }

    public void makeSave(int score, String name) {
        serScores.addResult(score, name);
        try (FileOutputStream fileOut = new FileOutputStream(System.getProperty("user.dir") + File.separator + "scores.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(serScores);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public List<String> getNames() {
        return names;
    }

    public List<Integer> getScores() {
        return scores;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public void setScores(List<Integer> scores) {
        this.scores = scores;
    }
}

class HighScores implements Serializable {
    private List<Integer> scores = new ArrayList<>();
    private List<String> names = new ArrayList<>();

    public HighScores() {

    }

    public void addResult(int score, String name) {
        scores.add(score);
        names.add(name);
        applySort();
    }

    private void applySort() {
        List<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            pairs.add(new Pair(names.get(i), scores.get(i)));
        }

        pairs.sort(Collections.reverseOrder(Comparator.comparing(Pair::getSecond)));

        List<Integer> sortedScores = new ArrayList<>();
        List<String> sortedNames = new ArrayList<>();
        for (Pair pair : pairs) {
            sortedNames.add(pair.getFirst());
            sortedScores.add(pair.getSecond());
        }

        names = sortedNames;
        scores = sortedScores;
    }

    public List<Integer> getScores() { return scores; }
    public List<String> getNames() { return names; }
}

class Pair {
    private final String first;
    private final Integer second;

    public Pair(String first, Integer second) {
        this.first = first;
        this.second = second;
    }

    public String getFirst() {
        return first;
    }

    public Integer getSecond() {
        return second;
    }
}