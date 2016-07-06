package kata.rover;

import kata.rover.command.Command;
import kata.rover.command.RoverListener;
import org.assertj.core.api.Condition;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class RoverTest {
    @Captor
    private ArgumentCaptor<Rover> roverCaptor;

    @BeforeTest
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testExecute_rotate() {
        // Given
        final Direction direction = mock(Direction.class), newDirection = mock(Direction.class);
        final Coordinate coordinate = mock(Coordinate.class);
        final Rover underTest = new Rover(direction, coordinate);

        final RoverListener consumer = mock(RoverListener.class);
        final Command command = new Command() {
            @Override
            public Command modify(CanChangeDirection directionConsumer, Direction direction, Coordinate coordinate1) {
                directionConsumer.rotateTo(newDirection);
                return this;
            }
        };

        // When
        final Rover result = underTest.execute(consumer, command);

        // Then
        verify(consumer).roverChange(roverCaptor.capture());
        assertThat(roverCaptor.getValue()).is(new CloneCondition(newDirection, coordinate, "roverConsumer"));
        verifyNoMoreInteractions(consumer);

        assertThat(result).is(new CloneCondition(direction, coordinate));

    }

    @Test
    public void testExecute_position() {
        // Given
        final Direction direction = mock(Direction.class);
        final Coordinate coordinate = mock(Coordinate.class), newCoordinate = mock(Coordinate.class);
        final Rover underTest = new Rover(direction, coordinate);

        final RoverListener consumer = mock(RoverListener.class);
        final Command command = new Command() {
            @Override
            public Command modify(CanChangeDirection positionConsumer, Direction direction, Coordinate coordinate1) {
                ((CanChangePosition) positionConsumer).position(newCoordinate);
                return this;
            }
        };

        // When
        final Rover result = underTest.execute(consumer, command);

        // Then
        verify(consumer).roverChange(roverCaptor.capture());
        assertThat(roverCaptor.getValue()).is(new CloneCondition(direction, newCoordinate, "roverConsumer"));
        verifyNoMoreInteractions(consumer);

        assertThat(result).is(new CloneCondition(direction, coordinate));
    }

    @Test
    public void testPosition() {
        // Given
        final Coordinate coordinate = mock(Coordinate.class), newCoordinate = mock(Coordinate.class);
        final Direction direction = mock(Direction.class);

        final Rover underTest = new Rover(direction, coordinate);

        // When
        final Rover result = underTest.position(newCoordinate);

        // Then
        verifyNoMoreInteractions(direction, coordinate, newCoordinate);

        assertThat(result).is(new CloneCondition(direction, coordinate));

    }

    @Test
    public void testRotateTo() {
        // Given
        final Coordinate coordinate = mock(Coordinate.class);
        final Direction direction = mock(Direction.class), newDirection = mock(Direction.class);

        final Rover underTest = new Rover(direction, coordinate);

        // When
        final Rover result = underTest.rotateTo(newDirection);

        // Then
        verifyNoMoreInteractions(direction, coordinate, newDirection);

        assertThat(result).is(new CloneCondition(direction, coordinate));

    }

    @Test
    public void testMove() {
        // Given
        final Coordinate coordinate = mock(Coordinate.class);
        final Direction direction = mock(Direction.class);
        final Vector vector = mock(Vector.class);

        final Rover underTest = new Rover(direction, coordinate);

        // When
        final Rover result = underTest.move(vector);

        // Then
        verify(vector).applyOnCoordinate(underTest, coordinate);
        verifyNoMoreInteractions(vector);

        assertThat(result).is(new CloneCondition(direction, coordinate));
    }

    @Test
    public void testDisplay() {
        // Given
        final Coordinate coordinate = mock(Coordinate.class);
        final Direction direction = mock(Direction.class);

        final Rover underTest = new Rover(direction, coordinate);
        final PrintStream printStream = mock(PrintStream.class);

        // When
        final Rover result = underTest.display(printStream);

        // Then
        verify(printStream).append("direction ");
        verify(printStream).print(direction);
        verify(printStream).append(" coordinate (");
        verify(coordinate).display(printStream);
        verify(printStream).append(")");
        assertThat(result).is(new CloneCondition(direction, coordinate));
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
