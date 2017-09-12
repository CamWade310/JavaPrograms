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
public class Customer {

    private String firstName;
    private String lastName;
    private int customerID;
    private String dob;
    private String address;
    private String phoneNumber;
    private int pin;
    private ArrayList<Account> accounts;

    public Customer() {
        firstName = "Zanetta";
        lastName = "Tyler";
        customerID = 0606;
        dob = "June 6, 1995";
        address = "None Ya Business";
        phoneNumber = "336-***-****";
        pin = 0606;
        accounts = new ArrayList();
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the customerID
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * @param customerID the customerID to set
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * @return the dob
     */
    public String getDob() {
        return dob;
    }

    /**
     * @param dob the dob to set
     */
    public void setDob(String dob) {
        this.dob = dob;
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
     * @return the pin
     */
    public int getPin() {
        return pin;
    }

    /**
     * @param pin the pin to set
     */
    public void setPin(int pin) {
        this.pin = pin;
    }

    public int getNumAccounts() {
        return accounts.size();
    }

    public Account getAccounts(int index) {
        return accounts.get(index);
    }

    public void setAccounts(int index, Account item) {
        accounts.set(index, item);
    }

    public void addAccount(Account item) {
        accounts.add(item);
        /**
         * if (item.getAccountType() == AccountType.credit) { balance +=
         * item.getAmount(); } else if (item.getAccountType() ==
         * AccountType.debit) { balance -= item.getAmount(); } }
         */

    }

    public Account deleteAccount(int index) {

        Account item = accounts.remove(index);
        /**
         * if (item.getAccountType() == AccountType.credit) { balance -=
         * item.getAmount(); } else if (item.getAccountType() ==
         * AccountType.debit) { balance += item.getAmount();
        }
         */
        return item;
    }

    @Override
    public String toString() {
        String output = "";

        output = firstName + "#" + lastName + "#" + customerID + "#"
                + dob + "#" + address + "#" + phoneNumber + "#" + pin +"#"+getNumAccounts()+System.getProperty("line.separator");

        for (int i = 0; i < accounts.size(); i++) {
            output += accounts.get(i).toString()+System.getProperty("line.separator");
        }

        return output;
    }
}
