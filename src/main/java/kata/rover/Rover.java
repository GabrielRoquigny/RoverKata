package kata.rover;

import kata.rover.command.Command;

import java.io.PrintStream;
import java.util.function.Consumer;

public class Rover {
    private final Direction direction;
    private final Coordinate coordinate;

    public Rover(Direction direction, Coordinate coordinate) {
        super();
        this.coordinate = coordinate;
        this.direction = direction;
    }

    /**
     * Execute command and create new rovers with new state.
     *
     * @param consumer to send new rovers.
     * @param command  command to execute.
     *
     * @return this or equivalent.
     */
    public Rover execute(Consumer<Rover> consumer, Command command) {
        command.modifyDirection(newDirection -> consumer.accept(new Rover(newDirection, coordinate)), direction);
        return this;
    }

    public Rover display(PrintStream printStream) {
        printStream.println(direction);
        return this;
    }
}
