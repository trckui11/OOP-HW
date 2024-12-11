public class Neg extends UnaryExpression implements Expression {

    public Neg(Expression exp) {
        super(exp);
    }

    @Override
    public double evaluate() throws Exception {
        return -exp1().evaluate();
    }

    @Override
    public String toString() {
        return "(-" + exp1().toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Neg(exp1().assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        return new Neg(exp1().differentiate(var));
    }

    @Override
    public Expression simplify() {
        return new Neg(exp1().simplify());
    }
}
