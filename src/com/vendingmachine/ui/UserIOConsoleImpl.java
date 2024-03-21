package com.vendingmachine.ui;

import java.math.BigDecimal;
import java.util.Scanner;

public class UserIOConsoleImpl implements UserIO{
    Scanner s = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        return s.nextLine();
    }

    @Override
    public BigDecimal readBigDecimal(String prompt) {
        System.out.println(prompt);
        return BigDecimal.valueOf(
                Double.parseDouble(s.nextLine().trim()));
    }

    @Override
    public BigDecimal readBigDecimal(String prompt, BigDecimal min, BigDecimal max) {
        while(true){
            System.out.println(prompt);
            try{
                BigDecimal input = BigDecimal.valueOf(
                        Double.parseDouble(s.nextLine().trim()));
                if(input.compareTo(min) > 0 && input.compareTo(max) < 0) return input;
            }catch(Exception ignored){}
            System.out.println("Invalid input! Please try again.");
        }
    }

    @Override
    public int readInt(String prompt) {
        System.out.println(prompt);
        return Integer.parseInt(s.nextLine().trim());
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        while(true){
            System.out.println(prompt);
            try{
                int input = Integer.parseInt(s.nextLine().trim());
                if(input >= min && input <= max) return input;
            }catch(Exception ignored){}
            System.out.println("Invalid input! Please try again.");
        }
    }

    @Override
    public double readDouble(String prompt) {
        System.out.println(prompt);
        return Double.parseDouble(s.nextLine().trim());
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        while(true){
            System.out.println(prompt);
            try{
                double input = Double.parseDouble(s.nextLine().trim());
                if(input >= min && input <= max) return input;
            }catch(Exception ignored){}
            System.out.println("Invalid input! Please try again.");
        }
    }

    @Override
    public float readFloat(String prompt) {
        System.out.println(prompt);
        return Float.parseFloat(s.nextLine().trim());
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        while(true){
            System.out.println(prompt);
            try{
                float input = Float.parseFloat(s.nextLine().trim());
                if(input >= min && input <= max) return input;
            }catch(Exception ignored){}
            System.out.println("Invalid input! Please try again.");
        }
    }

    @Override
    public long readLong(String prompt) {
        System.out.println(prompt);
        return Long.parseLong(s.nextLine().trim());
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        while(true){
            System.out.println(prompt);
            try{
                long input = Long.parseLong(s.nextLine().trim());
                if(input >= min && input <= max) return input;
            }catch(Exception ignored){}
            System.out.println("Invalid input! Please try again.");
        }
    }
}
