package kata.rover.command;

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
}
