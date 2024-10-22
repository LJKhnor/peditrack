package joachim.lejeune.peditrack.model.enums.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import joachim.lejeune.peditrack.model.enums.CirculationType;
import joachim.lejeune.peditrack.model.enums.SweatType;

import java.io.IOException;

public class CirculationTypeDeserializer extends JsonDeserializer<CirculationType> {
    @Override
    public CirculationType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        String label = jsonParser.getText();
        try{
            return CirculationType.valueForLabel(label);
        } catch(IllegalArgumentException e){
            return CirculationType.NONE;
        }
    }
}
