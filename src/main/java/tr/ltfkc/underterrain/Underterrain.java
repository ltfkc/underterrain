package tr.ltfkc.underterrain;

import tr.ltfkc.underterrain.engine.Game;
import tr.ltfkc.underterrain.game.Survival;

public class Underterrain {

    public static void main(String[] args) {
        Game game = new Game(16 * 50, 9 * 50, "Underterrain", 144);
        game.setGameListener(new Survival());
        game.run();
    }
}
