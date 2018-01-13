package com.heruijun.designpatterns.behaviora.IteratorPattern;


/**
 * Created by heruijun on 2018/1/8.
 */

public class ConcreteAggregate extends Aggregate {

    private Object[] objArray = null;

    /**
     * 构造方法，传入聚合对象的具体内容
     *
     * @param objArray
     */
    public ConcreteAggregate(Object[] objArray) {
        this.objArray = objArray;
    }

    @Override
    public Iterator createIterator() {
        return new ConcreteIterator(this);
    }

    /**
     * 取值方法：向外界提供聚集元素
     *
     * @param index
     * @return
     */
    public Object getElement(int index) {
        if (index < objArray.length) {
            return objArray[index];
        } else {
            return null;
        }
    }

    /**
     * 取值方法：向外界提供聚集的大小
     *
     * @return
     */
    public int size() {
        return objArray.length;
    }
}
