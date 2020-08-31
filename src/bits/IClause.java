package bits;

import java.util.ArrayList;
import java.util.List;

import bits.exceptions.ClauseException;

/**
 * The <code>IClause</code> interface must be implemented by any class
 * definition of <code>Clause</code> contemplated as an alternative to the
 * <code>Clause</code> class provided by this package.
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.1
 * @since 2004/08/26
 */
public interface IClause extends Comparable<Object>
{
	boolean add(BooleanLiteral b) throws Exception;

	void addLiteral(IBooleanLiteral bl) throws Exception;

	Object clone();

	int compareTo(IClause o) throws Exception;

	boolean contains(IBooleanLiteral bl) throws Exception;

	boolean equals(IClause o) throws Exception;

	boolean evaluate();

	IBooleanVariable[] getBooleanVariables();

	void getBooleanVariables(List<IBooleanVariable> hs) throws Exception;

	ArrayList<IBooleanVariable> getBooleanVariablesList();

	int getIndex(IBooleanLiteral bl);

	IBooleanLiteral getLiteral(IBooleanVariable bv) throws ClauseException;

	IBooleanLiteral getLiteralAt(int n) throws Exception;

	boolean isEmpty();

	boolean isMemberOf(List<IClause> h) throws Exception;

	boolean isSatisfied();

	IClause nor(IBooleanVariable bv) throws Exception;

	IClause or(IBooleanVariable bv) throws Exception;

	IClause orNot(IBooleanVariable bv) throws Exception;

	boolean remove(IBooleanLiteral b);

	IBooleanLiteral removeClause(int i);

	IClause resolve(IBooleanLiteral ib) throws Exception;

	IClause resolve(IBooleanVariable b, boolean value) throws Exception;

	int size();

	IBooleanLiteral[] toArray();

	String toCode() throws ClauseException;

	IBooleanLiteral[] toSortedArray();
}