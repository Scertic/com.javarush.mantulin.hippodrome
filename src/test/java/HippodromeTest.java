import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {
    Hippodrome hippodrome;
    private static final Logger logger = LoggerFactory.getLogger(Hippodrome.class);
    private static final Logger logger2 = LoggerFactory.getLogger(Horse.class);
    static {
        Configurator.setLevel(logger.getName(), Level.OFF);
        Configurator.setLevel(logger2.getName(), Level.OFF);
    }

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

    @Test
    void getWinner() {
        Horse horse1 = new Horse("Horse1", 10, 10);
        Horse horse2 = new Horse("Horse2", 10, 100);
        hippodrome = new Hippodrome(List.of(horse1, horse2));
        assertEquals(horse2, hippodrome.getWinner());
    }

}
