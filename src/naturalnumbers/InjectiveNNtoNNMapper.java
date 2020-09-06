package naturalnumbers;

import java.util.ArrayList;

import bits.Conjunction;
import bits.Disjunction;
import bits.INaturalNumber;
import bits.IProblem;
import bits.Problem;

public class InjectiveNNtoNNMapper extends Problem implements IProblem
{
	public InjectiveNNtoNNMapper(NNtoNNMapper map) throws Exception
	{
		ArrayList<NaturalNumberPair> list = map.getPairs();
		ArrayList<IProblem> p = new ArrayList<IProblem>();
		for (NaturalNumberPair pr1 : list)
		{
			INaturalNumber Xi = pr1.getFirst();
			INaturalNumber Yi = pr1.getSecond();
			for (NaturalNumberPair pr2 : list)
			{
				INaturalNumber Xj = pr2.getFirst();
				INaturalNumber Yj = pr2.getSecond();

				p.add(new Disjunction(new NaturalNumberUnequalizer(Yi, Yj),
						new NaturalNumberEqualizer(Xi, Xj)));
			}
		}

		this.setClauses(new Conjunction(p).getClauses());
	}
}
