package fin;

public class Personal extends LoanP implements Generate{
    double interestRate = 0.06;
    public Personal(String clientNum, String clientName, double loanAmt, int yearsPay, String loanType) {
        super(clientNum, clientName, loanAmt, yearsPay, loanType);
    }

    @Override
    public void generateTable() {


    }
}