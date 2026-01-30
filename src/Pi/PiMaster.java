package Pi;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Orchestrateur pour l'approche Callable/FixedThreadPool.
 */
public class PiMaster {

    public long doRun(int totalCount, int numWorkers) throws InterruptedException, ExecutionException {
        long startTime = System.currentTimeMillis();

        List<Callable<Long>> tasks = new ArrayList<>();
        for (int i = 0; i < numWorkers; ++i) {
            tasks.add(new PiWorker(totalCount));
        }

        ExecutorService exec = Executors.newFixedThreadPool(numWorkers);
        List<Future<Long>> results = exec.invokeAll(tasks);

        long total = 0;
        for (Future<Long> f : results) {
            total += f.get();
        }

        double pi = 4.0 * total / (double) (totalCount * numWorkers);
        long stopTime = System.currentTimeMillis();

        System.out.println("Callable approach - Pi : " + pi);
        System.out.println("Error: " + (Math.abs((pi - Math.PI)) / Math.PI));
        System.out.println("Ntot: " + (long) totalCount * numWorkers);
        System.out.println("Workers: " + numWorkers);
        System.out.println("Time Duration (ms): " + (stopTime - startTime) + "\n");

        exec.shutdown();
        return total;
    }
}
