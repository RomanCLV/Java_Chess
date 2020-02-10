package fr.rphstudio.chess.game.pieces;

import fr.rphstudio.chess.game.*;
import fr.rphstudio.chess.interf.IChess.*;

import java.util.ArrayList;
import java.util.List;

public class Knight implements IMove {

    @Override
    public List<ChessPosition> getPossibleMoves(ChessPosition p, Board board) {
        List<ChessPosition> positions = new ArrayList<>();

        // top
        int line = p.y - 2;
        int column = p.x - 1;
        if (isValidCase(board, p, line, column)) {
            positions.add(new ChessPosition(column, line));
        }
        column = p.x + 1;
        if (isValidCase(board, p, line, column)) {
            positions.add(new ChessPosition(column, line));
        }

        // right
        line = p.y - 1;
        column = p.x + 2;
        if (isValidCase(board, p, line, column)) {
            positions.add(new ChessPosition(column, line));
        }
        line = p.y + 1;
        if (isValidCase(board, p, line, column)) {
            positions.add(new ChessPosition(column, line));
        }

        // bottom
        line = p.y + 2;
        column = p.x + 1;
        if (isValidCase(board, p, line, column)) {
            positions.add(new ChessPosition(column, line));
        }
        column = p.x - 1;
        if (isValidCase(board, p, line, column)) {
            positions.add(new ChessPosition(column, line));
        }

        // left
        line = p.y - 1;
        column = p.x - 2;
        if (isValidCase(board, p, line, column)) {
            positions.add(new ChessPosition(column, line));
        }
        line = p.y + 1;
        if (isValidCase(board, p, line, column)) {
            positions.add(new ChessPosition(column, line));
        }
        return positions;
    }

    // check if the case is in the square (0 ; 8)
    private boolean validPosition(int line, int column) {
        return line >= 0 && line < 8 && column >= 0 && column < 8;
    }

    private boolean isValidCase(Board board, ChessPosition p, int line, int column) {
        ChessColor color = board.getPiece(p).getPieceColor();
        if (validPosition(line, column)) {
            Piece pieceTested = board.getPiece(line, column);
            if (pieceTested == null) {
                return true;
            }
            else {
                return pieceTested.getPieceColor() != color;
            }
        }
        return false;
    }
}