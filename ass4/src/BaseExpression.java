import java.util.List;
import java.util.Map;

public abstract class BaseExpression {
    private Expression exp1;

    public BaseExpression(Expression exp) {
        this.exp1 = exp;
    }

    public Expression exp1() {
        return this.exp1;
    }

    public abstract String toString();

    public double evaluate(Map<String, Double> assignment) throws Exception {
        if (assignment.isEmpty()) {
            throw new Exception("No assignment");
        }
        BaseExpression result = this;
        for (String key : assignment.keySet()) {
            result = (BaseExpression) result.assign(key, new Num(assignment.get(key)));
        }
        if (!result.getVariables().isEmpty()) {
            throw new Exception("Not all variables assigned");
        }
        return result.evaluate();
    }

    public abstract List<String> getVariables();

    public abstract double evaluate() throws Exception;

    public abstract Expression assign(String var, Expression expression);
}
