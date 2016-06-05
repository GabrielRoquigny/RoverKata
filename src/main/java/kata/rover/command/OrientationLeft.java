package kata.rover.command;

import kata.rover.CanChangeDirection;
import kata.rover.Direction;

public class OrientationLeft implements Orientation {
    @Override
    public OrientationLeft giveNextDirection(CanChangeDirection directionConsumer, Direction direction) {
        direction.giveMeDirectionOnLeftRotation(directionConsumer);
        return this;
    }
}
