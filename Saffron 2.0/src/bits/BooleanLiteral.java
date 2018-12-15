package bits;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import exceptions.bits.BooleanLiteralException;

/**
 * A class which represents a Boolean literal. BooleanLiteral is essentially a
 * BooleanVariable together with a boolean indicating whether the
 * BooleanVariable is barred (negated) or not. NOTE: Alternative implementations
 * of BooleanLiteral must not only implement IBooleanLiteral, but must also
 * ensure that any two instances of BooleanLiteral are equal if and only if they
 * agree on the value of barred, and contain equal instances of BooleanVariable.
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since 2004/08/26
 */
public class BooleanLiteral implements IBooleanLiteral
{
	private static HashSet<IBooleanLiteral> instances = new HashSet<IBooleanLiteral>();

	/**
	 * Rather than providing a public constructor for the BooleanLiteral class,
	 * a static factory method is instead provided. This is to avoid duplication
	 * of a already-existing IBooleanLiteral. The <code>HashSet instances</code>
	 * is searched to determine whether an IBooleanLiteral with the desired
	 * internal IBooleanVariable and barred-ness already exists; if it does, the
	 * instance is returned. If not, the private constructor is used to create
	 * the desired IBooleanLiteral, which is then added to the
	 * <code>HashSet instances</code> and is returned.
	 * 
	 * @param bv
	 *            An IBooleanVariable
	 * @param barred
	 *            A boolean
	 * @return IBooleanLiteral
	 * @throws BooleanLiteralException
	 *             An instance of Exception
	 */
	public static IBooleanLiteral getBooleanLiteral(IBooleanVariable bv,
			boolean barred) throws BooleanLiteralException
	{
		if (bv == null)
			throw new BooleanLiteralException(
					"Null IBooleanVariable was passed to getBooleanLiteral method.");
		else
		{
			Object[] inarray = BooleanLiteral.getInstances().toArray();

			for (int i = 0; i < inarray.length; i++)
			{
				IBooleanLiteral nx = (IBooleanLiteral) (inarray[i]);
				if (nx.isBarred() == barred && nx.getBooleanVariable() != null
						&& nx.getBooleanVariable().equals(bv))
					return nx;
			}
			return new BooleanLiteral(bv, barred);
		}
	}

	public static HashSet<IBooleanLiteral> getInstances()
	{
		return BooleanLiteral.instances;
	}

	public static void interpret(List<?> list) throws BooleanLiteralException
	{
		if (list == null)
			throw new BooleanLiteralException(
					"Null List was passed to interpret method.");
		else
			for (int i = 0; i < list.size(); i++)
			{
				IBooleanLiteral ibool = (IBooleanLiteral) (list.get(i));
				try
				{
					ibool.load();
				}
				catch (Exception err)
				{
				}
			}
	}

	public static void listLiterals()
	{
		IBooleanLiteral[] ary = BooleanLiteral.getInstances().toArray(
				new BooleanLiteral[0]);
		List<IBooleanLiteral> lis = Arrays.asList(ary);
		Collections.sort(lis);
		System.out.println(lis);
	}

	/**
	 * Clear all of the values of the IBooleanVariables involved in
	 * <code>list</code>.
	 * 
	 * @param list
	 *            A List of IBooleanVariables
	 * @throws BooleanLiteralException
	 *             An instance of Exception
	 */
	public static void reset(List<?> list) throws BooleanLiteralException
	{
		if (list == null)
			throw new BooleanLiteralException(
					"Null List was passed to reset method.");
		else
			for (int i = 0; i < list.size(); i++)
			{
				IBooleanLiteral ibool = (IBooleanLiteral) (list.get(i));
				try
				{
					ibool.getBooleanVariable().setValue(false);
				}
				catch (Exception err)
				{
				}
			}
	}

	private boolean barred;

	private IBooleanVariable BV;

	private BooleanLiteral(IBooleanVariable bv, boolean barred)
			throws BooleanLiteralException
	{
		if (bv == null)
			throw new BooleanLiteralException(
					"Null IBooleanVariable was passed to constructor.");
		this.BV = bv;
		this.barred = barred;
		BooleanLiteral.getInstances().add(this);
	}

