package kata.rover;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.function.Consumer;

public enum CardinalDirection implements Direction {
    NORTH("WEST", "EAST"),
    SOUTH("EAST", "WEST"),
    EAST("NORTH", "SOUTH"),
    WEST("SOUTH", "NORTH");

    private String left;
    private String right;

    CardinalDirection(String left, String right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public CardinalDirection giveMeDirectionOnLeftRotation(CanChangeDirection directionConsumer) {
        directionConsumer.rotateTo(valueOf(left));
        return this;
    }

    @Override
    public CardinalDirection giveMeDirectionOnRightRotation(CanChangeDirection directionConsumer) {
        directionConsumer.rotateTo(valueOf(right));
        return this;
    }

    @Override
    public Direction giveMeVectorForward(Consumer<Vector> vectorConsumer) {
        throw new NotImplementedException();
    }


}
