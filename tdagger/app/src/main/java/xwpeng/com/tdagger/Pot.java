package xwpeng.com.tdagger;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by xwpeng on 2018/11/23.
 */
public class Pot {
    private Flower flower;
//
//    @Inject
//    public Pot(@Named("Rose") Flower flower) {
//        this.flower = flower;
//    }

    @Inject
    public Pot(@FlowerModule.IsRose Flower flower) {
        this.flower = flower;
    }

    public String show() {
        return flower.whisper();
    }

}
