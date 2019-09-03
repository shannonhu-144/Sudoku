import java.awt.Graphics;

public class Cell {
	private int myX, myY, number; // x,y position on grid
	public Cell(int row, int col, int number) {
		myX = col;
		myY = row;
		this.number = number;
	}

	public int getX() {
		return myX;
	}

	public int getY() {
		return myY;
	}
	public int getNumber()
	{
		return number;
	}

	public void draw(int x_offset, int y_offset, int width, int height,
			Graphics g) {
		// I leave this understanding to the reader
		int xleft = x_offset + 1 + (myX * (width + 1));
		int ytop = y_offset + 1 + (myY * (height + 1));

		g.drawRect(xleft, ytop, width, height);
	}
}

