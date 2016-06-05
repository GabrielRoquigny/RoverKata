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

public class OrientationRightTest {
    @Test
    public void testGiveNextDirection() {
        // Given
        final OrientationRight underTest = new OrientationRight();
        final Direction directionResult = mock(Direction.class);
        final CanChangeDirection directionConsumer = mock(CanChangeDirection.class);
        final Direction directionSrc = new Direction() {
            @Override
            public Direction giveMeDirectionOnLeftRotation(CanChangeDirection directionConsumer) {
                throw new NotImplementedException();
            }

            @Override
            public Direction giveMeDirectionOnRightRotation(CanChangeDirection directionConsumer) {
                directionConsumer.rotateTo(directionResult);
                return this;
            }
        };

        // When
        final OrientationRight result = underTest.giveNextDirection(directionConsumer, directionSrc);

        // Then
        verify(directionConsumer).rotateTo(directionResult);
        assertThat(result).is(new CloneCondition());
    }

    @Test
    public void testGiveNextDirection_withoutResponseFromDirection() {
        // Given
        final OrientationRight underTest = new OrientationRight();
        final CanChangeDirection directionConsumer = mock(CanChangeDirection.class);
        final Direction directionSrc = mock(Direction.class);

        // When
        final OrientationRight result = underTest.giveNextDirection(directionConsumer, directionSrc);

        // Then
        verify(directionSrc).giveMeDirectionOnRightRotation(any());

        assertThat(result).is(new CloneCondition());
    }


    private static class CloneCondition extends Condition<OrientationRight> {
        @Override
        public boolean matches(OrientationRight value) {
            return new ReflectionEquals(value).matches(new OrientationRight());
        }
    }
}
