package cz.simplycolour.logic;

import cz.simplycolour.Game;
import org.lwjgl.input.Keyboard;

public class Input {
    public static void processInput() {
        Game game = Game.getGame();

        if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
            game.player.setMomentumX(game.player.getMomentum().getX() + 0.4f);
        if (Keyboard.isKeyDown(Keyboard.KEY_LEFT))
            game.player.setMomentumX(game.player.getMomentum().getX() - 0.4f);
        if (Keyboard.isKeyDown(Keyboard.KEY_UP))
            game.player.setMomentumY(game.player.getMomentum().getY() + 2.8f);
    }
}
