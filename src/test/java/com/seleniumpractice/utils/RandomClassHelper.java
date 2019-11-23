package com.seleniumpractice.utils;

import java.util.Random;

public class RandomClassHelper {

    public int generateRandomNumber(int size)
    {
       // Random r= new Random();
       // int num= r.nextInt(size-1);
       // return num;
       return new Random().nextInt(size-1);

    }

}
