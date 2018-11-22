package bits;

import java.util.List;

/**
 * The <code>IBitString</code> interface must be implemented by any class
 * definition of <code>BitString</code> contemplated as an alternative to the
 * <code>BitString</code> class provided by this package.
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.1
 * @since 2005/10/27
 */
public interface IBitString extends Cloneable
{
	List<IBooleanVariable> asList();

	Object clone() throws CloneNotSupportedException;

	IBooleanVariable getBooleanVariable(int i) throws Exception;

	IBooleanVariable[] getBVArray() throws Exception;

	IBooleanVariable[] getBVArray(int size) throws Exception;

	String getName();

	void setBooleanVariable(int i, IBooleanVariable ib) throws Exception;

	void setName(String s) throws Exception;

	int size();

	String toBits();

	String toString();
}