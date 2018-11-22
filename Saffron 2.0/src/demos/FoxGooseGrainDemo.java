package demos;

import bits.BitEqualizer;
import bits.BitFixer;
import bits.BitUnequalizer;
import bits.BooleanVariable;
import bits.Conjunction;
import bits.Disjunction;
import bits.ExclusiveDisjunction;
import bits.IBooleanVariable;
import bits.IProblem;
import bits.Problem;
import bits.ProblemDenier;

class BoatAndFarmerTogether extends Problem implements IProblem
{
	public BoatAndFarmerTogether(Status s) throws Exception
	{
		this.setClauses(new BitEqualizer(s.boat, s.farmer).getClauses());
	}
}

class BoatCrosses extends Problem implements IProblem
{
	public BoatCrosses(Status s1, Status s2) throws Exception
	{
		this.setClauses(new BitUnequalizer(s1.boat, s2.boat).getClauses());
	}
}

class CrossAlone extends Problem implements IProblem
{
	public CrossAlone(Status s1, Status s2) throws Exception
	{
		this.setClauses(new Conjunction(new IProblem[]
		{ new BoatAndFarmerTogether(s1), new BoatAndFarmerTogether(s2),
				new BoatCrosses(s1, s2), new BitEqualizer(s1.goose, s2.goose),
				new BitEqualizer(s1.fox, s2.fox),
				new BitEqualizer(s1.grain, s2.grain) }).getClauses());
	}
}

class CrossTheRiver extends Problem implements IProblem
{
	public CrossTheRiver(Status s1, Status s2) throws Exception
	{
		this.setClauses(new ExclusiveDisjunction(new IProblem[]
		{ new CrossWithFox(s1, s2), new CrossWithGoose(s1, s2),
				new CrossWithGrain(s1, s2), new CrossAlone(s1, s2) })
				.getClauses());
	}
}

class CrossWithFox extends Problem implements IProblem
{
	public CrossWithFox(Status s1, Status s2) throws Exception
	{
		this.setClauses(new Conjunction(new IProblem[]
		{ new FarmerAndFoxTogether(s1), new FarmerAndFoxTogether(s2),
				new BitEqualizer(s1.boat, s1.farmer),
				new BitEqualizer(s2.boat, s2.farmer), new BoatCrosses(s1, s2),
				new BitEqualizer(s1.goose, s2.goose),
				new BitEqualizer(s1.grain, s2.grain) }).getClauses());
	}
}

class CrossWithGoose extends Problem implements IProblem
{
	public CrossWithGoose(Status s1, Status s2) throws Exception
	{
		this.setClauses(new Conjunction(new IProblem[]
		{ new FarmerAndGooseTogether(s1), new FarmerAndGooseTogether(s2),
				new BitEqualizer(s1.boat, s1.farmer),
				new BitEqualizer(s2.boat, s2.farmer), new BoatCrosses(s1, s2),
				new BitEqualizer(s1.fox, s2.fox),
				new BitEqualizer(s1.grain, s2.grain) }).getClauses());
	}
}

class CrossWithGrain extends Problem implements IProblem
{
	public CrossWithGrain(Status s1, Status s2) throws Exception
	{
		this.setClauses(new Conjunction(new IProblem[]
		{ new FarmerAndGrainTogether(s1), new FarmerAndGrainTogether(s2),
				new BitEqualizer(s1.boat, s1.farmer),
				new BitEqualizer(s2.boat, s2.farmer), new BoatCrosses(s1, s2),
				new BitEqualizer(s1.fox, s2.fox),
				new BitEqualizer(s1.goose, s2.goose) }).getClauses());
	}
}

class FarmerAndFoxTogether extends Problem implements IProblem
{
	public FarmerAndFoxTogether(Status s) throws Exception
	{
		this.setClauses(new BitEqualizer(s.farmer, s.fox).getClauses());
	}
}

class FarmerAndGooseTogether extends Problem implements IProblem
{
	public FarmerAndGooseTogether(Status s) throws Exception
	{
		this.setClauses(new BitEqualizer(s.farmer, s.goose).getClauses());
	}
}

