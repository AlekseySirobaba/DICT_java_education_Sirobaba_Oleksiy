package MatrixProcessing;

import java.util.Scanner;

class MatrixMenu {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        MatrixCalculator matrixCalculator = new MatrixCalculator();

        while (true) {
            printMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> matrixCalculator.addMatrices();
                case 2 -> matrixCalculator.multiplyByConstant();
                case 3 -> matrixCalculator.multiplyMatrices();
                case 4 -> matrixCalculator.transposeMatrix();
                case 5 -> matrixCalculator.calculateDeterminant();
                case 6 -> matrixCalculator.inverseMatrix();
                case 0 -> {
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please choose a valid option.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("1. Add matrices");
        System.out.println("2. Multiply matrix by a constant");
        System.out.println("3. Multiply matrices");
        System.out.println("4. Transpose matrix");
        System.out.println("5. Calculate a determinant");
        System.out.println("6. Inverse matrix");
        System.out.println("0. Exit");
        System.out.print("Your choice: ");
    }
}

class MatrixCalculator {
    private final Scanner scanner = new Scanner(System.in);

    public void addMatrices() {
        Matrix A = readMatrix("Enter size of the first matrix:");
        Matrix B = readMatrix("Enter size of the second matrix:");

        if (A.canAdd(B)) {
            Matrix result = A.add(B);
            System.out.println("The result is:");
            result.print();
        } else {
            System.out.println("ERROR: Matrices cannot be added. Dimensions do not match.");
        }
    }

    public void multiplyByConstant() {
        Matrix A = readMatrix("Enter size of the matrix:");
        double constant = readConstant();

        Matrix result = A.multiply(constant);
        System.out.println("The result is:");
        result.print();
    }

    public void multiplyMatrices() {
        Matrix A = readMatrix("Enter size of the first matrix:");
        Matrix B = readMatrix("Enter size of the second matrix:");

        if (A.canMultiply(B)) {
            Matrix result = A.multiply(B);
            System.out.println("The result is:");
            result.print();
        } else {
            System.out.println("ERROR: Matrices cannot be multiplied. Invalid dimensions.");
        }
    }

    public void transposeMatrix() {
        System.out.println("Choose transpose type:");
        System.out.println("1. Main diagonal");
        System.out.println("2. Side diagonal");
        System.out.println("3. Vertical line");
        System.out.println("4. Horizontal line");
        int transposeType = scanner.nextInt();

        Matrix A = readMatrix("Enter size of the matrix:");
        Matrix result = A.transpose(transposeType);

        System.out.println("The result is:");
        result.print();
    }

    public void calculateDeterminant() {
        Matrix A = readMatrix("Enter size of the matrix:");

        if (A.isSquare()) {
            double determinant = A.calculateDeterminant();
            System.out.println("The result is: " + OutputProcessor.number(determinant));
        } else {
            System.out.println("ERROR: Determinant can only be calculated for square matrices.");
        }
    }

    public void inverseMatrix() {
        Matrix A = readMatrix("Enter size of the matrix:");

        if (A.isSquare()) {
            Matrix inverse = A.calculateInverse();
            if (inverse != null) {
                System.out.println("The result is:");
                inverse.print();
            }
        } else {
            System.out.println("ERROR: Inverse can only be calculated for square matrices.");
        }
    }

    private Matrix readMatrix(String prompt) {
        System.out.println(prompt);
        int rows = scanner.nextInt();
        int columns = scanner.nextInt();
        System.out.println("Enter matrix:");
        double[][] data = new double[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                data[i][j] = scanner.nextDouble();
            }
        }
        return new Matrix(rows, columns, data);
    }

    private double readConstant() {
        System.out.println("Enter constant:");
        return scanner.nextDouble();
    }
}

class Matrix {
    private final int rows;
    private final int columns;
    private final double[][] data;

    public Matrix(int rows, int columns, double[][] data) {
        this.rows = rows;
        this.columns = columns;
        this.data = data;
    }

