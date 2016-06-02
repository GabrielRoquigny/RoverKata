package kata.rover.command;

import kata.rover.Direction;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.function.Consumer;

public class OrientationLeft implements Orientation {
    @Override
    public OrientationLeft giveNextDirection(Consumer<Direction> directionConsumer, Direction direction) {
        throw new NotImplementedException();
    }
}
