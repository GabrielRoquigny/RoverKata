package kata.rover;

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
    public CardinalDirection giveMeDirectionOnLeftRotation(Consumer<Direction> directionConsumer) {
        directionConsumer.accept(valueOf(left));
        return this;
    }

    @Override
    public CardinalDirection giveMeDirectionOnRightRotation(Consumer<Direction> directionConsumer) {
        directionConsumer.accept(valueOf(right));
        return this;
    }
}
