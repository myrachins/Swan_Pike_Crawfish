import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class InterfaceTests {
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;

    @BeforeAll
    public static void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterAll
    public static void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void validInputTests() {
        Assertions.assertDoesNotThrow(() -> Main.main(new String[]{"1", "1"}));
        Assertions.assertDoesNotThrow(() -> Main.main(new String[0]));
        Assertions.assertDoesNotThrow(() -> Main.main(new String[]{"1.111", "2.222"}));
        Assertions.assertDoesNotThrow(() -> Main.main(new String[]{"-i", "0", "2.222"}));
    }

    @Test
    public void invalidInputTests() throws InterruptedException {
        Main.main(null);
        Assertions.assertTrue(outContent.toString().contains("Program can not continue"));

        Main.main(new String[] {"1"});
        Assertions.assertTrue(outContent.toString().contains("Program can not continue"));

        Main.main(new String[] {"a", "b"});
        Assertions.assertTrue(outContent.toString().contains("Program can not continue"));
    }
}
