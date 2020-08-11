package fin;

public class LoanP {

    private String ClientNum;
    private String ClientName;
    private double LoanAmt;
    private int YearsPay;
    private String loanType;

    public LoanP(String ClientNum, String ClientName, double LoanAmt, int YearsPay, String loanType) {

        this.ClientNum = ClientNum;
        this.ClientName = ClientName;
        this.LoanAmt = LoanAmt;
        this.YearsPay = YearsPay;
        this.loanType = loanType;
    }

    public String getClientNum() {
        return ClientNum;
    }

    public void setClientNum(String ClientNum) {
        this.ClientNum = ClientNum;
    }

    public String getClientName() {
        return ClientName;
    }

    public void setClientName(String clientName) {
        this.ClientName = clientName;
    }

    public double getLoanAmt() {
        return LoanAmt;
    }

    public void setLoanAmt(double loanAmt) {
        this.LoanAmt = loanAmt;
    }

    public int getYearsPay() {
        return YearsPay;
    }

    public void setYearsPay(int yearsPay) {
        this.YearsPay = yearsPay;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    }

