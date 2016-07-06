package kata.rover.command.iterator;

import kata.rover.Rover;
import kata.rover.command.CommandIterator;
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

public class CommandControllerIteratorTest {
    @Test
    public void testExecuteOn_withoutCommandIterator() {
        // Given
        final CommandControllerIterator underTest = new CommandControllerIterator();

        final RoverListener consumer = mock(RoverListener.class);
        final Rover rover = mock(Rover.class);

        // When
        final CommandControllerIterator result = underTest.executeOn(consumer, rover);

        // Then
        assertThat(result).is(new CloneCondition());
        verifyNoMoreInteractions(consumer, rover);
    }

    @Test
    public void testExecuteOn_withoutRoverChange() {
        // Given
        final CommandIterator commandIterator1 = mock(CommandIterator.class), commandIterator2 = mock(
                CommandIterator.class);
        final CommandControllerIterator underTest = new CommandControllerIterator(commandIterator1, commandIterator2);

        final RoverListener consumer = mock(RoverListener.class);
        final Rover rover = mock(Rover.class);

        // When
        final CommandControllerIterator result = underTest.executeOn(consumer, rover);

        // Then
        assertThat(result).is(new CloneCondition(commandIterator1, commandIterator2));
        InOrder inOrder = inOrder(commandIterator1, commandIterator2);
        inOrder.verify(commandIterator1).executeOn(any(), eq(rover));
        inOrder.verify(commandIterator2).executeOn(any(), eq(rover));
        verifyNoMoreInteractions(consumer, rover, commandIterator1, commandIterator2);
    }

    @Test
    public void testExecuteOn() {
        // Given
        final Rover rover = mock(Rover.class), roverChange = mock(Rover.class);
        final CommandIterator commandIterator1 = new CommandIterator() {
            public CommandIterator roverChange(Rover rover) {
                return null;
            }

            public CommandIterator executeOn(RoverListener roverConsumer, Rover rover) {
                roverConsumer.roverChange(roverChange);
                return this;
            }
        }, commandIterator2 = mock(CommandIterator.class);
        final CommandControllerIterator underTest = new CommandControllerIterator(commandIterator1, commandIterator2);

        final RoverListener consumer = mock(RoverListener.class);

        // When
        final CommandControllerIterator result = underTest.executeOn(consumer, rover);

        // Then
        assertThat(result).is(new CloneCondition(commandIterator1, commandIterator2));
        InOrder inOrder = inOrder(commandIterator2, consumer);
        inOrder.verify(consumer).roverChange(roverChange);
        inOrder.verify(commandIterator2).executeOn(any(), eq(roverChange));
        verifyNoMoreInteractions(consumer, rover, commandIterator2);

    }

    @Test
    public void testRoverChange() {
        // Given
        final CommandIterator commandIterator1 = mock(CommandIterator.class), commandIterator2 = mock(
                CommandIterator.class);
        final CommandControllerIterator underTest = new CommandControllerIterator(commandIterator1, commandIterator2);

        final Rover rover = mock(Rover.class);

        // When
        final CommandControllerIterator result = underTest.roverChange(rover);

        // Then
        assertThat(result).is(new CloneCondition(commandIterator1, commandIterator2));
        verifyNoMoreInteractions(rover, commandIterator1, commandIterator2);
    }

    private static class CloneCondition extends Condition<CommandControllerIterator> {
        private CommandIterator[] commandIterators;

        CloneCondition(CommandIterator... commandIterators) {
            this.commandIterators = commandIterators;
        }

        @Override
        public boolean matches(CommandControllerIterator value) {
            return new ReflectionEquals(value).matches(new CommandControllerIterator(commandIterators));
        }
    }
}
