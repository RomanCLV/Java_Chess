package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.*;
import java.util.ArrayList;
import java.util.List;

public class ChessModel implements IChess {

    private static ChessModel instance = new ChessModel();
    
    public String boardPath;
    private Board game;

    private int modeBoard; // a supprimer quand on fera le retour coup d'avant

    private ChessModel() {
        this.boardPath = "../../../resources/sprites/board2.png";
        //this.game = new Board(); // pas besoin car reinit est appele des le debut
    }

    public static ChessModel getInstance() {
        return instance;
    }

    @Override
    public void reinit() {
        if (true) {
            switch (modeBoard) {
                case 1:
                    game = new Board(ChessType.TYP_ROOK);
                    break;
                case 2:
                    game = new Board(ChessType.TYP_KNIGHT);
                    break;
                case 3:
                    game = new Board(ChessType.TYP_BISHOP);
                    break;
                case 4:
                    game = new Board(ChessType.TYP_QUEEN);
                    break;
                case 5:
                    game = new Board(ChessType.TYP_KING);
                    break;
                default:
                    modeBoard = 0;
                    game = new Board(ChessType.TYP_PAWN);
                    break;
            }
            modeBoard++;
        }
        else {
            game = new Board();
        }
    }

    @Override
    public ChessType getPieceType(ChessPosition p) throws EmptyCellException, OutOfBoardException {
        Piece piece = this.game.getPiece(p);
        if (piece != null) {
            return piece.getPieceType();
        }
        throw new EmptyCellException();
    }

    @Override
    public ChessColor getPieceColor(ChessPosition p) throws EmptyCellException, OutOfBoardException {
        Piece piece = this.game.getPiece(p);
        if (piece != null) {
            return piece.getPieceColor();
        }
        throw new EmptyCellException();
    }

    @Override
    public int getNbRemainingPieces(ChessColor color) {
        return this.game.getNbPieceColor(color);
    }

    @Override
    public List<ChessPosition> getPieceMoves(ChessPosition p) {
        Piece piece = this.game.getPiece(p);
        if (piece != null) {
            List<ChessPosition> moves = piece.getPiecesMoves(p, this.game);
            List<ChessPosition> validMoves = new ArrayList<>();
            for (ChessPosition position : moves) {
                Board boardTmp = this.game.clone();
                boardTmp.movePiece(p, position);
                if (boardTmp.getKingState(piece.getPieceColor()) == ChessKingState.KING_SAFE) {
                    validMoves.add(position);
                }
            }
            return validMoves;
        }
        return new ArrayList<>();
    }

    @Override
    public void movePiece(ChessPosition p0, ChessPosition p1) {
        this.game.movePiece(p0, p1);
    }

    @Override
    public ChessKingState getKingState(ChessColor color) {
        return this.game.getKingState(color);
    }

    @Override
    public List<ChessType> getRemovedPieces(ChessColor color) {
        return this.game.getPiecesLost(color);
    }

    @Override
    public boolean undoLastMove() {
        return true;
    }

    @Override
    public long getPlayerDuration(ChessColor color, boolean isPlaying) {
        return 0;
    }
}
