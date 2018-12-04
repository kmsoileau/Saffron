package demos.setsplitting;

public class SetSplittingDemo
{
	public static void main(String[] args) throws Exception
	{
		boolean[][] data = new boolean[][]
		{
		{ true, true, true, true },
		{ true, false, true, false },
		{ true, true, false, true } };

		SetSplitting.solve(data);
	}
}