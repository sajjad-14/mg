class MatrixAddition implements Runnable {
    private int[][] result;
    private int[][] matrix1;
    private int[][] matrix2;
    private int startRow;
    private int endRow;

    public MatrixAddition(int[][] result, int[][] matrix1, int[][] matrix2, int startRow, int endRow) {
        this.result = result;
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        this.startRow = startRow;
        this.endRow = endRow;
    }

    @Override
    public void run() {
        for (int i = startRow; i < endRow; i++) {
            for (int j = 0; j < matrix1[i].length; j++) {
                result[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int[][] matrix1 = {{1, 2}, {3, 4}};
        int[][] matrix2 = {{5, 6}, {7, 8}};
        int[][] result = new int[2][2];

        int numberOfThreads = 2;  // You can adjust this number based on your matrix size
        Thread[] threads = new Thread[numberOfThreads];
        int rowsPerThread = matrix1.length / numberOfThreads;

        for (int i = 0; i < numberOfThreads; i++) {
            int startRow = i * rowsPerThread;
            int endRow = (i + 1) * rowsPerThread;
            threads[i] = new Thread(new MatrixAddition(result, matrix1, matrix2, startRow, endRow));
            threads[i].start();
        }

        for (Thread t : threads) {
            t.join();
        }

        // Print the result matrix
        for (int[] row : result) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}
