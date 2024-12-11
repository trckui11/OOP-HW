public class Cos extends UnaryExpression implements Expression {
    public Cos(Expression exp) {
        super(exp);
    }

    @Override
    public double evaluate() throws Exception {
        return Math.cos(Math.toRadians(exp1().evaluate()));
    }

    @Override
    public String toString() {
        return "cos(" + exp1().toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Cos(exp1().assign(var, expression));
    }

    @Override
    public Expression differentiate(String var) {
        return new Mult(exp1().differentiate(var),
                        new Neg(new Sin(exp1())));
    }

    @Override
    public Expression simplify() {
        return new Cos(exp1().simplify());
    }
}
