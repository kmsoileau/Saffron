package naturalnumberlists;

import java.util.List;

import bits.BooleanLiteral;
import bits.Conjunction;
import bits.IBooleanLiteral;
import bits.IProblem;
import bits.Problem;

public class PermutationDemo
{
	public static void main(String[] args) throws Exception
	{
		Permutation perm1 = new Permutation(5);

		IProblem p = new Conjunction(new IProblem[]
		{ new PermutationFixer(perm1, new long[]
		{ 1, 2, 0, 3, 4 }), });

		List<IBooleanLiteral> s = p.findModel(Problem.defaultSolver());
		if (s != null && s.size() > 0)
		{
			BooleanLiteral.interpret(s);
			System.out.println(perm1);
		}
		else
			System.out.println("No solution.");
	}
}
