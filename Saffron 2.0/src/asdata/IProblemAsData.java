package asdata;

import bits.IProblem;

public interface IProblemAsData
{
	// boolean addClause(IClauseAsData c);
	// ArrayList<IClauseAsData> getClauses();
	IClauseAsData[] getClausesArray();

	// boolean removeClause(IClauseAsData c);
	// IClauseAsData removeClause(int index);
	IProblem toProblem() throws Exception;
}