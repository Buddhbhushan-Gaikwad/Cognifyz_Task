

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        // Choose mode from command line: "unsafe" or "safe"
        boolean useLock = true; // default
        if (args.length > 0 && args[0].equalsIgnoreCase("unsafe")) {
            useLock = false;
        }

        System.out.println("Starting BankAccountSimulation. useLock = " + useLock);

        BankAccount account = new BankAccount(1000, useLock);

        // Thread pool (adjust size to change interleaving)
        ExecutorService executor = Executors.newFixedThreadPool(10);

        // Create 50 transactions (even i -> deposit, odd i -> withdraw)
        for (int i = 1; i <= 50; i++) {
            boolean deposit = (i % 2 == 0);
            int amount = (int) (Math.random() * 500) + 1; // 1..500
            executor.execute(new TransactionTask(account, deposit, amount, "Thread-" + i));
        }

        executor.shutdown();
        // Wait for all tasks to complete (busy-wait with small sleep)
        while (!executor.isTerminated()) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Final Balance: " + account.getBalance());
    }
}
