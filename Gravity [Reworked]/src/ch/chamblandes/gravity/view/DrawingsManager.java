package ch.chamblandes.gravity.view;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ch.chamblandes.gravity.displayables.Displayable;
import ch.chamblandes.gravity.displayables.Frame;
import ch.chamblandes.gravity.model.ScreenManager.Screen;

public class DrawingsManager {

    private Map<Screen, Set<Displayable>> staticDisplayables;

    public Set<Frame> getFramesForScreen(Screen screen) {
        Set<Frame> frames = new HashSet<Frame>();

        for (Displayable displayable : this.staticDisplayables.get(screen)) {
            if (displayable instanceof Frame) {
                frames.add((Frame) displayable);
            }
        }

        return frames;
    }
}
