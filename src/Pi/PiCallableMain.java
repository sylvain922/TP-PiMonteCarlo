package Pi;

/**
 * Main pour l'approche Callable/FixedThreadPool.
 */
public class PiCallableMain {

    public static void main(String[] args) throws Exception {
        long total = new PiMaster().doRun(50_000, 10);
        System.out.println("total from Master = " + total);
    }
}
