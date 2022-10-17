import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class l_specification {

    private List<String> reserved_words= Arrays.asList("int","char","while","if","else","else if","scan","print","var","list");
    private List<String> operators= Arrays.asList("+","-","*","/","%","=","==","<=",">=","<",">","!=");
    private List<String>separators=Arrays.asList("(",")","{","}",";","[","]",">","<");

    private final HashMap<String,Integer>codification=new HashMap<>();

    private void createCodification()
    {
        codification.put("identifier",0);

        int code=1;

        for(String word:reserved_words)
        {
            codification.put(word,code);
            code++;
        }

        for(String operator:operators)
        {
            codification.put(operator,code);
            code++;
        }

        for(String separator:separators)
        {
            codification.put(separator,code);
            code++;
        }


    }

    public boolean isReservedWord(String token)
    {
        return reserved_words.contains(token);
    }

    public boolean isOperator(String token)
    {
        return operators.contains(token);
    }

    public boolean isSeparator(String token)
    {
        return separators.contains(token);
    }

    public boolean isPartOfOperator(char op) {
        return op == '!' || isOperator(String.valueOf(op));
    }

    public boolean isIdentifier(String token)
    {
        String pattern = "^[a-zA-Z]([a-z|A-Z|0-9])*$";
        return token.matches(pattern);
    }

    public Integer getCode(String token)
    {
        return codification.get(token);
    }



}
