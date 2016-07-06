package kata.rover.command;

import kata.rover.Rover;

import javax.validation.constraints.NotNull;

public interface RoverListener {
    /**
     * Default implementation for <a href="https://en.wikipedia.org/wiki/Null_Object_pattern">null object pattern</a>.
     */
    RoverListener DEFAULT_LISTENER = new RoverListener() {
        @Override
        public RoverListener roverChange(Rover rover) {
            return this;
        }
    };

    RoverListener roverChange(@NotNull Rover rover);

}
