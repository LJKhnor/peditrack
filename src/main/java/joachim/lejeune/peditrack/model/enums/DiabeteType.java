package joachim.lejeune.peditrack.model.enums;

public enum DiabeteType {
    NONE(0),
    ONE(1),
    TWO(2);

    private final int value;

    DiabeteType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
