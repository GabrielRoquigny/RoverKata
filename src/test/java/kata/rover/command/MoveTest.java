package kata.rover.command;

import kata.rover.CanChangeDirection;
import kata.rover.CanChangePosition;
import kata.rover.Coordinate;
import kata.rover.Direction;
import kata.rover.Vector;
import org.assertj.core.api.Condition;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class MoveTest {
    @Test
    public void testModify() {
        // Given
        final CanChangeDirectionPosition canChangeDirectionPosition = mock(CanChangeDirectionPosition.class);
        final Direction direction = mock(Direction.class);
        final Coordinate coordinate = mock(Coordinate.class);
        final Vector vector = mock(Vector.class);
        final MoveOrientation moveOrientation = new MoveOrientation() {
            @Override
            public MoveOrientation modify(CanChangePosition coordinateConsumer, Direction direction, Coordinate coordinate) {
                coordinateConsumer.move(vector);
                return this;
            }
        };
        final Move underTest = new Move(moveOrientation);

        // When
        final Move result = underTest.modify(canChangeDirectionPosition, direction, coordinate);

        // Then
        verify(canChangeDirectionPosition).move(vector);
        verifyNoMoreInteractions(canChangeDirectionPosition);
        assertThat(result).is(new CloneCondition(moveOrientation));
    }


    private static class CloneCondition extends Condition<Move> {

        private MoveOrientation orientation;

        CloneCondition(MoveOrientation orientation) {
            this.orientation = orientation;
        }

        @Override
        public boolean matches(Move value) {
            return new ReflectionEquals(value).matches(new Move(orientation));
        }
    }

    private interface CanChangeDirectionPosition extends CanChangeDirection, CanChangePosition {

    }

}
