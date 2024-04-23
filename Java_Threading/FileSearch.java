class FileSearch extends Thread {
    private String[] data;
    private String keyword;
    private int start;
    private int end;

    public FileSearch(String[] data, String keyword, int start, int end) {
        this.data = data;
        this.keyword = keyword;
        this.start = start;
        this.end = end;
    }

    public void run() {
        for (int i = start; i < end; i++) {
            if (data[i].contains(keyword)) {
                System.out.println(Thread.currentThread().getName() + " found the keyword in line " + i);
            }
        }
    }

    public static void main(String[] args) {
        String[] lines = {"This is the first line", "This is the second line", "Keyword found here", "Another line", "Yet another line"};
        String keyword = "Keyword";

        int numberOfThreads = 2;
        int partSize = lines.length / numberOfThreads;

        for (int i = 0; i < numberOfThreads; i++) {
            int start = i * partSize;
            int end = (i + 1) * partSize;
            if (i == numberOfThreads - 1) {
                end = lines.length;
            }
            FileSearch searcher = new FileSearch(lines, keyword, start, end);
            searcher.start();
        }
    }
}
