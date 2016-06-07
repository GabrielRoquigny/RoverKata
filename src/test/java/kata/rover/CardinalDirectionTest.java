package kata.rover;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.function.Consumer;

import static kata.rover.CardinalDirection.EAST;
import static kata.rover.CardinalDirection.NORTH;
import static kata.rover.CardinalDirection.SOUTH;
import static kata.rover.CardinalDirection.WEST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class CardinalDirectionTest {

    @Mock
    private CanChangeDirection canChangeDirection;
    @Mock
    private Consumer<Vector> vectorConsumer;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGiveMeDirectionOnLeftRotationForNORTH() {
        CardinalDirection underTest = NORTH;
        CardinalDirection result = underTest.giveMeDirectionOnLeftRotation(canChangeDirection);

        verify(canChangeDirection).rotateTo(WEST);
        verifyNoMoreInteractions(canChangeDirection);
        assertThat(result).isSameAs(underTest);
    }

    @Test
    public void testGiveMeDirectionOnRightRotationForNORTH() {
        CardinalDirection underTest = NORTH;
        CardinalDirection result = underTest.giveMeDirectionOnRightRotation(canChangeDirection);

        verify(canChangeDirection).rotateTo(EAST);
        verifyNoMoreInteractions(canChangeDirection);
        assertThat(result).isSameAs(underTest);
    }

    @Test
    public void testGiveMeDirectionOnLeftRotationForSOUTH() {
        CardinalDirection underTest = SOUTH;
        CardinalDirection result = underTest.giveMeDirectionOnLeftRotation(canChangeDirection);

        verify(canChangeDirection).rotateTo(EAST);
        verifyNoMoreInteractions(canChangeDirection);
        assertThat(result).isSameAs(underTest);

    }

    @Test
    public void testGiveMeDirectionOnRightRotationForSOUTH() {
        CardinalDirection underTest = SOUTH;
        CardinalDirection result = underTest.giveMeDirectionOnRightRotation(canChangeDirection);

        verify(canChangeDirection).rotateTo(WEST);
        verifyNoMoreInteractions(canChangeDirection);
        assertThat(result).isSameAs(underTest);

    }

    @Test
    public void testGiveMeDirectionOnLeftRotationForEAST() {
        CardinalDirection underTest = EAST;
        CardinalDirection result = underTest.giveMeDirectionOnLeftRotation(canChangeDirection);

        verify(canChangeDirection).rotateTo(NORTH);
        verifyNoMoreInteractions(canChangeDirection);
        assertThat(result).isSameAs(underTest);
    }

    @Test
    public void testGiveMeDirectionOnRightRotationForEAST() {
        CardinalDirection underTest = EAST;
        CardinalDirection result = underTest.giveMeDirectionOnRightRotation(canChangeDirection);

        verify(canChangeDirection).rotateTo(SOUTH);
        verifyNoMoreInteractions(canChangeDirection);
        assertThat(result).isSameAs(underTest);

    }

    @Test
    public void testGiveMeDirectionOnLeftRotationForWEST() {
        CardinalDirection underTest = WEST;
        CardinalDirection result = underTest.giveMeDirectionOnLeftRotation(canChangeDirection);

        verify(canChangeDirection).rotateTo(SOUTH);
        verifyNoMoreInteractions(canChangeDirection);
        assertThat(result).isSameAs(underTest);
    }

    @Test
    public void testGiveMeDirectionOnRightRotationForWEST() {
        CardinalDirection underTest = WEST;
        CardinalDirection result = underTest.giveMeDirectionOnRightRotation(canChangeDirection);

        verify(canChangeDirection).rotateTo(NORTH);
        verifyNoMoreInteractions(canChangeDirection);
        assertThat(result).isSameAs(underTest);
    }

    @Test
    public void testGiveMeVectorForwardForNORTH() {
        CardinalDirection underTest = NORTH;
        CardinalDirection result = underTest.giveMeVectorForward(vectorConsumer);

        verify(vectorConsumer).accept(new Vector(0, 1));
        verifyNoMoreInteractions(canChangeDirection);
        assertThat(result).isSameAs(underTest);
    }

    @Test
    public void testGiveMeVectorForwardForSOUTH() {
        CardinalDirection underTest = SOUTH;
        CardinalDirection result = underTest.giveMeVectorForward(vectorConsumer);

        verify(vectorConsumer).accept(new Vector(0, -1));
        verifyNoMoreInteractions(canChangeDirection);
        assertThat(result).isSameAs(underTest);
    }

    @Test
    public void testGiveMeVectorForwardForWEST() {
        CardinalDirection underTest = WEST;
        CardinalDirection result = underTest.giveMeVectorForward(vectorConsumer);

        verify(vectorConsumer).accept(new Vector(-1, 0));
        verifyNoMoreInteractions(canChangeDirection);
        assertThat(result).isSameAs(underTest);
    }

    @Test
    public void testGiveMeVectorForwardForEAST() {
        CardinalDirection underTest = EAST;
        CardinalDirection result = underTest.giveMeVectorForward(vectorConsumer);

        verify(vectorConsumer).accept(new Vector(1, 0));
        verifyNoMoreInteractions(canChangeDirection);
        assertThat(result).isSameAs(underTest);
    }
}
