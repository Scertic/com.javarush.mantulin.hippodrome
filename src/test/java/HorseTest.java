import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {

    public static final String[] ILLEGAL_NAMES =
            {"", " ", "\t", "\r", "\n", "\f"};

    @Test
    void constructorWhenFirstParamNullThenThrowIAE() {
        assertThrows(IllegalArgumentException.class,
                () -> new Horse(null, 10.0));
    }

    @Test
    void constructorWhenFirstParamNullThenThrowIAEHasMessage() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(null, 10.0)
        );
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    static Stream<String> provideIllegalNames() {
        return Arrays.stream(ILLEGAL_NAMES);
    }

    @ParameterizedTest
    @MethodSource("provideIllegalNames")
    void constructorWhenFirstParamIllegalStringThenThrowIAE(String firstParam) {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(firstParam, 10.0)
        );
    }

    @ParameterizedTest
    @MethodSource("provideIllegalNames")
    void constructorWhenFirstParamIllegalStringThenThrowIAEHasMessage(String firstParam) {
        Throwable throwable = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(firstParam, 10.0)
        );
        assertEquals("Name cannot be blank.", throwable.getMessage());
    }

    @Test
    void constructorWhenSecondParamNegativeNumberThenThrowIAE() {
        assertThrows(IllegalArgumentException.class,
                () -> new Horse("Horse", -1));
    }

    @Test
    void constructorWhenSecondParamNegativeThenThrowIAEHasMessage() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("Horse", -1)
        );
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void constructorWhenThirdParamNegativeNumberThenThrowIAE() {
        assertThrows(IllegalArgumentException.class,
                () -> new Horse("Horse", 10, -1));
    }

    @Test
    void constructorWhenThirdParamNegativeThenThrowIAEHasMessage() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse("Horse", 10, -1)
        );
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void getName() {
        Horse horse = new Horse("Horse", 10);
        assertEquals("Horse", horse.getName());
    }

    @Test
    void getSpeed() {
        Horse horse = new Horse("Horse", 10);
        assertEquals(10.0, horse.getSpeed());
    }

    @Test
    void getDistanceAllArgsConstructor() {
        assertEquals(100,
                new Horse("Horse", 10, 100)
                        .getDistance()
        );
    }

    @Test
    void getDistanceTwoArgsConstructor() {
        assertEquals(0,
                new Horse("Horse", 10)
                        .getDistance()
        );
    }

    @Test
    void moveUseGetRandomDoubleWithFixParams0209() {
        Horse horse = new Horse("Horse", 10);
        try (MockedStatic<Horse> randomMockedStatic =
                     Mockito.mockStatic(Horse.class)) {
            horse.move();
            randomMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @Test
    void moveFormula() {
        Horse horse = new Horse("Horse", 10, 100);
        try (MockedStatic<Horse> randomMockedStatic =
                     Mockito.mockStatic(Horse.class)) {
            randomMockedStatic
                    .when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);
            double newDistance = 100 + 10 * Horse.getRandomDouble(0.2,0.9);
            horse.move();
            assertEquals(newDistance, horse.getDistance());
        }
    }

    @Test
    void getRandomDoubleBetweenParams() {
        double a = 0.1;
        double b = 0.5;
        double randomDouble = Horse.getRandomDouble(0.1, 0.5);
        assertTrue(randomDouble >= a && randomDouble < b);
    }

}