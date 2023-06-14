package com.almas.pivot;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public final class InputStreamPivotsTestCase {

    @Test
    void testTeslaCSV() throws IOException {
        try (var fileStream = new FileInputStream(this.getClass().getResource("/tesla.csv").getFile())) {
            final InputStreamPivots inputStreamPivots = new InputStreamPivots(
                fileStream,
                2,
                new DefaultPivotChecker()
            );
            //check low
            Assertions.assertEquals(171, inputStreamPivots.lowPivots().size());
            Assertions.assertEquals(175, inputStreamPivots.highPivots().size());
        }
    }

    @DisplayName("Test that high pivot point is on top")
    @Test
    void testHighPivotIsOnTop() throws IOException {
        final String file =
            "\"date\",\"close\",\"volume\",\"open\",\"high\",\"low\"\n" +
                "\"11:34\",\"270.49\",\"4,787,699\",\"264.50\",\"273.88\",\"262.24\"\n" +
                "\"2018/10/15\",\"259.5900\",\"6189026.0000\",\"259.0600\",\"263.2800\",\"254.5367\"\n";
        try (var fileStream = new ByteArrayInputStream(file.getBytes(StandardCharsets.UTF_8))) {
            final InputStreamPivots inputStreamPivots = new InputStreamPivots(
                fileStream,
                2,
                new DefaultPivotChecker()
            );
            Assertions.assertEquals(1, inputStreamPivots.highPivots().size());
            Assertions.assertEquals("\"11:34\"", inputStreamPivots.highPivots().get(0).date());

        }
    }

    @DisplayName("Test that high pivot point is in bottom")
    @Test
    void testHighPivotIsInBottom() throws IOException {
        final String file =
            "\"date\",\"close\",\"volume\",\"open\",\"high\",\"low\"\n" +
                "\"11:34\",\"270.49\",\"4,787,699\",\"264.50\",\"263.88\",\"262.24\"\n" +
                "\"2018/10/15\",\"259.5900\",\"6189026.0000\",\"259.0600\",\"283.2800\",\"254.5367\"\n";
        try (var fileStream = new ByteArrayInputStream(file.getBytes(StandardCharsets.UTF_8))) {
            final InputStreamPivots inputStreamPivots = new InputStreamPivots(
                fileStream,
                2,
                new DefaultPivotChecker()
            );
            Assertions.assertEquals(1, inputStreamPivots.highPivots().size());
            Assertions.assertEquals("\"2018/10/15\"", inputStreamPivots.highPivots().get(0).date());
        }
    }
    @DisplayName("Test that if step is bigger than file length it won'break")
    @Test
    void testStepIsBiggerThanFile() throws IOException {
        final String file =
            "\"date\",\"close\",\"volume\",\"open\",\"high\",\"low\"\n" +
                "\"11:34\",\"270.49\",\"4,787,699\",\"264.50\",\"263.88\",\"262.24\"\n" +
                "\"2018/10/15\",\"259.5900\",\"6189026.0000\",\"259.0600\",\"283.2800\",\"254.5367\"\n";
        try (var fileStream = new ByteArrayInputStream(file.getBytes(StandardCharsets.UTF_8))) {
            final InputStreamPivots inputStreamPivots = new InputStreamPivots(
                fileStream,
                Integer.MAX_VALUE,
                new DefaultPivotChecker()
            );
            Assertions.assertEquals(1, inputStreamPivots.highPivots().size());
            Assertions.assertEquals("\"2018/10/15\"", inputStreamPivots.highPivots().get(0).date());
        }
    }
}
