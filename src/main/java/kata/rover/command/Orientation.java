package kata.rover.command;

import kata.rover.Direction;

import java.util.function.Consumer;

public interface Orientation {
    Orientation giveNextDirection(Consumer<Direction> directionConsumer, Direction direction);
}
