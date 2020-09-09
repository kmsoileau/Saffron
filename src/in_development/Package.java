/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Jan 3, 2019
 */
package in_development;

import java.util.ArrayList;
import java.util.HashMap;

import bits.IBooleanVariable;
import bits.IClause;

class Package
{
	HashMap<IBooleanVariable, ArrayList<IClause>> barredCase;

	HashMap<IBooleanVariable, ArrayList<IClause>> unBarredCase;

	IBooleanVariable util;

	ArrayList<IClause> barredClauses()
	{
		return this.getBarredCase().get(this.getUtil());
	}

	public HashMap<IBooleanVariable, ArrayList<IClause>> getBarredCase()
	{
		return barredCase;
	}

	public HashMap<IBooleanVariable, ArrayList<IClause>> getUnBarredCase()
	{
		return unBarredCase;
	}

	public IBooleanVariable getUtil()
	{
		return util;
	}

	public void setBarredCase(HashMap<IBooleanVariable, ArrayList<IClause>> barredCase)
	{
		this.barredCase = barredCase;
	}

	public void setUnBarredCase(HashMap<IBooleanVariable, ArrayList<IClause>> unBarredCase)
	{
		this.unBarredCase = unBarredCase;
	}

	public void setUtil(IBooleanVariable util)
	{
		this.util = util;
	}

	ArrayList<IClause> unBarredClauses()
	{
		return this.getUnBarredCase().get(this.getUtil());
	}
}