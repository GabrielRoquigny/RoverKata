package kata.rover;

import org.testng.annotations.Test;

import java.util.function.Consumer;

import static kata.rover.CardinalDirection.EAST;
import static kata.rover.CardinalDirection.NORTH;
import static kata.rover.CardinalDirection.SOUTH;
import static kata.rover.CardinalDirection.WEST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class CardinalDirectionTest {
    @Test
    public void testGiveMeDirectionOnLeftRotationForNORTH() {
        Consumer<Direction> consumer = mock(Consumer.class);

        CardinalDirection underTest = NORTH;
        CardinalDirection result = underTest.giveMeDirectionOnLeftRotation(consumer);

        verify(consumer).accept(WEST);
        verifyNoMoreInteractions(consumer);
        assertThat(result).isSameAs(underTest);
    }

    @Test
    public void testGiveMeDirectionOnRightRotationForNORTH() {
        Consumer<Direction> consumer = mock(Consumer.class);

        CardinalDirection underTest = NORTH;
        CardinalDirection result = underTest.giveMeDirectionOnRightRotation(consumer);

        verify(consumer).accept(EAST);
        verifyNoMoreInteractions(consumer);
        assertThat(result).isSameAs(underTest);
    }

    @Test
    public void testGiveMeDirectionOnLeftRotationForSOUTH() {
        Consumer<Direction> consumer = mock(Consumer.class);

        CardinalDirection underTest = SOUTH;
        CardinalDirection result = underTest.giveMeDirectionOnLeftRotation(consumer);

        verify(consumer).accept(EAST);
        verifyNoMoreInteractions(consumer);
        assertThat(result).isSameAs(underTest);

    }

    @Test
    public void testGiveMeDirectionOnRightRotationForSOUTH() {
        Consumer<Direction> consumer = mock(Consumer.class);

        CardinalDirection underTest = SOUTH;
        CardinalDirection result = underTest.giveMeDirectionOnRightRotation(consumer);

        verify(consumer).accept(WEST);
        verifyNoMoreInteractions(consumer);
        assertThat(result).isSameAs(underTest);

    }

    @Test
    public void testGiveMeDirectionOnLeftRotationForEAST() {
        Consumer<Direction> consumer = mock(Consumer.class);

        CardinalDirection underTest = EAST;
        CardinalDirection result = underTest.giveMeDirectionOnLeftRotation(consumer);

        verify(consumer).accept(NORTH);
        verifyNoMoreInteractions(consumer);
        assertThat(result).isSameAs(underTest);
    }

    @Test
    public void testGiveMeDirectionOnRightRotationForEAST() {
        Consumer<Direction> consumer = mock(Consumer.class);

        CardinalDirection underTest = EAST;
        CardinalDirection result = underTest.giveMeDirectionOnRightRotation(consumer);

        verify(consumer).accept(SOUTH);
        verifyNoMoreInteractions(consumer);
        assertThat(result).isSameAs(underTest);

    }

    @Test
    public void testGiveMeDirectionOnLeftRotationForWEST() {
        Consumer<Direction> consumer = mock(Consumer.class);

        CardinalDirection underTest = WEST;
        CardinalDirection result = underTest.giveMeDirectionOnLeftRotation(consumer);

        verify(consumer).accept(SOUTH);
        verifyNoMoreInteractions(consumer);
        assertThat(result).isSameAs(underTest);
    }

    @Test
    public void testGiveMeDirectionOnRightRotationForWEST() {
        Consumer<Direction> consumer = mock(Consumer.class);
        CardinalDirection underTest = WEST;
        CardinalDirection result = underTest.giveMeDirectionOnRightRotation(consumer);

        verify(consumer).accept(NORTH);
        verifyNoMoreInteractions(consumer);
        assertThat(result).isSameAs(underTest);
    }


}
