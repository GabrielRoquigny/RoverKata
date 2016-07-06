package kata.rover;

import kata.rover.command.Command;
import kata.rover.command.RoverListener;

import java.io.PrintStream;

public class Rover implements CanChangeDirection, CanChangePosition, CanDisplay {

    private RoverListener roverConsumer;
    private Direction direction;
    private Coordinate coordinate;

    public Rover(Direction direction, Coordinate coordinate) {
        super();
        this.coordinate = coordinate;
        this.direction = direction;
        this.roverConsumer = RoverListener.DEFAULT_LISTENER;
    }

    /**
     * Execute command and create new rovers with new state.
     *
     * @param consumer to send new rovers.
     * @param command  command to execute.
     *
     * @return this or equivalent.
     */
    public Rover execute(RoverListener consumer, Command command) {
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
        this.roverConsumer.roverChange(this);
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
    public Rover position(Coordinate coordinate) {
        final Rover clone = createClone();
        this.coordinate = coordinate;
        this.roverConsumer.roverChange(this);
        return clone;
    }
}
