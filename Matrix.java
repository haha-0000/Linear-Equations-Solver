package solver;

import java.io.*;
import java.util.ArrayList;

public class Matrix {
    ArrayList <Row> matrix;
    int size;
    String inFile;
    String outFile;
    public Matrix(String inFile, String outFile) {
        this.inFile = inFile;
        this.outFile = outFile;
        matrix = new ArrayList<Row>();
        readFromFile();
    }
    void readFromFile() {
        try(BufferedReader br = new BufferedReader(new FileReader(inFile))) {
            this.size = Integer.parseInt(br.readLine());
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] str = line.split(" ");
                Row r = new Row();
                for (int i = 0; i < size; i++) {
                    r.row.add(Double.parseDouble(str[i]));
                }
                r.solution = Double.parseDouble(str[size]);
                matrix.add(r);
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    void writeToFile() {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(outFile))) {
            for (Row r : matrix) {
                bw.write(r.solution + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    void leadingOne(int position) {
        if(!matrix.get(position).isOne(position)) {
            matrix.get(position).divideRow(matrix.get(position).row.get(position));
        }
    }
    void zeroUnder(int position) {
        for(int i = position + 1; i < size; i++) {
            matrix.get(i).operationByRow(matrix.get(position), - matrix.get(i).row.get(position));
        }
    }
    void findSolutions() {
        for(int i = 0; i < size; i++) {
            leadingOne(i);
            zeroUnder(i);
        }
        for(int i = size - 1; i >= 0; i--) {
            zeroOver(i);
        }
    }
    void printMatrix(){
        for(int i = 0; i < size; i++) {
            matrix.get(i).printRow();
            System.out.println();
        }
    }
    void zeroOver(int position) {
        for(int i = position - 1; i >= 0; i--) {
            matrix.get(i).operationByRow(matrix.get(position), - matrix.get(i).row.get(position));
        }
    }

}