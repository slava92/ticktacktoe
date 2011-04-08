package tictactoe;

import fj.data.List;
import fj.data.Option;

public class Board extends BoardLike {

    public MoveResult moveTo(Position p) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Player whoseTurn() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Position> occupiedPositions() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int nmoves() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Option<Player> playerAt(Position p) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static final class EmptyBoard extends BoardLike {

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
            throw new UnsupportedOperationException("Not supported yet.");
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

        @Override
        public Player whoseTurn() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public boolean isEmpty() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public List<Position> occupiedPositions() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public int nmoves() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public Option<Player> playerAt(Position p) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
