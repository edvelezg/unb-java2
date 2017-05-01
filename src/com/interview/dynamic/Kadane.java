package com.interview.dynamic;

class Kadane
{
    public static void main (String[] args)
    {
        int [] a = {-2, -3, 4, -1, -2, 1, 5, -3};
        System.out.println("Maximum contiguous sum is " +
                                       maxSubArraySum(a));
    }
 
    static int maxSubArraySum(int a[])
    {
        int size = a.length;
        int am = Integer.MIN_VALUE, rm = 0;
 
        for (int i = 0; i < size; i++)
        {
            rm = rm + a[i];
            if (am < rm)
                am = rm;
            if (rm < 0)
                rm = 0;
        }
        return am;
    }
}