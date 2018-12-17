package demos.bitstrings;

import java.util.List;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBitString;
import bits.IBooleanLiteral;
import bits.IProblem;
import bits.Problem;
import bitstrings.BitString;
import bitstrings.BitStringBitAnder;
import bitstrings.BitStringFixer;

/**
 * Given a collection <code>C</code> of <code>IBitString</code>s each of size
 * <code>n</code>, and a bit position <code>i</code>, constrain
 * <code>targetBitString</code> to be the <code>AND</code> of the members of
 * <code>C</code> for which bit position <code>i</code> is <code>true</code>.
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since 2018/12/15
 */
public class BitStringBitAnderDemo
{
	public static void main(String[] args) throws Exception
	{
		IBitString[] C = new IBitString[]
		{ new BitString("011110000000000"), new BitString("000011110000000"),
				new BitString("000000010000111"),
				new BitString("001100000111000"),
				new BitString("100000000000000") };

		IBitString targetBitString = new BitString(C[0].size());

		int pos = 7;

		IProblem problem = new Conjunction(new BitStringFixer(C),
				new BitStringBitAnder(C, pos, targetBitString));

		List<IBooleanLiteral> s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			for (int i = 0; i < C.length; i++)
				System.out.println(C[i].toBits());
			System.out.println("targetBitString\n" + targetBitString.toBits());
		}
		else
			System.out.println("No solution.");
	}
}