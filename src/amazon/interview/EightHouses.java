package amazon.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EightHouses {

    public List<Integer> cellCompete(int[] states, int days) {
        for (int i = 0; i < days; i++) {
            int[] prev = Arrays.copyOf(states, states.length);
            System.out.println(Arrays.toString(prev));
            for (int j = 0; j < prev.length; j++) {
                states[j] = update(prev, j, states.length);
            }
        }

        List<Integer> list = new ArrayList<>(states.length);
        for (int state : states) {
            list.add(state);
        }

        return list;

    }

    private int update(int[] prev, int pos, int length) {
        if (pos == 0) {
            if (prev[pos + 1] == 1) {
                return 1;
            } else {
                return 0;
            }
        }
        if (pos == length - 1) {
            if (prev[pos - 1] == 1) {
                return 1;
            } else {
                return 0;
            }
        }

        if (prev[pos-1] == 0 && prev[pos+1] == 0) {
            return 0;
        }
        if (prev[pos-1] == 1 && prev[pos+1] == 1) {
            return 0;
        }

        return 1;
    }


}
