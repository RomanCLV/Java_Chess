package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.*;
import java.util.ArrayList;
import java.util.List;

public class ChessModel implements IChess {

    private static ChessModel instance = new ChessModel();
    
    public String boardPath;
    private Board game;
    private boolean modeTesting;
    private short modeBoard;


    private ChessModel() {
        this.boardPath = "../../../resources/sprites/board2.png";
        this.modeTesting = false;
        this.modeBoard = 0;
    }

    public static ChessModel getInstance() {
        return instance;
    }

    @Override
    public void reinit() {
        if (this.modeTesting) {
            this.game = new Board(modeBoard);
            modeBoard++;
            if (modeBoard > 9) {
                modeBoard = 0;
            }
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

            if (piece.getPieceType() == ChessType.TYP_KING && this.game.getKingState(piece.getPieceColor()) == ChessKingState.KING_THREATEN) {
                for (int i = 0; i < moves.size(); i++) {
                    Piece pieceTest = this.game.getPiece(moves.get(i));
                    if (pieceTest != null && pieceTest.getPieceType() == ChessType.TYP_ROOK) {
                        moves.remove(i);
                        i--;
                    }
                }
            }

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

        this.game.addState(this.game.clone());
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
        Board boardTmp = this.game.returnLastState();
        if (boardTmp != null) {

            this.game = boardTmp;
            return true;
        }
        else {
            reinit();
            return false;
        }
    }

    @Override
    public long getPlayerDuration(ChessColor color, boolean isPlaying) {
        return this.game.getTime(color, isPlaying);
    }
}
