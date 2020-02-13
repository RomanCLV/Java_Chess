package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess.*;

/**
 * ChessTimer Class
 */

public class ChessTimer {

    private long timeStart;
    private long timeStoppedWhite;
    private long timeStoppedBlack;
    private boolean whiteIsPlaying;
    private long timeSwitch;

    /**
     * watcher for getTime function
     */
    public ChessTimer() {
        this.timeStart = System.currentTimeMillis();
        this.timeStoppedWhite = 0;
        this.timeStoppedBlack = 0;
        this.whiteIsPlaying = true;
        this.timeSwitch = timeStart;
    }

    /**
     * stop the time each time a team played
     * @param color color of the piece
     * @param isPlaying use to see which color is playing
     * @return the time played by both team
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
     * copy the time at each new move to give back the good time when undo a move
     * @return the timer time
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