class FarmerAndGrainTogether extends Problem implements IProblem
{
	public FarmerAndGrainTogether(Status s) throws Exception
	{
		this.setClauses(new BitEqualizer(s.farmer, s.grain).getClauses());
	}
}

class FoxAndGooseAloneTogether extends Problem implements IProblem
{
	public FoxAndGooseAloneTogether(Status s) throws Exception
	{
		this.setClauses(new BitEqualizer(s.fox, s.goose).getClauses());
	}
}

class FoxAndGooseNotAloneTogether extends Problem implements IProblem
{
	public FoxAndGooseNotAloneTogether(Status s) throws Exception
	{
		this.setClauses(new BitUnequalizer(s.fox, s.goose).getClauses());
	}
}

public class FoxGooseGrainDemo
{
	static int crossings = 7;
	static Status[] status = new Status[crossings + 1];

	public static void main(String[] args) throws Exception
	{
		for (int i = 0; i < crossings + 1; i++)
			status[i] = new Status();

		IProblem initPositions = new Conjunction(new IProblem[]
		{ new BitFixer(status[0].boat), new BitFixer(status[0].farmer),
				new BitFixer(status[0].fox), new BitFixer(status[0].goose),
				new BitFixer(status[0].grain) });

		IProblem finalPositions = new Conjunction(new IProblem[]
		{ new BitFixer(status[crossings].boat, true),
				new BitFixer(status[crossings].farmer, true),
				new BitFixer(status[crossings].fox, true),
				new BitFixer(status[crossings].goose, true),
				new BitFixer(status[crossings].grain, true) });

		IProblem problem = new Conjunction(initPositions, finalPositions);

		for (int i = 0; i < crossings; i++)
			problem = new Conjunction(problem, new CrossTheRiver(status[i],
					status[i + 1]));

		for (int i = 0; i < crossings + 1; i++)
			problem = new Conjunction(problem, new StatusIsGood(status[i]));

		System.out.println(problem.toXML());

		boolean solved = problem.solve(Problem.defaultSolver());

		if (solved)
		{
			for (int i = 0; i < crossings + 1; i++)
			{
				System.out.println("\n"
						+ (status[i].boat.getValue() ? "\t\t" : "") + "Boat");
				System.out.println((status[i].farmer.getValue() ? "\t\t" : "")
						+ "Farmer");
				System.out.println((status[i].fox.getValue() ? "\t\t" : "")
						+ "Fox");
				System.out.println((status[i].goose.getValue() ? "\t\t" : "")
						+ "Goose");
				System.out.println((status[i].grain.getValue() ? "\t\t" : "")
						+ "Grain");
				System.out.println("------------------------------------");
			}
		}
		else
			System.out.println("There is no solution.");
	}
}

class GooseAndGrainTogether extends Problem implements IProblem
{
	public GooseAndGrainTogether(Status s) throws Exception
	{
		this.setClauses(new BitEqualizer(s.goose, s.grain).getClauses());
	}
}

class GooseIsSafe extends Problem implements IProblem
{
	public GooseIsSafe(Status s) throws Exception
	{
		this.setClauses(new Disjunction(new ProblemDenier(
				new FoxAndGooseAloneTogether(s)), new FarmerAndGooseTogether(s))
				.getClauses());
	}
}

class GrainIsSafe extends Problem implements IProblem
{
	public GrainIsSafe(Status s) throws Exception
	{
		this.setClauses(new Disjunction(new ProblemDenier(
				new GooseAndGrainTogether(s)), new FarmerAndGrainTogether(s))
				.getClauses());
	}
}

class Status
{
	IBooleanVariable boat;
	IBooleanVariable farmer;
	IBooleanVariable fox;
	IBooleanVariable goose;
	IBooleanVariable grain;

	public Status() throws Exception
	{
		boat = BooleanVariable.getBooleanVariable();
		farmer = BooleanVariable.getBooleanVariable();
		fox = BooleanVariable.getBooleanVariable();
		goose = BooleanVariable.getBooleanVariable();
		grain = BooleanVariable.getBooleanVariable();
	}
}

class StatusIsGood extends Problem implements IProblem
{
	public StatusIsGood(Status status) throws Exception
	{
		this.setClauses(new Conjunction(new GrainIsSafe(status),
				new GooseIsSafe(status)).getClauses());
	}
}