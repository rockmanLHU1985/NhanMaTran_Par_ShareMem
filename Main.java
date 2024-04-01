
public class Main {

	public static void main(String[] args) throws InterruptedException {
        final int SIZE = 1000; // Điều chỉnh kích thước ma trận phù hợp
        int[][] A = new int[SIZE][SIZE];
        int[][] B = new int[SIZE][SIZE];
        int[][] C = new int[SIZE][SIZE];
        
        // Khởi tạo ma trận A và B
        int value = 1;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                A[i][j] = value;
                B[i][j] = value;
                value=i+1;
            }
        }
        
        System.out.println("Số phần tử của ma trận A: " + SIZE * SIZE);
        System.out.println("Số phần tử của ma trận B: " + SIZE * SIZE);
        
        long startTime = System.currentTimeMillis();

        // Mảng để giữ các luồng
        Thread[] threads = new Thread[SIZE];
        
        // Tạo và khởi động luồng
        for (int i = 0; i < SIZE; i++) {
            final int row = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < SIZE; j++) {
                    for (int k = 0; k < SIZE; k++) {
                        C[row][j] += A[row][k] * B[k][j];
                    }
                }
            });
            threads[i].start();
        }
        
        // Chờ tất cả luồng hoàn thành
        for (Thread t : threads) {
            t.join();
        }

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        
        System.out.println("Thời gian bắt đầu: " + startTime + " ms");
        System.out.println("Thời gian kết thúc: " + endTime + " ms");
        System.out.println("Thời gian thực thi: " + totalTime + " ms");
        System.out.println("Số phần tử của ma trận C: " + SIZE * SIZE);

        // Tính tổng giá trị của tất cả phần tử trong ma trận C
        long sum = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                sum += C[i][j];
            }
        }

        System.out.println("Tổng giá trị của tất cả các phần tử trong ma trận C: " + sum);
    }

}
