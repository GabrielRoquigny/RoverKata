package kata.rover.command;

import kata.rover.CanChangePosition;
import kata.rover.Coordinate;
import kata.rover.Direction;
import kata.rover.Vector;

import java.util.function.Consumer;
import java.util.function.Function;

abstract class MoveAbstractWard<T extends MoveAbstractWard> implements MoveOrientation {

    @SuppressWarnings("unchecked")
    @Override
    public T modify(CanChangePosition vectorConsumer, Direction direction, Coordinate coordinate) {
        getFunction(direction).apply(vectorConsumer::move);
        return (T) this;
    }

    /**
     * Get the method to execute on direction with the vector to perform {@link #modify(CanChangePosition, Direction,
     * Coordinate) modify}
     *
     * @param direction direction onto execute the method.
     *
     * @return the method to execute.
     */
    protected abstract Function<Consumer<Vector>, Direction> getFunction(Direction direction);
}
