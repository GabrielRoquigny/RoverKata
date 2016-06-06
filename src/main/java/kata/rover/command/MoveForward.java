package kata.rover.command;

import kata.rover.CanChangePosition;
import kata.rover.Coordinate;
import kata.rover.Direction;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class MoveForward implements MoveOrientation {
    @Override
    public MoveForward modify(CanChangePosition coordinateConsumer, Direction direction, Coordinate coordinate) {
        throw new NotImplementedException();
    }
}
