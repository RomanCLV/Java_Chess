package fr.rphstudio.chess.game.pieces;

import fr.rphstudio.chess.game.Board;
import fr.rphstudio.chess.game.IMove;
import fr.rphstudio.chess.game.Piece;
import fr.rphstudio.chess.interf.IChess.*;

import java.util.ArrayList;
import java.util.List;

public class King implements IMove {

    @Override
    public List<ChessPosition> getPossibleMoves(ChessPosition p, Board board) {
        List<ChessPosition> positions = new ArrayList<>();
        ChessColor color = board.getPiece(p).getPieceColor();
        int line = p.y;
        int column = p.x;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (validPosition(line + i, column + j)) {
                    Piece pieceTested = board.getPiece(line + i, column + j);
                    if (pieceTested == null) {
                        positions.add(new ChessPosition(column + j, line + i));
                    }
                    else {
                        if (color != pieceTested.getPieceColor()) {
                            positions.add(new ChessPosition(column + j, line + i));
                        }
                    }
                }
            }
        }

        // Castling
        if (!board.getPiece(p).hasMoved()) {
            Piece rook = board.getPiece(p.y, 0);
            if (rook != null && rook.getPieceType() == ChessType.TYP_ROOK && !rook.hasMoved()) {
                boolean leftWayVoid = true;
                for (int i = p.x - 1; i > 0; i--) {
                    if (board.getPiece(p.y, i) != null) {
                        leftWayVoid = false;
                        break;
                    }
                }
                if (leftWayVoid) {
                    positions.add(new ChessPosition(0, p.y));
                }
            }

            rook = board.getPiece(p.y, 7);
            if (rook != null && rook.getPieceType() == ChessType.TYP_ROOK && !rook.hasMoved()) {
                boolean rightWayVoid = true;
                for (int i = p.x + 1; i < 7; i++) {
                    if (board.getPiece(p.y, i) != null) {
                        rightWayVoid = false;
                        break;
                    }
                }
                if (rightWayVoid) {
                    positions.add(new ChessPosition(7, p.y));
                }
            }
        }
        return positions;
    }

    private boolean validPosition(int line, int column) {
        return line >= 0 && line < 8 && column >= 0 && column < 8;
    }

}