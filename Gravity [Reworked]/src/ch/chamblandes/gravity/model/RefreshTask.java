package ch.chamblandes.gravity.model;

import java.util.TimerTask;

public class RefreshTask extends TimerTask {

    private GameEngine gameEngine;

    public RefreshTask(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override
    public void run() {
        this.gameEngine.computeOneStep();
    }
}