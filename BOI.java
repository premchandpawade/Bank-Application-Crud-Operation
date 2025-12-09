package bank;

import java.sql.*;
import java.util.Scanner;

class DBConnection {
    Connection cn;

    void connectiondb() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String name = "root";
        String password = "";
        String url = "jdbc:mysql://localhost:3306/bank";
        cn = DriverManager.getConnection(url, name, password);
    }
}

class Operation {

    Connection cn;

    Operation(Connection cn) {
        this.cn = cn;
    }

    void register() throws SQLException {
        Scanner sc = new Scanner(System.in);

        int accountnumber = Validation.validateAccountNumber(sc);
        String name = Validation.validateName(sc);
        String email = Validation.validateEmail(sc);
        long contact = Validation.validateContact(sc);
        String address = Validation.validateAddress(sc);
        double amount = Validation.validateAmount(sc, "initial");

        String query = "INSERT INTO BOI VALUES(?,?,?,?,?,?)";
        PreparedStatement ps = cn.prepareStatement(query);

        ps.setInt(1, accountnumber);
        ps.setString(2, name);
        ps.setString(3, email);
        ps.setLong(4, contact);
        ps.setString(5, address);
        ps.setDouble(6, amount);

        ps.executeUpdate();
        System.out.println("Account created successfully!");
    }

    void deposit() throws SQLException {
        Scanner sc = new Scanner(System.in);

        int accountnumber = Validation.validateAccountNumber(sc);
        double amt = Validation.validateAmount(sc, "deposit");

        String deposit = "UPDATE BOI SET amount = amount + ? WHERE accountnumber = ?";
        PreparedStatement ps = cn.prepareStatement(deposit);

        ps.setDouble(1, amt);
        ps.setInt(2, accountnumber);

        int update = ps.executeUpdate();

        if (update > 0)
            System.out.println("Amount deposited");
        else
            System.out.println("Account not found");
    }

    void withdrawl() throws SQLException {
        Scanner sc = new Scanner(System.in);

        int accountnumber = Validation.validateAccountNumber(sc);
        double amt = Validation.validateAmount(sc, "withdraw");

        String query = "SELECT amount FROM BOI WHERE accountnumber = ?";
        PreparedStatement ps = cn.prepareStatement(query);
        ps.setInt(1, accountnumber);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            double balance = rs.getDouble("amount");

            if (amt <= balance) {
                String update = "UPDATE BOI SET amount = amount - ? WHERE accountnumber = ?";
                PreparedStatement ps2 = cn.prepareStatement(update);

                ps2.setDouble(1, amt);
                ps2.setInt(2, accountnumber);
                ps2.executeUpdate();

                System.out.println("Withdrawal successful!");
            } else {
                System.out.println("Insufficient balance");
            }
        } else {
            System.out.println("Account not found");
        }
    }

    void checkbalance() throws SQLException {
        Scanner sc = new Scanner(System.in);

        int accountnumber = Validation.validateAccountNumber(sc);

        String query = "SELECT amount FROM BOI WHERE accountnumber = ?";
        PreparedStatement ps = cn.prepareStatement(query);
        ps.setInt(1, accountnumber);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            System.out.println("Available balance: " + rs.getDouble("amount"));
        } else {
            System.out.println("Account not found, please enter registered mobile number");
            long ct =sc.nextLong();
            String q="Select accountnumber from boi where contact =?";
            PreparedStatement pss=cn.prepareStatement(q);
            pss.setLong(1,ct);
            ResultSet rss=pss.executeQuery();
            while(rss.next()) {
            	System.out.println("accountnumber = "+rss.getInt("accountnumber"));
            }
            
        }
    }
}

public class BOI {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        DBConnection db = new DBConnection();
        db.connectiondb();

        Operation op = new Operation(db.cn);

        System.out.println("WELCOME TO BOI");
        System.out.println("Select operation :");
        System.out.println("1. Register");
        System.out.println("2. Deposit");
        System.out.println("3. Withdrawl");
        System.out.println("4. Check Balance");
        System.out.println("5. Exit");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                op.register();
                break;
            case 2:
                op.deposit();
                break;
            case 3:
                op.withdrawl();
                break;
            case 4:
                op.checkbalance();
                break;
            case 5:
                System.out.println("THANK YOU");
                return;
            default:
                System.out.println("Invalid option try again");
        }
    }
}
