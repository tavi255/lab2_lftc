public class Main {

    public static void main(String[] args)
    {
        TestSymTable();
    }

    public static void TestSymTable()
    {
        SymbolTable sym=new SymbolTable(17);

        assert (sym.add("abc"));

        assert (!sym.add("abc"));

        assert (sym.add("a"));
        assert (sym.add("b"));
        assert (sym.add("c"));

        Pair<Integer,Integer>p=new Pair<>(-1,-1);
        Pair<Integer,Integer>k=sym.getPosition("a");
        assert(p.getKey()!=k.getKey() && p.getValue()!=k.getValue());

        k=sym.getPosition("d");

        assert(p.getKey()==k.getKey() && p.getValue()==k.getValue());


        System.out.println(sym);


    }
}
