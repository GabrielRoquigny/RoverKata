package kata.rover;

import kata.rover.command.Command;
import org.assertj.core.api.Condition;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.function.Consumer;

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
    public void testExecute() {
        // Given
        final Direction direction = mock(Direction.class), newDirection = mock(Direction.class);
        final Coordinate coordinate = mock(Coordinate.class);
        final Rover underTest = new Rover(direction, coordinate);

        final Consumer<Rover> consumer = mock(Consumer.class);
        final Command command = new Command() {
            @Override
            public Command modifyDirection(Consumer<Direction> directionConsumer, Direction direction) {
                directionConsumer.accept(newDirection);
                return this;
            }
        };

        // When
        final Rover result = underTest.execute(consumer, command);

        // Then
        verify(consumer).accept(roverCaptor.capture());
        assertThat(roverCaptor.getValue()).is(new CloneCondition(newDirection, coordinate));
        verifyNoMoreInteractions(consumer);

        assertThat(result).is(new CloneCondition(direction, coordinate));

    }


    private static class CloneCondition extends Condition<Rover> {

        private Direction direction;
        private Coordinate coordinate;

        CloneCondition(Direction direction, Coordinate coordinate) {
            this.direction = direction;
            this.coordinate = coordinate;
        }

        @Override
        public boolean matches(Rover value) {
            return new ReflectionEquals(value).matches(new Rover(direction, coordinate));
        }
    }
}
