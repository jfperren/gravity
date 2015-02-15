package ch.chamblandes.gravity.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import ch.chamblandes.gravity.model.GameEngine.Level;

public class ScoreManager {

    private StringBuilder name;
    private Map<Level, SortedSet<ScoreEntry>> scoreBoard;

    public ScoreManager() {
        this.name = new StringBuilder();

        this.scoreBoard = new HashMap<Level, SortedSet<ScoreEntry>>();

        this.scoreBoard.put(Level.ONE, new TreeSet<ScoreEntry>());
        this.scoreBoard.put(Level.TWO, new TreeSet<ScoreEntry>());
        this.scoreBoard.put(Level.THREE, new TreeSet<ScoreEntry>());
    }

    public StringBuilder getName() {
        return this.name;
    }

    public void addScore(Level level, String name, int score) {
        this.scoreBoard.get(level).add(new ScoreEntry(name, score));
    }

    public List<ScoreEntry> getScoreForLevel(Level level) {
        return new ArrayList<ScoreEntry>(this.scoreBoard.get(level));
    }

    private static class ScoreEntry implements Comparable<ScoreEntry> {
        private String name;
        private int score;

        public ScoreEntry(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return this.name;
        }

        public int getScore() {
            return this.score;
        }

        @Override
        public int compareTo(ScoreEntry that) {
            return this.score - that.getScore();
        }
    }
}
