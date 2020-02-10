package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess.*;

public class Piece {

    private ChessType type;
    private ChessColor color;

    public Piece(ChessType type, ChessColor color) {
        this.type = type;
        this.color = color;
    }

    public ChessType getPieceType() {
        return this.type;
    }

    public ChessColor getPieceColor() {
        return this.color;
    }
}
