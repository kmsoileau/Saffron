package bits;

/**
 * An extension of the Problem class which implements a Toffoli gate. For
 * example, the IProblem p defined by
 *
 * <p>
 * <code>IProblem p=new ToffoliGater(A,B,C,P,Q,R);</code>
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
 * 1 0 1 | 1 0 1
 * <p>
 * 1 1 0 | 1 1 1
 * <p>
 * 1 1 1 | 1 1 0
 * 
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since 2006/04/08
 */
public class ToffoliGater extends Problem implements IProblem
{
	public ToffoliGater(IBooleanVariable A, IBooleanVariable B,
			IBooleanVariable C, IBooleanVariable P, IBooleanVariable Q,
			IBooleanVariable R) throws Exception
	{
		// p=a, q=b and r=c XOR (a AND b).
		// P=A
		IProblem p1 = new BitEqualizer(A, P);
		// Q=B
		IProblem p2 = new BitEqualizer(B, Q);
		// R=C XOR (A AND B)
		IBooleanVariable X = BooleanVariable.getBooleanVariable("X");
		IProblem p3 = new BitAnder(A, B, X);
		IProblem p4 = new BitXorer(C, X, R);
		this.setClauses(new Conjunction(p1, p2, p3, p4).getClauses());
	}
}