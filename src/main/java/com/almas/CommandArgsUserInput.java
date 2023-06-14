package com.almas;

public final class CommandArgsUserInput implements UserInput {

    private final String pathToFile;

    private final int steps;

    public CommandArgsUserInput(final String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("The usage is: java -jar homework.jar path_to_csv_file steps");
        }
        this.pathToFile = args[0];
        this.steps = Integer.parseInt(args[1]);
    }

    @Override
    public String pathToFile() {
        return this.pathToFile;
    }

    @Override
    public int steps() {
        return this.steps;
    }
}
