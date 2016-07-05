package kata.rover;

import kata.rover.command.Move;
import kata.rover.command.MoveForward;
import kata.rover.command.OrientationLeft;
import kata.rover.command.OrientationRight;
import kata.rover.command.Rotate;
import kata.rover.command.RoverListener;
import org.assertj.core.api.Condition;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static kata.rover.CardinalDirection.EAST;
import static kata.rover.CardinalDirection.NORTH;
import static kata.rover.CardinalDirection.SOUTH;
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
        final RoverListener consumer = mock(RoverListener.class);
        final Coordinate coordinate = new Coordinate(0, 0);
        final Rover roverTest = new Rover(EAST, coordinate);

        // When
        roverTest.execute(consumer, command);

        // Then
        verify(consumer).roverChange(roverCaptor.capture());
        assertThat(roverCaptor.getValue()).is(new CloneCondition(NORTH, coordinate, "roverConsumer"));
        verifyNoMoreInteractions(consumer);
    }

    @Test
    public void testEastDirectionRotateRight() {
        // Given
        final Rotate command = new Rotate(new OrientationRight());
        final RoverListener consumer = mock(RoverListener.class);
        final Coordinate coordinate = new Coordinate(0, 0);
        final Rover roverTest = new Rover(EAST, coordinate);

        // When
        roverTest.execute(consumer, command);

        // Then
        verify(consumer).roverChange(roverCaptor.capture());
        assertThat(roverCaptor.getValue()).is(new CloneCondition(SOUTH, coordinate, "roverConsumer"));
        verifyNoMoreInteractions(consumer);
    }

    @Test
    public void testMoveForward() {
        // Given
        final Move command = new Move(new MoveForward());
        final RoverListener consumer = mock(RoverListener.class);
        final Coordinate coordinate = new Coordinate(0, 0);
        final Coordinate newCoordinate = new Coordinate(1, 0);
        final Rover roverTest = new Rover(EAST, coordinate);

        // When
        roverTest.execute(consumer, command);

        // Then
        verify(consumer).roverChange(roverCaptor.capture());
        assertThat(roverCaptor.getValue()).is(new CloneCondition(EAST, newCoordinate, "roverConsumer"));
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
