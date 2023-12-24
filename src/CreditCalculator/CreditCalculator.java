package CreditCalculator;

import java.util.Properties;

public class CreditCalculator {

    private String type;
    private double principal;
    private double payment;
    private double interest;
    private double ir;
    private int months;

    public CreditCalculator(Properties properties) {
        try {
            this.type = properties.getProperty("type", "");
            this.principal = Double.parseDouble(properties.getProperty("principal", "0"));
            this.payment = Double.parseDouble(properties.getProperty("payment", "0"));
            this.interest = Double.parseDouble(properties.getProperty("interest", "0"));
            this.months = Integer.parseInt(properties.getProperty("periods", "0"));
        } catch (Exception e) {
            System.out.println("Incorrect parameters");
            System.exit(1);
        }

    }

    public static void main(String[] args) {
        Properties properties = System.getProperties();
        CreditCalculator calculator = new CreditCalculator(properties);
        calculator.conditions();
    }

    public void conditions() {
        boolean containsNegativeNums = false;
        int counter = (!type.isEmpty()) ? 1 : 0;

        for (double element : new double[]{principal, payment, interest, months}) {
            if (element != 0) {
                counter++;
            }

            if (element < 0) {
                containsNegativeNums = true;
            }
        }

        if (counter != 4 ||
            type.isEmpty() ||
            (!"annuity".equals(type) && !"diff".equals(type)) ||
            (type.equals("diff") && payment != 0) ||
            containsNegativeNums ||
            interest == 0
        ) {
            System.out.println("Incorrect parameters!");
        } else {
            if ("diff".equals(type)) {
                calcDiff();
            } else if ("annuity".equals(type)) {
                if (months == 0) {
                    calcN();
                }
                if (payment == 0) {
                    calcA();
                }
                if (principal == 0) {
                    calcP();
                }
            }
        }
    }

    private void calcDiff() {
        int totalPaymentAmount = 0;
        int currentMonth = 1;

        ir = interest / (12 * 100);

        while (currentMonth <= months) {
            int diff = (int) Math.ceil(principal / months + ir * (principal - (principal * (currentMonth - 1) / months)));
            totalPaymentAmount += diff;
            System.out.println("Month " + currentMonth + ": payment is " + diff);
            currentMonth++;
        }

        if (totalPaymentAmount > principal) {
            System.out.println("\nOverpayment = " + (int) (totalPaymentAmount - principal));
        }
    }

    private void calcN() {
        int years = 0;
        ir = interest / (12 * 100);
        months = (int) Math.ceil(logB((payment / (payment - ir * principal)), 1 + ir));

        while (months >= 12) {
            years++;
            months -= 12;
        }

        if (years > 0 && months > 0) {
            System.out.println("It will take " + years + " years and " + months + " months to repay the loan");
        } else if (years > 0 && months == 0) {
            System.out.println("It will take " + years + " years to repay the loan");
        } else if (years == 0) {
            System.out.println("It will take " + months + " months to repay the loan");
        }

        if (months * payment > principal) {
            System.out.println("Overpayment = " + (int) (months * payment - principal));
        }
    }

    private void calcA() {
        ir = interest / (12 * 100);
        payment = (int) Math.ceil(principal * (ir * (1 + ir) * Math.pow(1 + ir, months)) / (Math.pow(1 + ir, months) - 1));

        System.out.println("Your annuity payment = " + payment);
        if (months * payment > principal) {
            System.out.println("Overpayment = " + (int) (months * payment - principal));
        }
    }

    private void calcP() {
        ir = interest / (12 * 100);
        principal = (int) Math.floor(payment / ((ir * Math.pow(1 + ir, months)) / (Math.pow(1 + ir, months) - 1)));

        System.out.println("Your loan principal = " + (int) principal);
        if (months * payment > principal) {
            System.out.println("Overpayment = " + (int) (months * payment - principal));
        }
    }

    private double logB(double value, double base) {
        return Math.log(value) / Math.log(base);
    }
}
