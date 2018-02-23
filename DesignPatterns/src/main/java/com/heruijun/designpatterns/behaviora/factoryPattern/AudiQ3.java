package com.heruijun.designpatterns.behaviora.factoryPattern;

/**
 * Created by heruijun on 2018/2/23.
 */

public class AudiQ3 extends AudiCar {

    @Override
    public void drive() {
        System.out.println("Q3 启动啦");
    }

    @Override
    public void selfNavigation() {
        System.out.println("Q3 开始自动巡航");
    }
}
