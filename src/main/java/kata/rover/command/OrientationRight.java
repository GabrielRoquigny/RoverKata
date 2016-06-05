package kata.rover.command;

import kata.rover.CanChangeDirection;
import kata.rover.Direction;

public class OrientationRight implements Orientation {
    @Override
    public OrientationRight giveNextDirection(CanChangeDirection directionConsumer, Direction direction) {
        direction.giveMeDirectionOnRightRotation(directionConsumer);
        return this;
    }
}
