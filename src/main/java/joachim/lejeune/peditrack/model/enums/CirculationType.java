package joachim.lejeune.peditrack.model.enums;

public enum CirculationType {
    NONE(0, "", "Auncun problème"),
    COLD_FEET(1, "Pieds froids", "Réduction de la circulation, provoquant une sensation de froid."),
    VARICOSITIES(2, "Varicosités", "Petites veines superficielles dilatées."),
    BLUISH_FEET(3, "Pieds bleutés", "Mauvaise oxygénation des pieds."),
    BLUSHES(4, "Rougeurs", "Irritations et rougeurs sur la peau."),
    EDEMA(5, "Œdèmes", "Accumulation de liquide dans les tissus, provoquant un gonflement.");

    private final int id;
    private final String label;
    private final String description;

    CirculationType(int id, String label, String description) {
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

    public static CirculationType fromId(int id) {
        for (CirculationType type : CirculationType.values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        throw new IllegalArgumentException("Circulation type not found for ID: " + id);
    }

    public static CirculationType valueForLabel(String circulationType) {
        for (CirculationType type : CirculationType.values()) {
            if(type.getLabel() == null){
                return CirculationType.NONE;
            }
            if (type.getLabel().equalsIgnoreCase(circulationType)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Circulation type not found for label: " + circulationType);
    }
}
