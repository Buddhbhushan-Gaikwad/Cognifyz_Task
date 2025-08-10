import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private int balance;
    private final Lock lock;
    private final boolean useLock;

    public BankAccount(int initialBalance, boolean useLock) {
        this.balance = initialBalance;
        this.useLock = useLock;
        if (useLock) {
            this.lock = new ReentrantLock();
        } else {
            this.lock = null;
        }
    }

    // Deposit (lock used only if useLock == true)
    public void deposit(int amount, String threadName) {
        if (useLock) {
            lock.lock();
            try {
                System.out.println(threadName + " is depositing: " + amount);
                int newBalance = balance + amount;
                try {
                    Thread.sleep(100); // simulate processing delay
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                balance = newBalance;
                System.out.println(threadName + " completed deposit. New Balance: " + balance);
            } finally {
                lock.unlock();
            }
        } else {
            // UNSAFE path (race condition likely)
            System.out.println(threadName + " is depositing: " + amount);
            int current = balance;               // read
            try {
                Thread.sleep(100);               // delay to allow interleaving
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            balance = current + amount;         // write
            System.out.println(threadName + " completed deposit. New Balance: " + balance);
        }
    }

    // Withdraw (lock used only if useLock == true)
    public void withdraw(int amount, String threadName) {
        if (useLock) {
            lock.lock();
            try {
                System.out.println(threadName + " is withdrawing: " + amount);
                if (balance >= amount) {
                    int newBalance = balance - amount;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    balance = newBalance;
                    System.out.println(threadName + " completed withdrawal. New Balance: " + balance);
                } else {
                    System.out.println(threadName + " - Insufficient funds! Current Balance: " + balance);
                }
            } finally {
                lock.unlock();
            }
        } else {
            // UNSAFE path (race condition likely)
            System.out.println(threadName + " is withdrawing: " + amount);
            int current = balance;              // read
            if (current >= amount) {
                try {
                    Thread.sleep(100);          // delay to allow interleaving
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                balance = current - amount;    // write
                System.out.println(threadName + " completed withdrawal. New Balance: " + balance);
            } else {
                System.out.println(threadName + " - Insufficient funds! Current Balance: " + balance);
            }
        }
    }

    public int getBalance() {
        return balance;
    }
}
