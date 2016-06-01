package kata.rover;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.function.Consumer;

public enum CardinalDirection implements Direction {
    NORTH,
    SOUTH,
    EAST,
    WEST;

    @Override
    public CardinalDirection giveMeDirectionOnLeftRotation(Consumer<Direction> directionConsumer) {
        throw new NotImplementedException();
    }

    @Override
    public CardinalDirection giveMeDirectionOnRightRotation(Consumer<Direction> directionConsumer) {
        throw new NotImplementedException();
    }
}
