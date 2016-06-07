package kata.rover;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Objects;
import java.util.function.Consumer;

public class Vector {
    private final Integer x, y;

    public Vector(Integer x, Integer y) {
        super();
        this.x = x;
        this.y = y;
    }

    public Vector applyOnCoordinate(Consumer<Coordinate> coordinateConsumer, Coordinate coordinate) {
        throw new NotImplementedException();
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
