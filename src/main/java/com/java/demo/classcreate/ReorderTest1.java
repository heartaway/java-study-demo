package com.java.demo.classcreate;

/**
 * <p>
 *     参考：http://ifeve.com/java-memory-model-1/
 *
 * </p>
 * User: <a href="mailto:yanmingming1989@163.com">严明明</a>
 * Date: 15/5/21
 * Time: 下午5:40
 */

import java.util.concurrent.*;

public class ReorderTest1 {
    private int a = 0;
    private int b = 0;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ReorderTest1 rt = new ReorderTest1();
        rt.doTest();
    }

    public void doTest() throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        for (int i = 1; i < 1000000; i++) {
            CountDownLatch latch = new CountDownLatch(1);
            Future<Integer> futureA = executor.submit(new ThreadATask(latch));
            Future<Integer> futureB = executor.submit(new ThreadBTask(latch));
            latch.countDown();
            int x = futureB.get();
            int y = futureA.get();
            if (x == y) {
                System.out.println("x=" + x + " : " + "y=" + y);
                System.out.println("reorder in " + i);
                return;
            }

//重置状态
            a = 0;
            b = 0;
        }
//在此关闭线程池
    }

    private class ThreadATask implements Callable {
        private CountDownLatch barrier;

        public ThreadATask(CountDownLatch barrier) {
            this.barrier = barrier;
        }

        public Integer call() throws InterruptedException, BrokenBarrierException {
            barrier.await();

            a = 1; // A1
            return b; // A2
        }
    }

    private class ThreadBTask implements Callable {
        private CountDownLatch barrier;

        public ThreadBTask(CountDownLatch barrier) {
            this.barrier = barrier;
        }

        public Integer call() throws InterruptedException, BrokenBarrierException {
            barrier.await();

            b = 2; // B1
            return a; // B2
        }
    }
}