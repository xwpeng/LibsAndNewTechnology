package xwpeng.com.tsubcomponent;

import android.content.Context;
import android.util.Log;

import dagger.Module;
import dagger.Provides;

@Module
public class AppMudule {
    private Context context;

    public AppMudule(Context context) {
        this.context = context;
    }

    @Provides
    public Context getContext() {
        Log.e("xwpeng16", "provide context: " + context.toString());
        return context;
    }
}
