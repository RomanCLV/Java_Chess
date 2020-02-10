package fr.rphstudio.chess.game.pieces;

import fr.rphstudio.chess.game.*;
import fr.rphstudio.chess.interf.IChess.*;

import java.util.ArrayList;
import java.util.List;

public class Rook  implements IMove {

    @Override
    public List<ChessPosition> getPossibleMoves(ChessPosition p, Board board) {
        List<ChessPosition> positions = new ArrayList<>();

        int line = p.y;
        int column = p.x;
        Piece pieceTested;
        ChessColor color = board.getPiece(p).getPieceColor();
        boolean run = true;
        int increment = 0;

        // top
        while (run) {
            increment++;
            if (line - increment >= 0) {
                pieceTested = board.getPiece(line - increment, column);
                if (pieceTested == null) {
                    positions.add(new ChessPosition(column, line - increment));
                }
                else {
                    if (color != pieceTested.getPieceColor()) {
                        positions.add(new ChessPosition(column, line - increment));
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

        // right
        while (run) {
            increment++;
            if (column + increment < 8) {
                pieceTested = board.getPiece(line, column + increment);
                if (pieceTested == null) {
                    positions.add(new ChessPosition(column + increment, line));
                }
                else {
                    if (color != pieceTested.getPieceColor()) {
                        positions.add(new ChessPosition(column + increment, line));
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

        // bottom
        while (run) {
            increment++;
            if (line + increment < 8) {
                pieceTested = board.getPiece(line + increment, column);
                if (pieceTested == null) {
                    positions.add(new ChessPosition(column, line + increment));
                }
                else {
                    if (color != pieceTested.getPieceColor()) {
                        positions.add(new ChessPosition(column, line + increment));
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

        // left
        while (run) {
            increment++;
            if (column - increment >= 0) {
                pieceTested = board.getPiece(line, column - increment);
                if (pieceTested == null) {
                    positions.add(new ChessPosition(column - increment, line));
                }
                else {
                    if (color != pieceTested.getPieceColor()) {
                        positions.add(new ChessPosition(column - increment, line));
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