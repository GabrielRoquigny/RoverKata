package kata.rover.command;

import kata.rover.CanChangePosition;
import kata.rover.Coordinate;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class MoveForward implements MoveOrientation {
    @Override
    public MoveForward modify(CanChangePosition coordinateConsumer, Coordinate coordinate) {
        throw new NotImplementedException();
    }
}
