package fr.rphstudio.chess.game;

import fr.rphstudio.chess.game.pieces.*;
import fr.rphstudio.chess.interf.IChess.*;

import java.util.List;

/**
 * Piece Class
 * @see #type
 * @see #color
 * @see #move
 * @see #nbTurns
 */
public class Piece {

    private ChessType type;
    private ChessColor color;
    private IMove move;
    private int nbTurns;

    /**
     * Constructor
     * @param type Piece's type
     * @param color Piece's color
     */
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

    /**
     * Get piece's type
     * @return A type
     */
    public ChessType getPieceType() {
        return this.type;
    }

    /**
     * Get Piece's color
     * @return A color
     */
    public ChessColor getPieceColor() {
        return this.color;
    }

    /**
     * Set the number of move of the piece
     * @param value
     */
    private void setNbTurns(int value) {
        this.nbTurns = value;
    }

    /**
     * Get the number of move of the piece
     * @return A number
     */
    public int getNbTurns() {
        return this.nbTurns;
    }

    /**
     * Get the possible moves of a piece
     * @param p Piece's position
     * @param board The board
     * @return A list of position
     */
    public List<ChessPosition> getPiecesMoves (ChessPosition p, Board board) {
        List<ChessPosition> moves = this.move.getPossibleMoves(p, board);

        return  moves;
    }

    /**
     * Increase the number of move of this piece
     */
    public void increaseNbTurn() {
        this.nbTurns++;
    }

    /**
     * Check if this piece moved
     * @return A boolean
     */
    public boolean hasMoved() {
        return this.nbTurns > 0;
    }

    /**
     * Get a copy of this
     * @return A piece
     */
    public Piece clone() {
        Piece pieceTmp = new Piece(this.type, this.color);
        pieceTmp.setNbTurns(this.nbTurns);
        return pieceTmp;
    }
}
