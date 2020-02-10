package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.*;

public class ChessModel implements IChess {

    private static ChessModel instance = new ChessModel();
    
    public String boardPath;
    
    private Piece[][] boardGame;

    private ChessModel() {
        this.boardPath = "../../../resources/sprites/board2.png";
        this.boardGame = new Piece[8][];
        for (int line = 0; line < 8; line++) {
            this.boardGame[line] = new Piece[8];
        }
        createBoardGame();
        //dispBoard();
    }

    private void createBoardGame() {
        for (int line = 0; line < 8; line++) {
            for (int column = 0; column < 8; column++) {
                Piece piece = null;
                switch (line) {
                    case 0:
                    case 7:
                        switch (column) {
                            case 0:
                            case 7:
                                piece = new Piece(ChessType.TYP_ROOK, line == 0 ? ChessColor.CLR_BLACK : ChessColor.CLR_WHITE);
                                break;
                            case 1:
                            case 6:
                                piece = new Piece(ChessType.TYP_KNIGHT, line == 0 ? ChessColor.CLR_BLACK : ChessColor.CLR_WHITE);
                                break;
                            case 2:
                            case 5:
                                piece = new Piece(ChessType.TYP_BISHOP, line == 0 ? ChessColor.CLR_BLACK : ChessColor.CLR_WHITE);
                                break;
                            case 3:
                                piece = new Piece(ChessType.TYP_QUEEN, line == 0 ? ChessColor.CLR_BLACK : ChessColor.CLR_WHITE);
                                break;
                            case 4:
                                piece = new Piece(ChessType.TYP_KING, line == 0 ? ChessColor.CLR_BLACK : ChessColor.CLR_WHITE);
                                break;
                        }
                        break;
                    case 1:
                    case 6:
                        piece = new Piece(ChessType.TYP_PAWN, line == 1 ? ChessColor.CLR_BLACK : ChessColor.CLR_WHITE);
                        break;
                    default:
                        break;
                }
                this.boardGame[line][column] = piece;
            }
        }
    }

    private void dispBoard() {
        for (int line = 0; line < 8; line++) {
            for (int column = 0; column < 8; column++) {
                if (this.boardGame[line][column] != null) {
                    System.out.print(this.boardGame[line][column].getPieceType());
                }
                else {
                    System.out.print("void");
                }
                if (column < 7) {
                    System.out.print(" ; ");
                }
            }
            System.out.println();
        }
    }

    private Piece[] boardToArray() {
        Piece[] pieces = new Piece[64];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                pieces[i * 8 + j] = this.boardGame[i][j];
            }
        }
        return pieces;
    }

    public static ChessModel getInstance() {
        return instance;
    }


    @Override
    public void reinit() {

    }

    @Override
    public ChessType getPieceType(ChessPosition p) throws EmptyCellException, OutOfBoardException {
        if (this.boardGame[p.y][p.x] !=  null) {
            return this.boardGame[p.y][p.x].getPieceType();
        }
        throw new EmptyCellException();
    }

    @Override
    public ChessColor getPieceColor(ChessPosition p) throws EmptyCellException, OutOfBoardException {
        if (this.boardGame[p.y][p.x] !=  null) {
            return this.boardGame[p.y][p.x].getPieceColor();
        }
        throw new EmptyCellException();
    }

    @Override
    public int getNbRemainingPieces(ChessColor color) git {
        Piece[] pieces = boardToArray();
        List<Piece> validPieces = new ArrayList<>();
        for (Piece piece: pieces) {
            if (piece != null && piece.getPieceColor() == color) {
                validPieces.add(piece);
            }
        }
        return validPieces.size();
    }

    @Override
    public List<ChessPosition> getPieceMoves(ChessPosition p) {
        return new ArrayList<>();
    }

    @Override
    public void movePiece(ChessPosition p0, ChessPosition p1) {

    }

    @Override
    public ChessKingState getKingState(ChessColor color) {
        return ChessKingState.KING_SAFE;
    }

    @Override
    public List<ChessType> getRemovedPieces(ChessColor color) {
        return new ArrayList<>();
    }

    @Override
    public boolean undoLastMove() {
        return false;
    }

    @Override
    public long getPlayerDuration(ChessColor color, boolean isPlaying) {
        return 0;
    }
}
