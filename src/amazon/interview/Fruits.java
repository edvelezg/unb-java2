package amazon.interview;

import java.util.List;

public class Fruits {

    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    public int checkWinner(List<List<String>> codeList,
                           List<String> shoppingCart)
    {
        for (List<String> codes : codeList) {
            for (String code : codes) {
                if (!shoppingCart.isEmpty()) {
                    String s = shoppingCart.get(0);
                    if (s.equals("anything") && code.equals("anything")) {
                        shoppingCart.remove(0);
                    } else if (!code.equals(s)) {
                        return 0;
                    }
                    shoppingCart.remove(0);
                }

            }
        }

        return 1;
    }
    // METHOD SIGNATURE ENDS
}
