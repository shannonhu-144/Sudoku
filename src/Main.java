import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		// Bring up a JFrame with squares to represent the cells
		final int DISPLAY_WIDTH = 800;
		final int DISPLAY_HEIGHT = 750;
		JFrame f = new JFrame();
		f.setSize(DISPLAY_WIDTH, DISPLAY_HEIGHT);
		Display display = new Display(DISPLAY_WIDTH, DISPLAY_HEIGHT);
		f.setLayout(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Sudoku");
		f.add(display);
		f.setVisible(true);
	}
	public static int[][] createGivens(Sudoku sudoku)
	{
		int[][] puzzle = sudoku.getSolution();
		for (int i = 0; i < Display.ROWS; i++)
			for (int j = 0; j < Display.COLS; j++)
			{
				if (Math.random() > Display.givens / (Display.ROWS * Display.COLS * 1.0))
				{
					puzzle[i][j] = 0;
				}
			}
		return puzzle;
	}
	public static void printInts(int[] a)
	{
		for (int i = 0; i < a.length; i++)
		{
			if (i == 0)
				System.out.print("{" + a[i] + " ");
			else if (i == a.length - 1)
				System.out.print(a[i] + "}");
			else System.out.print(a[i] + " ");
		}
	}
}