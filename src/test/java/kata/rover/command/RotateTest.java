package kata.rover.command;

import kata.rover.Direction;
import org.assertj.core.api.Condition;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.testng.annotations.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class RotateTest {
    @Test
    public void testModifyDirection() {
        // Given
        final Direction direction1 = mock(Direction.class);
        final Direction direction2 = mock(Direction.class);
        final Orientation orientation = new OrientationMock(direction2) {
            @Override
            public Orientation giveNextDirection(Consumer<Direction> directionConsumer, Direction direction) {
                directionConsumer.accept(this.direction);
                return this;
            }
        };
        final Consumer<Direction> consumer = mock(Consumer.class);
        final Rotate underTest = new Rotate(orientation);

        // When
        final Rotate result = underTest.modifyDirection(consumer, direction1);

        // Then
        verify(consumer).accept(direction2);
        verifyNoMoreInteractions(consumer);

        assertThat(result).is(CloneCondition.clone(orientation));
    }

    private static class CloneCondition extends Condition<Rotate> {

        public static CloneCondition clone(Orientation orientation) {
            return new CloneCondition(orientation);
        }

        private Orientation orientation;

        CloneCondition(Orientation orientation) {
            this.orientation = orientation;
        }

        @Override
        public boolean matches(Rotate value) {
            return new ReflectionEquals(value).matches(new Rotate(orientation));
        }
    }

    private class OrientationMock implements Orientation {

        protected final Direction direction;

        public OrientationMock(Direction direction) {
            this.direction = direction;
        }

        @Override
        public Orientation giveNextDirection(Consumer<Direction> directionConsumer, Direction direction) {
            throw new NotImplementedException();
        }
    }
}
