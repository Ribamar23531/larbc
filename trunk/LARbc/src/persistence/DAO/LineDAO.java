package persistence.DAO;

import java.util.List;

import beans.poi.Line;

public interface LineDAO {
	
	public void saveLine(Line line);
	
	public void removeLine(Line line);
	
	public List<Line> getLines();
	
//	public void updateLine(Line line);

}
