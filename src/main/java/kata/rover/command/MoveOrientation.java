package kata.rover.command;

import kata.rover.CanChangePosition;
import kata.rover.Coordinate;
import kata.rover.Direction;

public interface MoveOrientation {
    MoveOrientation modify(CanChangePosition coordinateConsumer, Direction direction, Coordinate coordinate);
}
