package bits;

import java.util.ArrayList;

import bits.exceptions.BitExclusiveSelectorException;

/**
 * For any positive integer n, if X_0, X_1, X_2, ... ,X_{n-1} are
 * IBooleanVariables and IBooleanVariable[] array = {X_0, X_1, X_2, ...
 * ,X_{n-1}}, then
 * <p>
 * <code>p=new BitExclusiveSelector(array);</code>
 * </p>
 * is satisfied if and only if at <b>EXACTLY</b> one of the following is
 * satisfied:
 * <p>
 * new BitFixer(X_0, true)
 * <p>
 * or
 * <p>
 * new BitFixer(X_1, true)
 * <p>
 * or
 * <p>
 * new BitFixer(X_2, true)
 * <p>
 * or
 * <p>
 * ...
 * <p>
 * new BitFixer(X_{n-1}, true)
 * 
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Dec 17, 2018
 */
public class BitExclusiveSelector extends Problem implements IProblem
{
	public BitExclusiveSelector(ArrayList<IBooleanVariable> bitArrayList) throws Exception
	{
		this(bitArrayList.toArray(new IBooleanVariable[0]));
	}

	public BitExclusiveSelector(IBitString string) throws Exception
	{
		this.setClauses(new bits.BitExclusiveSelector(string.getBVArray()).getClauses());
	}

	public BitExclusiveSelector(IBooleanVariable x, IBooleanVariable y) throws Exception
	{
		this(new IBooleanVariable[]
		{ x, y });
	}

	public BitExclusiveSelector(IBooleanVariable x, IBooleanVariable y, IBooleanVariable z) throws Exception
	{
		this(new IBooleanVariable[]
		{ x, y, z });
	}

	public BitExclusiveSelector(IBooleanVariable[] bitArrayList) throws Exception
	{
		if (bitArrayList == null || bitArrayList.length == 0)
			throw new BitExclusiveSelectorException("Null or empty partition passed to constructor.");
		if (bitArrayList.length == 1)
			this.setClauses(new BitFixer(bitArrayList[0], true).getClauses());
		else
		{
			int listSize = bitArrayList.length;
			BooleanLiteral.getBooleanLiteral(bitArrayList[0], false);
			ArrayList<IClause> ret = new ArrayList<IClause>();
			IClause build1 = Clause.newClause();
			for (IBooleanVariable curr : bitArrayList)
				((Clause) build1).add((BooleanLiteral) BooleanLiteral.getBooleanLiteral(curr, false));
			ret.add(build1);

			for (int i = 0; i < listSize; i++)
			{
				BooleanLiteral curr = (BooleanLiteral) BooleanLiteral.getBooleanLiteral(bitArrayList[i], true);
				for (int j = i + 1; j < listSize; j++)
				{
					IClause build2 = Clause.newClause();
					((Clause) build2).add(curr);
					((Clause) build2).add((BooleanLiteral) BooleanLiteral.getBooleanLiteral(bitArrayList[j], true));
					ret.add(build2);
				}
			}
			this.setClauses(ret);
		}
	}
}