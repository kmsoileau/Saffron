package bits;


public class ProblemTriple
{
	private IProblem firstProblem;
	private IProblem secondProblem;
	private IProblem thirdProblem;

	public ProblemTriple(IProblem firstProblem, IProblem secondProblem,
			IProblem thirdProblem)
	{
		this.firstProblem = firstProblem;
		this.secondProblem = secondProblem;
		this.thirdProblem = thirdProblem;
	}

	public IProblem getFirst()
	{
		return this.firstProblem;
	}

	public IProblem getSecond()
	{
		return this.secondProblem;
	}

	public IProblem getThird()
	{
		return this.thirdProblem;
	}
}
