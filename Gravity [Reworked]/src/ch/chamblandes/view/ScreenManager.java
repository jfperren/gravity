package ch.chamblandes.view;

public class ScreenManager {
    public enum Screen {
        TITLE,
        HELP,
        MENU,
        GAME,
        PAUSE,
        GAMEOVER,
        CREDITS,
        SCORE,
        CHEATS
    }

    private Screen screen;

    public ScreenManager() {
        this.screen = Screen.TITLE;
    }

    public void setScreen(Screen newScreen) {
        this.screen = newScreen;
    }

    public Screen getScreen() {
        return this.screen;
    }
}
