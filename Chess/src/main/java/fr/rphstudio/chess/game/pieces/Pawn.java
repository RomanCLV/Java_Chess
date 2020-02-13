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
        Piece pieceTested;

        // eat left
        if (column - 1 >= 0) {
            pieceTested = board.getPiece(line + way, column - 1);
            if (pieceTested != null && color != pieceTested.getPieceColor()) {
                positions.add(new ChessPosition(column - 1, line + way)); // x ; y  => y ; x
            }
        }

        // eat right
        if (column + 1 < 8) {
            pieceTested = board.getPiece(line + way, column + 1);
            if (pieceTested != null && color != pieceTested.getPieceColor()) {
                positions.add(new ChessPosition(column + 1, line + way)); // x ; y  => y ; x
            }
        }

        // front
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

        // prise en passant
        // left
        /*
        Piece lastPieceMoved = board.getLastPieceMoved();
        Piece currentPiece = board.getPiece(p);

        if (lastPieceMoved != null) {
            System.out.println("PAWN : \npiece : " + currentPiece + "\nlast  : " + lastPieceMoved);
            System.out.println("pos : \npiece : " + board.getPositionOf(currentPiece) + " : " + board.getPositionOf(currentPiece).x + " | " + board.getPositionOf(currentPiece).y);
            System.out.println("last  : " + board.getPositionOf(lastPieceMoved) + " : " + board.getPositionOf(lastPieceMoved).x + " | " + board.getPositionOf(lastPieceMoved).y);
            System.out.println("PAWN DONE");

            ChessPosition lastPieceMovedPos = board.getPositionOf(lastPieceMoved);

            if (lastPieceMoved.getPieceType() == ChessType.TYP_PAWN && lastPieceMoved.getNbTurns() == 1 &&
                    (lastPieceMovedPos.y == 3) || (lastPieceMovedPos.y == 4)) {

                if ((currentPiece.getPieceColor() == ChessColor.CLR_BLACK && line == 4) || (currentPiece.getPieceColor() == ChessColor.CLR_WHITE && line == 3)) {
                    if (Math.abs(p.x - lastPieceMovedPos.x) == 1) {
                        if (p.x < lastPieceMovedPos.x) {
                            positions.add(new ChessPosition(p.x + 1, p.y + way));
                        }
                        else {
                            positions.add(new ChessPosition(p.x - 1, p.y + way));
                        }
                    }
                }
            }
        }*/
        return positions;
    }
}
