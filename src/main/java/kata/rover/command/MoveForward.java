package kata.rover.command;

import kata.rover.Direction;
import kata.rover.Vector;

import java.util.function.Consumer;
import java.util.function.Function;

public class MoveForward extends MoveAbstractWard<MoveForward> {
    @Override
    protected Function<Consumer<Vector>, Direction> getFunction(Direction direction) {
        return direction::giveMeVectorForward;
    }
}
