package ch.chamblandes.gravity.model;

public class CheatManager {

    public static final String CHEAT_CODE = "Jaypi";
    public static final char ENTER_CHAR = '\n';

    public enum Cheat {
        ANTIGRAVITY,
        STARWARS,
        INFINITE_FUEL,
        INFINITE_AMMO;

        private boolean isActivated;

        private Cheat() {
            this.isActivated = false;
        }

        public void setActivated(boolean isActivated) {
            this.isActivated = isActivated;
        }

        public boolean isActivated() {
            return this.isActivated;
        }
    }

    private StringBuilder string;
    private boolean isActivated;

    public CheatManager() {
        this.string = new StringBuilder();
        this.isActivated = false;
    }

    private void tryActivate() {
        if (this.string.toString().equals(CHEAT_CODE)) {
            this.isActivated = true;
        }

        this.string = new StringBuilder();
    }

    public void append(char c) {
        if (c == ENTER_CHAR) {
            this.tryActivate();
        } else {
            this.string.append(c);
        }
    }
}
