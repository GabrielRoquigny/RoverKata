package kata.rover.command.iterator;

import kata.rover.Rover;
import kata.rover.command.CommandIterator;
import kata.rover.command.RoverListener;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class CommandControllerIterator implements CommandIterator {
    public CommandControllerIterator(CommandIterator... commandIterators) {
        throw new NotImplementedException();
    }

    @Override
    public CommandControllerIterator roverChange(Rover rover) {
        throw new NotImplementedException();
    }

    @Override
    public CommandControllerIterator executeOn(RoverListener roverConsumer, Rover rover) {
        throw new NotImplementedException();
    }
}
