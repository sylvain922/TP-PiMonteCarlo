package MonteCarlo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Worker task for Monte Carlo simulation (Runnable).
 */
public class MonteCarloRunnable implements Runnable {

    private final AtomicInteger nAtomSuccess;

    public MonteCarloRunnable(AtomicInteger nAtomSuccess) {
        this.nAtomSuccess = nAtomSuccess;
    }

    @Override
    public void run() {
        double x = Math.random();
        double y = Math.random();
        if (x * x + y * y <= 1) {
            nAtomSuccess.incrementAndGet();
        }
    }
}
