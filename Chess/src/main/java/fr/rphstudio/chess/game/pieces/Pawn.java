package fr.rphstudio.chess.game.pieces;

import fr.rphstudio.chess.game.Board;
import fr.rphstudio.chess.game.IMove;
import fr.rphstudio.chess.game.Piece;
import fr.rphstudio.chess.interf.IChess.*;

import java.util.ArrayList;
import java.util.List;

public class Pawn implements IMove {

    @Override
    public List<ChessPosition> getPossibleMoves(ChessPosition p, Board board) {
        List<ChessPosition> positions = new ArrayList<>();
        int line = p.y;
        int column = p.x;

        ChessColor color = board.getPiece(p).getPieceColor();
        int way = color == ChessColor.CLR_BLACK ? 1 : -1;
        Piece pieceTested = null;

        if (column - 1 >= 0) {
            pieceTested = board.getPiece(line + way, column - 1);
            if (pieceTested != null && color != pieceTested.getPieceColor()) {
                positions.add(new ChessPosition(column - 1, line + way)); // x ; y  => y ; x
            }
        }

        if (column + 1 < 8) {
            pieceTested = board.getPiece(line + way, column + 1);
            if (pieceTested != null && color != pieceTested.getPieceColor()) {
                positions.add(new ChessPosition(column + 1, line + way)); // x ; y  => y ; x
            }
        }

        pieceTested = board.getPiece(line + way, column);
        if (pieceTested == null) {
            positions.add(new ChessPosition(column, line + way)); // x ; y  => y ; x

            if ((color == ChessColor.CLR_BLACK && line == 1) || (color == ChessColor.CLR_WHITE && line == 6)) {
                pieceTested = board.getPiece(line + (way * 2), column);
                if (pieceTested == null) {
                    positions.add(new ChessPosition(column, line + (way * 2))); // x ; y  => y ; x
                }
            }
        }
        return positions;
    }
}
