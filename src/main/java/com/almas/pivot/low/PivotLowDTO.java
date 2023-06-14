package com.almas.pivot.low;

public final class PivotLowDTO implements PivotLow {

    private final double low;

    public PivotLowDTO(final double low) {
        this.low = low;
    }

    @Override
    public double low() {
        return this.low;
    }

    @Override
    public String toString() {
        return String.valueOf(this.low);
    }
}
