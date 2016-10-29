package other;
public class Permutations {
    private boolean[] used;
    private StringBuilder out = new StringBuilder();
    private final String in;

    public static void main(String[] args) {
        Permutations p = new Permutations("abc");
        p.permute();
    }

    public Permutations(final String str) {
        in = str; 
        used = new boolean[in.length()];
    }

    public void permute() {
        System.out.println("permute()");
        if ( out.length() == in.length() ) {
            System.out.println("out.length() == in.length()");
            System.out.println(out);
            return;
        }
        for ( int i = 0; i < in.length(); ++i ) {
            if ( used[i] ) continue;
            out.append(in.charAt(i));
            used[i] = true;
            System.out.println(String.format("befor: %5d%8s%12s", i, out, join(used)));
            permute();
            used[i] = false;
            out.setLength(out.length() - 1);
            System.out.println(String.format("after: %5d%8s%12s", i, out, join(used)));
        }
    }

    public String join(boolean[] array) {
        StringBuffer b = new StringBuffer();
        for ( int i = 0; i < array.length - 1; i++ ) {
            b.append(array[i] ? "T" : "F");
            b.append(",");
        }
        b.append(array[array.length - 1] ? "T" : "F");
        return b.toString();
    }
}
