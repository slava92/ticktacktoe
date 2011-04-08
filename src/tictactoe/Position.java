package tictactoe;

import fj.F;
import fj.F2;
import fj.Ord;
import fj.Ordering;
import fj.data.List;
import fj.data.Option;

public enum Position {

    C('_', 5), E('_', 6), N('_', 2), NE('_', 3), NW('_', 1), S('_', 8), SE('_', 9), SW('_', 7), W('_', 4);
    private final char chr;
    private final int nt;

    Position(char chr, int nt) {
        this.chr = chr;
        this.nt = nt;
    }
    public static final F<Position, Character> toChar = new F<Position, Character>() {

        @Override
        public Character f(Position a) {
            return a.chr;
        }
    };

    public int toInt() {
        return nt;
    }

    public char toChar() {
        return chr;
    }

    public static List<Position> positions() {
        return List.list(C, E, N, NE, NW, S, SE, SW, W);
    }

    public static Option<Position> fromInt(final int n) {
        F2<Option<Position>, Position, Option<Position>> comb = new F2<Option<Position>, Position, Option<Position>>() {

            @Override
            public Option<Position> f(Option<Position> a, Position b) {
                if (b.toInt() == n) {
                    return Option.some(b);
                } else {
                    return a;
                }
            }
        };
        Option<Position> nothing = Option.none();
        return positions().foldLeft(comb, nothing);
    }

    public static Option<Position> fromChar(final char c) {
        F2<Option<Position>, Position, Option<Position>> comb = new F2<Option<Position>, Position, Option<Position>>() {

            @Override
            public Option<Position> f(Option<Position> a, Position b) {
                if (b.toChar() == c) {
                    return Option.some(b);
                } else {
                    return a;
                }
            }
        };
        Option<Position> nothing = Option.none();
        return positions().foldLeft(comb, nothing);
    }
    private static final F<Position, F<Position, Ordering>> pof = new F<Position, F<Position, Ordering>>() {

        @Override
        public F<Position, Ordering> f(final Position a) {
            F<Position, Ordering> pofc = new F<Position, Ordering>() {

                @Override
                public Ordering f(Position b) {
                    return Ord.intOrd.compare(a.toInt(), b.toInt());
                }
            };
            return pofc;
        }
    };
    public static final Ord<Position> positionOrd = Ord.ord(pof);
}
