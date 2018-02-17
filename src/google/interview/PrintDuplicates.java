package google.interview;

public class PrintDuplicates {

	public static void main(String[] args) {
		String str1 = "   hello girl friand    ";

		String[] split = str1.trim().split(" ");
		System.out.println(split.length);

		int len = str1.length();
		int i = 0, word = 0, space = 0;
		
		while (i < len) {
			while (i < len) {
				char c = str1.charAt(i);
				if (c != ' ') {
					word += 1;
					i++;
					break;
				}
				i++;
			}

			while (i < len) {
				char c = str1.charAt(i);
				if (c == ' ') {
					space += 1;
					i++;
					break;
				}
				i++;
			}
		}
		
		System.out.println(word);
	}

}
