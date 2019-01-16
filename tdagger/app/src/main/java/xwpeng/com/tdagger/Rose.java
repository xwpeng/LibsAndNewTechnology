package xwpeng.com.tdagger;

import javax.inject.Inject;

/**
 * Created by xwpeng on 2018/11/23.
 */
public class Rose extends Flower {
    @Inject
    public Rose() {
    }

    public String whisper() {
        return "热恋";
    }
}
