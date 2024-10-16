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
    public static GroupType valueForCode(int id){
        for (GroupType type : GroupType.values()) {
            if (type.getValue() == id) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid GroupType id: " + id);
    }

    public static int codeForValue(GroupType groupType){
        for(GroupType type: GroupType.values()){
            if(type == groupType){
                return type.getValue();
            }
        }
        throw new IllegalArgumentException(("Invalid group type enum : " + groupType));
    }
}
