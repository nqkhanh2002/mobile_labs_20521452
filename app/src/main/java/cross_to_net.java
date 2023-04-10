    public class cross_to_net {
    private String fullName;
    private double grossSalary;

    public cross_to_net(String fullName, double grossSalary) {
        this.fullName = fullName;
        this.grossSalary = grossSalary;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public double getGrossSalary() {
        return grossSalary;
    }

    public void setGrossSalary(double grossSalary) {
        this.grossSalary = grossSalary;
    }

    public double getNetSalary(double taxRate) {
        double netSalary = grossSalary - (grossSalary * taxRate);
        return netSalary;
    }
}
