package ClientServer;

import java.io.Serializable;

public class Fireball implements Serializable {
    private final String text;

    public Fireball(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
