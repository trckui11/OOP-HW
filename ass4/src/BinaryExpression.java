import java.util.List;

public abstract class BinaryExpression extends BaseExpression {
    private Expression exp2;

    public BinaryExpression(Expression exp1, Expression exp2) {
        super(exp1);
        this.exp2 = exp2;
    }

    public Expression exp2() {
        return this.exp2;
    }

    @Override
    public List<String> getVariables() {
        List<String> exp1Vars = exp1().getVariables();
        List<String> exp2Vars = exp2.getVariables();
        if (!exp2Vars.isEmpty()) {
            exp1Vars.addAll(exp2Vars);
        }
        return exp1Vars;
    }
}
