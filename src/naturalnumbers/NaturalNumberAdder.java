package naturalnumbers;

import bits.BitFixer;
import bits.Conjunction;
import bits.IBooleanVariable;
import bits.IClause;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;
import bits.ThreeBitAdder;
import bits.TwoBitAdder;

/**
 * An extension of the Problem class which implements an adder of two
 * NaturalNumbers.
 *
 * In one way to use this class, one passes INaturalNumber X, INaturalNumber Y,
 * INaturalNumber Z and INaturalNumber C to the appropriate constructor. The
 * NaturalNumberAdder object produced is a Problem, and one may manipulate it
 * using any of the methods provided by the Problem class.
 *
 * For example, when the Problem instance p defined by
 *
 * <p>
 * <code>Problem p = new NaturalNumberAdder(X,Y,Z,C);</code>
 * </p>
 *
 * is satisfied, the following truth equation will be satisfied:
 *
 * <p>
 * <code>Z == X + Y</code>
 * </p>
 *
 * and the carry bits will be found in C.
 *
 * If the carry bits are of no interest, one may instead write
 *
 * <p>
 * <code>Problem p = new NaturalNumberAdder(X,Y,Z);</code>
 * </p>
 *
 * @author Kerry Michael Soileau <blockquote>
 * 
 *         <pre>
 * ksoileau2@yahoo.com
 * http://kerrysoileau.com/index.html
 * </pre>
 * 
 *         </blockquote>
 * @version 1.2, 05/03/08
 * @see IBooleanVariable
 * @see IClause
 * @see IProblem
 * @see Problem
 */

public class NaturalNumberAdder extends Problem implements IProblem
{
	public NaturalNumberAdder(INaturalNumber X, INaturalNumber Y,
			INaturalNumber Z) throws Exception
	{
		this(X, Y, Z, new NaturalNumber());
	}

	public NaturalNumberAdder(INaturalNumber X, INaturalNumber Y,
			INaturalNumber Z, INaturalNumber C) throws Exception
	{
		IProblem[] stagingArray = new IProblem[NaturalNumber.getLength() + 1];
		int stagingIndex = 0;
		stagingArray[stagingIndex++] = new TwoBitAdder(Y.getBooleanVariable(0),
				X.getBooleanVariable(0), Z.getBooleanVariable(0),
				C.getBooleanVariable(0));
		for (int i = 1; i < NaturalNumber.getLength(); i++)
			stagingArray[stagingIndex++] = new ThreeBitAdder(
					C.getBooleanVariable(i - 1), Y.getBooleanVariable(i),
					X.getBooleanVariable(i), Z.getBooleanVariable(i),
					C.getBooleanVariable(i));
		stagingArray[stagingIndex++] = new BitFixer(
				C.getBooleanVariable(NaturalNumber.getLength() - 1), false);
		this.setClauses(new Conjunction(stagingArray).getClauses());
	}
}