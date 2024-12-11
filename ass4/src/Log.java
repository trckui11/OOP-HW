public class Log extends BinaryExpression implements Expression {
    public Log(Expression exp1, Expression exp2) {
        super(exp1, exp2);
    }

    @Override
    public String toString() {
        return "log(" + exp1().toString() + ", " + exp2().toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Log(exp1().assign(var, expression), exp2().assign(var, expression));
    }

    @Override
    public double evaluate() throws Exception {
        return Math.log(exp2().evaluate()) / Math.log(exp1().evaluate());
    }

    @Override
    public Expression differentiate(String var) {
        return new Minus(new Div(exp2().differentiate(var),
                                 new Mult(exp2(),
                                          new Log(new Var("e"),
                                                  exp1()))),
                         new Div(new Mult(exp1().differentiate(var),
                                          new Log(exp1(), exp2())),
                                 new Mult(exp1(),
                                          new Log(new Var("e"),
                                                  exp1())))
        );
    }

    @Override
    public Expression simplify() {
        // base 1?
        Expression newExp1 = exp1().simplify();
        Expression newExp2 = exp2().simplify();
        try {
            if (newExp1.toString().equals(newExp2.toString())) {
                return new Num(1);
            }
            if (newExp1.getVariables().isEmpty() && newExp2.getVariables().isEmpty()) {
                return new Num(Math.log(newExp2.evaluate()) / Math.log(newExp1.evaluate()));
            }
            if (newExp1.getVariables().isEmpty()) {
                return new Log(new Num(newExp1.evaluate()), newExp2);
            }
            if (newExp2.getVariables().isEmpty()) {
                return new Log(newExp1, new Num(newExp2.evaluate()));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new Log(newExp1, newExp2);
    }
}
