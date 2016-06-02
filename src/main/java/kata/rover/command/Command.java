package kata.rover.command;

import kata.rover.Coordinate;
import kata.rover.Direction;

import java.util.function.Consumer;

public interface Command {
    /**
     * Give the new direction after executing command.
     *
     * @param directionConsumer consumer to give the new direction.
     * @param direction         the direction onto execute command.
     *
     * @return himself or equivalent.
     */
    default Command modifyDirection(Consumer<Direction> directionConsumer, Direction direction) {
        return this;
    }

    /**
     * Give the new coordinate after executing command.
     *
     * @param coordinateConsumer consumer to give the new coordinate.
     * @param coordinate         the coordinate onto execute command.
     *
     * @return himself or equivalent.
     */
    default Command modifyCoordinate(Consumer<Coordinate> coordinateConsumer, Coordinate coordinate) {
        return this;
    }
}
