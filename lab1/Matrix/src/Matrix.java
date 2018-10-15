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
        Matrix result = new Matrix(x,y);
        for (int i = 0; i < x; ++i)
            for (int j = 0; j < y; ++j)
                for (int k = 0; k < x; ++k)
                    result.arr[i][j] += arr[i][k] * m.arr[k][j];
        return result;
    }

    public static void main(String[] args) {
        Matrix m = new Matrix(2,2);
        m.arr[0][0] = 1;
        m.arr[0][1] = 2;
        m.arr[1][0] = 3;
        m.arr[1][1] = 4;
        Matrix n = new Matrix(2,2);
        n.arr[0][0] = 1;
        n.arr[0][1] = 2;
        n.arr[1][0] = 3;
        n.arr[1][1] = 4;

        Matrix w = m.add(n);

        for (int i = 0; i < m.x; ++i) {
            for (int j = 0; j < m.y; ++j) {
                System.out.print(w.arr[i][j]);
            }
            System.out.println();
        }

    }

}
