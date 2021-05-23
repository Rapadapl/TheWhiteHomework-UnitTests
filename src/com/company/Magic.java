package com.company;

import java.util.ArrayList;

public class Magic {

    public ArrayList<String> doSomeMagic(int amountOfRows,int minLength, int maxLength,String stringInput) {

        if (amountOfRows <= 0 || minLength <= 0 || maxLength <= 0 || maxLength < minLength || stringInput.length() == 0)
            throw new RuntimeException("Wrong vars");

        int outputAverageLength = stringInput.length();
        int outputMaxLength = (outputAverageLength + amountOfRows - 1) / amountOfRows;
        int outputMinCount = outputAverageLength % amountOfRows;
        outputAverageLength = outputAverageLength / amountOfRows;


        ArrayList<String> outputStrings = new ArrayList<>();

        if (outputMaxLength > maxLength || outputAverageLength < minLength)
            throw new RuntimeException("There is no answer");

        for (int i = 0; i < outputMinCount; ++i) {
            StringBuilder tempOutString = new StringBuilder();
            for (int j = 0; j < outputAverageLength + 1; ++j) {
                tempOutString.append(stringInput.charAt(j));
            }
            outputStrings.add(tempOutString.toString());
            stringInput = stringInput.substring(outputAverageLength + 1);
        }
        for (int i = outputMinCount; i < amountOfRows; ++i) {
            StringBuilder tempOutString = new StringBuilder();
            for (int j = 0; j < outputAverageLength; ++j) {
                tempOutString.append(stringInput.charAt(j));
            }
            outputStrings.add(tempOutString.toString());
            stringInput = stringInput.substring(outputAverageLength);
        }
        return outputStrings;
    }


}
