package com.almas.pivot.high;

public final class PivotHighDTO implements PivotHigh {

    private final String date;

    private final String close;

    private final String volume;

    private final double open;

    private final double high;

    private final double low;

    public PivotHighDTO(
        final String line
    ) {
        final String[] split = line.split(",");
        this.date = split[0];
        this.close = split[1];
        this.volume = split[2];
        this.open = Double.parseDouble(split[3].replace("\"", ""));
        this.high = Double.parseDouble(split[4].replace("\"", ""));
        this.low = Double.parseDouble(split[5].replace("\"", ""));
    }

    @Override
    public String date() {
        return this.date;
    }

    @Override
    public String close() {
        return this.close;
    }

    @Override
    public String volume() {
        return this.volume;
    }

    @Override
    public double open() {
        return this.open;
    }

    @Override
    public double high() {
        return this.high;
    }

    @Override
    public double low() {
        return this.low;
    }

    @Override
    public String toString() {
        return String.format(
            "%s,%s,%s,%,.4f,%,.4f,%,.4f",
            this.date,
            this.close,
            this.volume,
            this.open,
            this.high,
            this.low
        );
    }
}
