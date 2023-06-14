package com.almas.pivot;

import com.almas.pivot.high.PivotHigh;
import com.almas.pivot.low.PivotLow;
import java.util.List;

/**
 * Represents a list of pivots.
 */
public interface Pivots {

    int PIVOT_LOW_INDEX = 5;

    int PIVOT_HIGH_INDEX = 4;

    List<PivotLow> lowPivots();

    List<PivotHigh> highPivots();

}
