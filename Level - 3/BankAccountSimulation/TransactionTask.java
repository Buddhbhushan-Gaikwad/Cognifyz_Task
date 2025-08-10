

public class TransactionTask implements Runnable {
    private BankAccount account;
    private boolean depositAction;
    private int amount;
    private String name;

    public TransactionTask(BankAccount account, boolean depositAction, int amount, String name) {
        this.account = account;
        this.depositAction = depositAction;
        this.amount = amount;
        this.name = name;
    }

    public void run() {
        if (depositAction) {
            account.deposit(amount, name);
        } else {
            account.withdraw(amount, name);
        }
    }
}
