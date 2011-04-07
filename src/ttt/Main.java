package ttt;

import fj.F2;
import fj.data.Option;
import tictactoe.Board;
import tictactoe.Player;
import tictactoe.Position;

public class Main {

    public static void main(String[] args) {
        F2<Option<Player>, Position, Character> ft = new F2<Option<Player>, Position, Character>() {

            @Override
            public Character f(Option<Player> p, Position n) {
                if (p.isNone())
                    return n.toChar();
                else
                    return p.some().toSymbol();
            }
        };
        String brds = new Board.EmptyBoard().toString(ft);
        System.out.printf("Board = \n%s\n", brds);
    }
}
