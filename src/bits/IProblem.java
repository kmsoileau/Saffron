package bits;

import java.util.ArrayList;
import java.util.List;

import org.sat4j.specs.ISolver;

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
public interface IProblem extends Iterable<IClause>
{
	boolean addClause(IClause clause) throws Exception;

	void addClauseVoid(IClause clause) throws Exception;

	IProblem and(IProblem problem) throws Exception;

	boolean contains(IClause clause) throws Exception;

	IProblemMessage findModel() throws Exception;

	IProblemMessage findModel(ISolver solver) throws Exception;

	IProblemMessage[] findTwoModels(IBooleanVariable booleanVariable)
			throws Exception;

	ArrayList<IBooleanVariable> getBooleanVariables() throws Exception;

	IClause getClause(int i);

	List<IClause> getClauses();

	int numberOfClauses();

	void removeClause(int i);

	void setClause(int i, IClause clause);

	void setClauses(IClause[] clauseArray) throws Exception;

	int size();
	
	void sort() throws Exception;
}