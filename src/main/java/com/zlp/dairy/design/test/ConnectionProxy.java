package com.zlp.dairy.design.test;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Ygritte Zhu on 2021/1/26
 * 代理模式
 */
public class ConnectionProxy {

    public static void main(String[] args) {
        int rows = 0;
        ArrayList<Integer> list = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        System.out.print("请输入*号数：");
        rows = input.nextInt();
        for (int k = 0; k < rows; k++) {
            if (k % 2 != 0) {
                int count = k;
                for (int l = 1; l < k; l = l + 2) {
                    count = count + 2 * l;
                }
                if (count < rows) {
                    list.add(k);
                }
            }
        }
        System.out.println("画出最多的菱形");
        System.out.println();
        int sum = 0;
        if(null != list && !list.isEmpty()) {
            int n = (list.get(list.size() - 1) + 1) / 2;
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n - i; j++) {
                    System.out.print(" ");
                }
                for (int p = 1; p <= 2 * i - 1; p++) {
                    System.out.print("*");
                    sum++;
                }
                System.out.print("\n");
            }
            for (int i = n - 1; i >= 1; i--) {
                for (int j = 1; j <= n - i; j++) {
                    System.out.print(" ");
                }
                for (int q = 1; q <= 2 * i - 1; q++) {
                    System.out.print("*");
                    sum++;
                }
                System.out.print("\n");
            }
        }
        System.out.println("剩余*数:" + (rows - sum));
    }
}
