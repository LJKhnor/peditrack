package joachim.lejeune.peditrack.model.enums.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import joachim.lejeune.peditrack.model.enums.FootType;

import java.io.IOException;

public class FootTypeDeserializer extends JsonDeserializer<FootType> {
    @Override
    public FootType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        String label = jsonParser.getText();
        try{
            return FootType.valueForLabel(label);
        } catch(IllegalArgumentException e){
            return FootType.NONE;
        }
    }
}
