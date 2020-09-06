package integers;

import bits.Conjunction;
import bits.IBitString;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;
import integers.lists.IIntegerList;
import naturalnumbers.exceptions.ConditionalAdderException;

public class ConditionalAdder extends Problem implements IProblem
{
	public ConditionalAdder(IInteger[] numbers, IBitString membership,
			IInteger conditionalSum) throws Exception
	{
		this(numbers, membership.toBooleanVariableArray(), conditionalSum);
	}

	public ConditionalAdder(IInteger[] numbers, IBooleanVariable[] membership,
			IInteger conditionalSum) throws Exception
	{
		if (numbers.length == 0 || membership.length == 0)
			throw (new ConditionalAdderException(
					"numbers or bits array of zero length was passed to constructor."));
		if (numbers.length != membership.length)
			throw (new ConditionalAdderException(
					"numbers or bits arrays of different lengths were passed to constructor."));
		if (conditionalSum == null)
			throw (new ConditionalAdderException(
					"A null conditionalSum variable was passed to constructor."));

		IInteger[] subAnswer = new IInteger[numbers.length];
		IInteger[] subTotal = new IInteger[numbers.length];
		subTotal[0] = new Integer();
		subAnswer[0] = new Integer();

		IProblem[] stagingArray = new IProblem[2 * numbers.length + 1];
		int stagingIndex = 0;

		stagingArray[stagingIndex++] = new IntegerEqualizer(subTotal[0],
				subAnswer[0]);
		for (int i = 1; i < numbers.length; i++)
		{
			subAnswer[i] = new Integer();
			subTotal[i] = new Integer();
			stagingArray[stagingIndex++] = new IntegerAdder(subTotal[i - 1],
					subAnswer[i], subTotal[i]);
		}

		stagingArray[stagingIndex++] = new IntegerEqualizer(
				subTotal[numbers.length - 1], conditionalSum);

		IProblem problem = new Conjunction(stagingArray);
		this.setClauses(problem.getClauses());

	}

	public ConditionalAdder(IIntegerList numbersArray, IBitString membership,
			IInteger conditionalSum) throws Exception
	{
		this(numbersArray.getIntegerArray(), membership.getBVArray(),
				conditionalSum);
	}

	public ConditionalAdder(IIntegerList numbersArray,
			IBooleanVariable[] membership, IInteger conditionalSum)
			throws Exception
	{
		this(numbersArray.getIntegerArray(), membership, conditionalSum);
	}
}