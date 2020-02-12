package fr.rphstudio.chess.game;

import fr.rphstudio.chess.game.pieces.*;
import fr.rphstudio.chess.interf.IChess.*;

import java.util.List;

public class Piece {

    private ChessType type;
    private ChessColor color;
    private IMove move;
    private int nbTurns;

    public Piece(ChessType type, ChessColor color) {
        this.type = type;
        this.color = color;
        this.nbTurns = 0;
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

    public List<ChessPosition> getPiecesMoves (ChessPosition p, Board board) {
        return this.move.getPossibleMoves(p, board);
    }

    public void increaseNbTurn() {
        this.nbTurns++;
    }

    public boolean hasMoved() {
        return this.nbTurns > 0;
    }
}
