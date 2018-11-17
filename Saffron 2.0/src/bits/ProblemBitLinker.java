package bits;

/**
 * This class links the value of an IBooleanVariable to the truth value of a
 * given IProblem.
 * @author Kerry Michael Soileau <blockquote>
 * <pre>
 * ksoileau2@yahoo.com
 * </pre>
 * @version 1.0, 05/12/26
 */
public class ProblemBitLinker extends Problem implements IProblem
{
	public ProblemBitLinker(IProblem p, IBooleanVariable b) throws Exception
	{
		IClause left = new Clause();
		left.add((BooleanLiteral) BooleanLiteral.getBooleanLiteral(b, false));
		IProblem pl = new Problem(p.getClauses());
		pl.addClause(left);

		IClause right = new Clause();
		right.add((BooleanLiteral) BooleanLiteral.getBooleanLiteral(b, true));
		IProblem pr = new ProblemDenier(p);
		pr.addClause(right);

		IProblem result = new Disjunction(pl, pr);

		this.setClauses(result.getClauses());
	}
}
