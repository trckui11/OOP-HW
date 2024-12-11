public class Plus extends BinaryExpression implements Expression {
    public Plus(Expression exp1, Expression exp2) {
        super(exp1, exp2);
    }

    @Override
    public String toString() {
        return "(" + exp1().toString() + " + " + exp2().toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Plus(exp1().assign(var, expression), exp2().assign(var, expression));
    }

    @Override
    public double evaluate() throws Exception {
        double result1 = exp1().evaluate();
        double result2 = exp2().evaluate();
        return result1 + result2;
    }

    @Override
    public Expression differentiate(String var) {
        return new Plus(exp1().differentiate(var), exp2().differentiate(var));
    }

    @Override
    public Expression simplify() {
        Expression newExp1 = exp1().simplify();
        Expression newExp2 = exp2().simplify();
        try {
            if (newExp1.getVariables().isEmpty() && newExp1.evaluate() == 0) {
                return newExp2;
            }
            if (newExp2.getVariables().isEmpty() && newExp2.evaluate() == 0) {
                return exp1().simplify();
            }
            if (newExp1.getVariables().isEmpty() && newExp2.getVariables().isEmpty()) {
                return new Num(newExp1.evaluate() + newExp2.evaluate());
            }
            if (newExp1.getVariables().isEmpty()) {
                return new Plus(new Num(newExp1.evaluate()), newExp2);
            }
            if (newExp2.getVariables().isEmpty()) {
                return new Plus(newExp1, new Num(newExp2.evaluate()));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new Plus(newExp1, newExp2);
    }
}
