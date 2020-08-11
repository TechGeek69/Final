package fin;

public class Business extends LoanP implements Generate {
    double monthlyPayment;
    double interestRate = 0.09;

    public Business(String clientNum, String clientName, double loanAmt, int yearsPay, String loanType) {
        super(clientNum, clientName, loanAmt, yearsPay, loanType);
    }

    @Override
    public void generateTable() {


    }
}
