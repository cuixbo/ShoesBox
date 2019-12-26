package com.cuixbo.shoesbox;

import android.app.Application;

import com.cuixbo.lib.common.util.PreferenceUtil;
import com.cuixbo.shoesbox.data.Consts;
import com.cuixbo.shoesbox.data.local.ObjectBox;
import com.cuixbo.shoesbox.data.local.Owner;

import java.util.Arrays;

import androidx.collection.ArraySet;
import io.objectbox.Box;

/**
 * @author xiaobocui
 * @date 2019-12-09
 */
public class BoxApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // TODO 需要延迟加载
        ObjectBox.init(this);
        initOwners();
        initDictionary();
    }

    public void initOwners() {
        Box<Owner> ownerBox = ObjectBox.get().boxFor(Owner.class);
        if (ownerBox.count() == 0) {
            Owner cuixbo = new Owner();
            cuixbo.name = "小黄盖";
            cuixbo.gender = 0;
            ownerBox.put(cuixbo);

            Owner gina = new Owner();
            gina.name = "盖盖";
            gina.gender = 1;
            ownerBox.put(gina);
        }
    }

    public void initDictionary() {
        PreferenceUtil.get(this).setStringSet(Consts.Prefs.Keys.SEASONS, new ArraySet<>(Arrays.asList(Consts.Dicts.seasons)));
        PreferenceUtil.get(this).setStringSet(Consts.Prefs.Keys.BRANDS, new ArraySet<>(Arrays.asList(Consts.Dicts.brands)));
        PreferenceUtil.get(this).setStringSet(Consts.Prefs.Keys.TAGS, new ArraySet<>(Arrays.asList(Consts.Dicts.types)));
        PreferenceUtil.get(this).setStringSet(Consts.Prefs.Keys.COLORS, new ArraySet<>(Arrays.asList(Consts.Dicts.colors)));
        PreferenceUtil.get(this).setStringSet(Consts.Prefs.Keys.SIZES, new ArraySet<>(Arrays.asList(Consts.Dicts.sizes)));
        PreferenceUtil.get(this).setStringSet(Consts.Prefs.Keys.TAGS, new ArraySet<>(Arrays.asList(Consts.Dicts.tags)));
    }

}
