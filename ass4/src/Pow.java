public class Pow extends BinaryExpression implements Expression {
    public Pow(Expression exp1, Expression exp2) {
        super(exp1, exp2);
    }

    @Override
    public String toString() {
        return "(" + exp1().toString() + "^" + exp2().toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Pow(exp1().assign(var, expression), exp2().assign(var, expression));
    }

    @Override
    public double evaluate() throws Exception {
        return Math.pow(exp1().evaluate(), exp2().evaluate());
    }

    @Override
    public Expression differentiate(String var) {
        return new Mult(
                new Pow(exp1(), exp2()),
                new Plus(
                        new Mult(exp1().differentiate(var),
                                 new Div(exp2(), exp1())),
                        new Mult(exp2().differentiate(var),
                                 new Log(new Var("e"), exp1()))
                )
        );
    }

    @Override
    public Expression simplify() {
        Expression newExp1 = exp1().simplify();
        Expression newExp2 = exp2().simplify();
        try {
            if (newExp1.getVariables().isEmpty() && newExp2.getVariables().isEmpty()) {
                return new Num(Math.pow(newExp1.evaluate(), newExp2.evaluate()));
            }
            if (newExp1.getVariables().isEmpty()) {
                return new Pow(new Num(newExp1.evaluate()), newExp2);
            }
            if (exp2().getVariables().isEmpty()) {
                return new Pow(newExp1, new Num(newExp2.evaluate()));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new Pow(newExp1, newExp2);
    }
}
