package kata.rover.command;

import kata.rover.CanChangeDirection;
import kata.rover.Direction;

public interface Orientation {
    Orientation giveNextDirection(CanChangeDirection directionConsumer, Direction direction);
}
