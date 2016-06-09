package kata.rover;

import kata.rover.command.Command;

import java.io.PrintStream;
import java.util.function.Consumer;

public class Rover implements CanChangeDirection, CanChangePosition, CanDisplay {

    private Consumer<Rover> roverConsumer;
    private Direction direction;
    private Coordinate coordinate;

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
        final Rover clone = createClone();
        this.roverConsumer = consumer;
        command.modify(this, direction, coordinate);
        return clone;
    }

    public Rover display(PrintStream printStream) {
        printStream.append("direction ");
        printStream.print(direction);
        printStream.append(" coordinate (");
        coordinate.display(printStream);
        printStream.append(")");
        return this;
    }

    @Override
    public Rover rotateTo(Direction newDirection) {
        final Rover clone = createClone();
        this.direction = newDirection;
        this.roverConsumer.accept(this);
        return clone;
    }

    private Rover createClone() {
        final Rover clone = new Rover(direction, coordinate);
        clone.roverConsumer = roverConsumer;
        return clone;
    }

    @Override
    public Rover move(Vector vector) {
        vector.applyOnCoordinate(this, coordinate);
        return this;
    }

    @Override
    public CanChangePosition position(Coordinate coordinate) {
        final Rover clone = createClone();
        this.coordinate = coordinate;
        if (null != roverConsumer) {
            this.roverConsumer.accept(this);
        }
        return clone;
    }
}
