package joachim.lejeune.peditrack.model.enums;

public enum SweatType {
    HYPERHIDROSIS(1, "Hyperhidrose", "Transpiration excessive."),
    ANHYDROSIS(2, "Anidrose", "Absence de transpiration."),
    BROMIDROSE(3, "Bromidrose", "Transpiration accompagnée d'odeurs désagréables."),
    NORMAL(4, "Normale", "Transpiration normale.");

    private final int id;
    private final String label;
    private final String description;

    SweatType(int id, String label, String description) {
        this.id = id;
        this.label = label;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public String getDescription() {
        return description;
    }

    public static SweatType fromId(int id) {
        for (SweatType type : SweatType.values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        throw new IllegalArgumentException("Transpiration type not found for ID: " + id);
    }
}
