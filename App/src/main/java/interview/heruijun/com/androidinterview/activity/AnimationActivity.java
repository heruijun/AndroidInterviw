package interview.heruijun.com.androidinterview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import interview.heruijun.com.androidinterview.R;

public class AnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
    }

    //    1.帧动画
    //    <?xml version="1.0" encoding="utf-8"?>
    //    <animation-list xmlns:android="http://schemas.android.com/apk/res/android">
    //        <item
    //        android:drawable="@drawable/a_0"
    //        android:duration="100" />
    //        <item
    //        android:drawable="@drawable/a_1"
    //        android:duration="100" />
    //        <item
    //        android:drawable="@drawable/a_2"
    //        android:duration="100" />
    //    </animation-list>



    //    2.补间动画
    //    <?xml version="1.0" encoding="utf-8"?>
    //    <set xmlns:android="http://schemas.android.com/apk/res/android"
    //        android:interpolator="@[package:]anim/interpolator_resource"
    //        android:shareInterpolator=["true" | "false"] >
    //        <alpha
    //        android:fromAlpha="float"
    //        android:toAlpha="float" />
    //        <scale
    //        android:fromXScale="float"
    //        android:toXScale="float"
    //        android:fromYScale="float"
    //        android:toYScale="float"
    //        android:pivotX="float"
    //        android:pivotY="float" />
    //        <translate
    //        android:fromXDelta="float"
    //        android:toXDelta="float"
    //        android:fromYDelta="float"
    //        android:toYDelta="float" />
    //        <rotate
    //        android:fromDegrees="float"
    //        android:toDegrees="float"
    //        android:pivotX="float"
    //        android:pivotY="float" />
    //        <set>
    //            ...
    //        </set>
    //    </set>
}
