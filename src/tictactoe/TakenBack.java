package tictactoe;

import fj.F;
import fj.P1;

public abstract class TakenBack {

    public abstract <X> X fold(P1<X> isEmpty, F<Board, X> isBoard);

    public static TakenBack isEmpty() {
        return new TakenBack() {

            @Override
            public <X> X fold(P1<X> isEmpty, F<Board, X> isBoard) {
                return isEmpty._1();
            }
        };
    }

    public static TakenBack isBoard(final Board b) {
        return new TakenBack() {

            @Override
            public <X> X fold(P1<X> isEmpty, F<Board, X> isBoard) {
                return isBoard.f(b);
            }
        };
    }
}
