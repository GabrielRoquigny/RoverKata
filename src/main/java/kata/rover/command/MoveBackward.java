package kata.rover.command;

import kata.rover.CanChangePosition;
import kata.rover.Coordinate;
import kata.rover.Direction;

public class MoveBackward implements MoveOrientation {
    @Override
    public MoveBackward modify(CanChangePosition vectorConsumer, Direction direction, Coordinate coordinate) {
        direction.giveMeVectorBackward(vectorConsumer::move);
        return this;
    }
}
