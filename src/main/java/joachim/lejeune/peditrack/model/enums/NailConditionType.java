package joachim.lejeune.peditrack.model.enums;

public enum NailConditionType {
    NONE(0, null, "Auncun problème"),
    ONYCOMYCHOSES(1, "Onycomychoses", "Infection fongique des ongles."),
    ONYCHOGRYPHOSES(2, "Onychogryphoses", "Épaississement anormal et déformation des ongles."),
    ONGLES_INCARNES(3, "Ongles incarnés", "Ongles qui pénètrent dans la chair environnante."),
    ONYCHOLYSES(4, "Onycholyses", "Décollement partiel ou total de l'ongle."),
    AUTRES(5, "Autres", "Autres pathologies liées aux ongles.");

    private final int id;
    private final String label;
    private final String description;

    NailConditionType(int id, String label, String description) {
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

    public static NailConditionType fromId(int id) {
        for (NailConditionType type : NailConditionType.values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        throw new IllegalArgumentException("Nail condition type not found for ID: " + id);
    }

    public static NailConditionType valueForCode(String nailConditionType) {
        if(nailConditionType == null){
            return NailConditionType.NONE;
        }
        for (NailConditionType type : NailConditionType.values()) {
            if (type.getLabel().equalsIgnoreCase(nailConditionType)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Nail condition type not found for label: " + nailConditionType);
    }
}
