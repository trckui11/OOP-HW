import org.w3c.dom.ls.LSOutput;

import java.util.Map;
import java.util.TreeMap;

public class ExpressionsTest {
    public static void main(String[] args) throws Exception {
        // 1: (2x) + (sin(4y)) + (e^x)
        Expression exp = new Plus(new Mult(new Num(2), new Var("x")), new Plus(new Sin(new Mult(new Num(4), new Var("y"))), new Pow(new Var("e"), new Var("x"))));

        // 2:
        System.out.println(exp);

        // 3:
        Map<String, Double> assignment = new TreeMap<>();
        assignment.put("x", 2.0);
        assignment.put("y", 0.25);
        assignment.put("e", 2.71);
        System.out.println(exp.evaluate(assignment));

        // 4:
        Expression diff = exp.differentiate("x");
        System.out.println(diff);

        // 5:
        System.out.println(diff.evaluate(assignment));

        // 6:
        System.out.println(diff.simplify());
    }
}
