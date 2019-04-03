package Algorithm.JUNGOL.go;

import java.util.Scanner;

public class J1239 {
	static String[] alpha = { "000000", "001111", "010011", "011100", "100110", "101001", "110101", "111010" };

	public static boolean isRight(String alpha, String sixStr) {
		int count = 0;
		for (int i = 0; i < 6; ++i) {
			if (alpha.charAt(i) != sixStr.charAt(i))
				count++;
		}

		if (count == 0 || count == 1)
			return true;
		else
			return false;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int len = Integer.parseInt(sc.nextLine());
		String str = sc.nextLine();
		String result = "";

		for (int i = 0; i < len; i++) {
			boolean flag = false;
			for (int j = 0; j < 8; ++j) {
				if (isRight(alpha[j], str.substring(i * 6, i * 6 + 6))) {
					flag = true;
					result += (char) (j + 65);
					break;
				}
			}
			if (!flag) {
				System.out.println(i + 1);
				return;
			}
		}

		System.out.println(result);
	}
}
