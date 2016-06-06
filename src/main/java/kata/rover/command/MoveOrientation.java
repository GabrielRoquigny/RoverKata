package kata.rover.command;

import kata.rover.CanChangePosition;
import kata.rover.Coordinate;

public interface MoveOrientation {
    MoveOrientation modify(CanChangePosition coordinateConsumer, Coordinate coordinate);
}
