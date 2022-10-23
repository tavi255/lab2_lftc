public class Main {

    public static void main(String[] args)
    {
        runScanner();
    }

    private static void runScanner(){
        MyScanner scannerP1 = new MyScanner("D:\\lftc lab\\lab2_lftc\\lab2\\src\\resources\\p1.txt",
                "D:\\lftc lab\\lab2_lftc\\lab2\\src\\resources\\PIF1.txt",
                "D:\\lftc lab\\lab2_lftc\\lab2\\src\\resources\\ST1.txt");
        scannerP1.scan();
    }

}
