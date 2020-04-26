public class PrintNumberTable {

	// call validator class
	Validator v = new Validator();

	public void print(NumberTable nt, int puzOrAns) {
		// this will be either puzzle or answer table depending on the input
		Number[][] table;

		// int array to save the value of our table
		int[][] value = new int[9][9];

		boolean[][] orig = new boolean[9][9];
		// convert the int array to string array to deal with replacing 0 with
		// space issue
		String[][] valueStr = new String[9][9];

		// if puzOrAns is 0, then we print puzzle table, else, we print answer
		// table
		if (puzOrAns == 0) {
			table = nt.getPuzzle();
		} else {
			table = nt.getAnswer();
		}

		// convert int to string, replace 0 with " "
		for (int i = 0; i <= 8; i++) {
			for (int j = 0; j <= 8; j++) {
				value[i][j] = table[i][j].getValue();
				orig[i][j] = table[i][j].getOrig();
//				the following line can be used to test validator class
//				value[0][0] = 1;
				if (orig[i][j]) {
					switch (value[i][j]) {
					case 1:
						valueStr[i][j] = "⒈";
						break;
					case 2:
						valueStr[i][j] = "⒉";
						break;
					case 3:
						valueStr[i][j] = "⒊";
						break;
					case 4:
						valueStr[i][j] = "⒋";
						break;
					case 5:
						valueStr[i][j] = "⒌";
						break;
					case 6:
						valueStr[i][j] = "⒍";
						break;
					case 7:
						valueStr[i][j] = "⒎";
						break;
					case 8:
						valueStr[i][j] = "⒏";
						break;
					case 9:
						valueStr[i][j] = "⒐";
						break;
					}
					
				} else {
					if (value[i][j] == 0) {
						// replace 0 with space for the puzzle table
						valueStr[i][j] = "　";
					} else {         
						if (v.isValid(value[i][j], i, j, nt)) {
							switch (value[i][j]) {
							case 1:
								valueStr[i][j] = "⑴";
								break;
							case 2:
								valueStr[i][j] = "⑵";
								break;
							case 3:
								valueStr[i][j] = "⑶";
								break;
							case 4:
								valueStr[i][j] = "⑷";
								break;
							case 5:
								valueStr[i][j] = "⑸";
								break;
							case 6:
								valueStr[i][j] = "⑹";
								break;
							case 7:
								valueStr[i][j] = "⑺";
								break;
							case 8:
								valueStr[i][j] = "⑻";
								break;
							case 9:
								valueStr[i][j] = "⑼";
								break;
							}
						} else {
							switch (value[i][j]) {
							case 1:
								valueStr[i][j] = "⓵";
								break;
							case 2:
								valueStr[i][j] = "⓶";
								break;
							case 3:
								valueStr[i][j] = "⓷";
								break;
							case 4:
								valueStr[i][j] = "⓸";
								break;
							case 5:
								valueStr[i][j] = "⓹";
								break;
							case 6:
								valueStr[i][j] = "⓺";
								break;
							case 7:
								valueStr[i][j] = "⓻";
								break;
							case 8:
								valueStr[i][j] = "⓼";
								break;
							case 9:
								valueStr[i][j] = "⓽";
								break;
							}
						}
					}
				}
			}
		}

		// add a string array to save A to I as rowName
		String[] rowName = { "A", "B", "C", "D", "E", "F", "G", "H", "I" };

		// print column name         
		System.out.println("    ⒈  ⒉  ⒊   ⒋  ⒌  ⒍   ⒎  ⒏  ⒐  ");

		// print border
		System.out.println("  |---------|---------|---------|");

		for (int i = 0; i <= 2; i++) {
			// print row name
			System.out.print(rowName[i] + " | ");
			for (int j = 0; j <= 1; j++) {
				// print numbers
				System.out.print(valueStr[i][j] + "  ");
			}

			// print column 3
			System.out.print(valueStr[i][2] + " ");

			System.out.print("| ");
			for (int j = 3; j <= 4; j++) {
				System.out.print(valueStr[i][j] + "  ");

			}

			System.out.print(valueStr[i][5] + " ");

			System.out.print("| ");
			for (int j = 6; j <= 7; j++) {
				System.out.print(valueStr[i][j] + "  ");

			}

			System.out.print(valueStr[i][8] + " ");

			System.out.print("|");
			System.out.println();

		}
		System.out.println("  |---------|---------|---------|");

		for (int i = 3; i <= 5; i++) {
			System.out.print(rowName[i] + " | ");
			for (int j = 0; j <= 1; j++) {
				System.out.print(valueStr[i][j] + "  ");

			}

			System.out.print(valueStr[i][2] + " ");

			System.out.print("| ");
			for (int j = 3; j <= 4; j++) {
				System.out.print(valueStr[i][j] + "  ");

			}

			System.out.print(valueStr[i][5] + " ");

			System.out.print("| ");
			for (int j = 6; j <= 7; j++) {
				System.out.print(valueStr[i][j] + "  ");

			}

			System.out.print(valueStr[i][8] + " ");

			System.out.print("|");
			System.out.println();

		}
		System.out.println("  |---------|---------|---------|");

		for (int i = 6; i <= 8; i++) {
			System.out.print(rowName[i] + " | ");
			for (int j = 0; j <= 1; j++) {
				System.out.print(valueStr[i][j] + "  ");

			}

			System.out.print(valueStr[i][2] + " ");

			System.out.print("| ");
			for (int j = 3; j <= 4; j++) {
				System.out.print(valueStr[i][j] + "  ");

			}

			System.out.print(valueStr[i][5] + " ");

			System.out.print("| ");
			for (int j = 6; j <= 7; j++) {
				System.out.print(valueStr[i][j] + "  ");

			}

			System.out.print(valueStr[i][8] + " ");

			System.out.print("|");
			System.out.println();

		}
		System.out.println("  |---------|---------|---------|");

		// simple version
		// System.out.println("|---|---|---|---|---|---|---|---|---|");
		// for (int i = 0; i <= 8; i++) {
		// System.out.print("| ");
		// for (int j = 0; j <= 8; j++) {
		// System.out.print(valueStr[i][j] + " | ");
		// }
		// System.out.println();
		// System.out.println("|---|---|---|---|---|---|---|---|---|");
		// }

	}

	public static void main(String[] args) {
		// int[][] value = new int[9][9];
		// for (int i = 0; i <= 8; i++) {
		// for (int j = 0; j <= 8; j++) {
		// value[i][j] = 1;
		// }
		// }
		// System.out.println(Arrays.deepToString(value).replace("], ",
		// "\n").replace("[[", "").replace("]]", "").replace("[",
		// "").replace(",", ""));

		NewGameCreator nGC = new NewGameCreator();
		nGC.setGameSeed(0);
		// Read sudoku csv files
		nGC.setPuzzleS();
		nGC.setAnswerS();
		String puzzleS = nGC.getPuzzleS();
		String answerS = nGC.getAnswerS();
		NumberTable nt = new NumberTable(puzzleS, answerS);

		PrintNumberTable pNT = new PrintNumberTable();
		pNT.print(nt, 1);

	}
}
