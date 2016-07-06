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
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class CommandIteratorFifoControllerTest {

    @Test
    public void testModify() {
        // Given
        final Command command1 = mock(Command.class), command2 = mock(Command.class);
        final CommandIteratorFifoController underTest = new CommandIteratorFifoController(command1, command2);

        final RoverListener consumer = mock(RoverListener.class);
        final Rover rover = mock(Rover.class);

        // When
        final CommandIteratorFifoController result = underTest.executeOn(consumer, rover);

        // Then
        assertThat(result).is(new CloneCondition(command1, command2));
        InOrder inOrder = inOrder(rover);
        inOrder.verify(rover).execute(any(), eq(command1));
        inOrder.verify(rover).execute(any(), eq(command2));
        verifyNoMoreInteractions(rover, command1, command2, consumer);
    }

    @Test
    public void testModify_whenRoverChange() {
        // Given
        final Command command1 = mock(Command.class), command2 = mock(Command.class);
        final CommandIteratorFifoController underTest = new CommandIteratorFifoController(command1, command2);

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
        final CommandIteratorFifoController result = underTest.executeOn(consumer, rover);

        // Then
        assertThat(result).is(new CloneCondition(command1, command2));
        InOrder inOrder = inOrder(roverChange, consumer);
        inOrder.verify(consumer).roverChange(roverChange);
        inOrder.verify(roverChange).execute(any(), eq(command2));
        verifyNoMoreInteractions(roverChange, command1, command2, consumer);
    }

    @Test
    public void testRoverChange() {
        // Given
        final Command command1 = mock(Command.class), command2 = mock(Command.class);
        final CommandIteratorFifoController underTest = new CommandIteratorFifoController(command1, command2);

        final Rover rover = mock(Rover.class);

        // When
        final CommandIteratorFifoController result = underTest.roverChange(rover);

        // Then
        assertThat(result).is(new CloneCondition(command1, command2));
        verifyNoMoreInteractions(command1, command2, rover);
    }

    private static class CloneCondition extends Condition<CommandIteratorFifoController> {
        private Command[] commands;

        CloneCondition(Command... commands) {
            this.commands = commands;
        }

        @Override
        public boolean matches(CommandIteratorFifoController value) {
            return new ReflectionEquals(value).matches(new CommandIteratorFifoController(commands));
        }
    }
}
