
package TheCookBook2.converters;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

public class RecipeLinkedListConverter implements DynamoDBTypeConverter<String, List> {

    private static final Gson GSON = new Gson();
    private final Logger log = LogManager.getLogger();

    @Override
    public String convert(List listToBeConverted) {
        return GSON.toJson(listToBeConverted);
    }

    @Override
    public List unconvert(String dynamoDBRepresentation) {
        return GSON.fromJson(dynamoDBRepresentation, new TypeToken<LinkedList<String>>() { } .getType());
    }
}
