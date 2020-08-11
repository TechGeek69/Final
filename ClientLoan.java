package fin;

import java.awt.event.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.*;

import java.sql.*;
import java.util.Vector;

/**
 https://github.com/TechGeek69/Final.git
 */

public class ClientLoan extends JFrame {
    public ClientLoan() {
        initComponents();
    }

    Connection con1;
    PreparedStatement insert;
    String cID;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ClientLoan gui = new ClientLoan();
         gui.table();
         gui.setVisible(true);
         gui.loadDb();
    }
    //table 1 data
    private void table() {
        String[]col ={"Number","Name","Amount","Years","Type of Loan"};
        String[][]row ={{"10123","Johnny Jacobi","8000.0","8","Business"},{"1157","Joy Ramirez","100000.0","5","Business"},{"1141","Glowie","15000.0","2","Personal"}};
        DefaultTableModel df = new DefaultTableModel(row,col);
        dataTable.setModel(df);
    }
    //table 2 data
    private void table2() {
        String[]col ={"Payment","Principal","Interest","Monthly Payment","Balance"};
        String[][]row ={{""," ","","",""},{"","","","",""},{"","","","",""}};
        DefaultTableModel df = new DefaultTableModel(row,col);
        dataTable2.setModel(df);
    }

    private LoanP tempDataHold() {




             String clientNum = textFieldClientNum.getText() ;
             String clientName= textFieldClientName.getText();
             double loanAmt =Double.parseDouble(textFieldLoanAmt.getText()) ;
             int yearsPay=Integer.parseInt(textFieldYearsPay.getText()) ;;
             String loanType=comboBoxloanType.getSelectedItem().toString();
             LoanP loan = new LoanP(clientNum,clientName,loanAmt,yearsPay,loanType);
             return loan;



    }

    private void loadDb() throws ClassNotFoundException, SQLException {
        int columnCount;
        Class.forName("com.mysql.jdbc.Driver");
        con1 = DriverManager.getConnection("jdbc:mysql://localhost/loan","root","");
        insert = con1.prepareStatement("select * from loantable");
        ResultSet rs = insert.executeQuery();
        ResultSetMetaData res = rs.getMetaData();
        columnCount = res.getColumnCount();
        DefaultTableModel df= (DefaultTableModel) dataTable.getModel();
        df.setRowCount(0);

        while(rs.next()){
            Vector vector = new Vector();
            for(int i=0;i<=columnCount;i++){

                vector.add(rs.getString("clientno"));
                vector.add(rs.getString("clientname"));
                vector.add(rs.getString("loanamount"));
                vector.add(rs.getString("years"));
                vector.add(rs.getString("loantype"));

            }
            df.addRow(vector);

        }


    }


    private void buttonAddActionPerformed(ActionEvent e) throws ClassNotFoundException, SQLException {


        LoanP s=tempDataHold();


            Class.forName("com.mysql.jdbc.Driver");
            con1 = DriverManager.getConnection("jdbc:mysql://localhost/loan", "root", "");

            insert = con1.prepareStatement("Select * from loantable where clientno = ?");
            insert.setString(1,  s.getClientName());
            ResultSet resultSet = insert.executeQuery();
            if(resultSet.isBeforeFirst()){ JOptionPane.showMessageDialog(null,"already data"); }
            insert = con1.prepareStatement("insert into loantable values (?,?,?,?,?)");
            insert.setString(1, s.getClientNum());
            insert.setString(2, s.getClientName());
            insert.setString(3, Double.toString(s.getLoanAmt()));
            insert.setString(4, Integer.toString(s.getYearsPay()));
            insert.setString(5, s.getLoanType());

            insert.executeUpdate();
            JOptionPane.showMessageDialog(null,"Record Added");

        textFieldClientNum.setText("");
        textFieldClientName.setText("");
        textFieldLoanAmt.setText("");
        textFieldYearsPay.setText("");
        textFieldClientNum.requestFocus();
            loadDb();


    }


    private void dataTableMouseClicked(MouseEvent e) {
        table2();
        DefaultTableModel def = (DefaultTableModel) dataTable.getModel();




        //from selected table row
        int index = dataTable.getSelectedRow();
        textFieldClientNum.setText(def.getValueAt(index,0).toString());
        cID=textFieldClientNum.getText();
        textFieldClientName.setText(def.getValueAt(index,1).toString());
        textFieldLoanAmt.setText(def.getValueAt(index,2).toString());
        textFieldYearsPay.setText(def.getValueAt(index,3).toString());
        comboBoxloanType.setSelectedItem(index);

        //for table 2
        DefaultTableModel def2 = (DefaultTableModel) dataTable2.getModel();
        int index2 = dataTable.getSelectedRow();
    }

    private void buttonDeleteActionPerformed(ActionEvent e) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        con1= DriverManager.getConnection("jdbc:mysql://localhost/loan","root","");
        int result1 = JOptionPane.showConfirmDialog(null,"Want to delete?", "Delete",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(result1 == JOptionPane.YES_OPTION){
            insert=con1.prepareStatement("delete from loantable where clientno=?");
            insert.setString(1,cID);
            insert.execute();
            JOptionPane.showMessageDialog(null, "Record deleted");

        }


        loadDb();
    }

    private void buttonEditActionPerformed(ActionEvent e) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        con1 = DriverManager.getConnection("jdbc:mysql://localhost/loan", "root", "");
        insert = con1.prepareStatement("update loantable set clientno=?,clientname=?,loanamount=?,years=?,loantype=? where clientno =?");
        insert.setString(1, textFieldClientNum.getText());
        insert.setString(2, textFieldClientName.getText());
        insert.setString(3, textFieldLoanAmt.getText());
        insert.setString(4, textFieldYearsPay.getText());
        insert.setString(5, comboBoxloanType.getSelectedItem().toString());
        insert.setString(6, cID);
        insert.executeUpdate();
        JOptionPane.showMessageDialog(null,"Record Edited");
        textFieldClientNum.setText("");
        textFieldClientName.setText("");
        textFieldLoanAmt.setText("");
        textFieldYearsPay.setText("");
        textFieldClientNum.requestFocus();
        loadDb();
    }

    private void monPaymentActionPerformed(ActionEvent e) {

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - techgeek
        label1 = new JLabel();
        textFieldClientNum = new JTextField();
        label2 = new JLabel();
        textFieldClientName = new JTextField();
        label3 = new JLabel();
        textFieldLoanAmt = new JTextField();
        label4 = new JLabel();
        textFieldYearsPay = new JTextField();
        label5 = new JLabel();
        comboBoxloanType = new JComboBox<>();
        scrollPane2 = new JScrollPane();
        dataTable = new JTable();
        scrollPane3 = new JScrollPane();
        dataTable2 = new JTable();
        buttonAdd = new JButton();
        buttonEdit = new JButton();
        buttonDelete = new JButton();
        label6 = new JLabel();
        monPayment = new JTextField();

        //======== this ========
        setTitle("Compute Monthly Payment");
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[21,fill]" +
            "[136,fill]" +
            "[85,fill]" +
            "[fill]" +
            "[31,fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]",
            // rows
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]"));

        //---- label1 ----
        label1.setText("Enter the client number:");
        contentPane.add(label1, "cell 0 1 27 1");
        contentPane.add(textFieldClientNum, "cell 5 1 58 1");

        //---- label2 ----
        label2.setText("Enter the client name:");
        contentPane.add(label2, "cell 0 3 27 1");
        contentPane.add(textFieldClientName, "cell 5 3 58 1");

        //---- label3 ----
        label3.setText("Enter the customer loan amount:");
        contentPane.add(label3, "cell 0 5 27 1");
        contentPane.add(textFieldLoanAmt, "cell 5 5 58 1");

        //---- label4 ----
        label4.setText("Enter the number of years to pay");
        contentPane.add(label4, "cell 0 7 27 1");
        contentPane.add(textFieldYearsPay, "cell 5 7 58 1");

        //---- label5 ----
        label5.setText("Enter the loan type");
        contentPane.add(label5, "cell 1 9");

        //---- comboBoxloanType ----
        comboBoxloanType.setModel(new DefaultComboBoxModel<>(new String[] {
            "Business",
            "Personal"
        }));
        contentPane.add(comboBoxloanType, "cell 48 9 15 1");

        //======== scrollPane2 ========
        {

            //---- dataTable ----
            dataTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    dataTableMouseClicked(e);
                }
            });
            scrollPane2.setViewportView(dataTable);
        }
        contentPane.add(scrollPane2, "cell 0 10 13 1");

        //======== scrollPane3 ========
        {
            scrollPane3.setViewportView(dataTable2);
        }
        contentPane.add(scrollPane3, "cell 14 10 56 1");

        //---- buttonAdd ----
        buttonAdd.setText("Add");
        buttonAdd.addActionListener(e -> {
            try {
                buttonAddActionPerformed(e);
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        contentPane.add(buttonAdd, "cell 1 13 3 1");

        //---- buttonEdit ----
        buttonEdit.setText("Edit");
        buttonEdit.addActionListener(e -> {
            try {
                buttonEditActionPerformed(e);
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        contentPane.add(buttonEdit, "cell 4 13");

        //---- buttonDelete ----
        buttonDelete.setText("Delete");
        buttonDelete.addActionListener(e -> {
            try {
                buttonDeleteActionPerformed(e);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
        });
        contentPane.add(buttonDelete, "cell 5 13 8 1");

        //---- label6 ----
        label6.setText("Monthly Payment");
        contentPane.add(label6, "cell 18 13");

        //---- monPayment ----
        monPayment.addActionListener(e -> monPaymentActionPerformed(e));
        contentPane.add(monPayment, "cell 27 13 20 1");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - techgeek
    private JLabel label1;
    private JTextField textFieldClientNum;
    private JLabel label2;
    private JTextField textFieldClientName;
    private JLabel label3;
    private JTextField textFieldLoanAmt;
    private JLabel label4;
    private JTextField textFieldYearsPay;
    private JLabel label5;
    private JComboBox<String> comboBoxloanType;
    private JScrollPane scrollPane2;
    private JTable dataTable;
    private JScrollPane scrollPane3;
    private JTable dataTable2;
    private JButton buttonAdd;
    private JButton buttonEdit;
    private JButton buttonDelete;
    private JLabel label6;
    private JTextField monPayment;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
