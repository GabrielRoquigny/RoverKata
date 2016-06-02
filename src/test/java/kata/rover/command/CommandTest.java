package kata.rover.command;

import kata.rover.Coordinate;
import kata.rover.Direction;
import org.testng.annotations.Test;

import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class CommandTest {


    @Test
    public void testModifyDirection() {
        // Given
        final Direction direction = mock(Direction.class);
        final Consumer<Direction> consumer = mock(Consumer.class);
        final Command underTest = new Command() {
        };

        // When
        final Command result = underTest.modifyDirection(consumer, direction);

        // Then
        verifyNoMoreInteractions(direction);
        verifyNoMoreInteractions(consumer);

        assertThat(result).isSameAs(underTest);
    }

    @Test
    public void testModifyCoordinate() {
        // Given
        final Coordinate coordinate = mock(Coordinate.class);
        final Orientation orientation = mock(Orientation.class);
        final Consumer<Coordinate> consumer = mock(Consumer.class);
        final Command underTest = new Command() {
        };

        // When
        final Command result = underTest.modifyCoordinate(consumer, coordinate);

        // Then
        verifyNoMoreInteractions(orientation);
        verifyNoMoreInteractions(consumer);

        assertThat(result).isSameAs(underTest);
    }
}
