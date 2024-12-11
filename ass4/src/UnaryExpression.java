import java.util.List;

public abstract class UnaryExpression extends BaseExpression {
    public UnaryExpression(Expression exp) {
        super(exp);
    }

    @Override
    public List<String> getVariables() {
        return exp1().getVariables();
    }
}
