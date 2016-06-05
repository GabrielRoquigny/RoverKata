package kata.rover.command;

import kata.rover.CanChangeDirection;
import kata.rover.Direction;
import org.assertj.core.api.Condition;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.testng.annotations.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class OrientationLeftTest {
    @Test
    public void testGiveNextDirection() {
        // Given
        final OrientationLeft underTest = new OrientationLeft();
        final Direction directionResult = mock(Direction.class);
        final CanChangeDirection directionConsumer = mock(CanChangeDirection.class);
        final Direction directionSrc = new Direction() {
            @Override
            public Direction giveMeDirectionOnLeftRotation(CanChangeDirection directionConsumer) {
                directionConsumer.rotateTo(directionResult);
                return this;
            }

            @Override
            public Direction giveMeDirectionOnRightRotation(CanChangeDirection directionConsumer) {
                throw new NotImplementedException();
            }
        };

        // When
        final OrientationLeft result = underTest.giveNextDirection(directionConsumer, directionSrc);

        // Then
        verify(directionConsumer).rotateTo(directionResult);
        assertThat(result).is(new CloneCondition());
    }

    @Test
    public void testGiveNextDirection_withoutResponseFromDirection() {
        // Given
        final OrientationLeft underTest = new OrientationLeft();
        final CanChangeDirection directionConsumer = mock(CanChangeDirection.class);
        final Direction directionSrc = mock(Direction.class);

        // When
        final OrientationLeft result = underTest.giveNextDirection(directionConsumer, directionSrc);

        // Then
        verify(directionSrc).giveMeDirectionOnLeftRotation(any());

        assertThat(result).is(new CloneCondition());
    }


    private static class CloneCondition extends Condition<OrientationLeft> {
        @Override
        public boolean matches(OrientationLeft value) {
            return new ReflectionEquals(value).matches(new OrientationLeft());
        }
    }
}
