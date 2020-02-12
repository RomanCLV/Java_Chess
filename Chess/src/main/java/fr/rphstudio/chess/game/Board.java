package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess.*;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private Piece[][] game;

    private List<ChessType> whitePiecesLost;
    private List<ChessType> blackPiecesLost;

    public Board() {
        whitePiecesLost = new ArrayList<>();
        blackPiecesLost = new ArrayList<>();
        this.game = new Piece[8][];
        for (int line = 0; line < 8; line++) {
            this.game[line] = new Piece[8];
        }
        createBoardGame();
    }

    public Board(ChessType mode) {
        whitePiecesLost = new ArrayList<>();
        blackPiecesLost = new ArrayList<>();

        this.game = new Piece[8][];
        for (int line = 0; line < 8; line++) {
            this.game[line] = new Piece[8];
        }
        for (int line = 0; line < 8; line++) {
            for (int column = 0; column < 8; column++) {
                this.game[line][column] = null;
            }
        }
        switch (mode) {
            case TYP_PAWN:
                setPiece(6, 0, new Piece(ChessType.TYP_PAWN, ChessColor.CLR_WHITE));
                setPiece(6, 1, new Piece(ChessType.TYP_PAWN, ChessColor.CLR_WHITE));
                setPiece(5, 2, new Piece(ChessType.TYP_PAWN, ChessColor.CLR_WHITE));
                setPiece(5, 4, new Piece(ChessType.TYP_PAWN, ChessColor.CLR_WHITE));
                setPiece(5, 6, new Piece(ChessType.TYP_PAWN, ChessColor.CLR_WHITE));
                setPiece(6, 6, new Piece(ChessType.TYP_PAWN, ChessColor.CLR_WHITE));
                setPiece(5, 7, new Piece(ChessType.TYP_PAWN, ChessColor.CLR_WHITE));

                setPiece(4, 1, new Piece(ChessType.TYP_PAWN, ChessColor.CLR_BLACK));
                setPiece(1, 1, new Piece(ChessType.TYP_PAWN, ChessColor.CLR_BLACK));
                setPiece(2, 2, new Piece(ChessType.TYP_PAWN, ChessColor.CLR_BLACK));
                setPiece(4, 3, new Piece(ChessType.TYP_PAWN, ChessColor.CLR_BLACK));
                setPiece(2, 7, new Piece(ChessType.TYP_PAWN, ChessColor.CLR_BLACK));
                setPiece(3, 7, new Piece(ChessType.TYP_PAWN, ChessColor.CLR_BLACK));
                break;
            case TYP_ROOK:
                setPiece(4, 4, new Piece(ChessType.TYP_ROOK, ChessColor.CLR_BLACK));
                setPiece(7, 0, new Piece(ChessType.TYP_ROOK, ChessColor.CLR_WHITE));
                setPiece(0, 7, new Piece(ChessType.TYP_ROOK, ChessColor.CLR_BLACK));
                setPiece(6, 2, new Piece(ChessType.TYP_ROOK, ChessColor.CLR_BLACK));
                setPiece(6, 3, new Piece(ChessType.TYP_ROOK, ChessColor.CLR_WHITE));
                setPiece(3, 5, new Piece(ChessType.TYP_ROOK, ChessColor.CLR_WHITE));
                setPiece(3, 3, new Piece(ChessType.TYP_ROOK, ChessColor.CLR_WHITE));
                setPiece(5, 3, new Piece(ChessType.TYP_ROOK, ChessColor.CLR_BLACK));
                break;
            case TYP_KNIGHT:
                setPiece(0, 0, new Piece(ChessType.TYP_KNIGHT, ChessColor.CLR_BLACK));
                setPiece(0, 7, new Piece(ChessType.TYP_KNIGHT, ChessColor.CLR_WHITE));
                setPiece(2, 6, new Piece(ChessType.TYP_KNIGHT, ChessColor.CLR_WHITE));
                setPiece(7, 0, new Piece(ChessType.TYP_KNIGHT, ChessColor.CLR_WHITE));
                setPiece(7, 7, new Piece(ChessType.TYP_KNIGHT, ChessColor.CLR_BLACK));
                setPiece(4, 4, new Piece(ChessType.TYP_KNIGHT, ChessColor.CLR_BLACK));
                setPiece(5, 1, new Piece(ChessType.TYP_KNIGHT, ChessColor.CLR_BLACK));
                break;
            case TYP_BISHOP:
                setPiece(4, 4, new Piece(ChessType.TYP_BISHOP, ChessColor.CLR_BLACK));
                setPiece(7, 0, new Piece(ChessType.TYP_BISHOP, ChessColor.CLR_WHITE));
                setPiece(0, 7, new Piece(ChessType.TYP_BISHOP, ChessColor.CLR_BLACK));
                setPiece(6, 3, new Piece(ChessType.TYP_BISHOP, ChessColor.CLR_WHITE));
                setPiece(3, 5, new Piece(ChessType.TYP_BISHOP, ChessColor.CLR_WHITE));
                setPiece(3, 3, new Piece(ChessType.TYP_BISHOP, ChessColor.CLR_WHITE));
                setPiece(1, 3, new Piece(ChessType.TYP_BISHOP, ChessColor.CLR_WHITE));
                setPiece(5, 3, new Piece(ChessType.TYP_BISHOP, ChessColor.CLR_BLACK));
                break;
            case TYP_QUEEN:
                setPiece(4, 4, new Piece(ChessType.TYP_QUEEN, ChessColor.CLR_BLACK));
                setPiece(7, 0, new Piece(ChessType.TYP_QUEEN, ChessColor.CLR_WHITE));
                setPiece(0, 7, new Piece(ChessType.TYP_QUEEN, ChessColor.CLR_BLACK));
                setPiece(6, 3, new Piece(ChessType.TYP_QUEEN, ChessColor.CLR_WHITE));
                setPiece(3, 5, new Piece(ChessType.TYP_QUEEN, ChessColor.CLR_WHITE));
                setPiece(3, 3, new Piece(ChessType.TYP_QUEEN, ChessColor.CLR_WHITE));
                setPiece(1, 3, new Piece(ChessType.TYP_QUEEN, ChessColor.CLR_WHITE));
                setPiece(5, 3, new Piece(ChessType.TYP_QUEEN, ChessColor.CLR_BLACK));
                break;
            case TYP_KING:
                setPiece(0, 0, new Piece(ChessType.TYP_KING, ChessColor.CLR_BLACK));
                setPiece(1, 1, new Piece(ChessType.TYP_KING, ChessColor.CLR_BLACK));
                setPiece(4, 4, new Piece(ChessType.TYP_KING, ChessColor.CLR_BLACK));
                setPiece(7, 0, new Piece(ChessType.TYP_KING, ChessColor.CLR_WHITE));
                setPiece(2, 6, new Piece(ChessType.TYP_KING, ChessColor.CLR_BLACK));
                setPiece(2, 7, new Piece(ChessType.TYP_KING, ChessColor.CLR_WHITE));
                break;
        }
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
                this.game[line][column] = piece;
            }
        }
    }

    private void dispBoard() {
        for (int line = 0; line < 8; line++) {
            for (int column = 0; column < 8; column++) {
                if (this.game[line][column] != null) {
                    System.out.print(this.game[line][column].getPieceType());
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

    public Piece[] toArray() {
        Piece[] pieces = new Piece[64];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                pieces[i * 8 + j] = this.game[i][j];
            }
        }
        return pieces;
    }

    public Piece getPiece(ChessPosition p) {
        if (p.x < 0 || p.x > 7 || p.y < 0 || p.y > 7)
            return null;
        return getPiece(p.y, p.x);
    }

    public Piece getPiece(int line, int column) {
        return this.game[line][column];
    }

    public void setPiece(ChessPosition p, Piece value) {
        setPiece(p.y, p.x, value);
    }

    public void setPiece(int line, int column, Piece value) {
        this.game[line][column] = value;
    }

    public int getNbPieceColor(ChessColor color) {
        List<Piece> validPieces = new ArrayList<>();
        for (Piece piece: toArray()) {
            if (piece != null && piece.getPieceColor() == color) {
                validPieces.add(piece);
            }
        }
        return validPieces.size();
    }

    public void addLostPiece(Piece piece) {
        if (piece != null) {
            if (piece.getPieceColor() == ChessColor.CLR_WHITE) {
                whitePiecesLost.add(piece.getPieceType());
            }
            else {
                blackPiecesLost.add(piece.getPieceType());
            }
        }
    }

    public List<ChessType> getPiecesLost(ChessColor color) {
        if (color == ChessColor.CLR_BLACK) {
            return this.blackPiecesLost;
        }
        return this.whitePiecesLost;
    }
}
