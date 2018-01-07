import java.util.ArrayList;
import java.util.List;

/**
 * Solves a quadratic-equationsystem by using Cramer's rules
 * @author Frithjof Hoppe
 */
public class QuadEquationSystem {
    int equations = 1;
    int decimalPlace = 2;

    ArrayList<ArrayList<Double>> rawNumbers = new ArrayList<ArrayList<Double>>();

    public QuadEquationSystem() {

    }

    /**
     * Info: Pay attention by addin decimal values
     * @param a
     * @param b
     */
    public void addPointFromGraph(double a, double b) {
//       a*x^2 + b*x + c = y
        if (equations <= 3) {
            ArrayList<Double> temp = new ArrayList<Double>();
            temp.add(Math.pow(a, 2));
            temp.add(a);
            temp.add(1.0);
            temp.add(b);
            rawNumbers.add(temp);
            equations++;
        }
    }

    private void setDecimalPlace(int place)
    {
        if(place >= 1 && place <= 6)
        {
            this.decimalPlace = place;
        }
    }

    public List<Double> result() {
        List<Double> result = new ArrayList();

        double d, d1, d2, d3 = 0.0;

        if (equations < 3) {
            System.out.println("Only " + Double.toString(equations) + " of 3 points added");
            return null;
        }

        double[][] beginMatrix = createBeginMatrix(rawNumbers);
        double[][] d1Matrix = createDMatrix(rawNumbers, 0);
        double[][] d2Matrix = createDMatrix(rawNumbers, 1);
        double[][] d3Matrix = createDMatrix(rawNumbers, 2);

        d = getDeterminant(beginMatrix);
        d1 = getDeterminant(d1Matrix);
        d2 = getDeterminant(d2Matrix);
        d3 = getDeterminant(d3Matrix);

        result.add(d1/d);
        result.add(d2/d);
        result.add(d3/d);
        return result;
    }

    /**
     * Creates a matrix in which the solution values are placed at a certain position
     * @param rawNumbers
     * @param position:
     * @return
     */
    private double[][] createDMatrix(ArrayList<ArrayList<Double>> rawNumbers, int position) {
        int counter = 0;
        double[][] result = new double[3][3];
        for (int a = 0; a <= 2; a++) {
            for (int b = 0; b <= 2; b++) {
                result[a][b] = rawNumbers.get(a).get(b);
            }
        }

        for(int a = 0; a <= 2; a++)
        {
            result[a][position] = rawNumbers.get(a).get(rawNumbers.get(a).size()-1);
        }

        return result;
    }

    /**
     * Creates the first
     * @param rawNumbers
     * @return
     */
    private double[][] createBeginMatrix(ArrayList<ArrayList<Double>> rawNumbers) {
        int counter = 0;
        double[][] result = new double[3][3];
        for (ArrayList<Double> list : rawNumbers) {
            for (int a = 0; a < list.size() - 1; a++) {
                result[counter][a] = list.get(a);
            }
            counter++;
        }
        return result;
    }

    private double[][] createBigMatrix(ArrayList<ArrayList<Double>> rawNumbers) {
        double[][] result = new double[2][2];
        int counter = 0;

        for (ArrayList<Double> list : rawNumbers) {
            for (int a = 0; a < list.size(); a++) {
                result[counter][a] = list.get(a);
            }
            counter++;
        }
        return result;
    }

    private boolean isMatrixValid(double[][] in) {
        if (in.length == 2) {
            for (int a = 0; a < 2; a++) {
                if (in[a].length != 2) {
                    return false;
                }
            }
        }

        return true;
    }

    private double[][] extendArray(double[][] in) {
        double[][] result = new double[3][5];
        for (int a = 0; a < in.length; a++) {
            for (int b = 0; b < in[a].length; b++) {
                result[a][b] = in[a][b];
            }
        }

        for (int a = 3; a <= 4; a++) {
            for (int b = 0; b <= 2; b++) {
                result[b][a] = result[b][a - 3];
            }
        }
        return result;
    }

    private double getDeterminant(double[][] matrix) {
        double result = 0.0;
        double[][] temp = new double[3][5];

        if (!isMatrixValid(matrix)) {
            return Double.parseDouble(null);
        }

        temp = extendArray(matrix);
        double tResult = 0;

        for (int a = 0; a <= 2; a++) {
            for (int b = 0; b <= 2; b++) {
                double t = temp[b][a + b];
                if (b == 0) {
                    tResult = t;
                } else {
                    tResult *= t;
                }
            }
            result += tResult;
        }

        for (int a = 0; a <= 2; a++) {
            for (int b = 0; b <= 2; b++) {
                double t = temp[2 - b][a + b];
                if (b == 0) {
                    tResult = t;
                } else {
                    tResult *= t;
                }
            }
            result -= tResult;
        }

        System.out.println("Determinant of matrix: " + result);

        return result;
    }
}
