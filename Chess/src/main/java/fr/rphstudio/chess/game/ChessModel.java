package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ChessModel Class
 * @see #ChessModel
 * @see #boardPath
 * @see #game
 * @see #modeTesting
 * @see #modeBoard
 */
public class ChessModel implements IChess {

    private static ChessModel instance = new ChessModel();
    
    public String boardPath;
    private Board game;
    private boolean modeTesting;
    private short modeBoard;

    /**
     * Constructor
     */
    private ChessModel() {
        this.boardPath = "../../../resources/sprites/board2.png";
        this.modeTesting = false;
        this.modeBoard = 0;
    }

    /**
     * Get the instance (singleton)
     * @return
     */
    public static ChessModel getInstance() {
        return instance;
    }

    /**
     * Initialisation of the game
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
     * Get the type of a piece
     * @param p x/y position on the board where we want to get the piece type.
     * @return The type
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
     * Get the color of a piece
     * @param p x/y position on the board where we want to get the piece color.
     * @return The color
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
     * Give the number of pieces of a color
     * @param color The color
     * @return The number
     */
    @Override
    public int getNbRemainingPieces(ChessColor color) {
        return this.game.getNbPieceColor(color);
    }

    /**
     * Get a list of possible position of a piece
     * @param p requested piece position.
     * @return
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
     * Move a piece
     * @param p0 source position on the board.
     * @param p1 destination position on the board.
     */
    @Override
    public void movePiece(ChessPosition p0, ChessPosition p1) {

        this.game.addState(this.game.clone());
        this.game.movePiece(p0, p1);
    }

    /**
     * Get the state of a king
     * @param color the requested king color.
     * @return
     */
    @Override
    public ChessKingState getKingState(ChessColor color) {
        return this.game.getKingState(color);
    }

    /**
     * Get the eaten pieces
     * @param color color of the removed pieces
     * @return
     */
    @Override
    public List<ChessType> getRemovedPieces(ChessColor color) {
        return this.game.getPiecesLost(color);
    }

    /**
     * Return to the last state of board
     * @return change the player
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
     * Get the player's time
     * @param color The color of the player from whom we want to get the current duration.
     * @param isPlaying Indicates if the player color is the one currently playing.
     * @return
     */
    @Override
    public long getPlayerDuration(ChessColor color, boolean isPlaying) {
        return this.game.getTime(color, isPlaying);
    }
}
