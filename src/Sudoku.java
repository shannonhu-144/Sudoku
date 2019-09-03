import java.util.ArrayList;


public class Sudoku {
	private static int[][] solution;
	private static ArrayList<String> operations;
	private static final int ROWS = 9;
	private static final int COLS = 9;
	public static final int GIVENS = 45;
	public Sudoku()
	{
		solution = new int[9][9];
		operations = new ArrayList<String>();
		operations.add("Permute 2-1-3 row");
		operations.add("Permute 2-3-1 row");
		operations.add("Permute 3-1-2 row");
		operations.add("Permute 3-2-1 row");
		operations.add("Permute 1-3-2 row");
		operations.add("Permute 5-4-6 row");
		operations.add("Permute 5-6-4 row");
		operations.add("Permute 6-4-5 row");
		operations.add("Permute 6-5-4 row");
		operations.add("Permute 4-6-5 row");
		operations.add("Permute 8-7-9 row");
		operations.add("Permute 8-9-7 row");
		operations.add("Permute 9-7-8 row");
		operations.add("Permute 9-8-7 row");
		operations.add("Permute 7-9-8 row");
		operations.add("Permute 2-1-3 col");
		operations.add("Permute 2-3-1 col");
		operations.add("Permute 3-1-2 col");
		operations.add("Permute 3-2-1 col");
		operations.add("Permute 1-3-2 col");
		operations.add("Permute 5-4-6 col");
		operations.add("Permute 5-6-4 col");
		operations.add("Permute 6-4-5 col");
		operations.add("Permute 6-5-4 col");
		operations.add("Permute 4-6-5 col");
		operations.add("Permute 8-7-9 col");
		operations.add("Permute 8-9-7 col");
		operations.add("Permute 9-7-8 col");
		operations.add("Permute 9-8-7 col");
		operations.add("Permute 7-9-8 col");
		operations.add("permute 2-1-3 rb");
		operations.add("permute 2-3-1 rb");
		operations.add("permute 3-1-2 rb");
		operations.add("permute 3-2-1 rb");
		operations.add("permute 1-3-2 rb");
		operations.add("permute 2-1-3 cb");
		operations.add("permute 2-3-1 cb");
		operations.add("permute 3-1-2 cb");
		operations.add("permute 3-2-1 cb");
		operations.add("permute 1-3-2 cb");
		operations.add("rotate 90");
		operations.add("rotate 180");
		operations.add("rotate 270");
		operations.add("reflect horizontal");
		operations.add("reflect vertical");
		operations.add("reflect diagonal");
		int[][] solution1 = {
				{5, 6, 1, 8, 4, 7, 9, 2, 3}, 
				{3, 7, 9, 5, 2, 1, 6, 8, 4}, 
				{4, 2, 8, 9, 6, 3, 1, 7, 5}, 
				{6, 1, 3, 7, 8, 9, 5, 4, 2}, 
				{7, 9, 4, 6, 5, 2, 3, 1, 8}, 
				{8, 5, 2, 1, 3, 4, 7, 9, 6}, 
				{9, 3, 5, 4, 7, 8, 2, 6, 1}, 
				{1, 4, 6, 2, 9, 5, 8, 3, 7}, 
				{2, 8, 7, 3, 1, 6, 4, 5, 9}};
		int[][] solution2 = {
				{3, 4, 9, 6, 7, 8, 1, 5, 2},
				{5, 6, 1, 3, 2, 9, 7, 8, 4},
				{7, 8, 2, 1, 4, 5, 6, 3, 9},
				{9, 1, 5, 7, 3, 2, 4, 6, 8}, 
				{6, 2, 8, 9, 1, 4, 5, 7, 3}, 
				{4, 3, 7, 5, 8, 6, 9, 2, 1}, 
				{2, 5, 4, 8, 6, 1, 3, 9, 7}, 
				{8, 7, 6, 4, 9, 3, 2, 1, 5}, 
				{1, 9, 3, 2, 5, 7, 8, 4, 6}};
		int[][] solution3 = {
				{8, 3, 5, 4, 1, 6, 9, 2, 7},
				{2, 9, 6, 8, 5, 7, 4, 3, 1},
				{4, 1, 7, 2, 9, 3, 6, 5, 8},
				{5, 6, 9, 1, 3, 4, 7, 8, 2},
				{1, 2, 3, 6, 7, 8, 5, 4, 9},
				{7, 4, 8, 5, 2, 9, 1, 6, 3},
				{6, 5, 2, 7, 8, 1, 3, 9, 4},
				{9, 8, 1, 3, 4, 5, 2, 7, 6},
				{3, 7, 4, 9, 6, 2, 8, 1, 5}};
		int[][] solution4 = {
				{8, 4, 6, 7, 9, 2, 3, 1, 5},
				{2, 1, 7, 4, 3, 5, 8, 9, 6},
				{5, 9, 3, 6, 8, 1, 2, 7, 4},
				{6, 5, 8, 9, 1, 7, 4, 2, 3},
				{3, 7, 1, 2, 4, 8, 5, 6, 9},
				{4, 2, 9, 3, 5, 6, 7, 8, 1},
				{9, 6, 4, 8, 7, 3, 1, 5, 2},
				{7, 3, 5, 1, 2, 9, 6, 4, 8},
				{1, 8, 2, 5, 6, 4, 9, 3, 7}};
		int[][] solution5 = {
				{6, 3, 2, 7, 8, 1, 9, 4, 5},
				{4, 8, 7, 5, 9, 6, 2, 1, 3},
				{5, 1, 9, 2, 4, 3, 8, 7, 6},
				{8, 6, 4, 3, 5, 2, 7, 9, 1},
				{7, 5, 1, 9, 6, 8, 3, 2, 4},
				{2, 9, 3, 1, 7, 4, 6, 5, 8},
				{9, 4, 5, 6, 3, 7, 1, 8, 2},
				{1, 7, 6, 8, 2, 5, 4, 3, 9},
				{3, 2, 8, 4, 1, 9, 5, 6, 7}};
		int[][] solution6 = {
				{1, 7, 4, 9, 3, 8, 5, 6, 2},
				{8, 6, 3, 5, 2, 1, 4, 7, 9},
				{9, 5, 2, 7, 6, 4, 8, 3, 1},
				{4, 9, 7, 6, 8, 5, 1, 2, 3},
				{3, 2, 5, 1, 9, 7, 6, 8, 4},
				{6, 8, 1, 3, 4, 2, 9, 5, 7},
				{5, 1, 9, 8, 7, 3, 2, 4, 6},
				{2, 3, 6, 4, 5, 9, 7, 1, 8},
				{7, 4, 8, 2, 1, 6, 3, 9, 5}};
		int[][] solution7 = {
				{2, 6, 4, 7, 1, 5, 8, 3, 9},
				{1, 3, 7, 8, 9, 2, 6, 4, 5},
				{5, 9, 8, 4, 3, 6, 2, 7, 1},
				{4, 2, 3, 1, 7, 8, 5, 9, 6},
				{8, 1, 6, 5, 4, 9, 7, 2, 3},
				{7, 5, 9, 6, 2, 3, 4, 1, 8},
				{3, 7, 5, 2, 8, 1, 9, 6, 4},
				{9, 8, 2, 3, 6, 4, 1, 5, 7},
				{6, 4, 1, 9, 5, 7, 3, 8, 2}};
		int[][] solution8 = {
				{9, 4, 1, 8, 5, 7, 3, 6, 2},
				{6, 8, 2, 3, 9, 1, 4, 5, 7},
				{5, 7, 3, 6, 2, 4, 8, 1, 9},
				{1, 6, 9, 4, 8, 2, 7, 3, 5},
				{8, 3, 4, 1, 7, 5, 2, 9, 6},
				{2, 5, 7, 9, 6, 3, 1, 4, 8},
				{7, 1, 6, 5, 4, 8, 9, 2, 3},
				{3, 9, 8, 2, 1, 6, 5, 7, 4},
				{4, 2, 5, 7, 3, 9, 6, 8, 1}};
		int[][] solution9 = {
				{4, 5, 1, 2, 9, 6, 3, 7, 8},
				{3, 2, 9, 8, 5, 7, 4, 6, 1},
				{8, 6, 7, 1, 4, 3, 5, 9, 2},
				{7, 4, 5, 3, 2, 9, 8, 1, 6},
				{6, 9, 8, 5, 1, 4, 7, 2, 3},
				{2, 1, 3, 7, 6, 8, 9, 5, 4},
				{5, 3, 6, 4, 7, 1, 2, 8, 9},
				{9, 7, 4, 6, 8, 2, 1, 3, 5},
				{1, 8, 2, 9, 3, 5, 6, 4, 7}};
		int[][] solution10 = {
				{7, 4, 6, 2, 9, 3, 8, 5, 1},
				{9, 3, 5, 4, 1, 8, 6, 2, 7},
				{8, 1, 2, 7, 5, 6, 9, 4, 3},
				{2, 6, 7, 9, 4, 1, 5, 3, 8},
				{4, 8, 9, 5, 3, 7, 1, 6, 2},
				{3, 5, 1, 6, 8, 2, 4, 7, 9},
				{5, 2, 8, 1, 7, 4, 3, 9, 6},
				{6, 9, 3, 8, 2, 5, 7, 1, 4},
				{1, 7, 4, 3, 6, 9, 2, 8, 5}};
		double randomnumber = Math.random();
		if (randomnumber < 0.1)
			solution = solution1;
		else if (randomnumber < 0.2)
			solution = solution2;
		else if (randomnumber < 0.3)
			solution = solution3;
		else if (randomnumber < 0.4)
			solution = solution4;
		else if (randomnumber < 0.5)
			solution = solution5;
		else if (randomnumber < 0.6)
			solution = solution6;
		else if (randomnumber < 0.7)
			solution = solution7;
		else if (randomnumber < 0.8)
			solution = solution8;
		else if (randomnumber < 0.9)
			solution = solution9;
		else solution = solution10;
	}
	public void permuteRow(int row1, int row2)
	{
		int[] temp = solution[row1];
		solution[row1] = solution[row2];
		solution[row2] = temp;
	}
	public void permuteCol(int col1, int col2)
	{
		for (int i = 0; i <= 8; i++)
		{
			int temp = solution[i][col1];
			solution[i][col1] = solution[i][col2];
			solution[i][col2] = temp;
		}
	}
	public void permuteRowBlock(int block1, int block2)
	{
		for (int i = 0; i <= 2; i++)
		{
			permuteRow(block1 * 3 + i, block2 * 3 + i);
		}
	}
	public void permuteColBlock(int block1, int block2)
	{
		for (int i = 0; i <= 2; i++)
		{
			permuteCol(block1 * 3 + i, block2 * 3 + i);
		}
	}
	public void rotate90()
	{
		int[][] result = new int[ROWS][COLS];
		for (int i = 0; i <= 8; i++)
		{
			for (int j = 0; j <= 8; j++)
			{
				result[i][j] = solution[8-j][i];
			}
		}
		solution = result;
	}
	public void rotate180()
	{
		int[][] result = new int[ROWS][COLS];
		for (int i = 0; i <= 8; i++)
		{
			for (int j = 0; j <= 8; j++)
			{
				result[i][j] = solution[8-j][8-i];
			}
		}
		solution = result;
	}
	public void rotate270()
	{
		int[][] result = new int[ROWS][COLS];
		for (int i = 0; i <= 8; i++)
		{
			for (int j = 0; j <= 8; j++)
			{
				result[i][j] = solution[j][8-i];
			}
		}
		solution = result;
	}
	public void reflectHorizontal()
	{
		for (int i = 0; i <= 3; i++)
		{
			permuteRow(i, 8-i);
		}
	}
	public void reflectVertical()
	{
		for (int i = 0; i <= 3; i++)
		{
			permuteCol(i, 8-i);
		}
	}
	public void reflectDiagonal()
	{
		for (int i = 0; i <= 7; i++)
		{
			for (int j = 0; j<= 7 - i; j++)
			{
				int k = 8 - i - j;
				int temp = solution[i][j];
				solution[i][j] = solution[i + k][j + k];
				solution[i + k][j + k] = temp;
			}
		}
	}
	public void solutionGenerator()
	{
		int ran = (int) (Math.random() * 46);
		for (int i = 0; i <= ran; i++)
		{
			int j = (int) (Math.random() * operations.size());
			String command = "";
			command = operations.get(j);
			if (command.equals("Permute 2-1-3 row"))
				permuteRow(0, 1);
			else if (command.equals("Permute 2-3-1 row"))
			{
				permuteRow(0, 1);
				permuteRow(1, 2);
			}
			else if (command.equals("Permute 3-1-2 row"))
			{
				permuteRow(0, 1);
				permuteRow(0, 2);
			}
			else if (command.equals("Permute 3-2-1 row"))
			{
				permuteRow(0, 2);
			}
			else if (command.equals("Permute 1-3-2 row"))
			{
				permuteRow(1, 2);
			}
			else if (command.equals("Permute 5-4-6 row"))
			{
				permuteRow(3, 4);
			}
			else if (command.equals("Permute 5-6-4 row"))
			{
				permuteRow(3, 4);
				permuteRow(4, 5);
			}
			else if (command.equals("Permute 6-4-5 row"))
			{
				permuteRow(3, 4);
				permuteRow(3, 5);
			}
			else if (command.equals("Permute 6-5-4 row"))
			{
				permuteRow(3, 5);
			}
			else if (command.equals("Permute 4-6-5 row"))
			{
				permuteRow(4, 5);
			}
			else if (command.equals("Permute 8-7-9 row"))
			{
				permuteRow(6, 7);
			}
			else if (command.equals("Permute 8-9-7 row"))
			{
				permuteRow(6, 7);
				permuteRow(7, 8);
			}
			else if (command.equals("Permute 9-7-8 row"))
			{
				permuteRow(6, 7);
				permuteRow(6, 8);
			}
			else if (command.equals("Permute 9-8-7 row"))
			{
				permuteRow(6, 8);
			}
			else if (command.equals("Permute 7-9-8 row"))
			{
				permuteRow(7, 8);
			}
			else if (command.equals("Permute 2-1-3 col"))
				permuteCol(0, 1);
			else if (command.equals("Permute 2-3-1 col"))
			{
				permuteCol(0, 1);
				permuteCol(1, 2);
			}
			else if (command.equals("Permute 3-1-2 col"))
			{
				permuteCol(0, 1);
				permuteCol(0, 2);
			}
			else if (command.equals("Permute 3-2-1 col"))
			{
				permuteCol(0, 2);
			}
			else if (command.equals("Permute 1-3-2 col"))
			{
				permuteCol(1, 2);
			}
			else if (command.equals("Permute 5-4-6 col"))
			{
				permuteCol(3, 4);
			}
			else if (command.equals("Permute 5-6-4 col"))
			{
				permuteCol(3, 4);
				permuteCol(4, 5);
			}
			else if (command.equals("Permute 6-4-5 col"))
			{
				permuteCol(3, 4);
				permuteCol(3, 5);
			}
			else if (command.equals("Permute 6-5-4 col"))
			{
				permuteCol(3, 5);
			}
			else if (command.equals("Permute 4-6-5 col"))
			{
				permuteCol(4, 5);
			}
			else if (command.equals("Permute 8-7-9 col"))
			{
				permuteCol(6, 7);
			}
			else if (command.equals("Permute 8-9-7 col"))
			{
				permuteCol(6, 7);
				permuteCol(7, 8);
			}
			else if (command.equals("Permute 9-7-8 col"))
			{
				permuteCol(6, 7);
				permuteCol(6, 8);
			}
			else if (command.equals("Permute 9-8-7 col"))
			{
				permuteCol(6, 8);
			}
			else if (command.equals("Permute 7-9-8 col"))
			{
				permuteCol(7, 8);
			}
			else if (command.equals("permute 2-1-3 rb"))
			{
				permuteRowBlock(0, 1);
			}
			else if (command.equals("permute 2-3-1 rb"))
			{
				permuteRowBlock(0, 1);
				permuteRowBlock(1, 2);
			}
			else if (command.equals("permute 3-1-2 rb"))
			{
				permuteRowBlock(0, 1);
				permuteRowBlock(0, 2);
			}
			else if (command.equals("permute 3-2-1 rb"))
			{
				permuteRowBlock(0, 2);
			}
			else if (command.equals("permute 1-3-2 rb"))
			{
				permuteRowBlock(1, 2);
			}
			else if (command.equals("permute 2-1-3 cb"))
			{
				permuteColBlock(0, 1);
			}
			else if (command.equals("permute 2-3-1 cb"))
			{
				permuteColBlock(0, 1);
				permuteColBlock(1, 2);
			}
			else if (command.equals("permute 3-1-2 cb"))
			{
				permuteColBlock(0, 1);
				permuteColBlock(0, 2);
			}
			else if (command.equals("permute 3-2-1 cb"))
			{
				permuteColBlock(0, 2);
			}
			else if (command.equals("permute 1-3-2 cb"))
			{
				permuteColBlock(1, 2);
			}
			else if (command.equals("rotate 90"))
				rotate90();
			else if (command.equals("rotate 180"))
				rotate180();
			else if (command.equals("rotate 270"))
				rotate270();
			else if (command.equals("reflect horizontal"))
				reflectHorizontal();
			else if (command.equals("reflect vertical"))
				reflectVertical();
			else if (command.equals("reflect diagonal"))
				reflectDiagonal();
			operations.remove(j);
		}
	}
	public boolean valid()
	{
		boolean valid = true;
		for(int i = 0; i <= 8; i++)
		{
			for (int j = 0; j <= 8; j++)
			{
				int[] col = new int[9];
				for (int k = 0; k <= 8; k++)
					col[k] = solution[k][j];
				int[] square = new int[9];
				int urow = 0;
				int drow = 0;
				int lcol = 0;
				int rcol = 0;
				if (i % 3 == 0)
					{
					urow = i + 1;
					drow = i + 2;
					}
				else if (i % 3 == 1)
				{
					urow = i - 1;
					drow = i + 1;
				}
				else {
					urow = i - 2;
					drow = i - 1;
				}
				if (j % 3 == 0)
				{
					lcol = j + 1;
					rcol = j + 2;
				}
				else if (j % 3 == 1)
				{
					lcol = j - 1;
					rcol = j + 1;
				}
				else {
					lcol = j - 2;
					rcol = j - 1;
				}
				square[0] = solution[urow][lcol];
				square[1] = solution[urow][j];
				square[2] = solution[urow][rcol];
				square[3] = solution[i][lcol];
				square[4] = solution[i][j];
				square[5] = solution[i][rcol];
				square[6] = solution[drow][lcol];
				square[7] = solution[drow][j];
				square[8] = solution[drow][rcol];
				if (appearances(solution[i][j], solution[i]) > 1 || appearances(solution[i][j], col) > 1 || appearances(solution[i][j], square) > 1)
					valid = false;
			}
		}
		return valid;
	}
	public static boolean appears(int a, int[] arr)
	{
		boolean appears = false;
		for (int i = 0; i < arr.length; i++)
			if (a == arr[i])
				appears = true;
		return appears;
	}
	public static int appearances(int a, int[] arr)
	{
		int counter = 0;
		for (int i = 0; i < arr.length; i++)
			if (a == arr[i])
				counter += 1;
		return counter;
	}
	public int[][] getSolution()
	{
		return solution;
	}
	
}
