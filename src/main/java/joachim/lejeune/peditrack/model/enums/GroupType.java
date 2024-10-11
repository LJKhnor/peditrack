package joachim.lejeune.peditrack.model.enums;

public enum GroupType {
    NONE(0),
    LOW_RISK(1),
    MEDIUM_RISK(2),
    HIGH_RISK(3),
    CRITICAL(4);

    private final int value;

    GroupType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
