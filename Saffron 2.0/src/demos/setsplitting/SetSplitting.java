package demos.setsplitting;

import java.util.List;

import bits.BitFixer;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Conjunction;
import bits.IBitString;
import bits.IBooleanLiteral;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;
import bitstrings.BitString;
import bitstrings.BitStringNonDominator;
import bitstrings.BitStringNoter;

/**
*
* @author Kerry Michael Soileau
*         <p>
*         email: ksoileau2@yahoo.com
*         <p>
*         website: http://kerrysoileau.com/index.html
* @version 1.0
* @since 2018/12/04
*/
public class SetSplitting
{
	static void solve(boolean[][] data) throws Exception
	{
		int degree = data[0].length;
		int strings = data.length;
		IProblem problem = Problem.newProblem();
		IBitString[] X = new BitString[strings];
		for (int i = 0; i < strings; i++)
		{
			X[i] = new BitString("X" + i, new IBooleanVariable[degree]);
			String ret = "";
			for (int j = 0; j < degree; j++)
			{
				boolean value = data[i][j];
				ret += value ? "1" : "0";
				X[i].setBooleanVariable(
						j,
						BooleanVariable.getBooleanVariable(X[i].getName() + "_"
								+ j));
				problem = new Conjunction(problem, new BitFixer(
						X[i].getBooleanVariable(j), value));
			}
			System.out.println(ret);
		}

		IBitString S1 = new BitString(degree);
		IBitString S2 = new BitString(degree);

		IProblem complement = new BitStringNoter(S1, S2);

		problem = new Conjunction(problem, complement);

		for (int i = 0; i < strings; i++)
			problem = new Conjunction(problem, new BitStringNonDominator(X[i],
					S1), new BitStringNonDominator(X[i], S2));

		List<IBooleanLiteral> s = problem.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println("S1= " + S1.toBits());
			System.out.println("S2= " + S2.toBits());
		}
		else
			System.out.println("No solution.");
	}
}
