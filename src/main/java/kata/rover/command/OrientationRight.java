package kata.rover.command;

import kata.rover.CanChangeDirection;
import kata.rover.Direction;

import java.util.function.Function;

public class OrientationRight extends OrientationAbstract<OrientationRight> {
    /**
     * Get the method to execute on direction with the direction to perform {@link #giveNextDirection(
     *CanChangeDirection,
     * Direction) modify}
     *
     * @param direction direction onto execute the method.
     *
     * @return the method to execute.
     */
    @Override
    protected Function<CanChangeDirection, Direction> getFunction(Direction direction) {
        return direction::giveMeDirectionOnRightRotation;
    }
}
