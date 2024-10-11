package joachim.lejeune.peditrack.model.enums;

public enum FootDeformityType {
    ORTEILS_EN_MARTEAU(1, "Orteils en marteau", "Déformation des orteils qui sont pliés vers le bas."),
    HALLUX_VALGUS(2, "Hallux valgus", "Déformation où le gros orteil est incliné vers les autres orteils."),
    HALLUX_VARUS(3, "Hallux varus", "Déviation du gros orteil vers l'extérieur."),
    QUINTUS_VALGUS(4, "Quintus valgus", "Déviation du petit orteil vers l'intérieur."),
    QUINTUS_VARUS(5, "Quintus varus", "Déviation du petit orteil vers l'extérieur."),
    HALLUX_RIGIDUS(6, "Hallux rigidus", "Rigidité de l'articulation du gros orteil."),
    AUTRES(7, "Autres", "Autres types de déformations du pied.");

    private final int id;
    private final String label;
    private final String description;

    FootDeformityType(int id, String label, String description) {
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

    public static FootDeformityType fromId(int id) {
        for (FootDeformityType type : FootDeformityType.values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        throw new IllegalArgumentException("Foot deformity type not found for ID: " + id);
    }
}
