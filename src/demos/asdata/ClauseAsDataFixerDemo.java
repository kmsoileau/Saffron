/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Mar 11, 2019
 */
package demos.asdata;

import java.util.ArrayList;

import asdata.ClauseAsData;
import asdata.ClauseAsDataFixer;
import asdata.IClauseAsData;
import bits.BooleanLiteral;
import bits.BooleanVariable;
import bits.Clause;
import bits.Conjunction;
import bits.IBooleanVariable;
import bits.IClause;
import bits.IProblem;
import bits.IProblemMessage;
import bits.Problem;

/**
 * 
 *
 */
public class ClauseAsDataFixerDemo
{
	public static void main(String[] args) throws Exception
	{
		IBooleanVariable A = BooleanVariable.getBooleanVariable("A");
		IBooleanVariable B = BooleanVariable.getBooleanVariable("B");
		IBooleanVariable C = BooleanVariable.getBooleanVariable("C");
		IBooleanVariable D = BooleanVariable.getBooleanVariable("D");
		IBooleanVariable E = BooleanVariable.getBooleanVariable("E");
		IBooleanVariable F = BooleanVariable.getBooleanVariable("F");

		ArrayList<IBooleanVariable> ary = new ArrayList<IBooleanVariable>();
		ary.add(B);
		ary.add(A);
		ary.add(F);
		ary.add(E);
		ary.add(C);
		ary.add(D);
		ClauseAsData.declare(ary);

		IClauseAsData c1 = new ClauseAsData();
		IClause clause1 = Clause.newClause().nor(A).or(B);
		IClauseAsData c2 = new ClauseAsData();
		IClause clause2 = Clause.randomClause(ClauseAsData.getVARIABLES()
				.toArray(new IBooleanVariable[0]));

		IProblem prob1 = new ClauseAsDataFixer(c1, clause1);
		IProblem prob2 = new ClauseAsDataFixer(c2, clause2);
		IProblem prob = new Conjunction(prob1, prob2);
		System.out.println(prob);

		IProblemMessage s = prob.findModel(Problem.defaultSolver());
		System.out.println("Reporting...");
		if (s.getStatus() == IProblemMessage.SATISFIABLE)
		{
			BooleanLiteral.interpret(s.getLiterals());
			System.out.println(clause1);
			System.out.println(c1);
			System.out.println(clause2);
			System.out.println(c2);
		}
		else
			System.out.println("No solution.");

		System.out.println(">\t" + c1.toClause());
		System.out.println(">\t" + c2.toClause());
	}
}
