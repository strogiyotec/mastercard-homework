package com.almas;

import com.almas.pivot.DefaultPivotChecker;
import com.almas.pivot.InputStreamPivots;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Main entry point.
 *
 * @author Almas Abdrazak (almas337519@gmail.com)
 */
public final class App {

    public static void main(final String[] args) throws IOException {
        final CommandArgsUserInput cliArgs = new CommandArgsUserInput(args);
        try (final InputStream fileInputStream = App.getFileInputStream(cliArgs.pathToFile())) {
            final InputStreamPivots inputStreamPivots = new InputStreamPivots(
                fileInputStream,
                cliArgs.steps(),
                new DefaultPivotChecker()
            );
            System.out.println("Pivot lows:");
            System.out.println(inputStreamPivots.lowPivots());
            System.out.println("Pivot highs:");
            System.out.println(inputStreamPivots.highPivots());
        }
    }

    private static FileInputStream getFileInputStream(final String pathToFile) {
        if (!pathToFile.endsWith(".csv")) {
            throw new IllegalArgumentException(
                String.format("File %s must be a csv", pathToFile)
            );
        }
        try {
            return new FileInputStream(pathToFile);
        } catch (final FileNotFoundException exception) {
            throw new IllegalArgumentException(
                String.format(
                    "Can't file a file %s",
                    pathToFile
                ),
                exception
            );
        }
    }

}
