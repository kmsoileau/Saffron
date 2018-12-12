package TBS;

import java.util.List;

import naturalnumbers.BitStringTotaler;
import naturalnumbers.NaturalNumber;
import bits.BitFixer;
import bits.BooleanLiteral;
import bits.Conjunction;
import bits.Disjunction;
import bits.IBitString;
import bits.IBooleanLiteral;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import bitstrings.BitString;
import bitstrings.BitStringFixer;
import bitstrings.BitStringNoter;

/**
 * A collection C of sets and a positive integer K: Does C include K mutually
 * disjoint sets?
 * 
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since 2018/12/12
 */
public class SetPacker extends Problem implements IProblem
{
	public static void main(String[] args) throws Exception
	{
		/*
		 * IBitString[] C = new IBitString[] { new BitString("01001010"), new
		 * BitString("10101010"), new BitString("00001000"), new
		 * BitString("00100000"), new BitString("00101000") };
		 */

		IBitString[] C = new IBitString[]
		{ new BitString("001"), new BitString("010"), new BitString("100") };

		IProblem p1 = Problem.newProblem();
		for (int i = 0; i < C.length; i++)
			p1 = new Conjunction(p1, new BitStringFixer(C[i]));

		int sizeOfPacking = 2;

		IBitString membership = new BitString(C.length);
		INaturalNumber K = new NaturalNumber(sizeOfPacking);

		List<IBooleanLiteral> s = new Conjunction(p1, new BitStringFixer(K),
				new SetPacker(C, K, membership)).findModel(Problem
				.defaultSolver());

		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("C[0]=" + C[0].toBits());
			System.out.println("membership=" + membership.toBits());
			for (int i = 0; i < C.length; i++)
				System.out.println("C[" + i + "]=" + C[i].toBits());
		}
		else
			System.out.println("No solution.");
	}

	public SetPacker(IBitString[] C, INaturalNumber K, IBitString membership)
			throws Exception
	{
		int problemIndex = 0;
		IProblem[] problemArray = new IProblem[(C.length - 1) * C.length / 2
				+ 1];

		problemArray[problemIndex++] = new BitStringTotaler(membership, K);

		for (int i = 0; i < C.length - 1; i++)
			for (int j = i + 1; j < C.length; j++)
				problemArray[problemIndex++] = new Disjunction(new BitFixer(
						membership.getBooleanVariable(i), false), new BitFixer(
						membership.getBooleanVariable(j), false),
						new BitStringNoter(C[i], C[j]));

		IProblem problem = new Conjunction(problemArray);
		this.setClauses(problem.getClauses());

	}

}
