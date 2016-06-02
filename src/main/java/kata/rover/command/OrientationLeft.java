package kata.rover.command;

import kata.rover.Direction;

import java.util.function.Consumer;

public class OrientationLeft implements Orientation {
    @Override
    public OrientationLeft giveNextDirection(Consumer<Direction> directionConsumer, Direction direction) {
        direction.giveMeDirectionOnLeftRotation(directionConsumer);
        return this;
    }
}
