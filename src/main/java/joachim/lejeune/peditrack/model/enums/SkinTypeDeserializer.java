package joachim.lejeune.peditrack.model.enums;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class SkinTypeDeserializer extends JsonDeserializer<SkinType> {
    private static final Logger LOG = LoggerFactory.getLogger(SkinTypeDeserializer.class);
    @Override
    public SkinType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        LOG.warn("Enter skintypeDeserializer");
        String label = jsonParser.getText();
        try{
            return SkinType.valueForLabel(label);
        } catch(IllegalArgumentException e){
            return SkinType.NONE;
        }
    }
}
