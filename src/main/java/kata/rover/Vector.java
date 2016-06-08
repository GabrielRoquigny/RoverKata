package kata.rover;

import java.util.Objects;

public class Vector {
    private final Integer x, y;
    private Integer cx, cy;

    public Vector(Integer x, Integer y) {
        super();
        this.x = x;
        this.y = y;
    }

    public Vector applyOnCoordinate(CanChangePosition positionConsumer, Coordinate coordinate) {
        Vector clone = new Vector(x, y);
        coordinate.giveMeX(x -> clone.cx = x);
        coordinate.giveMeY(y -> clone.cy = y);
        positionConsumer.position(new Coordinate(clone.cx + x, clone.cy + y));
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vector vector = (Vector) o;
        return Objects.equals(x, vector.x) && Objects.equals(y, vector.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
