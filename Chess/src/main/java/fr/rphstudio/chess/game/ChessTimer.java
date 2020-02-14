package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess.*;

/**
 * ChessTimer Class
 * @see #timeStart
 * @see #timeStoppedBlack
 * @see #timeStoppedWhite
 * @see #whiteIsPlaying
 * @see #timeSwitch
 */
public class ChessTimer {

    private long timeStart;
    private long timeStoppedWhite;
    private long timeStoppedBlack;
    private boolean whiteIsPlaying;
    private long timeSwitch;

    /**
     * Constructor
     */
    public ChessTimer() {
        this.timeStart = System.currentTimeMillis();
        this.timeStoppedWhite = 0;
        this.timeStoppedBlack = 0;
        this.whiteIsPlaying = true;
        this.timeSwitch = timeStart;
    }

    /**
     * Get play's time
     * @param color Player's color
     * @param isPlaying Player's state
     * @return A time in milliseconds
     */
    public long getTime(ChessColor color, boolean isPlaying) {
        if (whiteIsPlaying != isPlaying) {
            if (whiteIsPlaying) {
                timeStoppedWhite += System.currentTimeMillis() - timeSwitch;
            }
            else {
                timeStoppedBlack += System.currentTimeMillis() - timeSwitch;
            }
            timeSwitch = System.currentTimeMillis();
            whiteIsPlaying = isPlaying;
        }

        if (ChessColor.CLR_WHITE == color) {
            return System.currentTimeMillis() - this.timeStart - this.timeStoppedWhite;
        }
        else {
            return System.currentTimeMillis() - this.timeStart - this.timeStoppedBlack;
        }
    }

    /**
     * Get a copy of the instance
     * @return A timer
     */
    public ChessTimer clone() {
        ChessTimer timerTmp = new ChessTimer();
        timerTmp.timeStart = this.timeStart;
        timerTmp.timeStoppedBlack = this.timeStoppedBlack;
        timerTmp.timeStoppedWhite = this.timeStoppedWhite;
        timerTmp.whiteIsPlaying = this.whiteIsPlaying;
        timerTmp.timeSwitch = this.timeSwitch;
        return timerTmp;
    }
}
