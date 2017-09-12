/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package majorprogram1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cambino10
 */
public class CyberBank {

    private String bankName;
    private String address;
    private String phoneNumber;
    private double bankBalance;
    private ArrayList<Customer> customers;

    public CyberBank() {
        bankName = "Get Money Bank";
        address = "0606 Money Lane";
        phoneNumber = "336-Money";
        bankBalance = 1000000.0;
        customers = new ArrayList<>();
    }

    public CyberBank(String bankName, String address, String phoneNumber, double bankBalance, ArrayList<Customer> customers) {
        this.bankName = bankName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.bankBalance = bankBalance;
        this.customers = customers;
        

    }

    /**
     * @return the bankName
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * @param bankName the bankName to set
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return the bankBalance
     */
    public double getBankBalance() {
        return bankBalance;
    }

    /**
     * @param bankBalance the bankBalance to set
     */
    public void setBankBalance(double bankBalance) {
        this.bankBalance = bankBalance;
    }

    public Customer authenticateCustomer(int custID, int pin) {
        for (int i = 0; i < customers.size(); i++) {

            if (customers.get(i).getCustomerID() == custID) {
                if (customers.get(i).getPin() == pin) {
                    return customers.get(i);

                }
            }
        }
        return null;
    }

    public int getNumCustomers() {
        return customers.size();
    }

    public Customer getAccounts(int index) {
        return customers.get(index);
    }

    public void setCustomers(int index, Customer item) {
        customers.set(index, item);
    }

    public void addCustomer(Customer item) {
        customers.add(item);
        /**
         * if (item.getAccountType() == AccountType.credit) { balance +=
         * item.getAmount(); } else if (item.getAccountType() ==
         * AccountType.debit) { balance -= item.getAmount(); } }
         */

    }

    public Customer deleteCustomer(int index) {

        Customer item = customers.remove(index);
        /**
         * if (item.getAccountType() == AccountType.credit) { balance -=
         * item.getAmount(); } else if (item.getAccountType() ==
         * AccountType.debit) { balance += item.getAmount(); }
         */
        return item;
    }
    public void loadBankData(String inputFile) {
        try {
            Scanner scan = new Scanner(new File(inputFile));
            String info = scan.nextLine();
            String[] temp = info.split("#");
            setBankName(temp[0]);
            setAddress(temp[1]);
            setPhoneNumber(temp[2]);
            while (scan.hasNext()) {
                String[] temp2;
                String i = scan.nextLine();
                temp2 = i.split("#");
                Customer c = new Customer();
                c.setFirstName(temp2[0]);
                c.setLastName(temp2[1]);
                c.setCustomerID(Integer.parseInt(temp2[2]));
                c.setDob(temp2[3]);
                c.setAddress(temp2[4]);
                c.setPhoneNumber(temp2[5]);
                c.setPin(Integer.parseInt(temp2[6]));
                int numAccounts = Integer.parseInt(temp2[7]);
                for (int c2 = 0; c2 < numAccounts; c2++) {
                    String a = scan.nextLine();
                    String[] information = a.split("#");
                    Account account = new Account();
                    account.setAccountType(AccountType.valueOf(information[0]));
                    account.setAccountNumber(information[1]);
                    //account.setBalance(Double.parseDouble(information[2]));
                    int numTransactions = Integer.parseInt(information[3]);
                    for (int t = 0; t < numTransactions; t++) {
                        a = scan.nextLine();
                        Transaction trans = new Transaction();
                        information = a.split("#");
                        trans.setTransactionType(TransactionType.valueOf(information[0]));
                        trans.setDate(information[1]);
                        trans.setAmount(Double.parseDouble(information[2]));
                        trans.setDescription(information[3]);
                        //Add transaction to account
                        account.addTransaction(trans);
                    }
                    //Add acount to the customer
                    c.addAccount(account);
                }
                //Add the customer to the bank
                customers.add(c);
            }
        } catch (FileNotFoundException ex) {
        }

    }

    public void saveBankData(String inputFile) {
        try {
            PrintWriter pw = new PrintWriter(new File(inputFile));
            pw.write(toString());
            pw.close();
          
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(CyberBank.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }

    @Override
    public String toString() {
        String output = "";
        String newLine = System.getProperty("line.separator");
        output += bankName + "#" + address + "#" + phoneNumber + "#" + bankBalance + newLine;
        for (int i = 0; i < customers.size(); i++) {
            output += customers.get(i).toString();
        }

        return output;
    }

}
