package infos;

import java.io.Console;

public class ConsoleColors {

    static Console console = System.console();

    //Colors for console
    // Declaring ANSI_RESET so that we can reset the color
    public static final String ANSI_RESET = (console == null) ? "\u001B[0m" : "";

    // Declaring the colors
    // Custom declaration
    public static final String ANSI_YELLOW = (console == null) ? "\u001B[33m" : "";
    // Custom declaration
    public static final String ANSI_RED = (console == null) ? "\u001B[31m" : "";
    // Custom declaration
    public static final String ANSI_GREEN = (console == null) ? "\u001B[32m" : "";

    public static final String ANSI_BLUE = (console == null) ? "\u001B[34m" : "";

}
