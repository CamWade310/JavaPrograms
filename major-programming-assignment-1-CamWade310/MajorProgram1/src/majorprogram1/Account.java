/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package majorprogram1;

import java.util.ArrayList;

/**
 *
 * @author Camden Wade
 */
public class Account {

    private AccountType accountType;
    private String accountNumber;
    private double balance;
    private final ArrayList<Transaction> transactions;

    public Account() {
        accountType = AccountType.checking;
        accountNumber = "";
        balance = 0.0;
        transactions = new ArrayList();
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getNumTransactions() {
        return transactions.size();
    }

    public Transaction getTransaction(int index) {
        return transactions.get(index);
    }

    public void setTransactions(int index, Transaction item) {
        Transaction overwrittenTransaction = transactions.get(index);
        if (overwrittenTransaction.getTransactionType() == TransactionType.credit) {
            balance -= overwrittenTransaction.getAmount();
        } else if (overwrittenTransaction.getTransactionType() == TransactionType.debit) {
            balance += overwrittenTransaction.getAmount();
        }
        transactions.set(index, item);
        if (item.getTransactionType() == TransactionType.credit) {
            balance += item.getAmount();
        } else if (item.getTransactionType() == TransactionType.debit) {
            balance -= item.getAmount();
        }

    }

    public void addTransaction(Transaction item) {
        transactions.add(item);
        if (item.getTransactionType() == TransactionType.credit) {
            balance += item.getAmount();
        } else if (item.getTransactionType() == TransactionType.debit) {
            balance -= item.getAmount();
        }
    }

    public Transaction deleteTransaction(int index) {
        
        Transaction item = transactions.remove(index);
        if (item.getTransactionType() == TransactionType.credit) {
            balance -= item.getAmount();
        } else if (item.getTransactionType() == TransactionType.debit) {
            balance += item.getAmount();
        }
        return item;
    }

    @Override
    public String toString() {
        String s = "";
        s += accountType + "#";
        s += accountNumber + "#";
        s += balance + "#" + transactions.size() +"#"+getNumTransactions() + System.lineSeparator();

        for (Transaction transaction : transactions) {
            s += transaction.toString();
            if (transaction != transactions.get(transactions.size() - 1)) {
                s += System.lineSeparator();

            }
        }
        return s;
    }
}


