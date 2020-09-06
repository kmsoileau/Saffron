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
package asdata.inwork;

import java.util.ArrayList;

import asdata.ClauseAsData;
import asdata.ClauseAsDataFixer;
import asdata.IClauseAsData;
import bits.BooleanVariable;
import bits.Clause;
import bits.Conjunction;
import bits.IBooleanVariable;
import bits.IClause;
import bits.IProblem;
import bits.Problem;

public class LogicalDifferenceDemo extends Problem implements IProblem
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
		IBooleanVariable[] bvarray = ClauseAsData.getVARIABLES().toArray(
				new IBooleanVariable[0]);
		IClause clause1 = Clause.randomClause(bvarray);
		IClauseAsData c2 = new ClauseAsData();
		IClause clause2 = Clause.randomClause(bvarray);
		IProblem prob1 = null;
		for (int i = 0; i < clause1.size(); i++)
			prob1 = new Conjunction(prob1, new ClauseAsDataFixer(c1, clause1));
		IProblem prob2 = null;
		for (int i = 0; i < clause2.size(); i++)
			prob2 = new Conjunction(prob2, new ClauseAsDataFixer(c2, clause2));
	}
}
