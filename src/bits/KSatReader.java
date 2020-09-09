package bits;

import java.util.ArrayList;

/**
 * A reader which is created using an object of type
 * <code>positronic.satisfiability.elements.ISolver</code> and has a method
 * <code>parseInstance</code> which translates a
 * <code>positronic.satisfiability.elements.IProblem</code> object into a
 * corresponding <code>org.sat4j.specs.IProblem</code> object.
 * 
 * For example, here's how a <code>KSatReader</code> might be used on a
 * <code>positronic.satisfiability.elements.IProblem</code> called
 * <code>problem</code>:
 * 
 * <p>
 * <code>...</code>
 * </p>
 * <p>
 * <code>ISolver solver = ... // some solver from a solver factory</code>
 * </p>
 * <p>
 * <code>KSatReader reader = new KSatReader(solver);</code>
 * </p>
 * <p>
 * <code>positronic.satisfiability.elements.IProblem problem = ... // some problem from KSat API</code>
 * </p>
 * <p>
 * <code>org.sat4j.specs.IProblem sat4jproblem = reader.parseInstance(problem);</code>
 * </p>
 * <p>
 * <code>if (sat4jproblem.isSatisfiable()) {</code>
 * </p>
 * <p>
 * <code>...</code>
 * </p>
 * <p>
 * <code>}</code>
 * </p>
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.1
 * @since 2006/01/29
 * 
 * @see org.sat4j.core.VecInt
 * @see org.sat4j.specs.IVecInt
 */
public class KSatReader
{
	static org.sat4j.specs.IProblem parseInstance(IProblem problem) throws bits.exceptions.UnsolvableProblemException
	{
		Sat4j.DIMACSBVassign(problem);

		Sat4j.DIMACSBLassign(problem);

		Sat4j.createSolver(problem);

		return Sat4j.getSolver();
	}

	static ArrayList<IBooleanLiteral> toBooleanLiterals(int[] dimacs)
	{
		ArrayList<IBooleanLiteral> cl = new ArrayList<IBooleanLiteral>();
		for (int i = 0; i < dimacs.length; i++)
		{
			try
			{
				IBooleanLiteral curr = Sat4j.getBL(dimacs[i]);
				if (curr != null)
					cl.add(curr);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return cl;
	}
}