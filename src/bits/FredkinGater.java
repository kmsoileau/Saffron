package bits;

/**
 * An extension of the Problem class which implements a Fredkin gate. For
 * example, the IProblem p defined by
 *
 * <p>
 * <code>IProblem p=new FredkinGater(A,B,C,P,Q,R);</code>
 * </p>
 *
 * is satisfied if and only if the following Boolean relation is satisfied:
 *
 * <p>
 * A B C | P Q R
 * <p>
 * ------|------
 * <p>
 * 0 0 0 | 0 0 0
 * <p>
 * 0 0 1 | 0 0 1
 * <p>
 * 0 1 0 | 0 1 0
 * <p>
 * 0 1 1 | 0 1 1
 * <p>
 * 1 0 0 | 1 0 0
 * <p>
 * 1 0 1 | 1 1 0
 * <p>
 * 1 1 0 | 1 0 1
 * <p>
 * 1 1 1 | 1 1 1
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since 2006/04/08
 */
public class FredkinGater extends Problem implements IProblem
{
	public FredkinGater(IBooleanVariable A, IBooleanVariable B, IBooleanVariable C, IBooleanVariable P,
			IBooleanVariable Q, IBooleanVariable R) throws Exception
	{
		// A=P
		IProblem p1 = new BitEqualizer(A, P);
		// A | (B=Q)&(C=R)
		IProblem p2 = new Disjunction(new BitFixer(A, true),
				new Conjunction(new BitEqualizer(B, Q), new BitEqualizer(C, R)));
		// $A | (B=R)&(C=Q)
		IProblem p3 = new Disjunction(new BitFixer(A, false),
				new Conjunction(new BitEqualizer(B, R), new BitEqualizer(C, Q)));

		this.setClauses(new Conjunction(p1, p2, p3).getClauses());
	}
}