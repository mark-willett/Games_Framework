package ai;

import java.util.Random;

import core.NaughtsAndCrossesGame;

/**
 * Created by Dom on 20/09/2016.
 */

public class NaughtsAndCrossesRandomAI extends NaughtsAndCrossesAI {

    public NaughtsAndCrossesRandomAI(NaughtsAndCrossesGame game) {
        this.game = game;
    }

    @Override
    public void makeMove() {
        Random rand = new Random();
        int row = rand.nextInt(3);
        int col = rand.nextInt(3);
        String result = game.makeMove(row, col);
        while(result == "") {
            row = rand.nextInt(3);
            col = rand.nextInt(3);
            result = game.makeMove(row, col);
        }
    }
}
