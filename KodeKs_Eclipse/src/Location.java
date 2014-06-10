
import java.io.Serializable;



public class Location implements Serializable
{
	private static final long serialVersionUID = 5087471276680520399L;
	int Row;
	int Column;
	
	public Location()
	{
		Row = 0;
		Column = 0;
	}
	
	public Location(int Row, int Column)
	{
		this.Row = Row;
		this.Column = Column;
	}
	
	public Location(KodeKsData.KodeKsMove move)
	{
		this.Row = move.fromRow;
		this.Column = move.fromCol;
	}
	
	public int getRow()
	{
		return Row;
	}
	
	public int getCol()
	{
		return Column;
	}
	
    public boolean equals(Location loc)
    {
        return loc.getRow() == this.getRow() && loc.getCol() == this.getCol();
    }
    
    public String toString()
    {
    	int col = getCol();
    	int row = getRow();
    	int analyseCol;
    	char analyseRow;
    	switch (col)
    	{
    		case 0:
    			analyseCol = 1; break;
    		case 1:
    			analyseCol = 2; break;
    		case 2:
    			analyseCol = 3; break;
    		case 3:
    			analyseCol = 4; break;
    		case 4:
    			analyseCol = 5; break;
    		case 5:
    			analyseCol = 6; break;
    		case 6:
    			analyseCol = 7; break;
    		case 7:
    			analyseCol = 8; break;
    		case 8:
    			analyseCol = 9; break;
    		case 9:
    			analyseCol = 10; break;
    		default:
    			analyseCol = 0; break;
    	}
    	
    	switch (row)
    	{
    		case 0:
    			analyseRow = 'a'; break;
    		case 1:
    			analyseRow = 'b'; break;
    		case 2:
    			analyseRow = 'c'; break;
    		case 3:
    			analyseRow = 'd'; break;
    		case 4:
    			analyseRow = 'e'; break;
    		case 5:
    			analyseRow = 'f'; break;
    		case 6:
    			analyseRow = 'g'; break;
    		case 7:
    			analyseRow = 'h'; break;
    		case 8:
    			analyseRow = 'i'; break;
    		case 9:
    			analyseRow = 'j'; break;
    		default:
    			analyseRow = ' '; break;
    	}
    	
    	return ""+analyseCol+""+analyseRow;
    }
}
