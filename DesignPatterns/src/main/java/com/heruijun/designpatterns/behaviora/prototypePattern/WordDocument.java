package com.heruijun.designpatterns.behaviora.prototypePattern;

import java.util.ArrayList;

/**
 * Created by heruijun on 2018/2/23.
 */

public class WordDocument implements Cloneable {

    // 文本
    private String mText;
    // 图片
    private ArrayList<String> mImages = new ArrayList<>();

    public WordDocument() {
        System.out.println("----------- WordDocument 构造函数 ----------");
    }

    public String getText() {
        return mText;
    }

    public void setText(String mText) {
        this.mText = mText;
    }

    public ArrayList<String> getImages() {
        return mImages;
    }

    public void addImage(String img) {
        this.mImages.add(img);
    }

    @Override
    protected WordDocument clone() {
        try {
            WordDocument doc = (WordDocument) super.clone();
            doc.mText = this.mText;
            // doc.mImages = this.mImages; // 浅拷贝
            doc.mImages = (ArrayList<String>) this.mImages.clone(); // 深拷贝
            return doc;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void showDocument() {
        System.out.println("----------- Word Content Start ----------");
        System.out.println("Text: " + mText);
        System.out.println("Image List: ");
        for (String imgName : mImages) {
            System.out.println("image name : " + imgName);
        }
        System.out.println("----------- Word Content end ----------");
    }
}
