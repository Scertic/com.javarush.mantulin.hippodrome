import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    @Disabled
    void mainUnderTimeout() {
        assertTimeout(Duration.of(20, ChronoUnit.SECONDS),
                () -> Main.main(new String[2]));
    }
}