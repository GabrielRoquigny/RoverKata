package kata.rover;

public interface CanChangePosition {
    CanChangePosition move(Vector vector);

    CanChangePosition position(Coordinate coordinate);
}
