import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class MScanner {

    private List<String> reservedWords= Arrays.asList("int",
            "list","char","if","else","else if","start","var","scan","print");

    private List<String>operators= Arrays.asList("+","-",
            "*","/","%","=","==","!=","<",">");
    private List<String>separators=Arrays.asList("{","}","(",")",";","[","]"," ","<",">",",");

    private SymbolTable sym=new SymbolTable(17);

    private final HashMap<String,Pair<Integer,Integer>>pInternalForm=new HashMap<>();

    private int curly_brackets=0;
    private int angle_brackets=0;
    private int square_brackets=0;
    private int parenthesses=0;



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

    public boolean detect_Tokens(String line)
    {

        String []vars=line.split(" ");
        for(String value:vars)
        {
            int i=0;
            while(i<value.length())
            {
                StringBuilder sb=new StringBuilder();
                while(i<value.length() && !operators.contains(String.valueOf(value.charAt(i))) && !separators.contains(String.valueOf(value.charAt(i))))
                {
                    sb.append(value.charAt(i));
                    i++;
                }

                String v=sb.toString();
                if(v.length()>0 && reservedWords.contains(v))
                    pInternalForm.put(v,new Pair<>(0,0));
                else if(v.length()>0 && (isIdentifier(v)|| isConstant(v)))
                {
                    sym.add(v);
                    Pair<Integer,Integer> poz=sym.getPosition(v);
                    pInternalForm.put(v,poz);
                }

                else if(v.length()>0)
                    return false;

                while(i<value.length() && value.charAt(i)==';')
                {
                    pInternalForm.put(String.valueOf(value.charAt(i)),new Pair<>(0,0));
                    i++;
                }

                if(i<value.length() && value.charAt(i)=='(')
                {
                    pInternalForm.put(String.valueOf(value.charAt(i)),new Pair<>(0,0));
                    parenthesses++;
                    i++;
                }

                else if(i<value.length() && value.charAt(i)==')')
                {
                    pInternalForm.put(String.valueOf(value.charAt(i)),new Pair<>(0,0));
                    parenthesses--;
                    if(parenthesses<0)
                        return false;
                    i++;
                }

                else if(i<value.length() && value.charAt(i)=='{')
                {
                    pInternalForm.put(String.valueOf(value.charAt(i)),new Pair<>(0,0));
                    curly_brackets++;
                    i++;
                }

                else if(i<value.length() && value.charAt(i)=='}')
                {
                    pInternalForm.put(String.valueOf(value.charAt(i)),new Pair<>(0,0));
                    curly_brackets--;
                    if(curly_brackets<0)
                        return false;

                    i++;
                }

                else if(i<value.length() && value.charAt(i)=='[')
                {
                    pInternalForm.put(String.valueOf(value.charAt(i)),new Pair<>(0,0));
                    square_brackets++;
                    i++;
                }

                else if(i<value.length() && value.charAt(i)==']')
                {
                    pInternalForm.put(String.valueOf(value.charAt(i)),new Pair<>(0,0));
                    square_brackets--;
                    if(square_brackets<0)
                        return false;
                    i++;
                }

                else if(i<value.length() && value.charAt(i)=='<')
                {
                    pInternalForm.put(String.valueOf(value.charAt(i)),new Pair<>(0,0));
                    angle_brackets++;
                    i++;
                }

                else if(i<value.length() && value.charAt(i)=='>')
                {
                    pInternalForm.put(String.valueOf(value.charAt(i)),new Pair<>(0,0));
                    angle_brackets--;
                    if(angle_brackets<0)
                        return false;
                    i++;
                }

                else if(i<value.length() && operators.contains(String.valueOf(value.charAt(i))))
                {
                    StringBuilder sb2=new StringBuilder();
                    while(i<value.length() && operators.contains(String.valueOf(value.charAt(i))))
                    {
                        sb2.append(value.charAt(i));
                        i++;
                    }

                    if(!operators.contains(sb2.toString()))
                        return false;
                    else
                        pInternalForm.put(sb2.toString(),new Pair<>(0,0));

                } else if (i<value.length()) {

                    return false;
                }

            }


        }

        return true;

    }


    public void scan(String filename)
    {

        boolean ok=true;

        try {

            Scanner scanner= new Scanner(new File(filename));
            int line=0;
            while (scanner.hasNextLine() && ok) {
                String data = scanner.nextLine();
                line++;

                if(!detect_Tokens(data))
                {
                    System.out.println("lexical error line: " + line);
                    ok=false;
                }

            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        if(ok)
        {
            System.out.println("lexically correct");
            try
            {
                FileWriter st=new FileWriter("D:\\lftc lab\\lab2_lftc\\lab2\\src\\test_files\\ST.out");
                FileWriter pif=new FileWriter("D:\\lftc lab\\lab2_lftc\\lab2\\src\\test_files\\PIF.out");

                StringBuilder result = new StringBuilder();
                for (Map.Entry<String, Pair<Integer, Integer>> pair : pInternalForm.entrySet()) {
                    result.append(pair.getKey()).append(" -> (").append(pair.getValue().getKey()).append(", ").append(pair.getValue().getValue()).append(")\n");
                }

                pif.write(result.toString());
                st.write(sym.toString());
                st.close();
                pif.close();

            }

            catch (IOException ex)
            {
                System.out.println(ex.getMessage());
            }


        }

    }


}
