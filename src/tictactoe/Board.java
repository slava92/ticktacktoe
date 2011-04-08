package tictactoe;

import fj.F;
import fj.F2;
import fj.Ord;
import fj.data.List;
import fj.data.Option;
import fj.data.TreeMap;
import fj.function.Booleans;

public final class Board extends BoardLike {

    private final Player nextMove;
    private final TreeMap<Integer, Player> posMap;

    private Board(Player nextMove, TreeMap<Integer, Player> posMap) {
        this.nextMove = nextMove;
        this.posMap = posMap;
    }

    public MoveResult moveTo(Position p) {
        if (posMap.contains(p.toInt())) {
            return MoveResult.positionAlreadyOccupied();
        } else {
            Board nb = new Board(whoseNotTurn(), posMap.set(p.toInt(), whoseTurn()));
            if (nb.isGameOver()) {
                return MoveResult.gameOver(new FinishedBoard(nb));
            } else {
                return MoveResult.keepPlaying(nb);
            }
        }
    }

    public TakenBack takeBack() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Player whoseTurn() {
        return nextMove;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public List<Position> occupiedPositions() {
        F<Integer, Position> i2p = new F<Integer, Position>() {

            @Override
            public Position f(Integer a) {
                return Position.fromInt(a.intValue()).some();
            }
        };
        return posMap.keys().map(i2p);
    }

    @Override
    public int nmoves() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Option<Player> playerAt(Position p) {
        return posMap.get(p.toInt());
    }
    @SuppressWarnings(value = "unchecked")
    private static final List<List<Position>> winners = List.list(
            List.list(Position.NW, Position.N, Position.NE),
            List.list(Position.W, Position.C, Position.E),
            List.list(Position.SW, Position.S, Position.SE),
            List.list(Position.NW, Position.W, Position.SW),
            List.list(Position.N, Position.C, Position.S),
            List.list(Position.NE, Position.E, Position.SE),
            List.list(Position.NW, Position.C, Position.SE),
            List.list(Position.NE, Position.C, Position.SE));

    private boolean isDraw() {
        return this.occupiedPositions().length() == Position.values().length;
    }

    private boolean isGameOver() {
        if (isDraw()) {
            return true;
        }
        final Board self = this;
        F<List<Position>, Boolean> fLists = new F<List<Position>, Boolean>() {

            @Override
            public Boolean f(List<Position> diag) {
                F<Position, Option<Player>> pos2plr = new F<Position, Option<Player>>() {

                    @Override
                    public Option<Player> f(Position a) {
                        return self.playerAt(a);
                    }
                };
                F2<Option<Player>, Option<Player>, Option<Player>> allSame =
                        new F2<Option<Player>, Option<Player>, Option<Player>>() {

                            @Override
                            public Option<Player> f(Option<Player> a, Option<Player> b) {
                                if (a.isNone() || b.isNone() || a.some().toSymbol() == b.some().toSymbol()) {
                                    return Option.none();
                                } else {
                                    return a;
                                }
                            }
                        };
                return diag.map(pos2plr).foldLeft1(allSame).isSome();
            }
        };
        return Booleans.or(winners.map(fLists));
    }

    public static final class EmptyBoard extends BoardLike {

        public Board moveTo(Position p) {
            TreeMap<Integer, Player> ppm = TreeMap.empty(Ord.intOrd);
            return new Board(whoseTurn().alternate(), ppm.set(p.toInt(), whoseTurn()));
        }

        public static Board.EmptyBoard empty() {
            return new Board.EmptyBoard();
        }

        @Override
        public Player whoseTurn() {
            return Player.Player1;
        }

        @Override
        public boolean isEmpty() {
            return true;
        }

        @Override
        public List<Position> occupiedPositions() {
            List<Position> op = List.nil();
            return op;
        }

        @Override
        public int nmoves() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public Option<Player> playerAt(Position p) {
            return Option.none();
        }
    }

    public static final class FinishedBoard extends BoardLike {

        private final Board board;

        private FinishedBoard(Board board) {
            this.board = board;
        }

        @Override
        public Player whoseTurn() {
            return board.whoseTurn();
        }

        @Override
        public boolean isEmpty() {
            return board.isEmpty();
        }

        @Override
        public List<Position> occupiedPositions() {
            return board.occupiedPositions();
        }

        @Override
        public int nmoves() {
            return board.nmoves();
        }

        @Override
        public Option<Player> playerAt(Position p) {
            return board.playerAt(p);
        }

        public Board takeBack() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public GameResult result() {
            return GameResult.win(board.whoseNotTurn());
        }
    }
}
