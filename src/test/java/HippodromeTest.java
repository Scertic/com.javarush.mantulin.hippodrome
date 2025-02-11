import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {
    Hippodrome hippodrome;

    @Test
    void constructorParamNullThenIAE() {
        assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(null));
    }

    @Test
    void constructorParamNullThenIAEHasMessage() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(null)
        );
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void constructorParamEmptyListThenIAE() {
        assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(List.of()));
    }

    @Test
    void constructorParamEmptyListThenIAEHasMessage() {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(List.of())
        );
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void getHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("Horse-"+i, 10, 100));
        }
        hippodrome = new Hippodrome(horses);
        List<Horse> hippodromeHorses = hippodrome.getHorses();
        assertIterableEquals(horses, hippodromeHorses);
    }

    @Test
    void move() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Horse horse = Mockito.mock(Horse.class);
            horses.add(horse);
        }
        hippodrome = new Hippodrome(horses);
        hippodrome.move();
        for (Horse horse : horses) {
            Mockito.verify(horse).move();
        }
    }

}
