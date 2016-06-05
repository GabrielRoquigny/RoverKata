package kata.rover.command;

import kata.rover.CanChangeDirection;
import kata.rover.CanChangePosition;
import kata.rover.Coordinate;
import kata.rover.Direction;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class CommandTest {


    @Test
    public void testModify() {
        // Given
        final Direction direction = mock(Direction.class);
        final Coordinate coordinate = mock(Coordinate.class);
        final CanChangeDirectionPosition consumer = mock(CanChangeDirectionPosition.class);
        final Command underTest = new Command() {
        };

        // When
        final Command result = underTest.modify(consumer, direction, coordinate);

        // Then
        verifyNoMoreInteractions(direction);
        verifyNoMoreInteractions(consumer);
        verifyNoMoreInteractions(coordinate);

        assertThat(result).isSameAs(underTest);
    }

    private interface CanChangeDirectionPosition extends CanChangeDirection, CanChangePosition {

    }
}
