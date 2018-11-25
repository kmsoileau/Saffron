package asdata;

import bits.IBooleanVariable;
import bits.IClause;

public interface IClauseAsData 
{
	IBooleanVariable barredIndicator(IBooleanVariable bv) throws Exception;
	String getName();
	IBooleanVariable occurrenceIndicator(IBooleanVariable bv) throws Exception;
	IClause toClause() throws Exception;
	//String toString();
}

