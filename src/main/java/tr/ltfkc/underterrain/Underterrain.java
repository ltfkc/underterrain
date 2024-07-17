package tr.ltfkc.underterrain;

import tr.ltfkc.underterrain.engine.Game;
import tr.ltfkc.underterrain.engine.GameListener;

public class Underterrain implements GameListener {

    @Override
    public void create(Game game) {

    }

    @Override
    public void render(Game game) {

    }

    @Override
    public void dispose(Game game) {

    }

    public static void main(String[] args) {
        Game game = new Game(800, 450, "Underterrain");
        game.setGameListener(new Underterrain());
        game.run();
    }
}
