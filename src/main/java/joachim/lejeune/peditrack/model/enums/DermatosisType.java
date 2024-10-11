package joachim.lejeune.peditrack.model.enums;

public enum DermatosisType {
    ECZEMA(1, "Eczéma", "Inflammation de la peau, souvent accompagnée de démangeaisons."),
    PSORIASIS(2, "Psoriasis", "Affection chronique de la peau causant des plaques rouges."),
    VERRUES(3, "Verrues", "Croissance cutanée causée par un virus."),
    INTERTRIGO_GD(4, "Intertrigo G/D", "Inflammation des plis cutanés causée par l'humidité.");

    private final int id;
    private final String label;
    private final String description;

    DermatosisType(int id, String label, String description) {
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

    public static DermatosisType fromId(int id) {
        for (DermatosisType type : DermatosisType.values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        throw new IllegalArgumentException("Dermatose type not found for ID: " + id);
    }
}
