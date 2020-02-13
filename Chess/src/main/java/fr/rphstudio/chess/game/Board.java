package fr.rphstudio.chess.game;

import fr.rphstudio.chess.interf.IChess.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Board Class
 * @see #states : a public static List<Board>
 * @see #game : a private Piece[][]
 * @see #whitePiecesLost : a private List<ChessType>
 * @see #blackPiecesLost : a private List<ChessType>
 * @see #lastPieceMoved : a private Piece
 * @see #timer : a private ChessTimer
 */
public class Board {

    private static List<Board> states = new ArrayList<Board>();

    private Piece[][] game;

    private List<ChessType> whitePiecesLost;
    private List<ChessType> blackPiecesLost;
    private Piece lastPieceMoved;

    private ChessTimer timer;

    /**
     * Constructor
     */
    public Board() {
        whitePiecesLost = new ArrayList<>();
        blackPiecesLost = new ArrayList<>();
        lastPieceMoved = null;
        this.timer = new ChessTimer();

        this.game = new Piece[8][];
        for (int line = 0; line < 8; line++) {
            this.game[line] = new Piece[8];
        }
        createBoardGame();
    }

    /**
     * Constructor
     * @param mode : a short to create a Board with specific options
     */
    public Board(short mode) {
        whitePiecesLost = new ArrayList<>();
        blackPiecesLost = new ArrayList<>();
        lastPieceMoved = null;
        this.timer = new ChessTimer();

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
            case 0:
                setPiece(0, 0, new Piece(ChessType.TYP_KING, ChessColor.CLR_BLACK));
                setPiece(7, 7, new Piece(ChessType.TYP_KING, ChessColor.CLR_WHITE));

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
            case 1:
                setPiece(0, 1, new Piece(ChessType.TYP_KING, ChessColor.CLR_BLACK));
                setPiece(7, 6, new Piece(ChessType.TYP_KING, ChessColor.CLR_WHITE));

                setPiece(4, 4, new Piece(ChessType.TYP_ROOK, ChessColor.CLR_BLACK));
                setPiece(7, 0, new Piece(ChessType.TYP_ROOK, ChessColor.CLR_WHITE));
                setPiece(0, 7, new Piece(ChessType.TYP_ROOK, ChessColor.CLR_BLACK));
                setPiece(6, 2, new Piece(ChessType.TYP_ROOK, ChessColor.CLR_BLACK));
                setPiece(6, 3, new Piece(ChessType.TYP_ROOK, ChessColor.CLR_WHITE));
                setPiece(3, 5, new Piece(ChessType.TYP_ROOK, ChessColor.CLR_WHITE));
                setPiece(3, 3, new Piece(ChessType.TYP_ROOK, ChessColor.CLR_WHITE));
                setPiece(5, 3, new Piece(ChessType.TYP_ROOK, ChessColor.CLR_BLACK));
                break;
            case 2:
                setPiece(0, 1, new Piece(ChessType.TYP_KING, ChessColor.CLR_BLACK));
                setPiece(7, 6, new Piece(ChessType.TYP_KING, ChessColor.CLR_WHITE));

                setPiece(0, 0, new Piece(ChessType.TYP_KNIGHT, ChessColor.CLR_BLACK));
                setPiece(0, 7, new Piece(ChessType.TYP_KNIGHT, ChessColor.CLR_WHITE));
                setPiece(2, 6, new Piece(ChessType.TYP_KNIGHT, ChessColor.CLR_WHITE));
                setPiece(7, 0, new Piece(ChessType.TYP_KNIGHT, ChessColor.CLR_WHITE));
                setPiece(7, 7, new Piece(ChessType.TYP_KNIGHT, ChessColor.CLR_BLACK));
                setPiece(4, 4, new Piece(ChessType.TYP_KNIGHT, ChessColor.CLR_BLACK));
                setPiece(5, 1, new Piece(ChessType.TYP_KNIGHT, ChessColor.CLR_BLACK));
                break;
            case 3:
                setPiece(0, 1, new Piece(ChessType.TYP_KING, ChessColor.CLR_BLACK));
                setPiece(7, 6, new Piece(ChessType.TYP_KING, ChessColor.CLR_WHITE));

                setPiece(4, 4, new Piece(ChessType.TYP_BISHOP, ChessColor.CLR_BLACK));
                setPiece(7, 0, new Piece(ChessType.TYP_BISHOP, ChessColor.CLR_WHITE));
                setPiece(0, 7, new Piece(ChessType.TYP_BISHOP, ChessColor.CLR_BLACK));
                setPiece(6, 3, new Piece(ChessType.TYP_BISHOP, ChessColor.CLR_WHITE));
                setPiece(3, 5, new Piece(ChessType.TYP_BISHOP, ChessColor.CLR_WHITE));
                setPiece(3, 3, new Piece(ChessType.TYP_BISHOP, ChessColor.CLR_WHITE));
                setPiece(1, 3, new Piece(ChessType.TYP_BISHOP, ChessColor.CLR_WHITE));
                setPiece(5, 3, new Piece(ChessType.TYP_BISHOP, ChessColor.CLR_BLACK));
                break;
            case 4:
                setPiece(1, 0, new Piece(ChessType.TYP_KING, ChessColor.CLR_BLACK));
                setPiece(6, 7, new Piece(ChessType.TYP_KING, ChessColor.CLR_WHITE));

                setPiece(4, 4, new Piece(ChessType.TYP_QUEEN, ChessColor.CLR_BLACK));
                setPiece(7, 0, new Piece(ChessType.TYP_QUEEN, ChessColor.CLR_WHITE));
                setPiece(0, 7, new Piece(ChessType.TYP_QUEEN, ChessColor.CLR_BLACK));
                setPiece(6, 3, new Piece(ChessType.TYP_QUEEN, ChessColor.CLR_WHITE));
                setPiece(3, 5, new Piece(ChessType.TYP_QUEEN, ChessColor.CLR_WHITE));
                setPiece(3, 3, new Piece(ChessType.TYP_QUEEN, ChessColor.CLR_WHITE));
                setPiece(1, 3, new Piece(ChessType.TYP_QUEEN, ChessColor.CLR_WHITE));
                setPiece(5, 3, new Piece(ChessType.TYP_QUEEN, ChessColor.CLR_BLACK));
                break;
            case 5:
                setPiece(0, 0, new Piece(ChessType.TYP_KING, ChessColor.CLR_BLACK));
                setPiece(1, 1, new Piece(ChessType.TYP_KING, ChessColor.CLR_BLACK));
                setPiece(4, 4, new Piece(ChessType.TYP_KING, ChessColor.CLR_BLACK));
                setPiece(7, 0, new Piece(ChessType.TYP_KING, ChessColor.CLR_WHITE));
                setPiece(2, 6, new Piece(ChessType.TYP_KING, ChessColor.CLR_BLACK));
                setPiece(2, 7, new Piece(ChessType.TYP_KING, ChessColor.CLR_WHITE));
                break;
            case 6:
                setPiece(0, 4, new Piece(ChessType.TYP_KING, ChessColor.CLR_BLACK));
                setPiece(0, 7, new Piece(ChessType.TYP_ROOK, ChessColor.CLR_BLACK));
                setPiece(0, 2, new Piece(ChessType.TYP_ROOK, ChessColor.CLR_BLACK));
                setPiece(7, 4, new Piece(ChessType.TYP_KING, ChessColor.CLR_WHITE));
                setPiece(7, 7, new Piece(ChessType.TYP_ROOK, ChessColor.CLR_WHITE));
                setPiece(7, 0, new Piece(ChessType.TYP_ROOK, ChessColor.CLR_WHITE));
                setPiece(6, 0, new Piece(ChessType.TYP_PAWN, ChessColor.CLR_WHITE));
                setPiece(6, 1, new Piece(ChessType.TYP_PAWN, ChessColor.CLR_WHITE));
                setPiece(6, 2, new Piece(ChessType.TYP_PAWN, ChessColor.CLR_WHITE));
                setPiece(6, 3, new Piece(ChessType.TYP_PAWN, ChessColor.CLR_WHITE));
                setPiece(6, 4, new Piece(ChessType.TYP_PAWN, ChessColor.CLR_WHITE));
                break;
            case 7:
                setPiece(3, 4, new Piece(ChessType.TYP_KING, ChessColor.CLR_BLACK));
                setPiece(7, 4, new Piece(ChessType.TYP_KING, ChessColor.CLR_WHITE));
                setPiece(7, 0, new Piece(ChessType.TYP_ROOK, ChessColor.CLR_WHITE));
                setPiece(7, 7, new Piece(ChessType.TYP_ROOK, ChessColor.CLR_WHITE));
                break;
            case 8:
                setPiece(3, 4, new Piece(ChessType.TYP_KING, ChessColor.CLR_BLACK));
                setPiece(7, 4, new Piece(ChessType.TYP_KING, ChessColor.CLR_WHITE));
                setPiece(7, 0, new Piece(ChessType.TYP_ROOK, ChessColor.CLR_WHITE));
                break;
            case 9:
                setPiece(3, 4, new Piece(ChessType.TYP_KING, ChessColor.CLR_BLACK));
                setPiece(7, 4, new Piece(ChessType.TYP_KING, ChessColor.CLR_WHITE));
                setPiece(7, 3, new Piece(ChessType.TYP_QUEEN, ChessColor.CLR_WHITE));
                break;
        }
    }

    /**
     * Create the default board, called by constructor()
     */
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

    /**
     * Concat all game's (Piece[][]) array into a array
     * @return a Piece[]
     */
    public Piece[] toArray() {
        Piece[] pieces = new Piece[64];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                pieces[i * 8 + j] = this.game[i][j];
            }
        }
        return pieces;
    }

    /**
     * Get a Piece from a position
     * @param p the position (x, y)
     * @return a Piece if it exists or null
     */
    public Piece getPiece(ChessPosition p) {
        if (p.x < 0 || p.x > 7 || p.y < 0 || p.y > 7)
            return null;
        return getPiece(p.y, p.x);
    }

    /**
     * Get a Piece with two integers
     * @param line The line (0 ; 7)
     * @param column The column (0 ; 7)
     * @return a Piece
     */
    public Piece getPiece(int line, int column) {
        return this.game[line][column];
    }

    /**
     * Set a Piece
     * @param p The Piece's position
     * @param value a Piece
     */
    public void setPiece(ChessPosition p, Piece value) {
        setPiece(p.y, p.x, value);
    }

    /**
     * Set a Piece
     * @param line The line (0 ; 7)
     * @param column The column (0 ; 7)
     * @param value A Piece
     */
    public void setPiece(int line, int column, Piece value) {
        this.game[line][column] = value;
    }

    /**
     * Get all pieces of a color
     * @param color The color
     * @return The list List<Piece>
     */
    public List<Piece> getPiecesColor(ChessColor color) {
        List<Piece> pieces = new ArrayList<>();
        for (Piece piece : this.toArray()) {
            if (piece != null && piece.getPieceColor() == color) {
                pieces.add(piece);
            }
        }
        return pieces;
    }

    /**
     * Give the number of pieces of a color
     * @param color The color
     * @return The number
     */
    public int getNbPieceColor(ChessColor color) {
        return getPiecesColor(color).size();
    }

    /**
     * Add a part to the list of eaten pieces
     * @param piece The Piece
     */
    public void addLostPiece(Piece piece) {
        if (piece != null) {
            if (piece.getPieceColor() == ChessColor.CLR_WHITE) {
                whitePiecesLost.add(piece.getPieceType());
            } else {
                blackPiecesLost.add(piece.getPieceType());
            }
        }
    }

    /**
     * Get the eaten pieces
     * @param color The color of the pieces
     * @return A list
     */
    public List<ChessType> getPiecesLost(ChessColor color) {
        if (color == ChessColor.CLR_BLACK) {
            return this.blackPiecesLost;
        }
        return this.whitePiecesLost;
    }

    /**
     * Move a piece to a position
     * @param p0 The original position
     * @param p1 The targeted position
     */
    public void movePiece(ChessPosition p0, ChessPosition p1) {
        Piece piece = getPiece(p0);
        Piece target = getPiece(p1);

        // Castling
        if (piece.getPieceType() == ChessType.TYP_KING && target != null && piece.getPieceColor() == target.getPieceColor()) {
            int x1;
            int x2;
            if (p1.x == 0) {
                x1 = 2;
                x2 = 3;
            } else {
                x1 = 6;
                x2 = 5;
            }
            setPiece(new ChessPosition(x1, p1.y), piece);
            setPiece(new ChessPosition(x2, p1.y), target);
            setPiece(p0, null);
            setPiece(p1, null);
        }
        // Normal
        else {
            addLostPiece(target);
            setPiece(p1, piece);
            setPiece(p0, null);
            piece.increaseNbTurn();

            if (piece.getPieceType() == ChessType.TYP_PAWN && (p1.y == 7 || p1.y == 0)) {
                setPiece(p1, new Piece(ChessType.TYP_QUEEN, piece.getPieceColor()));
            }
        }
        setLastPieceMoved(piece);
        /*System.out.println("setLast : \npiece : " + piece + "\nlast  : " + this.lastPieceMoved);
        System.out.println("pos : \npiece : " + getPositionOf(piece) + " : " + getPositionOf(piece).x + " | " + getPositionOf(piece).y);
        System.out.println("last  : " + getPositionOf(this.lastPieceMoved) + " : " + getPositionOf(this.lastPieceMoved).x + " | " + getPositionOf(this.lastPieceMoved).y);
        System.out.println("set Done");*/
    }

    /**
     * Get the position of a piece
     * @param piece The piece
     * @return Its position
     */
    public ChessPosition getPositionOf(Piece piece) {
        for (int line = 0; line < 8; line++) {
            for (int column = 0; column < 8; column++) {
                Piece pieceTested = getPiece(line, column);
                if (pieceTested == piece) {
                    return new ChessPosition(column, line);
                }
            }
        }
        return null;
    }

    /**
     * Get the king of a color
     * @param color The king's color
     * @return A Piece, specifically a King
     */
    public Piece getKing(ChessColor color) {
        for (int line = 0; line < 8; line++) {
            for (int column = 0; column < 8; column++) {
                Piece pieceTested = getPiece(line, column);
                if (pieceTested != null && pieceTested.getPieceType() == ChessType.TYP_KING && pieceTested.getPieceColor() == color) {
                    return pieceTested;
                }
            }
        }
        return null;
    }

    /**
     * Get the state of a king of a color
     * @param color The king's color
     * @return The state
     */
    public ChessKingState getKingState(ChessColor color) {
        ChessPosition positionKing = getPositionOf(getKing(color));
        ChessColor colorEnemy = color == ChessColor.CLR_BLACK ? ChessColor.CLR_WHITE : ChessColor.CLR_BLACK;
        for (Piece piece : getPiecesColor(colorEnemy)) {
            List<ChessPosition> moves = piece.getPiecesMoves(getPositionOf(piece), this);
            for (ChessPosition move : moves) {
                if (move.x == positionKing.x && move.y == positionKing.y) {
                    return ChessKingState.KING_THREATEN;
                }
            }
        }
        return ChessKingState.KING_SAFE;
    }

    /**
     * Add a state of the board
     * @param newState The board to save
     */
    public void addState(Board newState) {
        states.add(newState);
    }

    /**
     * Return the last state of the board
     * @return The last Board
     */
    public Board returnLastState() {
        if (states.size() > 0) {
            int lastIndex = states.size() - 1;
            Board boardTmp = states.get(lastIndex);
            states.remove(lastIndex);
            return boardTmp;
        }
        return null;
    }

    /**
     * Returns the last piece that moved
     * @return A Piece
     */
    public Piece getLastPieceMoved() {
        return this.lastPieceMoved;
    }

    /**
     * Set the last piece that moved
     * @param value The Piece
     */
    public void setLastPieceMoved(Piece value) {
        this.lastPieceMoved = value;
    }

    /**
     * Get the player's time
     * @param color The player's color
     * @param isPlaying The player's state
     * @return The time in milliseconds
     */
    public long getTime(ChessColor color, boolean isPlaying) {
        return this.timer.getTime(color, isPlaying);
    }

    /**
     * Get a copy of the current Board
     * @return A Board
     */
    public Board clone() {
        Board boardTmp = new Board();
        Piece[][] gameTmp = new Piece[8][8];
        for (int line = 0; line < 8; line++) {
            for (int column = 0; column < 8; column++) {
                Piece pieceTmp = this.getPiece(line, column);
                if (pieceTmp != null) {
                    gameTmp[line][column] = pieceTmp.clone();
                }
            }
        }
        boardTmp.game = gameTmp;
        boardTmp.whitePiecesLost = new ArrayList<>(whitePiecesLost);
        boardTmp.blackPiecesLost = new ArrayList<>(blackPiecesLost);
        boardTmp.timer = this.timer.clone();
        return boardTmp;
    }
}
