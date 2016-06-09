package kata.rover;

import java.io.PrintStream;
import java.util.Objects;
import java.util.function.Consumer;

public class Coordinate implements CanDisplay {
    private Integer x, y;

    public Coordinate(Integer x, Integer y) {
        super();
        this.x = x;
        this.y = y;
    }

    public Coordinate giveMeX(Consumer<Integer> consumer) {
        consumer.accept(this.x);
        return this;
    }

    public Coordinate giveMeY(Consumer<Integer> consumer) {
        consumer.accept(this.y);
        return this;
    }

    @Override
    public Coordinate display(PrintStream printStream) {
        printStream.append("x:");
        printStream.print(x);
        printStream.append(";y:");
        printStream.print(y);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Coordinate)) {
            return false;
        }
        Coordinate that = (Coordinate) o;
        return Objects.equals(x, that.x) && Objects.equals(y, that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
