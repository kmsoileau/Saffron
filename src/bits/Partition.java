package bits;

/**
 * TBS
 *
 * @author Kerry Michael Soileau
 *         <p>
 *         email: ksoileau2@yahoo.com
 *         <p>
 *         website: http://kerrysoileau.com/index.html
 * @version 1.0
 * @since 2018/12/03
 */
public class Partition
{
	private IBooleanVariable[][] partition;

	public Partition(int sets, int elements) throws Exception
	{
		super();
		this.partition = new IBooleanVariable[sets][elements];
		for (int i = 0; i < sets; i++)
		{
			partition[i] = new IBooleanVariable[elements];
			for (int j = 0; j < elements; j++)
				partition[i][j] = BooleanVariable.getBooleanVariable("BV-" + i + "-" + j);
		}
	}

	public IBooleanVariable[][] getBackingArray()
	{
		return this.partition;
	}

	public IBooleanVariable getBV(int set, int element)
	{
		return partition[set][element];
	}

	public int getNumberOfElements()
	{
		return this.partition[0].length;
	}

	public int getNumberOfSets()
	{
		return this.partition.length;
	}

	public IBooleanVariable[] getSet(int i)
	{
		return this.partition[i];
	}
}
