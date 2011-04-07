package tictactoe;

import fj.data.Option;

public enum GameResult {

    Draw, Player1Wins, Player2Wins;

    public boolean isWin() {
        return (Draw != this);
    }

    public boolean isDraw() {
        return (Draw == this);
    }

    public static GameResult win(Player p) {
        switch (p) {
            case Player1:
                return Player1Wins;
            case Player2:
                return Player2Wins;
        }
        throw new IllegalStateException("unknown player: "+p);
    }

    public Option<Player> winner() {
        switch (this) {
            case Player1Wins:
                return Option.some(Player.Player1);
            case Player2Wins:
                return Option.some(Player.Player2);
        }
        return Option.none();
    }

    public <X> X strictFold(X player1Wins, X player2Wins, X draw) {
        switch (this) {
            case Player1Wins:
                return player1Wins;
            case Player2Wins:
                return player2Wins;
            case Draw:
                return draw;
        }
        throw new IllegalStateException("unknown GameResult: "+this.toString());
    }
}
