/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Feb 27, 2019
 */
package demos.integers;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IProblemMessage;
import bits.Problem;
import integers.IInteger;
import integers.Integer;
import integers.IntegerFixer;
import integers.NegativeInteger;
import naturalnumbers.NaturalNumber;

/**
 * 
 *
 */
public class NegativeIntegerDemo
{

	public static void main(String[] args) throws Exception
	{
		NaturalNumber.setLargestNaturalNumber(23);

		IInteger a = new Integer(23);
		IInteger b = new Integer(0);
		IInteger c = new Integer(-12);

		IProblemMessage s = new Conjunction(new IntegerFixer(a),
				new NegativeInteger(a)).findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println(a);
		}
		else
			System.out.println("No solution.");

		s = new Conjunction(new IntegerFixer(b), new NegativeInteger(b))
				.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println(b);
		}
		else
			System.out.println("No solution.");

		s = new Conjunction(new IntegerFixer(c), new NegativeInteger(c))
				.findModel(Problem.defaultSolver());
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println(c);
		}
		else
			System.out.println("No solution.");
	}
}
