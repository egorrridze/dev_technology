package my.egorrridze.trppconverter;

public class Value {
    String name;
    double coefficient;

    public Value(String name, double coefficient) {
        this.name = name;
        this.coefficient = coefficient;
    }

    public String getName() {
        return name;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoefficient(float coefficient) {
        this.coefficient = coefficient;
    }
}
