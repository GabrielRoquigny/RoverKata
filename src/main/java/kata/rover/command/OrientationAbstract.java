package kata.rover.command;

import kata.rover.CanChangeDirection;
import kata.rover.Direction;

import java.util.function.Function;

abstract class OrientationAbstract<T extends OrientationAbstract> implements Orientation {

    @SuppressWarnings("unchecked")
    @Override
    public T giveNextDirection(CanChangeDirection directionConsumer, Direction direction) {
        getFunction(direction).apply(directionConsumer);
        return (T) this;
    }


    /**
     * Get the method to execute on direction with the direction to perform {@link #giveNextDirection(CanChangeDirection,
     * Direction) modify}
     *
     * @param direction direction onto execute the method.
     *
     * @return the method to execute.
     */
    protected abstract Function<CanChangeDirection, Direction> getFunction(Direction direction);

}
