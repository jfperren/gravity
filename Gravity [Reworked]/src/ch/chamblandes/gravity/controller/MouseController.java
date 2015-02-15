package ch.chamblandes.gravity.controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ch.chamblandes.gravity.displayables.Button;
import ch.chamblandes.gravity.model.GravityApplet;
import ch.chamblandes.gravity.view.DisplayPanel;

public class MouseController extends MouseAdapter {

    private GravityApplet applet;

    public MouseController(GravityApplet applet) {
        this.applet = applet;
    }

    @Override
    public void mouseClicked(MouseEvent evt) {
        double x = evt.getX() - DisplayPanel.MARGIN_LEFT;
        double y = evt.getY() - DisplayPanel.MARGIN_TOP;

        for (Button button : this.applet.getDrawingsManager().getButtonsForScreen(
            this.applet.getScreenManager().getScreen())) {
            button.dispatchClick(x, y);
        }

        // switch (this.applet.getScreenManager().getScreen()) {
        //
        // case 0:// Ecran titre
        // if ((x > (PANEL_WIDTH / 4)) && (x < ((3 * PANEL_WIDTH) / 4)) && (y >
        // ((145 * PANEL_HEIGHT) / 160))
        // && (y < ((154 * PANEL_HEIGHT) / 160))) {
        // if (this.playerName.isEmpty() == false) {
        // // Bouton "Play"
        // this.screen = 1;
        // }
        // }
        // break;
        // case 1:// Ecran aide
        //
        // if ((x > (PANEL_WIDTH / 4)) && (x < ((3 * PANEL_WIDTH) / 4)) && (y >
        // ((145 * PANEL_HEIGHT) / 160))
        // && (y < ((154 * PANEL_HEIGHT) / 160))) {
        // if (this.isComingFromGame) {
        // this.screen = 4;
        // } else {
        // this.screen = 2;
        // }
        // }
        // break;
        // case 2:// Ecran menu
        // if ((x > (PANEL_WIDTH / 4)) && (x < ((3 * PANEL_WIDTH) / 4)) && (y >
        // ((53 * PANEL_HEIGHT) / 160))
        // && (y < ((62 * PANEL_HEIGHT) / 160))) {
        // // Bouton "Niveau 1"
        // this.screen = 3;
        // this.niveau = 0;
        // this.reset();
        // } else if ((x > (PANEL_WIDTH / 4)) && (x < ((3 * PANEL_WIDTH) / 4))
        // && (y > ((64 * PANEL_HEIGHT) / 160)) && (y < ((73 * PANEL_HEIGHT) /
        // 160))) {
        // // Bouton "Niveau 2"
        // this.screen = 3;
        // this.niveau = 1;
        // this.reset();
        // } else if ((x > (PANEL_WIDTH / 4)) && (x < ((3 * PANEL_WIDTH) / 4))
        // && (y > ((75 * PANEL_HEIGHT) / 160)) && (y < ((84 * PANEL_HEIGHT) /
        // 160))) {
        // // Bouton "Niveau 3"
        // this.screen = 3;
        // this.niveau = 2;
        // this.reset();
        // } else if ((x > (PANEL_WIDTH / 4)) && (x < ((3 * PANEL_WIDTH) / 4))
        // && (y > ((86 * PANEL_HEIGHT) / 160)) && (y < ((95 * PANEL_HEIGHT) /
        // 160))) {
        // // Bouton "Controles"
        // this.screen = 1;
        // } else if ((x > (PANEL_WIDTH / 4)) && (x < ((3 * PANEL_WIDTH) / 4))
        // && (y > ((97 * PANEL_HEIGHT) / 160)) && (y < ((106 * PANEL_HEIGHT) /
        // 160))) {
        // // Bouton "Crédits"
        // this.screen = 6;
        // } else if ((x > (PANEL_WIDTH / 4)) && (x < ((3 * PANEL_WIDTH) / 4))
        // && (y > ((108 * PANEL_HEIGHT) / 160)) && (y < ((117 * PANEL_HEIGHT) /
        // 160))) {
        // // Bouton "Scores"
        // this.screen = 7;
        // } else if ((x > (PANEL_WIDTH / 4)) && (x < ((3 * PANEL_WIDTH) / 4))
        // && (y > ((119 * PANEL_HEIGHT) / 160)) && (y < ((128 * PANEL_HEIGHT) /
        // 160))) {
        // // Bouton "Cheat Codes"
        // if (this.isCheatCodeActivated) {
        // this.screen = 8;
        // }
        // }
        //
        // break;
        // case 3:// En jeu
        //
        // break;
        //
        // case 4:// En pause
        // // En appuyant sur le bouton "Reprendre"
        // if ((x > (PANEL_WIDTH / 4)) && (x < ((3 * PANEL_WIDTH) / 4)) && (y >
        // ((64 * PANEL_HEIGHT) / 160))
        // && (y < ((73 * PANEL_HEIGHT) / 160))) {
        // this.screen = 3;
        // } // Sinon en appuyant sur le bouton "Aide"
        // else if ((x > (PANEL_WIDTH / 4)) && (x < ((3 * PANEL_WIDTH) / 4)) &&
        // (y > ((75 * PANEL_HEIGHT) / 160))
        // && (y < ((84 * PANEL_HEIGHT) / 160))) {
        // // Envoie a la page d'aide.
        // this.isComingFromGame = true;
        // this.screen = 1;
        // } // Sinon en appuyant sur le bouton "Arreter"
        // else if ((x > (PANEL_WIDTH / 4)) && (x < ((3 * PANEL_WIDTH) / 4)) &&
        // (y > ((86 * PANEL_HEIGHT) / 160))
        // && (y < ((95 * PANEL_HEIGHT) / 160))) {
        // // Mets le jeu en mode game over.
        // this.addScore(this.niveau, this.playerName, (int) this.playerScore);
        // this.screen = 5;
        // }
        // break;
        // case 5:// En game over
        // // En appuyant sur le bouton "Recommencer"
        // if ((x > (PANEL_WIDTH / 4)) && (x < ((3 * PANEL_WIDTH) / 4)) && (y >
        // ((64 * PANEL_HEIGHT) / 160))
        // && (y < ((73 * PANEL_HEIGHT) / 160))) {
        // // relance le jeu.
        // this.reset();
        // } // Sinon en appuyant sur le bouton "Menu"
        // else if ((x > (PANEL_WIDTH / 4)) && (x < ((3 * PANEL_WIDTH) / 4)) &&
        // (y > ((75 * PANEL_HEIGHT) / 160))
        // && (y < ((84 * PANEL_HEIGHT) / 160))) {
        // // Envoie au menu.
        // this.screen = 2;
        // } // Sinon en appuyant sur le bouton "Crédits"
        // else if ((x > (PANEL_WIDTH / 4)) && (x < ((3 * PANEL_WIDTH) / 4)) &&
        // (y > ((86 * PANEL_HEIGHT) / 160))
        // && (y < ((95 * PANEL_HEIGHT) / 160))) {
        // // Envoie sur la page crédits
        // this.screen = 6;
        // }
        // break;
        //
        // case 6:// Sur la page de crédits
        // // En appuyant sur le bouton "Menu"
        // if ((x > (PANEL_WIDTH / 4)) && (x < ((3 * PANEL_WIDTH) / 4)) && (y >
        // ((145 * PANEL_HEIGHT) / 160))
        // && (y < ((154 * PANEL_HEIGHT) / 160))) {
        // // Renvoie au menu.
        // this.screen = 2;
        // }
        //
        // break;
        // case 7:// Sur la page des scores
        // // En appuyant sur le bouton "Menu"
        // if ((x > (PANEL_WIDTH / 4)) && (x < ((3 * PANEL_WIDTH) / 4)) && (y >
        // ((145 * PANEL_HEIGHT) / 160))
        // && (y < ((154 * PANEL_HEIGHT) / 160))) {
        // // Renvoie au menu.
        // this.screen = 2;
        // } else if ((x > (PANEL_WIDTH / 20)) && (x < ((PANEL_WIDTH / 20) + ((9
        // * PANEL_HEIGHT) / 160)))
        // && (y > ((42 * PANEL_HEIGHT) / 160)) && (y < ((51 * PANEL_HEIGHT) /
        // 160))) {
        // // Bouton fleche vers le haut
        // if (this.rawScoreTable == 1) {
        // } else {
        // this.rawScoreTable -= 1;
        // }
        // } else if ((x > (PANEL_WIDTH / 20)) && (x < ((PANEL_WIDTH / 20) + ((9
        // * PANEL_HEIGHT) / 160)))
        // && (y > ((108 * PANEL_HEIGHT) / 160)) && (y < ((117 * PANEL_HEIGHT) /
        // 160))) {
        // // Bouton fleche du bas
        // if (this.rawScoreTable < 16) {
        // this.rawScoreTable += 1;
        // }
        // } else if ((x > ((15 * PANEL_WIDTH) / 80)) && (x < ((34 *
        // PANEL_WIDTH) / 80))
        // && (y > ((108 * PANEL_HEIGHT) / 160)) && (y < ((117 * PANEL_HEIGHT) /
        // 160))) {
        // // Bouton "Niveau 1"
        // this.levelScoreTable = 1;
        // this.rawScoreTable = 1;
        // } else if ((x > ((36 * PANEL_WIDTH) / 80)) && (x < ((55 *
        // PANEL_WIDTH) / 80))
        // && (y > ((108 * PANEL_HEIGHT) / 160)) && (y < ((117 * PANEL_HEIGHT) /
        // 160))) {
        // // Bouton "Niveau 2"
        // this.levelScoreTable = 2;
        // this.rawScoreTable = 1;
        // } else if ((x > ((57 * PANEL_WIDTH) / 80)) && (x < ((76 *
        // PANEL_WIDTH) / 80))
        // && (y > ((108 * PANEL_HEIGHT) / 160)) && (y < ((117 * PANEL_HEIGHT) /
        // 160))) {
        // // Bouton "Niveau 3"
        // this.levelScoreTable = 3;
        // this.rawScoreTable = 1;
        //
        // }
        // break;
        // case 8:// Sur la page des cheat codes
        // if ((x > (PANEL_WIDTH / 4)) && (x < ((3 * PANEL_WIDTH) / 4)) && (y >
        // ((145 * PANEL_HEIGHT) / 160))
        // && (y < ((154 * PANEL_HEIGHT) / 160))) {
        //
        // this.screen = 2;
        // } else if ((x > (PANEL_WIDTH / 4)) && (x < ((3 * PANEL_WIDTH) / 4))
        // && (y > ((64 * PANEL_HEIGHT) / 160)) && (y < ((73 * PANEL_HEIGHT) /
        // 160))) {
        // // Bouton "Antigravité"
        // // Change l'état du boolean correspondant.
        // if (this.antiGravity) {
        // this.antiGravity = false;
        // } else {
        // this.antiGravity = true;
        // }
        // } else if ((x > (PANEL_WIDTH / 4)) && (x < ((3 * PANEL_WIDTH) / 4))
        // && (y > ((75 * PANEL_HEIGHT) / 160)) && (y < ((84 * PANEL_HEIGHT) /
        // 160))) {
        // // Bouton "Fuel Infini"
        // // Change l'état du boolean correspondant.
        // if (this.infiniteFuel) {
        // this.infiniteFuel = false;
        // } else {
        // this.infiniteFuel = true;
        // }
        // } else if ((x > (PANEL_WIDTH / 4)) && (x < ((3 * PANEL_WIDTH) / 4))
        // && (y > ((86 * PANEL_HEIGHT) / 160)) && (y < ((95 * PANEL_HEIGHT) /
        // 160))) {
        // // Bouton "Vaisseau Star Wars"
        // // Change l'état du boolean correspondant.
        // if (this.starWarsShip) {
        // this.starWarsShip = false;
        // fusee.couleur1 = couleurNavette1;
        // fusee.couleur2 = couleurNavette2;
        // fusee.couleur3 = couleurNavette3;
        // } else {
        // this.starWarsShip = true;
        // fusee.couleur1 = couleurStarWarsShip1;
        // fusee.couleur2 = couleurStarWarsShip2;
        // fusee.couleur3 = couleurStarWarsShip3;
        // }
        // }
        // break;
        //
        // }
    }

}
