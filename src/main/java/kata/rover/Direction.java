package kata.rover;

import java.util.function.Consumer;

public interface Direction {
    Direction giveMeDirectionOnLeftRotation(CanChangeDirection directionConsumer);

    Direction giveMeDirectionOnRightRotation(CanChangeDirection directionConsumer);

    Direction giveMeVectorForward(Consumer<Vector> vectorConsumer);
}
