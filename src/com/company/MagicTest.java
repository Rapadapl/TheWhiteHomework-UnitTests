package com.company;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class MagicTest {

    Magic magic = new Magic();

    private boolean validateResult(ArrayList<String> answer, int amountOfRows,int minLength, int maxLength,String stringInput){
        boolean checkResult = true;
        StringBuilder concatAnswer = new StringBuilder();
        for (var str:answer) {
            concatAnswer.append(str);
            if (str.length() < minLength || str.length() > maxLength) {
                checkResult = false;
                break;
            }
        }
        if (answer.size() != amountOfRows || !concatAnswer.toString().equals(stringInput))
            checkResult = false;
        return checkResult;
    }

    //region positives
    @ParameterizedTest
    @MethodSource("getAnswer")
    public void getAnswer(int amountOfRows,int minLength, int maxLength,String stringInput) {

        var result = magic.doSomeMagic(amountOfRows,minLength,maxLength,stringInput);
        var validation = validateResult(result,amountOfRows,minLength,maxLength,stringInput);
        assertTrue(validation);
    }

    static Stream<Arguments> getAnswer() {
        return Stream.of(
                arguments(3,2,5,"abrakadabra"), //basic two for
                arguments(5,2,5,"abrakadabr"), //basic only second for
                arguments(5,2,2,"abrakadabr") //min = max
        );
    }

    //endregion positives

    //region negatives

    @ParameterizedTest
    @MethodSource("getAnswerWithWrongInput")
    public void getAnswerWithWrongInput(int amountOfRows,int minLength, int maxLength,String stringInput) {

       var result = assertThrows(
               RuntimeException.class,
               () -> magic.doSomeMagic(amountOfRows,minLength,maxLength,stringInput)
       );
        assertEquals("Wrong vars",result.getLocalizedMessage());

    }
    static Stream<Arguments> getAnswerWithWrongInput() {
        return Stream.of(
                arguments(0,2,5,"abrakadabra"), //wrong amount
                arguments(5,-1,5,"abrakadabr"), //wrong min
                arguments(5,2,1,"abrakadabr"), //max > min
                arguments(5,1,-1,"abrakadabr"), //wrong max
                arguments(3,2,5, "") //wronr string
        );
    }


    @ParameterizedTest
    @MethodSource("getNoAnswers")
    public void getNoAnswers(int amountOfRows,int minLength, int maxLength,String stringInput) {

        var result = assertThrows(
                RuntimeException.class,
                () -> magic.doSomeMagic(amountOfRows,minLength,maxLength,stringInput)
        );
        assertEquals("There is no answer",result.getLocalizedMessage());

    }
    static Stream<Arguments> getNoAnswers() {
        return Stream.of(
                arguments(4,1,2,"abrakadabra"), //basic
                arguments(5,3,3,"abrakadabr"), //length % amountOfRows = 0, min=max
                arguments(6,2,2,"abrakadabra") //length % amountOfRows != 0, min=max

        );
    }




    //endregion negatives
}