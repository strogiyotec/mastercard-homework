package com.almas.pivot;

import com.almas.pivot.high.PivotHigh;
import com.almas.pivot.high.PivotHighDTO;
import com.almas.pivot.low.PivotLow;
import com.almas.pivot.low.PivotLowDTO;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Pivots from InputStream.
 */
public final class InputStreamPivots implements Pivots {

    private final List<PivotLow> lowPivots;
    private final List<PivotHigh> highPivots;

    /**
     * This Ctor. doesn't close given stream.
     */
    public InputStreamPivots(final InputStream stream, final int steps, final PivotChecker pivotChecker) throws IOException {
        final String[] lines = new String(stream.readAllBytes()).split("\n");
        final List<PivotLow> lowPivots = new ArrayList<>(lines.length);
        final List<PivotHigh> highPivots = new ArrayList<>(lines.length);
        InputStreamPivots.gatherPivots(lowPivots, highPivots, lines, steps, pivotChecker);
        this.lowPivots = lowPivots;
        this.highPivots = highPivots;
    }

    @Override
    public List<PivotLow> lowPivots() {
        return this.lowPivots;
    }

    @Override
    public List<PivotHigh> highPivots() {
        return this.highPivots;
    }

    private static void gatherPivots(
        final List<PivotLow> lowPivots,
        final List<PivotHigh> highPivots,
        final String[] lines,
        final int steps,
        final PivotChecker pivotChecker
    ) {
        //start from 1 to skip header
        for (int i = 1; i < lines.length; i++) {
            final String line = lines[i];
            if (pivotChecker.isLow(
                lines,
                steps,
                i
            )) {
                lowPivots.add(
                    new PivotLowDTO(
                        Double.parseDouble(lines[i].split(",")[Pivots.PIVOT_LOW_INDEX].replaceAll("[\r\"]",""))
                    )
                );
            }
            if (pivotChecker.isHigh(
                lines,
                steps,
                i
            )) {
                highPivots.add(
                    new PivotHighDTO(line)
                );
            }
        }
    }
}
