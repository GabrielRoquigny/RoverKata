package kata.rover;

import kata.rover.command.iterator.CommandControllerIterator;
import org.assertj.core.api.Condition;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class RoverControllerTest {
    @Test
    public void testExecute() {
        // Given
        final Rover rover = mock(Rover.class);
        final RoverController underTest = new RoverController(rover);

        final CommandControllerIterator commandControllerIterator = mock(CommandControllerIterator.class);

        // When
        final RoverController result = underTest.execute(commandControllerIterator);

        // Then
        assertThat(result).is(new CloneCondition(rover));
        verify(commandControllerIterator).executeOn(any(), eq(rover));
    }

    private static class CloneCondition extends Condition<RoverController> {
        private Rover rover;

        CloneCondition(Rover rover) {
            this.rover = rover;
        }

        @Override
        public boolean matches(RoverController value) {
            return new ReflectionEquals(value).matches(new RoverController(rover));
        }
    }
}
