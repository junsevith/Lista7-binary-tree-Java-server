import java.util.LinkedHashMap;
import java.util.function.Function;

public class Parser {
    private final LinkedHashMap<String, Function<String, ?>> converterMap = new LinkedHashMap<>();
    private Function<String, ?> converter;

    public Parser() {
        converterMap.put("integer", Integer::parseInt);
        converterMap.put("double", Double::parseDouble);
        converterMap.put("string", p -> p);
    }

    public boolean setConverter(String string) {
        converter = converterMap.get(string);
        return converter != null;
    }

    public <T extends Comparable<T>> T parse(String string) {
        try {
            return (T) converter.apply(string);
        } catch (Exception e) {
            throw new IllegalArgumentException("Niepoprawny argument dla tej komendy");
        }

    }

    public String getOptions() {
        return converterMap.keySet().toString();
    }

    public String getType(){
        return parse("0").getClass().getName();
    }
}
