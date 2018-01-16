package interview.heruijun.com.androidinterview.classloader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import interview.heruijun.com.androidinterview.R;

public class ClassloaderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classloader);

        ClassLoader classLoader = getClassLoader();
        if (classLoader != null) {
            Log.e("classloader: ", classLoader.toString());

            while (classLoader.getParent() != null) {
                classLoader = classLoader.getParent();
                Log.e("classloader: ", classLoader.toString());
            }
        }
    }
}
