package kata.rover.command.iterator;

import kata.rover.Rover;
import kata.rover.command.Command;
import kata.rover.command.CommandIterator;
import kata.rover.command.RoverListener;

import javax.validation.constraints.NotNull;

public class CommandRepeaterController implements CommandIterator {
    private int nb;
    private Command command;
    private Rover rover;
    private RoverListener roverListener;

    /**
     * Constructor for clone.
     *
     * @param rover         rover onto execute command.
     * @param roverListener listener for rover.
     * @param nb            times to execute command.
     * @param command       command to execute.
     */
    private CommandRepeaterController(@NotNull Rover rover, @NotNull RoverListener roverListener, int nb, @NotNull Command command) {
        this(nb, command);
        this.rover = rover;
        this.roverListener = roverListener;
    }

    public CommandRepeaterController(int nb, @NotNull Command command) {
        super();
        this.nb = nb;
        this.command = command;
        this.roverListener = DEFAULT_LISTENER;
    }

    @Override
    public CommandRepeaterController roverChange(@NotNull Rover rover) {
        CommandRepeaterController clone = new CommandRepeaterController(this.rover, roverListener, nb, command);
        this.rover = rover;
        roverListener.roverChange(rover);
        return clone;
    }

    @Override
    public CommandRepeaterController executeOn(@NotNull RoverListener roverConsumer, @NotNull Rover rover) {
        CommandRepeaterController clone = new CommandRepeaterController(rover, roverConsumer, nb, command);
        for (int i = 0; i < nb; i++) {
            clone.rover.execute(clone, command);
        }
        return this;
    }
}
