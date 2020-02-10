package fr.rphstudio.chess.game.pieces;

import fr.rphstudio.chess.game.Board;
import fr.rphstudio.chess.game.IMove;
import fr.rphstudio.chess.game.Piece;
import fr.rphstudio.chess.interf.IChess.*;

import java.util.ArrayList;
import java.util.List;

public class Bishop  implements IMove {

    @Override
    public List<ChessPosition> getPossibleMoves(ChessPosition p, Board board) {
        List<ChessPosition> positions = new ArrayList<>();

        int line = p.y;
        int column = p.x;
        Piece pieceTested;
        ChessColor color = board.getPiece(p).getPieceColor();
        boolean run = true;
        int increment = 0;

        // diagonal top right
        while (run) {
            increment++;
            if (line - increment >= 0 && column + increment < 8) {
                pieceTested = board.getPiece(line - increment, column + increment);
                if (pieceTested == null) {
                    positions.add(new ChessPosition(column + increment, line - increment));
                }
                else {
                    if (color != pieceTested.getPieceColor()) {
                        positions.add(new ChessPosition(column + increment, line - increment));
                    }
                    run = false;
                }
            }
            else {
                run = false;
            }
        }
        run = true;
        increment = 0;

        // diagonal bottom right
        while (run) {
            increment++;
            if (line + increment < 8 && column + increment < 8) {
                pieceTested = board.getPiece(line + increment, column + increment);
                if (pieceTested == null) {
                    positions.add(new ChessPosition(column + increment, line + increment));
                }
                else {
                    if (color != pieceTested.getPieceColor()) {
                        positions.add(new ChessPosition(column + increment, line + increment));
                    }
                    run = false;
                }
            }
            else {
                run = false;
            }
        }
        run = true;
        increment = 0;

        // diagonal bottom left
        while (run) {
            increment++;
            if (line + increment < 8 && column - increment >= 0) {
                pieceTested = board.getPiece(line + increment, column - increment);
                if (pieceTested == null) {
                    positions.add(new ChessPosition(column - increment, line + increment));
                }
                else {
                    if (color != pieceTested.getPieceColor()) {
                        positions.add(new ChessPosition(column - increment, line + increment));
                    }
                    run = false;
                }
            }
            else {
                run = false;
            }
        }
        run = true;
        increment = 0;

        // diagonal top left
        while (run) {
            increment++;
            if (line - increment >= 0 && column - increment >= 0) {
                pieceTested = board.getPiece(line - increment, column - increment);
                if (pieceTested == null) {
                    positions.add(new ChessPosition(column - increment, line - increment));
                }
                else {
                    if (color != pieceTested.getPieceColor()) {
                        positions.add(new ChessPosition(column - increment, line - increment));
                    }
                    run = false;
                }
            }
            else {
                run = false;
            }
        }
        return positions;
    }
}
