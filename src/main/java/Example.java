import java.util.ArrayList;
import java.util.List;

public class Example
{
    /**
     * Start method for solving an quadratic-equationsystem
     * @param args
     */
    public static void main(String[] args)
    {
        QuadEquationSystem system = new QuadEquationSystem();

        system.addPointFromGraph(-4,0);
        system.addPointFromGraph(-2,2);
        system.addPointFromGraph(0,0);

        List<Double> res = system.result();

        System.out.println("y = " + res.get(0) + "x^2 " + res.get(1) + "x " +res.get(2));
    }
}
