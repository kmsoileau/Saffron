/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Dec 27, 2018
 */
package sets;

import java.util.Arrays;

import naturalnumbers.NaturalNumber;
import sets.exceptions.WeightedSetException;
import bits.IBitString;
import bits.IBooleanVariable;
import bits.INaturalNumber;
import bits.strings.BitString;

public class WeightedSet
{
	private IBitString membership;
	private INaturalNumber[] weight;

	public WeightedSet() throws Exception
	{
		super();
		this.weight = new INaturalNumber[Set.getSetSupportSize()];
		for (int i = 0; i < Set.getSetSupportSize(); i++)
			this.weight[i] = new NaturalNumber();
		this.membership = new BitString(weight.length);
	}

	public WeightedSet(INaturalNumber[] weight) throws Exception
	{
		super();
		if (Set.getSetSupportSize() != weight.length)
			throw new WeightedSetException(
					"In constructor call, INaturalNumber array has impropoer size.");
		this.weight = weight;
		this.membership = new BitString(weight.length);
	}

	public Integer getIndex(String name)
	{
		String[] qq = Set.getElementNames();
		for (int i = 0; i < qq.length; i++)
		{
			if (qq[i].compareTo(name) == 0)
				return i;
		}
		return null;
	}

	public IBitString getMembership()
	{
		return membership;
	}

	public IBooleanVariable getMembership(String name) throws Exception
	{
		Integer qq = this.getIndex(name);
		if (qq != null)
			return this.membership.getBooleanVariable(qq);
		else
			return null;
	}

	public INaturalNumber[] getWeight()
	{
		return weight;
	}

	public INaturalNumber getWeight(int i)
	{
		return this.weight[i];
	}

	public INaturalNumber getWeight(String name)
	{
		Integer qq = this.getIndex(name);
		if (qq != null)
			return this.weight[qq];
		else
			return null;
	}

	public INaturalNumber[] getWeights()
	{
		return this.weight;
	}

	public void setMembership(IBitString membership)
	{
		this.membership = membership;
	}

	public void setWeight(INaturalNumber[] weight)
	{
		this.weight = weight;
	}

	@Override
	public String toString()
	{
		return "WeightedSet [membership=" + membership + ", weight="
				+ Arrays.toString(weight) + "]";
	}
}
