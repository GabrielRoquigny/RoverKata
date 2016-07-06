package kata.rover.command.iterator;

import kata.rover.Rover;
import kata.rover.command.Command;
import kata.rover.command.CommandIterator;
import kata.rover.command.RoverListener;

import javax.validation.constraints.NotNull;

public class CommandIteratorFifoController implements CommandIterator {
    private Command[] commands;
    private Rover rover;
    private RoverListener listener;

    /**
     * Constructor for clone.
     *
     * @param listener the listener to call after roverChange.
     * @param rover    the rover onto execute commands.
     * @param commands commands to execute.
     */
    private CommandIteratorFifoController(@NotNull RoverListener listener, @NotNull Rover rover, @NotNull Command... commands) {
        this(commands);
        this.listener = listener;
        this.rover = rover;
    }

    public CommandIteratorFifoController(@NotNull Command... commands) {
        super();
        this.commands = commands;
        this.listener = DEFAULT_LISTENER;
    }

    @Override
    public CommandIteratorFifoController roverChange(@NotNull Rover rover) {
        CommandIteratorFifoController clone = new CommandIteratorFifoController(listener, this.rover, commands);
        this.rover = rover;
        listener.roverChange(rover);
        return clone;
    }

    @Override
    public CommandIteratorFifoController executeOn(@NotNull RoverListener roverConsumer, @NotNull Rover rover) {
        CommandIteratorFifoController clone = new CommandIteratorFifoController(roverConsumer, rover, commands);
        for (Command command : clone.commands) {
            clone.rover.execute(clone, command);
        }
        return this;
    }
}
