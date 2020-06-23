package solver;


public class Main {
    public static void main(String[] args) {
        Matrix m = new Matrix(args[1], args[3]);
        m.findSolutions();
        m.printMatrix();
        m.writeToFile();
    }

}
