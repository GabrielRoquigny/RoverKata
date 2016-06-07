package kata.rover;

import org.assertj.core.api.Condition;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.testng.annotations.Test;

import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class VectorTest {
    @Test
    public void testApplyOnCoordinate() {
        // Given
        final Consumer<Coordinate> consumer = mock(Consumer.class);
        Integer cx = 0, cy = -1, vx = 8, vy = -9;
        final Coordinate coordinate = new Coordinate(cx, cy) {
            @Override
            public Coordinate giveMeX(Consumer<Integer> consumer) {
                consumer.accept(cx);
                return this;
            }

            @Override
            public Coordinate giveMeY(Consumer<Integer> consumer) {
                consumer.accept(cy);
                return this;
            }
        };
        final Vector underTest = new Vector(vx, vy);

        // When
        final Vector result = underTest.applyOnCoordinate(consumer, coordinate);

        // Then
        verify(consumer).accept(new Coordinate(cx + vx, cy + vy));
        verifyNoMoreInteractions(consumer);

        assertThat(result).is(new CloneCondition(vx, vy));
    }

    @Test
    public void testEquals() {
        assertThat(new Vector(0, 0)).isEqualTo(new Vector(0, 0));
        assertThat(new Vector(0, 0)).isNotEqualTo(new Vector(1, 0));
        assertThat(new Vector(0, 0)).isNotEqualTo(new Vector(1, 1));
        assertThat(new Vector(0, 0)).isNotEqualTo(new Vector(0, 1));
    }

    @Test
    public void testHashCode() {
        assertThat(new Vector(0, 0).hashCode()).isEqualTo(new Vector(0, 0).hashCode());
        assertThat(new Vector(0, 0).hashCode()).isNotEqualTo(new Vector(1, 0).hashCode());
        assertThat(new Vector(0, 0).hashCode()).isNotEqualTo(new Vector(1, 1).hashCode());
        assertThat(new Vector(0, 0).hashCode()).isNotEqualTo(new Vector(0, 1).hashCode());
    }


    private static class CloneCondition extends Condition<Vector> {

        private Integer x;
        private Integer y;
        private String[] excludeFields;

        CloneCondition(Integer x, Integer y, String... excludeFields) {
            this.x = x;
            this.y = y;
            this.excludeFields = excludeFields;
        }

        @Override
        public boolean matches(Vector value) {
            return new ReflectionEquals(value, excludeFields).matches(new Vector(x, y));
        }
    }
}
