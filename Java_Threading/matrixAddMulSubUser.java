import java.util.Scanner;

class MatrixOperation extends Thread {
    private int[][] A;
    private int[][] B;
    private int[][] C;
    private int row, col;
    private char operation;

    public MatrixOperation(int[][] A, int[][] B, int[][] C, int row, int col, char operation) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.row = row;
        this.col = col;
        this.operation = operation;
    }

    public void run() {
        switch (operation) {
            case '+':
                C[row][col] = A[row][col] + B[row][col];
                break;
            case '-':
                C[row][col] = A[row][col] - B[row][col];
                break;
            case '*':
                // For multiplication, we need to calculate the entire row and column
                for (int k = 0; k < A.length; k++) {
                    C[row][col] += A[row][k] * B[k][col];
                }
                break;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter matrix size (2 or 3 for 2x2 or 3x3): ");
        int size = scanner.nextInt();
        int[][] A = new int[size][size];
        int[][] B = new int[size][size];
        int[][] C = new int[size][size];

        System.out.println("Enter matrix A:");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                A[i][j] = scanner.nextInt();
            }
        }

        System.out.println("Enter matrix B:");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                B[i][j] = scanner.nextInt();
            }
        }

        System.out.print("Choose operation (+, -, *): ");
        char operation = scanner.next().charAt(0);

        MatrixOperation[][] threads = new MatrixOperation[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                threads[i][j] = new MatrixOperation(A, B, C, i, j, operation);
                threads[i][j].start();
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                try {
                    threads[i][j].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("Result Matrix C:");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(C[i][j] + " ");
            }
            System.out.println();
        }
    }
}
