package kata.rover.command;

import kata.rover.Rover;

public interface RoverListener {
    RoverListener roverChange(Rover rover);
}
