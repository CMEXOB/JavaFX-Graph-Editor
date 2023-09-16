package com.applic;

import com.applic.entity.lines.ZdaLine;

public class EntityTesting extends  Thread {
    public static void main(String[] args) {
        EntityTesting e = new EntityTesting();
        e.run();
    }

    @Override
    public void run() {
        for(int i = 1; i < 3; ++i){
            System.out.println(i);
        }
    }
}
