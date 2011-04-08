package tictactoe;

import fj.F;
import fj.F2;
import fj.P1;
import fj.Unit;
import fj.data.List;
import fj.data.Option;

public abstract class BoardLike {

    public abstract Player whoseTurn();

    public final Player whoseNotTurn() {
        return whoseTurn().alternate();
    }

    public abstract boolean isEmpty();

    public abstract List<Position> occupiedPositions();

    public abstract int nmoves();

    public abstract Option<Player> playerAt(Position p);

    public final Player playerAtOr(Position p, P1<Player> or) {
        if (playerAt(p).isNone()) {
            return or._1();
        } else {
            return playerAt(p).some();
        }
    }

    public final boolean isOccupied(Position p) {
        return !playerAt(p).isNone();
    }

    public final boolean isNotOccupied(Position p) {
        return playerAt(p).isNone();
    }

    public final String toString(final F2<Option<Player>, Position, Character> af) {
        final StringBuilder sb = new StringBuilder();
        Position.positions().sort(Position.positionOrd).foreach(new F<Position, Unit>() {

            @Override
            public Unit f(Position a) {
                Option<Player> pl = playerAt(a);
                sb.append(af.f(pl, a)).append(' ');
                if (a.toInt() % 3 == 0)
                    sb.append("\n");
                return Unit.unit();
            }
        });
        return sb.toString();
    }
}