    public boolean canAdd(Matrix other) {
        return this.rows == other.rows && this.columns == other.columns;
    }

    public boolean canMultiply(Matrix other) {
        return this.columns == other.rows;
    }

    public boolean isSquare() {
        return rows == columns;
    }

    public Matrix add(Matrix other) {
        double[][] resultData = new double[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                resultData[i][j] = this.data[i][j] + other.data[i][j];
            }
        }
        return new Matrix(rows, columns, resultData);
    }

    public Matrix multiply(double constant) {
        double[][] resultData = new double[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                resultData[i][j] = this.data[i][j] * constant;
            }
        }
        return new Matrix(rows, columns, resultData);
    }

    public Matrix multiply(Matrix other) {
        double[][] resultData = new double[this.rows][other.columns];
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < other.columns; j++) {
                for (int k = 0; k < this.columns; k++) {
                    resultData[i][j] += this.data[i][k] * other.data[k][j];
                }
            }
        }
        return new Matrix(this.rows, other.columns, resultData);
    }

    public Matrix transpose(int type) {
        double[][] resultData = new double[columns][rows];

        switch (type) {
            case 1 -> { // Main diagonal
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                        resultData[j][i] = this.data[i][j];
                    }
                }
            }
            case 2 -> { // Side diagonal
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                        resultData[columns - 1 - j][rows - 1 - i] = this.data[i][j];
                    }
                }
            }
            case 3 -> { // Vertical line
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                        resultData[i][columns - 1 - j] = this.data[i][j];
                    }
                }
            }
            case 4 -> { // Horizontal line
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                        resultData[rows - 1 - i][j] = this.data[i][j];
                    }
                }
            }
            default -> {
                System.out.println("Invalid transpose type.");
                return null;
            }
        }

        return new Matrix(columns, rows, resultData);
    }

    public double calculateDeterminant() {
        if (!isSquare()) {
            throw new IllegalArgumentException("Determinant can only be calculated for square matrices.");
        }
        if (rows == 1) {
            return data[0][0];
        }
        if (rows == 2) {
            return data[0][0] * data[1][1] - data[0][1] * data[1][0];
        }

        double determinant = 0;
        for (int j = 0; j < columns; j++) {
            determinant += Math.pow(-1, j) * data[0][j] * minor(0, j).calculateDeterminant();
        }
        return determinant;
    }

    private Matrix minor(int row, int col) {
        double[][] minorData = new double[rows - 1][columns - 1];
        for (int i = 0, mi = 0; i < rows; i++) {
            if (i != row) {
                for (int j = 0, mj = 0; j < columns; j++) {
                    if (j != col) {
                        minorData[mi][mj] = data[i][j];
                        mj++;
                    }
                }
                mi++;
            }
        }
        return new Matrix(rows - 1, columns - 1, minorData);
    }

    public Matrix calculateInverse() {
        double determinant = calculateDeterminant();
        if (determinant == 0) {
            System.out.println("ERROR: The inverse matrix does not exist. Determinant is zero.");
            return null;
        }
        Matrix adjugate = calculateAdjugate();
        return adjugate.multiply(1.0 / determinant);
    }

    private Matrix calculateAdjugate() {
        double[][] adjugateData = new double[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                double cofactor = Math.pow(-1, i + j) * minor(i, j).calculateDeterminant();
                adjugateData[j][i] = cofactor; // Transpose the cofactor matrix
            }
        }

        return new Matrix(rows, columns, adjugateData);
    }

    public void print() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(OutputProcessor.number(data[i][j]) + " ");
            }
            System.out.println();
        }
    }

}

class OutputProcessor {
    public static String number(double number) {
        String numberAsString = String.valueOf(number);

        if (numberAsString.matches(".*\\.0*")) {
            return String.format("%d", (int) number);
        } else {
            return String.format("%.2f", number);
        }
    }
}