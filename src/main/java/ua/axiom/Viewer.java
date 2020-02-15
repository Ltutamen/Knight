package ua.axiom;

import java.io.PrintStream;

public class Viewer {
    private PrintStream printStream;

    public Viewer(PrintStream printStream) {
        this.printStream = printStream;
    }

    public Viewer() {
        this(System.out);
    }

    public void print(String s) {
        printStream.println(s);
    }
}
