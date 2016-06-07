package kata.rover.command;

import kata.rover.CanChangePosition;
import kata.rover.Coordinate;
import kata.rover.Direction;

public class MoveForward implements MoveOrientation {
    @Override
    public MoveForward modify(CanChangePosition vectorConsumer, Direction direction, Coordinate coordinate) {
        direction.giveMeVectorForward(vectorConsumer::move);
        return this;
    }
}
