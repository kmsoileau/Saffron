package graphs;

import bits.strings.RangeSubset;

/**
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since Jan 19, 2019
 */
public class GraphVertexSubset
{
	private IGraph base;
	private RangeSubset subsetMembership;

	public GraphVertexSubset(IGraph base) throws Exception
	{
		this(base, new RangeSubset(base.size()));
	}

	public GraphVertexSubset(IGraph g, RangeSubset membership)
	{
		this.base = g;
		this.subsetMembership = membership;
	}

	public IGraph getBase()
	{
		return base;
	}

	public RangeSubset getSubsetMembership()
	{
		return subsetMembership;
	}

	public boolean isMember(int i) throws Exception
	{
		return subsetMembership.getMembership().getBooleanVariable(i)
				.getValue();
	}

	public int size()
	{
		return this.getSubsetMembership().size();
	}

	@Override
	public String toString()
	{
		String ret = "";
		for (int i = 0; i < subsetMembership.size(); i++)
			try
			{
				if (subsetMembership.getMembership().getBooleanVariable(i)
						.getValue())
					System.out.print(i + " ");
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return ret;
	}
}
