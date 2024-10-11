package joachim.lejeune.peditrack.model.enums;

public enum SkinType {
    NORMAL(1, "Normale", "La peau est dans un état normal, sans particularité."),
    PALE(2, "Pale", "La peau est pâle, probablement en raison d'une faible circulation sanguine."),
    CYANOTIC(3, "Cyanosée", "La peau présente une coloration bleutée, indiquant un manque d'oxygénation."),
    DRY(4, "Sèche", "La peau est sèche, indiquant un manque d'hydratation."),
    OILY(5, "Grasse", "La peau est grasse, avec un excès de sébum.");

    private final int id;
    private final String label;
    private final String description;

    // Constructeur
    SkinType(int id, String label, String description) {
        this.id = id;
        this.label = label;
        this.description = description;
    }

    // Méthode pour obtenir l'ID
    public int getId() {
        return id;
    }

    // Méthode pour obtenir l'étiquette (label)
    public String getLabel() {
        return label;
    }

    // Méthode pour obtenir la description
    public String getDescription() {
        return description;
    }

    // Méthode personnalisée (exemple)
    public static SkinType fromLabel(String label) {
        for (SkinType condition : SkinType.values()) {
            if (condition.getLabel().equalsIgnoreCase(label)) {
                return condition;
            }
        }
        throw new IllegalArgumentException("Condition de peau inconnue : " + label);
    }
}
