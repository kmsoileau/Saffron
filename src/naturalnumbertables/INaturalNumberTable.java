package naturalnumbertables;

import bits.INaturalNumber;

public interface INaturalNumberTable
{
	INaturalNumber getNaturalNumber(int i, int j) throws Exception;

	int getNumberOfColumns() throws Exception;

	// String getName();
	int getNumberOfRows();

	boolean isSameSizeAs(INaturalNumberTable y) throws Exception;
	// void set(int i, int j, INaturalNumber in) throws Exception;
	// void setName(String name);
	// String toString();
}