package kata.rover.command;

import kata.rover.Coordinate;
import kata.rover.Direction;

import java.util.function.Consumer;

public class Rotate implements Command {

    private final Orientation orientation;

    /**
     * Constructor.
     *
     * @param orientation direction to rotate
     */
    public Rotate(Orientation orientation) {
        super();
        this.orientation = orientation;
    }

    /**
     * Give the new direction after executing command.
     *
     * @param directionConsumer consumer to give the new direction.
     * @param direction         the direction onto execute command.
     *
     * @return himself or equivalent.
     */
    @Override
    public Rotate modifyDirection(Consumer<Direction> directionConsumer, Direction direction) {
        orientation.giveNextDirection(directionConsumer, direction);
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
    @Override
    public Rotate modifyCoordinate(Consumer<Coordinate> coordinateConsumer, Coordinate coordinate) {
        return this;
    }
}
