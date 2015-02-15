package ch.chamblandes.gravity.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ch.chamblandes.gravity.displayables.Background;
import ch.chamblandes.gravity.displayables.Button;
import ch.chamblandes.gravity.displayables.Displayable;
import ch.chamblandes.gravity.displayables.TextView;
import ch.chamblandes.gravity.model.ScreenManager.Screen;
import ch.chamblandes.gravity.ressources.Buttons;
import ch.chamblandes.gravity.ressources.Drawings;
import ch.chamblandes.gravity.ressources.TextViews;

public class RessourceManager {

    private Map<Screen, Set<Button>> buttons;
    private Map<Screen, Set<TextView>> textViews;

    private Map<Screen, List<Displayable>> drawings;

    public RessourceManager() {

        // Buttons
        this.buttons = new HashMap<Screen, Set<Button>>();

        for (Screen screen : Screen.values()) {
            this.buttons.put(screen, new HashSet<Button>());
        }

        this.buttons.get(Screen.MENU).add(Buttons.BUTTON_MENU_LEVEL_1);
        this.buttons.get(Screen.MENU).add(Buttons.BUTTON_MENU_LEVEL_2);
        this.buttons.get(Screen.MENU).add(Buttons.BUTTON_MENU_LEVEL_3);
        this.buttons.get(Screen.MENU).add(Buttons.BUTTON_MENU_HELP);
        this.buttons.get(Screen.MENU).add(Buttons.BUTTON_MENU_CREDITS);
        this.buttons.get(Screen.MENU).add(Buttons.BUTTON_MENU_SCORES);
        this.buttons.get(Screen.MENU).add(Buttons.BUTTON_MENU_CHEATS);

        // Texts
        this.textViews = new HashMap<Screen, Set<TextView>>();

        for (Screen screen : Screen.values()) {
            this.textViews.put(screen, new HashSet<TextView>());
        }

        this.textViews.get(Screen.TITLE).add(TextViews.TEXT_TITLE_NAME);
        this.textViews.get(Screen.TITLE).add(TextViews.TEXT_TITLE_WRITE_NAME);
        this.textViews.get(Screen.TITLE).add(TextViews.TEXT_TITLE_TITLE);

        // Drawings
        this.drawings = new HashMap<Screen, List<Displayable>>();

        for (Screen screen : Screen.values()) {
            this.drawings.put(screen, new ArrayList<Displayable>());
        }

        this.drawings.get(Screen.TITLE).add(Drawings.ASTEROID_TITLE_1);
        this.drawings.get(Screen.TITLE).add(Drawings.ASTEROID_TITLE_2);
        this.drawings.get(Screen.TITLE).add(Drawings.ASTEROID_TITLE_3);
        this.drawings.get(Screen.TITLE).add(Drawings.ASTEROID_TITLE_4);
        this.drawings.get(Screen.TITLE).add(Drawings.ASTEROID_TITLE_5);
        this.drawings.get(Screen.TITLE).add(Drawings.ASTEROID_TITLE_6);
        this.drawings.get(Screen.TITLE).add(Drawings.ASTEROID_TITLE_7);
        this.drawings.get(Screen.TITLE).add(Drawings.ASTEROID_TITLE_8);
        this.drawings.get(Screen.TITLE).add(Drawings.ASTEROID_TITLE_9);
        this.drawings.get(Screen.TITLE).add(Drawings.ASTEROID_TITLE_10);
        this.drawings.get(Screen.TITLE).add(Drawings.PLANET_TITLE_1);
        this.drawings.get(Screen.TITLE).add(Drawings.PLANET_TITLE_2);
        this.drawings.get(Screen.TITLE).add(Drawings.PLANET_TITLE_3);
        this.drawings.get(Screen.TITLE).add(Drawings.STAR_TITLE);
    }

    public Set<Button> getButtonsForScreen(Screen screen) {
        return this.buttons.get(screen);
    }

    public Background getBackground() {
        return Drawings.BACKGROUND;
    }

    public List<Displayable> getDrawingsForScreen(Screen screen) {
        return this.drawings.get(screen);
    }

    public Set<TextView> getTextViewsForScreen(Screen screen) {
        return this.textViews.get(screen);
    }
}
