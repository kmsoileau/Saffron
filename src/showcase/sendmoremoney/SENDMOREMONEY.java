package showcase.sendmoremoney;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;

/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Mar 22, 2019
 */

import naturalnumbers.NaturalNumber;
import naturalnumbers.NaturalNumberAdder;
import naturalnumbers.NaturalNumberDigiter;
import naturalnumbers.NaturalNumberFixer;
import naturalnumbers.NaturalNumberPositiver;
import naturalnumbers.lists.INaturalNumberList;
import naturalnumbers.lists.NaturalNumberList;
import naturalnumbers.lists.NaturalNumberListDotter;
import naturalnumbers.lists.NaturalNumberListFixer;
import naturalnumbers.lists.NaturalNumberListNonrepeater;

public class SENDMOREMONEY
{
	public static void main(String[] args) throws Exception
	{
		/**
		 * Set Java variables:
		 */

		/**
		 * Set globals:
		 */
		
		NaturalNumber.setLargestNaturalNumber(99999);

		/**
		 * Create Saffron objects and arrays:
		 */

		INaturalNumber S = new NaturalNumber("S");
		INaturalNumber E = new NaturalNumber("E");
		INaturalNumber N = new NaturalNumber("N");
		INaturalNumber D = new NaturalNumber("D");
		INaturalNumber M = new NaturalNumber("M");
		INaturalNumber O = new NaturalNumber("O");
		INaturalNumber R = new NaturalNumber("R");
		INaturalNumber Y = new NaturalNumber("Y");
		INaturalNumber zero = new NaturalNumber(0);
		INaturalNumber SENDvalue = new NaturalNumber();
		INaturalNumber MOREvalue = new NaturalNumber();
		INaturalNumber MONEYvalue = new NaturalNumber();

		INaturalNumberList digits = new NaturalNumberList(new INaturalNumber[]
		{ S, E, N, D, M, O, R, Y });
		INaturalNumberList SEND = new NaturalNumberList(new INaturalNumber[]
		{ zero, S, E, N, D });
		INaturalNumberList MORE = new NaturalNumberList(new INaturalNumber[]
		{ zero, M, O, R, E });
		INaturalNumberList MONEY = new NaturalNumberList(new INaturalNumber[]
		{ M, O, N, E, Y });
		INaturalNumberList decimals = new NaturalNumberList(new long[]
		{ 10000, 1000, 100, 10, 1 });

		/**
		 * Create problems which constrain the values of these Saffron objects:
		 */

		IProblem fixDecimals = new Conjunction(new NaturalNumberListFixer(
				decimals));
		IProblem computeSENDvalue = new NaturalNumberListDotter(decimals, SEND,
				SENDvalue);
		IProblem computeMOREvalue = new NaturalNumberListDotter(decimals, MORE,
				MOREvalue);
		IProblem computeMONEYvalue = new NaturalNumberListDotter(decimals,
				MONEY, MONEYvalue);
		IProblem imposeSum = new NaturalNumberAdder(SENDvalue, MOREvalue,
				MONEYvalue);
		IProblem imposeDigitConstraint = new Conjunction(new IProblem[]
		{ new NaturalNumberDigiter(S), new NaturalNumberDigiter(E),
				new NaturalNumberDigiter(N), new NaturalNumberDigiter(D),
				new NaturalNumberDigiter(M), new NaturalNumberDigiter(O),
				new NaturalNumberDigiter(R), new NaturalNumberDigiter(Y) });
		IProblem DifferentLettersAreDistinctDigits = new NaturalNumberListNonrepeater(
				digits);
		IProblem zeroEquals0 = new NaturalNumberFixer(zero);
		IProblem MDigitIsNonZero = new NaturalNumberPositiver(M);

		/**
		 * Create the IProblem of satisfying all of these constraining problems:
		 */

		IProblem problem = new Conjunction(new IProblem[]
		{ fixDecimals, computeSENDvalue, computeMOREvalue, computeMONEYvalue,
				imposeSum, imposeDigitConstraint, MDigitIsNonZero,
				DifferentLettersAreDistinctDigits, zeroEquals0 });

		/**
		 * Solve the IProblem:
		 */
		
		IProblemMessage s = problem.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());

			System.out.println("S = " + S);
			System.out.println("E = " + E);
			System.out.println("N = " + N);
			System.out.println("D = " + D);
			System.out.println("M = " + M);
			System.out.println("O = " + O);
			System.out.println("R = " + R);
			System.out.println("Y = " + Y);

			System.out.println("SENDvalue = " + SENDvalue);
			System.out.println("MOREvalue = " + MOREvalue);
			System.out.println("MONEYvalue = " + MONEYvalue);
		}
		else
			System.out.println("No solution.");
	}
}