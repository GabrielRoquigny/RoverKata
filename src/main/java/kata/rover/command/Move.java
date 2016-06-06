package kata.rover.command;

import kata.rover.CanChangeDirection;
import kata.rover.CanChangePosition;
import kata.rover.Coordinate;
import kata.rover.Direction;

public class Move implements Command {

    public Move(MoveOrientation moveOrientation) {
        super();
    }

    /**
     * Give the new state after executing command.
     *
     * @param coordinateConsumer consumer to give the new coordinate.
     * @param direction          the direction onto execute command.
     * @param coordinate         the coordinate onto execute command.
     *
     * @return himself or equivalent.
     */
    @Override
    public <T extends CanChangeDirection & CanChangePosition> Move modify(T coordinateConsumer, Direction direction, Coordinate coordinate) {
        return null;
    }
}
