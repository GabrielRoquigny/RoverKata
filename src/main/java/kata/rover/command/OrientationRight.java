package kata.rover.command;

import kata.rover.Direction;

import java.util.function.Consumer;

public class OrientationRight implements Orientation {
    @Override
    public OrientationRight giveNextDirection(Consumer<Direction> directionConsumer, Direction direction) {
        direction.giveMeDirectionOnRightRotation(directionConsumer);
        return this;
    }
}
