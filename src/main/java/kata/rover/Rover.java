package kata.rover;

import kata.rover.command.Command;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.function.Consumer;

public class Rover {
    public Rover(Direction direction, Coordinate coordinate) {
        super();
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
        throw new NotImplementedException();
    }
}
