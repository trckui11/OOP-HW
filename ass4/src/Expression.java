import java.util.List;
import java.util.Map;

public interface Expression {
    double evaluate(Map<String, Double> assignment) throws Exception;

    double evaluate() throws Exception;

    List<String> getVariables();

    String toString();

    Expression assign(String var, Expression expression);

    Expression differentiate(String var);

    Expression simplify();
}