package kata.rover;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.function.Consumer;

public interface Direction {
    default Direction giveMeDirectionOnLeftRotation(CanChangeDirection directionConsumer) {
        throw new NotImplementedException();
    }

    default Direction giveMeDirectionOnRightRotation(CanChangeDirection directionConsumer) {
        throw new NotImplementedException();
    }

    default Direction giveMeVectorForward(Consumer<Vector> vectorConsumer) {
        throw new NotImplementedException();
    }

    default Direction giveMeVectorBackward(Consumer<Vector> vectorConsumer) {
        throw new NotImplementedException();
    }
}
