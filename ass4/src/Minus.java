public class Minus extends BinaryExpression implements Expression {
    public Minus(Expression exp1, Expression exp2) {
        super(exp1, exp2);
    }

    @Override
    public String toString() {
        return "(" + exp1().toString() + " - " + exp2().toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Minus(exp1().assign(var, expression), exp2().assign(var, expression));
    }

    @Override
    public double evaluate() throws Exception {
        return exp1().evaluate() - exp2().evaluate();
    }

    @Override
    public Expression differentiate(String var) {
        return new Minus(exp1().differentiate(var), exp2().differentiate(var));
    }

    @Override
    public Expression simplify() {
        Expression newExp1 = exp1().simplify();
        Expression newExp2 = exp2().simplify();
        try {
            if (newExp1.getVariables().isEmpty() && newExp1.evaluate() == 0) {
                return new Neg(newExp2);
            }
            if (newExp2.getVariables().isEmpty() && newExp2.evaluate() == 0) {
                return newExp1;
            }
            if (newExp1.toString().equals(newExp2.toString())) {
                return new Num(0);
            }
            if (newExp1.getVariables().isEmpty() && exp2().getVariables().isEmpty()) {
                return new Num(newExp1.evaluate() - exp2().evaluate());
            }
            if (newExp1.getVariables().isEmpty()) {
                return new Minus(new Num(newExp1.evaluate()), newExp2);
            }
            if (exp2().getVariables().isEmpty()) {
                return new Minus(newExp1, new Num(newExp2.evaluate()));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new Minus(newExp1, newExp2);
    }
}
