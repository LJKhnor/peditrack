package joachim.lejeune.peditrack.model.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SkinTypeConverter implements AttributeConverter<SkinType, String> {
    @Override
    public String convertToDatabaseColumn(SkinType skinType) {
        return skinType != null ? skinType.getLabel() : null;
    }

    @Override
    public SkinType convertToEntityAttribute(String s) {
        if(s == null){
            return null;
        }
        try{
            return SkinType.valueOf(s);
        }catch (IllegalArgumentException e){
            throw new RuntimeException("Invalid value for Skintype enum : " + s, e);
        }
    }
}
