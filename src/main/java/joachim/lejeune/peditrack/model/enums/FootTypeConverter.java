package joachim.lejeune.peditrack.model.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class FootTypeConverter implements AttributeConverter<FootType,String> {
    @Override
    public String convertToDatabaseColumn(FootType footType) {
        return footType != null ? footType.getLabel() : null;
    }

    @Override
    public FootType convertToEntityAttribute(String s) {
        if(s == null){
            return null;
        }
        try{
            return FootType.valueOf(s);
        } catch (IllegalArgumentException e ){
            throw new RuntimeException("Invalid value for FootType enum: " + s, e);
        }
    }
}
