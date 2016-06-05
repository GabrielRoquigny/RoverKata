package kata.rover.command;

import kata.rover.CanChangeDirection;
import kata.rover.CanChangePosition;
import kata.rover.Coordinate;
import kata.rover.Direction;

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
     * @param coordinate        the coordinate onto execute command.
     *
     * @return himself or equivalent.
     */
    @Override
    public <T extends CanChangeDirection & CanChangePosition> Rotate modify(T directionConsumer, Direction direction, Coordinate coordinate) {
        orientation.giveNextDirection(directionConsumer, direction);
        return this;
    }
}
