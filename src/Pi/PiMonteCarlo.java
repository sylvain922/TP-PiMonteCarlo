package Pi;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Monte Carlo Pi estimation using Runnables and WorkStealingPool.
 */
public class PiMonteCarlo {

    private final AtomicInteger nAtomSuccess;
    private final int nThrows;

    public PiMonteCarlo(int nThrows) {
        this.nAtomSuccess = new AtomicInteger(0);
        this.nThrows = nThrows;
    }

    public double getPi() {
        int nProcessors = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newWorkStealingPool(nProcessors);
        MonteCarloRunnable worker = new MonteCarloRunnable(nAtomSuccess);

        for (int i = 0; i < nThrows; i++) {
            executor.execute(worker);
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
            // attente
        }
        return 4.0 * nAtomSuccess.get() / nThrows;
    }
}