	@Override
	public int compareTo(Object o)
	{
		String thisName = this.getBooleanVariable().getName();
		String oName = ((IBooleanLiteral) o).getBooleanVariable().getName();
		if (thisName.compareTo(oName) != 0)
			return thisName.compareTo(oName);
		if (this.isBarred() == ((IBooleanLiteral) o).isBarred())
			return 0;
		if (this.isBarred() && !((IBooleanLiteral) o).isBarred())
			return -1;
		else
			return 1;
	}

	@Override
	public IBooleanLiteral complement() throws BooleanLiteralException
	{
		return BooleanLiteral.getBooleanLiteral(this.getBooleanVariable(),
				!(this.barred));
	}

	/**
	 * If <code>o</code> is null, this method returns <code>false</code>. If
	 * <code>o</code> is not of type IBooleanLiteral, this method returns
	 * <code>false</code>. If the Object passed has a different internal
	 * IBooleanVariable, this method returns <code>false</code>. If the
	 * "barred-ness" of <code>this</code> and <code>o</code> is different, this
	 * method returns <code>false</code>. Otherwise, this method returns
	 * <code>true</code>.
	 */
	public boolean equals(Object o)
	{
		if (o == null)
			return false; // this is never equal to null.
		if (!(o instanceof IBooleanLiteral))
			return false;
		IBooleanLiteral bo = (IBooleanLiteral) o;
		IBooleanVariable bov = bo.getBooleanVariable();
		if (bov == null)
			return false;
		else
		{
			if (bo.isBarred() != this.isBarred())
				return false;
			else if (bov.equals(this.getBooleanVariable()))
				return true;
			else
				return false;
		}
	}

	@Override
	public boolean evaluate()
	{
		if (this.getBooleanVariable().getValue() && !(this.isBarred()))
			return true;
		if (!(this.getBooleanVariable().getValue()) && this.isBarred())
			return true;
		return false;
	}

	@Override
	public IBooleanVariable getBooleanVariable()
	{
		return BV;
	}

	@Override
	public boolean isBarred()
	{
		return barred;
	}

	@Override
	public void load()
	{
		this.getBooleanVariable().setValue(!this.isBarred());
	}

	public ArrayList<ArrayList<IBooleanLiteral>> multiply(
			ArrayList<ArrayList<IBooleanLiteral>> a,
			ArrayList<ArrayList<IBooleanLiteral>> b)
	{
		ArrayList<ArrayList<IBooleanLiteral>> ret = new ArrayList<ArrayList<IBooleanLiteral>>();
		for (int m = 0; m < a.size(); m++)
		{
			ArrayList<IBooleanLiteral> x = a.get(m);
			for (int n = 0; n < b.size(); n++)
			{
				ArrayList<IBooleanLiteral> y = b.get(n);
				ArrayList<IBooleanLiteral> z = new ArrayList<IBooleanLiteral>();
				z.addAll(x);
				z.addAll(y);
				ret.add(z);
			}
		}
		return ret;
	}

	public void setBarred(boolean barred)
	{
		this.barred = barred;
	}

	public void setBooleanVariable(IBooleanVariable BV)
			throws BooleanLiteralException
	{
		if (BV == null)
			throw new BooleanLiteralException(
					"Null IBooleanVariable was passed to setBooleanVariable method.");
		else
			this.BV = BV;
	}

	public String toCode()
	{
		String nm = this.getBooleanVariable().getName();
		if (!this.barred)
			nm = "(1-" + nm + ")";
		return nm + "*" + nm;
	}

	@Override
	public String toString()
	{
		if (this.isBarred())
			return " $" + this.getBooleanVariable().getName() + " ";
		else
			return " " + this.getBooleanVariable().getName() + " ";
	}

	@Override
	public String toString(List<IBooleanLiteral> l)
			throws BooleanLiteralException
	{
		if (l == null)
			throw new BooleanLiteralException(
					"Null List was passed to toString method.");
		else
		{
			BooleanLiteral.interpret(l);
			return this.toString();
		}
	}
}