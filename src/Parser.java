import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public class Parser {
    private static final Map<String, Function<String, ?>> converterMap = Map.of(
            "Integer", Integer::parseInt,
            "Double", Double::parseDouble,
            "String", p -> p
    );

    protected Function<String, ?> converter;

    public Parser(String string) {
        converter = converterMap.get(string);
    }


    public <T extends Comparable<T>> T parse(String string) {
        return (T) converter.apply(string);
    }

    public static String getOptions(){
        return converterMap.keySet().toString();
    }
}
