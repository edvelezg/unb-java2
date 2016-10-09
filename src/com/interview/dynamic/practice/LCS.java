package com.interview.dynamic.practice;

public class LCS {
    public static void main(String args[]){
        LCS lcs = new LCS();
        String str1 = "ABCDGHLQR";
        String str2 = "AEDPHR";
        
        int result = lcs.lcsDynamic(str1.toCharArray(), str2.toCharArray());
        System.out.print(result);
    }

    private int lcsDynamic(char[] str1, char[] str2) {
        int max = 0;
        int mtx[][] = new int[str1.length+1][str2.length+1];
        for (int i = 1; i < mtx.length; i++) {
            for (int j = 1; j < mtx[i].length; j++) {
                if (str1[i-1] == str2[j-1]) {
                    mtx[i][j] = mtx[i-1][j-1] + 1;
                    
                    if (max < mtx[i][j]) {
                        max = mtx[i][j];
                    }
                } else {
                    mtx[i][j] = Math.max(mtx[i-1][j], mtx[i][j-1]); 
                }
                
            }
        }
        return max;
    }

}
