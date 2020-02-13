package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.*;
import java.util.ArrayList;
import java.util.List;

public class ChessModel implements IChess {

    private static ChessModel instance = new ChessModel();
    
    public String boardPath;
    private Board game;
    private boolean modeTesting;
    private int modeBoard;

    /**
     * chess model
     */
    private ChessModel() {
        this.boardPath = "../../../resources/sprites/board2.png";
        this.modeTesting = false;
        this.modeBoard = 0;
    }

    /**
     * instance
     * @return new chess model
     */
    public static ChessModel getInstance() {
        return instance;
    }

    /**
     * reinit the game
     */
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

    /**
     * type of piece of position p
     * @param p x/y position on the board where we want to get the piece type.
     * @return type of piece
     * @throws EmptyCellException
     * @throws OutOfBoardException
     */
    @Override
    public ChessType getPieceType(ChessPosition p) throws EmptyCellException, OutOfBoardException {
        Piece piece = this.game.getPiece(p);
        if (piece != null) {
            return piece.getPieceType();
        }
        throw new EmptyCellException();
    }

    /**
     * color of piece of position p
     * @param p x/y position on the board where we want to get the piece color.
     * @return color of piece
     * @throws EmptyCellException
     * @throws OutOfBoardException
     */
    @Override
    public ChessColor getPieceColor(ChessPosition p) throws EmptyCellException, OutOfBoardException {
        Piece piece = this.game.getPiece(p);
        if (piece != null) {
            return piece.getPieceColor();
        }
        throw new EmptyCellException();
    }

    /**
     * all pieces of the color
     * @param color the requested color of the pieces to count.
     * @return all pieces of the color
     */
    @Override
    public int getNbRemainingPieces(ChessColor color) {
        return this.game.getNbPieceColor(color);
    }

    /**
     * valid moves of piece
     * @param p requested piece position.
     * @return list of valid moves
     */
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

    /**
     * move piece
     * @param p0 source position on the board.
     * @param p1 destination position on the board.
     */
    @Override
    public void movePiece(ChessPosition p0, ChessPosition p1) {

        this.game.addState(this.game.clone());
        this.game.movePiece(p0, p1);
    }

    /**
     * if king is check
     * @param color the requested king color.
     * @return color if king is check
     */
    @Override
    public ChessKingState getKingState(ChessColor color) {
        return this.game.getKingState(color);
    }

    /**
     * piece removed
     * @param color color of the removed pieces
     * @return list of removed piece
     */
    @Override
    public List<ChessType> getRemovedPieces(ChessColor color) {
        return this.game.getPiecesLost(color);
    }

    /**
     * back to back
     * @return boolean is board in the list
     */
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

    /**
     * timer
     * @param color The color of the player from whom we want to get the current duration.
     * @param isPlaying Indicates if the player color is the one currently playing.
     * @return time of color is playing
     */
    @Override
    public long getPlayerDuration(ChessColor color, boolean isPlaying) {
        return this.game.getTime(color, isPlaying);
    }
}
