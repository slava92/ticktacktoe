package tictactoe;

import fj.F;
import fj.P1;
import fj.data.Option;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import tictactoe.Board.FinishedBoard;

public abstract class MoveResult {

    private Option<Board> board;
    private MoveResult(Option<Board> board) {
        this.board = board;
    }

    public abstract <X> X fold(P1<X> positionAlreadyOccupied,
            F<Board, X> keepPlaying,
            F<Board.FinishedBoard, X> gameOver);

    public Option<Board> keepPlaying() {
        return board;
    }

    public <A> A keepPlayingOr(P1<A> els, F<Board, A> fb) {
        if (board.isSome())
            return fb.f(board.some());
        else
            return els._1();
    }

    public MoveResult tryMove(Position p) {
        if (board.isSome())
            if (board.some().isOccupied(p))
                return positionAlreadyOccupied();
//            else
//                board.some().
        return null;
    }

    public static MoveResult positionAlreadyOccupied() {
        Option<Board> board = Option.none();
        return new MoveResult(board) {
            @Override
            public <X> X fold(P1<X> positionAlreadyOccupied, F<Board, X> keepPlaying, F<FinishedBoard, X> gameOver) {
                return positionAlreadyOccupied._1();
            }
        };
    }

    public static MoveResult keepPlaying(final Board b) {
        Option<Board> board = Option.some(b);
        return new MoveResult(board) {

            @Override
            public <X> X fold(P1<X> positionAlreadyOccupied, F<Board, X> keepPlaying, F<FinishedBoard, X> gameOver) {
                return keepPlaying.f(b);
            }
        };
    }

    public static MoveResult gameOver(final Board.FinishedBoard b) {
        Option<Board> board = Option.none();
        return new MoveResult(board) {

            @Override
            public <X> X fold(P1<X> positionAlreadyOccupied, F<Board, X> keepPlaying, F<FinishedBoard, X> gameOver) {
                return gameOver.f(b);
            }
        };
    }
}
