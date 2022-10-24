import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MScanner {

    private List<String> reservedWords= Arrays.asList("int",
            "list","char","if","else","else if","start","var","scan","print");

    private List<String>operators= Arrays.asList("+","-",
            "*","/","%","=","==","!=","<",">");
    private List<String>separators=Arrays.asList("{","}","(",")",";","[","]"," ","<",">",",");

    private SymbolTable sym=new SymbolTable(17);

    private final HashMap<String,Integer>pInternalForm=new HashMap<>();

    public boolean isIdentifier(String token) {
        String pattern = "^[a-zA-Z]([a-z|A-Z|0-9|_])*$";
        return token.matches(pattern);
    }

    public boolean isConstant(String token) {
        String numericPattern = "^0|[+|-][1-9]([0-9])*|[1-9]([0-9])*|[+|-][1-9]([0-9])*\\.([0-9])*|[1-9]([0-9])*\\.([0-9])*$";
        String charPattern = "^\'[a-zA-Z0-9_?!#*./%+=<>;)(}{ ]\'";
        String stringPattern = "^\"[a-zA-Z0-9_?!#*./%+=<>;)(}{ ]+\"";
        return token.matches(numericPattern) ||
                token.matches(charPattern) ||
                token.matches(stringPattern);

    }

    public void detect_Tokens(String line)
    {
        String []vals=line.split(" ");


    }


    public void scan(String filename)
    {


        try {

            Scanner scanner= new Scanner(new File(filename));
            int line=0;
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                line++;



                System.out.println(data);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }





}
