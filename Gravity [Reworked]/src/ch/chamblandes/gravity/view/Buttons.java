package ch.chamblandes.gravity.view;

import static ch.chamblandes.gravity.view.DisplayPanel.HEIGHT;
import static ch.chamblandes.gravity.view.DisplayPanel.WIDTH;
import ch.chamblandes.gravity.displayables.Button;

public class Buttons {
    public static final Button BUTTON_MENU_LEVEL_1 = new Button(WIDTH / 4, (53 * HEIGHT) / 160, WIDTH / 2,
        (9 * HEIGHT) / 160, "Lvl 1") {
        @Override
        public void onClick() {

        }
    };

    public static final Button BUTTON_MENU_LEVEL_2 = new Button(WIDTH / 4, (64 * HEIGHT) / 160, WIDTH / 2,
        (9 * HEIGHT) / 160, "Lvl 2") {
        @Override
        public void onClick() {

        }
    };

    public static final Button cadre3 = new Button(WIDTH / 4, (75 * HEIGHT) / 160, WIDTH / 2, (9 * HEIGHT) / 160,
        "Lvl 3") {
        @Override
        public void onClick() {

        }
    };
    public static final Button cadre4 = new Button(WIDTH / 4, (86 * HEIGHT) / 160, WIDTH / 2, (9 * HEIGHT) / 160,
        "Help") {
        @Override
        public void onClick() {

        }
    };
    public static final Button cadre5 = new Button(WIDTH / 4, (97 * HEIGHT) / 160, WIDTH / 2, (9 * HEIGHT) / 160,
        "Credits") {
        @Override
        public void onClick() {

        }
    };
    public static final Button cadre6 = new Button(WIDTH / 4, (108 * HEIGHT) / 160, WIDTH / 2, (9 * HEIGHT) / 160,
        "Scores") {
        @Override
        public void onClick() {

        }
    };
    public static final Button cadre7 = new Button(WIDTH / 4, (119 * HEIGHT) / 160, WIDTH / 2, (9 * HEIGHT) / 160,
        "Cheats") {
        @Override
        public void onClick() {

        }
    };
}
