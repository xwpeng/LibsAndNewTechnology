package xwpeng.com.tcomponents.bean;

/**
 * Created by xwpeng on 2018/11/29.
 */
public class Pot {
    private Flower flower;

    public Pot(Flower flower) {
        this.flower = flower;
    }

    public String show() {
        return flower.whisper();
    }
}
