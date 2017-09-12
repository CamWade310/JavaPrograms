/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package majorprogram1;

import javax.swing.JOptionPane;

/**
 *
 * @author Camden Wade
 */
public class MajorProgram1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        Transaction t1 = new Transaction();
//        Transaction t2 = new Transaction();
//        Transaction t3 = new Transaction ();
//        Transaction t4 = new Transaction ();
//        Transaction t5 = new Transaction ();
//        Account a = new Account();
//        Account a2 = new Account();
//        Customer c1 = new Customer();
//        Customer c2= new Customer();
        CyberBank bank1 = new CyberBank();
        bank1.loadBankData(args[0]);
        
        AggieBank aggieBank = new AggieBank(bank1, args[0], args[1]);
        aggieBank.setTitle("Bank");
        aggieBank.setSize(700, 700);
        aggieBank.setLocationRelativeTo(null);
        aggieBank.setVisible(true);
        //System.out.println(bank1.toString());
        
        
        
        
//        t1.setTransactionType(TransactionType.debit);
//        t1.setAmount(431);
//        
//        t2.setTransactionType(TransactionType.credit);
//        t2.setAmount(435);
//        
//        t3.setTransactionType(TransactionType.credit);
//        t3.setAmount(454);
//        
//        a.setAccountType(AccountType.loan);
//        a.setAccountNumber("a1");
//        
//        a.addTransaction(t3);
//        a.addTransaction(t2);
//        a.addTransaction(t1);
//        a2.setAccountType(AccountType.loan);
//        a2.setAccountNumber("a1");
//        
//        a2.addTransaction(t3);
//        a2.addTransaction(t2);
//        a2.addTransaction(t1);
//        c1.addAccount(a);
//        c2.addAccount(a2);
//        bank1.addCustomer(c1);
//        bank1.addCustomer(c2);
//        
//        
//        
//        
//        JOptionPane.showMessageDialog(null, bank1.toString());
//        
       
        
        
    }
    
}
