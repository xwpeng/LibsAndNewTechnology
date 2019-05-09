package xwpeng.com.tdagger;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by xwpeng on 2018/11/23.
 */
public class Pot {
    @FlowerModule.IsRose
    @Inject
    Flower flower;
//
//    @Inject
//    public Pot(@Named("Rose") Flower flower) {
//        this.flower = flower;
//    }

//    @Inject
//    public Pot(Rose rose) {
//        this.flower = rose;
//    }

    @Inject
    public Pot() {
    }

    public String show() {
        return flower.whisper();
    }


}
