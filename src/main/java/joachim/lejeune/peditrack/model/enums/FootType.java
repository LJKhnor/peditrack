package joachim.lejeune.peditrack.model.enums;

public enum FootType {
    NONE(0, null, "Auncun problème"),
    HOLLOW(1, "Creux", "Pied avec une arche très élevée."),
    FLAT(2, "Plat", "Pied avec une arche basse ou inexistante."),
    GREEK(3, "Grec", "Deuxième orteil plus long que le gros orteil."),
    EGYPTIAN(4, "Egyptien", "Gros orteil plus long que les autres orteils."),
    SQUARE(5, "Carré", "Tous les orteils sont presque de la même longueur."),
    NORMAL(6, "Normal", "Arche normale du pied.");

    private final int id;
    private final String label;
    private final String description;

    FootType(int id, String label, String description) {
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

    public static FootType fromId(int id) {
        for (FootType type : FootType.values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        throw new IllegalArgumentException("Foot type not found for ID: " + id);
    }

    public static FootType valueForCode(String footType) {
        for (FootType type : FootType.values()) {
            if(type.getLabel() != null){
                if (type.getLabel().equalsIgnoreCase(footType)) {
                    return type;
                }
            }
        }
        throw new IllegalArgumentException("Foot type not found for label: " + footType);
    }
//    public static String codeForValue(FootType footType){
//        for(FootType type: FootType.values()){
//            if(type == footType){
//                return type.getLabel();
//            }
//        }
//        throw new IllegalArgumentException(("Invalid foot type enum : " + footType));
//    }
}
