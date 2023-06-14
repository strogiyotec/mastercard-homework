package com.almas.pivot;

public final class DefaultPivotChecker implements PivotChecker {

    @Override
    public boolean isLow(final String[] lines, final int steps, final int currentIndex) {
        final double currentLow = getValue(lines[currentIndex], Pivots.PIVOT_LOW_INDEX);
        for (
            int indexDown = currentIndex - 1, indexUp = currentIndex + 1;
            indexDown > currentIndex - steps || indexUp < currentIndex + steps;
            indexDown--, indexUp++
        ) {
            if (indexDown > 0) {
                final double lowDown = getValue(lines[indexDown], Pivots.PIVOT_LOW_INDEX);
                if (lowDown < currentLow) {
                    return false;
                }
            }
            if (indexUp < lines.length) {
                final double lowUp = getValue(lines[indexUp], Pivots.PIVOT_LOW_INDEX);
                if (lowUp < currentLow) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean isHigh(final String[] lines, final int steps, final int currentIndex) {
        final double currentHigh = getValue(lines[currentIndex], Pivots.PIVOT_LOW_INDEX);
        for (
            int indexDown = currentIndex - 1, indexUp = currentIndex + 1;
            indexDown > currentIndex - steps || indexUp < currentIndex + steps;
            indexDown--, indexUp++
        ) {
            if (indexDown > 0) {
                final double highDown = getValue(lines[indexDown], Pivots.PIVOT_LOW_INDEX);
                if (highDown > currentHigh) {
                    return false;
                }
            }
            if (indexUp < lines.length) {
                final double highUp = getValue(lines[indexUp], Pivots.PIVOT_LOW_INDEX);
                if (highUp > currentHigh) {
                    return false;
                }
            }
        }
        return true;
    }

    private static double getValue(final String line, int index) {
        return Double.parseDouble(line.split(",")[index].replace("\"", ""));
    }
}
