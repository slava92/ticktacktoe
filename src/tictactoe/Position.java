package tictactoe;

import fj.F;
import fj.F2;
import fj.data.List;
import fj.data.Option;

public enum Position {

    C('a', 1), E('b', 2), N('c', 3), NE('d', 4), NW('e', 5), S('f', 6), SE('g', 7), SW('h', 8), W('i', 9);
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
}
