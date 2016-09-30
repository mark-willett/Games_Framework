package ai;

import core.NaughtsAndCrossesGame;

/**
 * Abstract class for all naughts and crosses AIs
 * @author Dom Parfitt
 */

public abstract class NaughtsAndCrossesAI {

    protected NaughtsAndCrossesGame game;

    public abstract void makeMove();
}
