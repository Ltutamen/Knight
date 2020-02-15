package ua.axiom.model.wearable;

public enum Test implements Wearable {
    AA1("afd", 55),
    AA2("TR", 332),
    AA5("TS", 673);

    private String string;
    private Integer integer;

    Test(String afd, int i) {
        this.string = afd;
        this.integer = i;
    }
}
