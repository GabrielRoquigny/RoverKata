package kata.rover.command.iterator;

import kata.rover.Rover;
import kata.rover.command.Command;
import kata.rover.command.CommandIterator;
import kata.rover.command.RoverListener;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class CommandRepeaterController implements CommandIterator {
    public CommandRepeaterController(int nb, Command command) {
        throw new NotImplementedException();
    }

    @Override
    public CommandIterator roverChange(Rover rover) {
        throw new NotImplementedException();
    }

    @Override
    public CommandIterator executeOn(RoverListener roverConsumer, Rover rover) {
        return null;
    }
}
