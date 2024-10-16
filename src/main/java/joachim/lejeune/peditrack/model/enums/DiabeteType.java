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

    public static DiabeteType valueForCode(int id) {
        for (DiabeteType type : DiabeteType.values()) {
            if (type.getValue() == id) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid DiabeteType id: " + id);
    }
}
