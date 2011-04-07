package tictactoe;

import fj.F;

public enum Player {

    Player1, Player2;
    static F<Player, Character> toSymbol = new F<Player, Character>() {

        @Override
        public Character f(Player a) {
            return a.toSymbol();
        }
    };

    public Player alternate() {
        switch (this) {
            case Player1:
                return Player2;
            case Player2:
                return Player1;
        }
        throw new IllegalStateException("unknown player: "+this);
    }

    public char toSymbol() {
        switch (this) {
            case Player1:
                return 'X';
            case Player2:
                return 'O';
        }
        throw new IllegalStateException("unknown player: "+this);
    }
}
