package ch.chamblandes.gravity.view;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ch.chamblandes.gravity.displayables.Background;
import ch.chamblandes.gravity.displayables.Button;
import ch.chamblandes.gravity.model.ScreenManager.Screen;
import ch.chamlandes.gravity.ressources.Buttons;
import ch.chamlandes.gravity.ressources.Drawings;

public class RessourceManager {

    private Map<Screen, Set<Button>> buttons;

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

    }

    public Set<Button> getButtonsForScreen(Screen screen) {
        return this.buttons.get(screen);
    }

    public Background getBackground() {
        return Drawings.BACKGROUND;
    }
}
