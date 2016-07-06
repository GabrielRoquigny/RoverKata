package kata.rover;

import kata.rover.command.CommandIterator;
import kata.rover.command.RoverListener;

public class RoverController {
    private Rover rover;

    public RoverController(Rover rover) {
        super();
        this.rover = rover;
    }

    public RoverController execute(CommandIterator commandIterator) {
        commandIterator.executeOn(RoverListener.DEFAULT_LISTENER, rover);
        return this;
    }

}
