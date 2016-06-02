package kata.rover;

import org.assertj.core.api.Condition;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.testng.annotations.Test;

import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class CoordinateTest {
    @Test
    public void giveMeX() {
        // Given
        Integer x = 1, y = 50;
        Consumer<Integer> consumer = mock(Consumer.class);
        Coordinate underTest = new Coordinate(x, y);

        // When
        Coordinate result = underTest.giveMeX(consumer);

        // Then
        verify(consumer).accept(x);
        verifyNoMoreInteractions(consumer);
        assertThat(result).is(new CloneCondition(x, y));
    }

    @Test
    public void giveMeY() {
        // Given
        Integer x = 1, y = 50;
        Consumer<Integer> consumer = mock(Consumer.class);
        Coordinate underTest = new Coordinate(x, y);

        // When
        Coordinate result = underTest.giveMeY(consumer);

        // Then
        verify(consumer).accept(y);
        verifyNoMoreInteractions(consumer);
        assertThat(result).is(new CloneCondition(x, y));
    }

    private static class CloneCondition extends Condition<Coordinate> {
        private Integer x, y;

        CloneCondition(Integer x, Integer y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean matches(Coordinate value) {
            return new ReflectionEquals(value).matches(new Coordinate(x, y));
        }
    }
}
