package joachim.lejeune.peditrack.model.enums;

public enum RemarkType {
    CORS(1, "Cors", "Épaississement douloureux de la peau sur les zones de pression."),
    DURILLONS(2, "Durillons", "Épaississement moins douloureux de la peau, souvent sur la plante des pieds."),
    CREVASSES(3, "Crevasses", "Fissures dans la peau causées par la sécheresse."),
    PHLYCTENES(4, "Phlyctènes", "Ampoules résultant de frottements.");

    private final int id;
    private final String label;
    private final String description;

    RemarkType(int id, String label, String description) {
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

    public static RemarkType fromId(int id) {
        for (RemarkType type : RemarkType.values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        throw new IllegalArgumentException("Comment type not found for ID: " + id);
    }
}
