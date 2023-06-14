package com.almas.pivot;

public interface PivotChecker {

    boolean isLow(String[] lines, int steps, int currentIndex);

    boolean isHigh(String[] lines, int steps, int currentIndex);

}
