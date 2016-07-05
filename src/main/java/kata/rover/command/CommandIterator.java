package kata.rover.command;

import kata.rover.Rover;

public interface CommandIterator extends RoverListener {
    CommandIterator roverChange(Rover rover);

    CommandIterator executeOn(RoverListener roverConsumer, Rover rover);
}
