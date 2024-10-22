package joachim.lejeune.peditrack.model.enums.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import joachim.lejeune.peditrack.model.enums.NailConditionType;
import joachim.lejeune.peditrack.model.enums.SweatType;

import java.io.IOException;

public class NailConditionTypeDeserializer extends JsonDeserializer<NailConditionType> {
    @Override
    public NailConditionType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        String label = jsonParser.getText();
        try{
            return NailConditionType.valueForLabel(label);
        } catch(IllegalArgumentException e){
            return NailConditionType.NONE;
        }
    }
}
