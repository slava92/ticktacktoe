package tictactoe;

import fj.F;
import fj.P;
import fj.P1;
import fj.data.Option;
import tictactoe.Board.FinishedBoard;

public abstract class MoveResult {

    public abstract <X> X fold(P1<X> positionAlreadyOccupied,
            F<Board, X> keepPlaying,
            F<Board.FinishedBoard, X> gameOver);

    public Option<Board> keepPlaying() {
        final Option<Board> eb = Option.none();
        F<Board, Option<Board>> kpf = new F<Board, Option<Board>>() {

            @Override
            public Option<Board> f(Board a) {
                return Option.some(a);
            }
        };
        F<Board.FinishedBoard, Option<Board>> gof = new F<Board.FinishedBoard, Option<Board>>() {

            @Override
            public Option<Board> f(FinishedBoard a) {
                return eb;
            }
        };
        return fold(P.p(eb), kpf, gof);
    }

    public <A> A keepPlayingOr(final P1<A> els, F<Board, A> fb) {
        F<Board.FinishedBoard, A> fgo = new F<Board.FinishedBoard, A>() {

            @Override
            public A f(FinishedBoard a) {
                return els._1();
            }
        };
        return fold(els, fb, fgo);
    }

    public MoveResult tryMove(final Position p) {
        P1<MoveResult> pao = P.p(positionAlreadyOccupied());
        F<Board.FinishedBoard, MoveResult> go = new F<Board.FinishedBoard, MoveResult>() {

            @Override
            public MoveResult f(FinishedBoard a) {
                return gameOver(a);
            }
        };
        F<Board, MoveResult> kp = new F<Board, MoveResult>() {

            @Override
            public MoveResult f(Board b) {
                if (b.isOccupied(p)) {
                    return positionAlreadyOccupied();
                } else {
                    return b.moveTo(p);
                }
            }
        };
        return fold(pao, kp, go);
    }

    public static MoveResult positionAlreadyOccupied() {
        return new MoveResult() {

            @Override
            public <X> X fold(P1<X> positionAlreadyOccupied, F<Board, X> keepPlaying, F<FinishedBoard, X> gameOver) {
                return positionAlreadyOccupied._1();
            }
        };
    }

    public static MoveResult keepPlaying(final Board b) {
        return new MoveResult() {

            @Override
            public <X> X fold(P1<X> positionAlreadyOccupied, F<Board, X> keepPlaying, F<FinishedBoard, X> gameOver) {
                return keepPlaying.f(b);
            }
        };
    }

    public static MoveResult gameOver(final Board.FinishedBoard b) {
        return new MoveResult() {

            @Override
            public <X> X fold(P1<X> positionAlreadyOccupied, F<Board, X> keepPlaying, F<FinishedBoard, X> gameOver) {
                return gameOver.f(b);
            }
        };
    }
}
