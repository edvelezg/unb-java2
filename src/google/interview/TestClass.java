package google.interview;

import java.util.HashSet;

class TestClass {

    public static boolean hasDups(String str)
    {
    	if (str == null || str.length() == 0) return true;
		
        boolean[] charSet = new boolean[256];
        for (int i = 0; i < str.length(); ++i)
        {
            if (charSet[str.charAt(i)] == true) return true;
            else charSet[str.charAt(i)] = true;
        }
        return false;
    }


    public static boolean hasDupsHM(String str)
    {
        if (str == null || str.length() == 0) return true;

        HashSet<Integer> hm = new HashSet<Integer>();
        for (int i = 0; i < str.length(); ++i)
        {
            if (hm.contains((int) str.charAt(i))) return true;
            else {
                hm.add( (int) str.charAt(i) );
            }
        }
        return false;
    }    


    public static boolean hasDupsND(String str)
    {
        if (str == null || str.length() == 0) return true;

        for (int i = 0; i < str.length()-1; ++i)
        {
            for (int j = i+1; j < str.length(); ++j)
            {
                if (str.charAt(i) == str.charAt(j)) return true;
            }
        }
        return false;
    }    

    public static void main (String args[])
    {
        System.out.println(hasDupsND("hello"));
        System.out.println(hasDupsND("helo"));
        System.out.println(hasDupsND(""));
        System.out.println(hasDupsND(null));
    }


}
