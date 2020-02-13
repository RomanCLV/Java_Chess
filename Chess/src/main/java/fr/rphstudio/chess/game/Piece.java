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

    private void setNbTurns(int value) {
        this.nbTurns = value;
    }

    public int getNbTurns() {
        return this.nbTurns;
    }

    public List<ChessPosition> getPiecesMoves (ChessPosition p, Board board) {
        List<ChessPosition> moves = this.move.getPossibleMoves(p, board);

        return  moves;
    }

    public void increaseNbTurn() {
        this.nbTurns++;
    }

    public boolean hasMoved() {
        return this.nbTurns > 0;
    }

    public Piece clone() {
        Piece pieceTmp = new Piece(this.type, this.color);
        pieceTmp.setNbTurns(this.nbTurns);
        return pieceTmp;
    }
}
