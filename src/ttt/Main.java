package ttt;

import fj.F;
import fj.F2;
import fj.data.Option;
import tictactoe.Board;
import tictactoe.MoveResult;
import tictactoe.Player;
import tictactoe.Position;

public class Main {

    public static void main(String[] args) {
        F2<Option<Player>, Position, Character> ft = new F2<Option<Player>, Position, Character>() {

            @Override
            public Character f(Option<Player> p, Position n) {
                if (p.isNone()) {
                    return n.toChar();
                } else {
                    return p.some().toSymbol();
                }
            }
        };
        Board.EmptyBoard eb = new Board.EmptyBoard();
        String brds = eb.toString(ft);
        System.out.printf("EmptyBoard = \n%s\n", brds);

        Board br1 = eb.moveTo(Position.C);
        System.out.printf("Board1 = \n%s\n", br1.toString(ft));
        MoveResult mr2 = br1.moveTo(Position.NW);
        F<Board, Board> kpf1 = new F<Board, Board>() {

            @Override
            public Board f(Board a) {
                return a;
            }
        };
        Board br2 = mr2.fold(null, kpf1, null);
        System.out.printf("Board2 = \n%s\n", br2.toString(ft));
    }
}
