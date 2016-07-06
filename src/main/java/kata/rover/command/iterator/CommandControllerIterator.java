package kata.rover.command.iterator;

import kata.rover.Rover;
import kata.rover.command.CommandIterator;
import kata.rover.command.RoverListener;

import javax.validation.constraints.NotNull;

public class CommandControllerIterator implements CommandIterator {
    private RoverListener listener;
    private CommandIterator[] commandIterators;
    private Rover rover;

    private CommandControllerIterator(@NotNull Rover rover, @NotNull RoverListener listener, @NotNull CommandIterator... commandIterators) {
        this(commandIterators);
        this.rover = rover;
        this.listener = listener;
    }

    public CommandControllerIterator(@NotNull CommandIterator... commandIterators) {
        super();
        this.commandIterators = commandIterators;
        this.listener = DEFAULT_LISTENER;
    }

    @Override
    public CommandControllerIterator roverChange(@NotNull Rover rover) {
        CommandControllerIterator clone = new CommandControllerIterator(this.rover, listener, commandIterators);
        this.rover = rover;
        listener.roverChange(rover);
        return clone;
    }

    @Override
    public CommandControllerIterator executeOn(@NotNull RoverListener roverConsumer, @NotNull Rover rover) {
        CommandControllerIterator clone = new CommandControllerIterator(rover, roverConsumer, commandIterators);
        for (CommandIterator commandIterator : clone.commandIterators) {
            commandIterator.executeOn(clone, clone.rover);
        }
        return this;
    }
}
