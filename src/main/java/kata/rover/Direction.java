package kata.rover;

public interface Direction {
    Direction giveMeDirectionOnLeftRotation(CanChangeDirection directionConsumer);

    Direction giveMeDirectionOnRightRotation(CanChangeDirection directionConsumer);
}
