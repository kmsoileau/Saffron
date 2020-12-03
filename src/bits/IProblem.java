package bits;

import java.util.List;

/**
 * The <code>IProblem</code> interface must be implemented by any class
 * definition of <code>Problem</code> contemplated as an alternative to the
 * <code>Problem</code> class provided by this package.
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.4
 * @since 2004/08/26
 */
public interface IProblem extends Iterable<Object>
{
	/*
	 * Returns <code>true</code> if <code>Problem</code> includes
	 * <code>clause</code>.
	 */
	boolean contains(IClause clause) throws Exception;

	/*
	 * Attempts to satisfy the <code>Problem</code>, returns results in an instance
	 * of <code>IProblemMessage</code>.
	 */
	IProblemMessage findModel(org.sat4j.specs.ISolver solver) throws Exception;

	/*
	 * Returns a <code>List</code> containing the <code>IBooleanVariable</code>s
	 * that appear in any of the <code>Problem</code>'s <code>IClause</code>s.
	 */
	java.util.List<?> getBooleanVariables() throws Exception;

	IClause getClause(int i);

	IClause[] getClauses();

	IProblem resolve(List<IBooleanLiteral> ib) throws Exception;

	void setClauses(IClause[] c) throws Exception;

	int size();
}