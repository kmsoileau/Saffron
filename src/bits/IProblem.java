package bits;

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
	boolean contains(IClause clause) throws Exception;

	IProblemMessage findModel(org.sat4j.specs.ISolver solver) throws Exception;

	java.util.List<?> getBooleanVariables() throws Exception;

	IClause getClause(int i);

	IClause[] getClauses();

	void setClauses(IClause[] c) throws Exception;

	int size();
}