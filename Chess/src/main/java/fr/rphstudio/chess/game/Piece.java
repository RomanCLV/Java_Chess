package fr.rphstudio.chess.game;

import fr.rphstudio.chess.game.pieces.*;
import fr.rphstudio.chess.interf.IChess.*;

public class Piece {

    private ChessType type;
    private ChessColor color;
    public IMove move;

    public Piece(ChessType type, ChessColor color) {
        this.type = type;
        this.color = color;
        switch (type) {
            case TYP_PAWN:
                this.move = new Pawn();
                break;
            case TYP_ROOK:
                this.move = new Rook();
                break;
            case TYP_KNIGHT:
                this.move = new Knight();
                break;
            case TYP_BISHOP:
                this.move = new Bishop();
                break;
            case TYP_QUEEN:
                this.move = new Queen();
                break;
            case TYP_KING:
                this.move = new King();
                break;
            default:
                this.move = null;
                break;
        }
    }

    public ChessType getPieceType() {
        return this.type;
    }

    public ChessColor getPieceColor() {
        return this.color;
    }
}
