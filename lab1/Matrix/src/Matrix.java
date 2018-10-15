import java.util.Scanner;

public class Matrix {
    int x;
    int y;
    int[][] arr;
    Matrix() { x = 0; y = 0; arr = new int[x][y]; }
    Matrix(int _x, int _y) { x = _x; y = _y; arr = new int[_x][_y]; }
    Matrix add(Matrix m) {
        //if (x != m.x || y != m.y) throw new RuntimeException("Illegal vector dimensions.");
        Matrix result = new Matrix(x,y);
        for (int i = 0; i < x; ++i)
            for (int j = 0; j < y; ++j)
                result.arr[i][j] = arr[i][j] + m.arr[i][j];
        return result;
    }
    Matrix sub(Matrix m) {
        Matrix result = new Matrix(x,y);
        for (int i = 0; i < x; ++i)
            for (int j = 0; j < y; ++j)
                result.arr[i][j] = arr[i][j] - m.arr[i][j];
        return result;
    }

    Matrix mul(Matrix m) {
        Matrix result = new Matrix(x, y);
        for (int i = 0; i < x; ++i)
            for (int j = 0; j < y; ++j)
                for (int k = 0; k < x; ++k)
                    result.arr[i][j] += arr[i][k] * m.arr[k][j];
        return result;
    }

    static Matrix getMatrix() {
        System.out.print("Podaj wymiary macierzy: ");
        Scanner reader = new Scanner(System.in);
        int x = reader.nextInt();
        int y = reader.nextInt();
        Matrix ma = new Matrix(x,y);
        System.out.print("Podaj wartości (wierszami): ");
        for (int i = 0; i < x; ++i)
            for (int j = 0; j <y; ++j)
                 ma.arr[i][j] = reader.nextInt();

        reader.close();
        return ma;
    }

    void showMatrix() {
        for (int i = 0; i < x; ++i) {
            for (int j = 0; j < y; ++j) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String args[]) {
        Matrix m = getMatrix();
        m.showMatrix();

    }

}
