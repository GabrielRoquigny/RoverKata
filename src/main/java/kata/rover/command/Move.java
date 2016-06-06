package kata.rover.command;

import kata.rover.CanChangeDirection;
import kata.rover.CanChangePosition;
import kata.rover.Coordinate;
import kata.rover.Direction;

public class Move implements Command {

    private MoveOrientation moveOrientation;

    public Move(MoveOrientation moveOrientation) {
        super();
        this.moveOrientation = moveOrientation;
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
        moveOrientation.modify(coordinateConsumer, direction, coordinate);
        return this;
    }
}
