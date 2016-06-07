package kata.rover.command;

import kata.rover.CanChangeDirection;
import kata.rover.CanChangePosition;
import kata.rover.Coordinate;
import kata.rover.Direction;
import kata.rover.Vector;
import org.assertj.core.api.Condition;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.testng.annotations.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class MoveForwardTest {
    @Test
    public void testModify() {
        // Given
        final Vector vector = mock(Vector.class);
        final CanChangePosition canChangePosition = mock(CanChangePosition.class);
        final Direction direction = new Direction() {
            public Direction giveMeDirectionOnLeftRotation(CanChangeDirection directionConsumer) {
                throw new NotImplementedException();
            }

            public Direction giveMeDirectionOnRightRotation(CanChangeDirection directionConsumer) {
                throw new NotImplementedException();
            }

            public Direction giveMeVectorForward(Consumer<Vector> vectorConsumer) {
                vectorConsumer.accept(vector);
                return this;
            }
        };
        final Coordinate coordinate = mock(Coordinate.class);
        final MoveForward underTest = new MoveForward();

        // When
        final MoveForward result = underTest.modify(canChangePosition, direction, coordinate);

        // Then
        verify(canChangePosition).move(vector);
        verifyNoMoreInteractions(canChangePosition);

        assertThat(result).is(new CloneCondition());
    }

    private static class CloneCondition extends Condition<MoveForward> {
        @Override
        public boolean matches(MoveForward value) {
            return new ReflectionEquals(value).matches(new MoveForward());
        }
    }

}
