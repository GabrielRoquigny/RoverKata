package kata.rover;

import java.util.function.Consumer;

public interface Direction {
    Direction giveMeDirectionOnLeftRotation(Consumer<Direction> directionConsumer);

    Direction giveMeDirectionOnRightRotation(Consumer<Direction> directionConsumer);
}
