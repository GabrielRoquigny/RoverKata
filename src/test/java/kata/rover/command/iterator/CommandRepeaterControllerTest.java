package kata.rover.command.iterator;

import kata.rover.Rover;
import kata.rover.command.Command;
import kata.rover.command.RoverListener;
import org.assertj.core.api.Condition;
import org.mockito.InOrder;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class CommandRepeaterControllerTest {

    @Test
    public void testModify() {
        // Given
        final Command command = mock(Command.class);
        int times = 5;
        final CommandRepeaterController underTest = new CommandRepeaterController(times, command);

        final RoverListener consumer = mock(RoverListener.class);
        final Rover rover = mock(Rover.class);

        // When
        final CommandRepeaterController result = underTest.executeOn(consumer, rover);

        // Then
        assertThat(result).is(new CloneCondition(times, command));
        verify(rover, times(times)).execute(any(), eq(command));
        verifyNoMoreInteractions(rover, command, consumer);
    }

    @Test
    public void testModify_whenRoverChange() {
        // Given
        final Command command = mock(Command.class);
        int times = 5;
        final CommandRepeaterController underTest = new CommandRepeaterController(times, command);

        final RoverListener consumer = mock(RoverListener.class);
        final Rover roverChange = mock(Rover.class);
        final Rover rover = new Rover(null, null) {
            @Override
            public Rover execute(RoverListener consumer, Command command) {
                consumer.roverChange(roverChange);
                return this;
            }
        };

        // When
        final CommandRepeaterController result = underTest.executeOn(consumer, rover);

        // Then
        assertThat(result).is(new CloneCondition(times, command));
        InOrder inOrder = inOrder(consumer, roverChange);
        inOrder.verify(consumer).roverChange(roverChange);
        inOrder.verify(roverChange, times(times - 1)).execute(any(), eq(command));
        verifyNoMoreInteractions(roverChange, command, consumer);
    }

    @Test
    public void testRoverChange() {
        // Given
        final Command command = mock(Command.class);
        final int times = 5;
        final CommandRepeaterController underTest = new CommandRepeaterController(times, command);

        final Rover rover = mock(Rover.class);

        // When
        final CommandRepeaterController result = underTest.roverChange(rover);

        // Then
        assertThat(result).is(new CloneCondition(times, command));
        verifyNoMoreInteractions(command, rover);
    }

    private static class CloneCondition extends Condition<CommandRepeaterController> {
        private int times;
        private Command command;

        CloneCondition(int times, Command command) {
            this.times = times;
            this.command = command;
        }

        @Override
        public boolean matches(CommandRepeaterController value) {
            return new ReflectionEquals(value).matches(new CommandRepeaterController(times, command));
        }
    }
}
