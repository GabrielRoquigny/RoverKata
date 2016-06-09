package kata.rover;

import java.util.function.Consumer;

public enum CardinalDirection implements Direction {
    NORTH("WEST", "EAST", 0, 1),
    SOUTH("EAST", "WEST", 0, -1),
    EAST("NORTH", "SOUTH", 1, 0),
    WEST("SOUTH", "NORTH", -1, 0);

    private String left;
    private String right;
    private Vector vectorForward;
    private Vector vectorBackward;

    CardinalDirection(String left, String right, Integer x, Integer y) {
        this.left = left;
        this.right = right;
        this.vectorForward = new Vector(x, y);
        this.vectorBackward = new Vector(-x, -y);
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
    public CardinalDirection giveMeVectorForward(Consumer<Vector> vectorConsumer) {
        vectorConsumer.accept(vectorForward);
        return this;
    }

    @Override
    public Direction giveMeVectorBackward(Consumer<Vector> vectorConsumer) {
        vectorConsumer.accept(vectorBackward);
        return this;
    }


}
