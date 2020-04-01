package com.cloud.dao.api.design.simpleFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * @ Description   :  Main
 * @ Author        :  秦云峰（cloud）
 * @ CreateDate    :  2020-03-31 9:15
 */
public class Main {

    public static void main(String[] args) {
        String type = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Scanner scanner = new Scanner(System.in);
        try {
            //type = reader.readLine();
            type = scanner.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FruitFactory.getFruit(type);
    }
}
