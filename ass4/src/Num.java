import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Num implements Expression {
    private double num;

    public Num(double num) {
        this.num = num;
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return 0;
    }

    @Override
    public double evaluate() {
        return num;
    }

    @Override
    public String toString() {
        return Double.toString(num);
    }

    @Override
    public List<String> getVariables() {
        return new ArrayList<>();
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return this;
    }

    @Override
    public Expression differentiate(String var) {
        return new Num(0);
    }

    @Override
    public Expression simplify() {
        return this;
    }
}
