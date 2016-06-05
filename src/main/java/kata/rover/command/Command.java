package kata.rover.command;

import kata.rover.CanChangeDirection;
import kata.rover.CanChangePosition;
import kata.rover.Coordinate;
import kata.rover.Direction;

public interface Command {
    /**
     * Give the new state after executing command.
     *
     * @param stateConsumer consumer to give the new state.
     * @param direction     the direction onto execute command.
     * @param coordinate    the coordinate onto execute command.
     *
     * @return himself or equivalent.
     */
    default <T extends CanChangeDirection & CanChangePosition> Command modify(T stateConsumer, Direction direction, Coordinate coordinate) {
        return this;
    }
}
