import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Var implements Expression {
    private String var;

    public Var(String var) {
        this.var = var;
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return 0;
    }

    @Override
    public double evaluate() {
        return 0;
    }

    @Override
    public String toString() {
        return var;
    }

    @Override
    public List<String> getVariables() {
        List<String> varList = new ArrayList<>();
        varList.add(this.var);
        return varList;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        if (var.equals(this.var)) {
            return expression;
        }
        return this;
    }

    @Override
    public Expression differentiate(String var) {
        if (var.equals(this.var)) {
            return new Num(1);
        }
        return new Num(0);
    }

    @Override
    public Expression simplify() {
        return this;
    }
}
