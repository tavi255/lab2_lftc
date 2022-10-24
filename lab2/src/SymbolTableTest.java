import static org.junit.jupiter.api.Assertions.*;

class SymbolTableTest {

    @org.junit.jupiter.api.Test
    void getSize() {

        SymbolTable sm=new SymbolTable(17);
        assertEquals(sm.getSize(),17);

    }

    @org.junit.jupiter.api.Test
    void add() {

        SymbolTable sym=new SymbolTable(17);

        assertEquals(sym.add("abc"),true);
        assertEquals(sym.add("abc"),false);
        assertEquals(sym.add("a"),true);
        assertEquals(sym.add("b"),true);
        assertEquals(sym.add("c"),true);



        System.out.println(sym);


    }

    @org.junit.jupiter.api.Test
    void contains() {

        SymbolTable sym=new SymbolTable(17);
        sym.add("abc");

        assertEquals(sym.contains("abc"),true);

    }

    @org.junit.jupiter.api.Test
    void getPosition() {

        SymbolTable sym=new SymbolTable(17);
        sym.add("abc");
        Pair<Integer,Integer>p=new Pair<>(-1,-1);
        Pair<Integer,Integer>p2=sym.getPosition("abc");
        assertNotEquals(p.getKey(),p2.getKey());
        assertNotEquals(p.getValue(),p2.getValue());

        p2=sym.getPosition("a");

        assertEquals(p.getKey(),p2.getKey());
        assertEquals(p.getValue(),p2.getValue());



    }

    @org.junit.jupiter.api.Test
    void testToString() {

        SymbolTable sm=new SymbolTable(17);

        sm.add("abc");
        sm.add("bc");
        sm.add("cd");
        sm.add("aaabc");

        System.out.println(sm);

    }
}