public class Sin extends UnaryExpression implements Expression {

    public Sin(Expression exp) {
        super(exp);
    }

    @Override
    public double evaluate() throws Exception {
        return Math.sin(Math.toRadians(exp1().evaluate()));
    }

    @Override
    public String toString() {
        return "sin(" + exp1().toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Sin(exp1().assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        return new Mult(exp1().differentiate(var),
                new Cos(exp1()));
    }

    @Override
    public Expression simplify() {
        return new Sin(exp1().simplify());
    }
}
