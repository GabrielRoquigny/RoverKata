package kata.rover;

import kata.rover.command.OrientationLeft;
import kata.rover.command.OrientationRight;
import kata.rover.command.Rotate;
import org.assertj.core.api.Condition;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.function.Consumer;

import static kata.rover.CardinalDirection.EAST;
import static kata.rover.CardinalDirection.NORTH;
import static kata.rover.CardinalDirection.SOUTH;
import static kata.rover.CardinalDirection.WEST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class RoverTestIT {

    @Captor
    private ArgumentCaptor<Rover> roverCaptor;

    @BeforeTest
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testEastDirectionRotateLeft() {
        // Given
        final Rotate command = new Rotate(new OrientationLeft());
        final Consumer<Rover> consumer = mock(Consumer.class);
        final Coordinate coordinate = new Coordinate(0, 0);
        final Rover roverTest = new Rover(EAST, coordinate);

        // When
        roverTest.execute(consumer, command);

        // Then
        verify(consumer).accept(roverCaptor.capture());
        assertThat(roverCaptor.getValue()).is(new CloneCondition(NORTH, coordinate, "roverConsumer"));
        verifyNoMoreInteractions(consumer);
    }

    @Test
    public void testEastDirectionRotateRight() {
        // Given
        final Rotate command = new Rotate(new OrientationRight());
        final Consumer<Rover> consumer = mock(Consumer.class);
        final Coordinate coordinate = new Coordinate(0, 0);
        final Rover roverTest = new Rover(EAST, coordinate);

        // When
        roverTest.execute(consumer, command);

        // Then
        verify(consumer).accept(roverCaptor.capture());
        assertThat(roverCaptor.getValue()).is(new CloneCondition(SOUTH, coordinate, "roverConsumer"));
        verifyNoMoreInteractions(consumer);
    }

    private static class CloneCondition extends Condition<Rover> {

        private Direction direction;
        private Coordinate coordinate;
        private String[] excludeFields;

        CloneCondition(Direction direction, Coordinate coordinate, String... excludeFields) {
            this.direction = direction;
            this.coordinate = coordinate;
            this.excludeFields = excludeFields;
        }

        @Override
        public boolean matches(Rover value) {
            return new ReflectionEquals(value, excludeFields).matches(new Rover(direction, coordinate));
        }
    }
}
