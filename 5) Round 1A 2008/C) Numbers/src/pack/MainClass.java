package pack;

import java.io.File;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Scanner;

public class MainClass {

	private static final BigDecimal SQRT_DIG = new BigDecimal(150);
	private static final BigDecimal SQRT_PRE = new BigDecimal(10).pow(SQRT_DIG
			.intValue());

	/**
	 * Private utility method used to compute the square root of a BigDecimal.
	 * 
	 * @author Luciano Culacciatti
	 * @url http://www.codeproject.com/Tips/257031/Implementing-SqrtRoot-in-
	 *      BigDecimal
	 */
	private static BigDecimal sqrtNewtonRaphson(BigDecimal c, BigDecimal xn,
			BigDecimal precision) {
		BigDecimal fx = xn.pow(2).add(c.negate());
		BigDecimal fpx = xn.multiply(new BigDecimal(2));
		BigDecimal xn1 = fx.divide(fpx, 2 * SQRT_DIG.intValue(),
				RoundingMode.HALF_DOWN);
		xn1 = xn.add(xn1.negate());
		BigDecimal currentSquare = xn1.pow(2);
		BigDecimal currentPrecision = currentSquare.subtract(c);
		currentPrecision = currentPrecision.abs();
		if (currentPrecision.compareTo(precision) <= -1) {
			return xn1;
		}
		return sqrtNewtonRaphson(c, xn1, precision);
	}

	/**
	 * Uses Newton Raphson to compute the square root of a BigDecimal.
	 * 
	 * @author Luciano Culacciatti
	 * @url http://www.codeproject.com/Tips/257031/Implementing-SqrtRoot-in-
	 *      BigDecimal
	 */
	public static BigDecimal bigSqrt(BigDecimal c) {
		return sqrtNewtonRaphson(c, new BigDecimal(1),
				new BigDecimal(1).divide(SQRT_PRE));
	}

	public static int linearSolution(int n) {

		BigInteger a = BigInteger.valueOf(3), b = BigInteger.valueOf(1);
		BigInteger na = BigInteger.valueOf(3), nb = BigInteger.valueOf(1), oa = BigInteger
				.valueOf(3), ob = BigInteger.valueOf(1);

		BigInteger five = BigInteger.valueOf(5);

		for (int i = 1; i < n; i++) {
			ob = nb;
			oa = na;
			na = (a.multiply(oa)).add(five.multiply(b).multiply(ob));
			nb = (a.multiply(ob)).add(b.multiply(oa));
		}

		BigDecimal rootFive = bigSqrt(new BigDecimal(five));
		BigDecimal product = rootFive.multiply(new BigDecimal(nb));
		BigDecimal sum = product.add(new BigDecimal(na));

		return Integer.parseInt(sum.toString().substring(
				(sum.toString().indexOf('.') - 3) >= 0 ? sum.toString()
						.indexOf('.') - 3 : 0, sum.toString().indexOf('.')));
	}

	public static void main(String[] args) throws Exception {

		Scanner scan = new Scanner(new File("input.in"));
		PrintWriter out = new PrintWriter(new File("output.out"));

		int T = scan.nextInt(), solution;

		for (int cT = 1; cT <= T; cT++) {
			solution = linearSolution(scan.nextInt());
			out.print("Case #" + cT + ": ");
			if (solution < 100)
				out.print("0");
			if (solution < 10)
				out.print("0");
			if (solution < 1)
				out.print("0");
			out.println(solution);
		}

		scan.close();
		out.close();
	}
}
