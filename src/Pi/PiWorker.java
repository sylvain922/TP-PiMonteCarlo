package Pi;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * Worker task for Monte Carlo simulation (Callable).
 */
public class PiWorker implements Callable<Long> {

    private final int numIterations;

    public PiWorker(int numIterations) {
        this.numIterations = numIterations;
    }

    @Override
    public Long call() {
        long circleCount = 0;
        Random prng = new Random();
        for (int j = 0; j < numIterations; j++) {
            double x = prng.nextDouble();
            double y = prng.nextDouble();
            if ((x * x + y * y) < 1) {
                ++circleCount;
            }
        }
        return circleCount;
    }
}
