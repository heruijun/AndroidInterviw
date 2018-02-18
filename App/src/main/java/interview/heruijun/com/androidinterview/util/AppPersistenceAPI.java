package interview.heruijun.com.androidinterview.util;

import android.content.Context;

import com.heruijun.baselibrary.util.AppPersistence;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by heruijun on 2018/2/18.
 */

public class AppPersistenceAPI {

    private AppPersistence mAppPersistence;

    public AppPersistenceAPI(Context context) {
        mAppPersistence = new AppPersistence(context);
    }
    
    @SuppressWarnings("unchecked")
    public Set<String> getCachedImageSet() {
        Object object = mAppPersistence.get("imgview_cached");
        if (null == object) {
            return new HashSet<>();
        }
        return (HashSet<String>) object;
    }

    public void setCachedImageSet(Set<String> imgs) {
        mAppPersistence.set("imgview_cached", imgs);
        mAppPersistence.saveSettings();
    }

    /**
     * 清除所有持久化数据方法，慎用
     */
    public void clearPersist() {
        mAppPersistence.clearPersist();
    }

}
