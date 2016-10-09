package other;
public class StringRotator {
	public static void main(String[] args) {

		StringRotator rtr = new StringRotator();
		
		String str = "team";
		
		char[] arr = str.toCharArray();
		for (int i = 0; i < arr.length; i++) {
			arr = rtr.rotateStr(arr, 0);
		}
		
		
	}
	
	public char[] rotateStr(char[] str, int n)
	{
		if (n == str.length-1) {
			System.out.println(str);
			char tmp = str[n];
			str[n] = str[n-1];
			str[n-1] = tmp;
			return str;
		}
		else
		{
			str = rotateStr(str, n+1);
			System.out.println(str);
			if (n > 0) {
				char tmp = str[n];
				str[n] = str[n-1];
				str[n-1] = tmp;			
			}
			return str;
		}
	}

}


