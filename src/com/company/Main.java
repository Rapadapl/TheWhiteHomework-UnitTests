package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String lineArg = null;
        String inputString = null;
        try {
            lineArg = br.readLine();
            inputString = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] strs = null;
        int[] arg = new int[3];
        if (lineArg != null) {
            strs = lineArg.trim().split("\\s+");
        }

        for (int i = 0; i < Math.min(3, Objects.requireNonNull(strs).length); i++) {
            arg[i] = Integer.parseInt(strs[i]);
        }

        Magic magic = new Magic();
        var ans = magic.doSomeMagic(arg[0],arg[1],arg[2],inputString);
        for (var str:ans) {
            System.out.println(str);
        }

    }
}

