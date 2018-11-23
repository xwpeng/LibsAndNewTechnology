package xwpeng.com.tdragger;

import javax.inject.Inject;

/**
 * Created by xwpeng on 2018/11/23.
 */
public class Pot {
    private Rose rose;

    @Inject
    public Pot(Rose rose) {
        this.rose = rose;
    }

    public String show() {
        return rose.whisper();
    }

}
