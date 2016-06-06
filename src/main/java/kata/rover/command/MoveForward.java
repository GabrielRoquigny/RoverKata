package kata.rover.command;

import kata.rover.CanChangePosition;
import kata.rover.Coordinate;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class MoveForward implements MoveOrientation {
    @Override
    public Command modify(CanChangePosition coordinateConsumer, Coordinate coordinate) {
        throw new NotImplementedException();
    }
}
