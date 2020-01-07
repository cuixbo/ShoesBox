package com.cuixbo.shoesbox.data;

/**
 * @author xiaobocui
 * @date 2019-12-12
 */
public class Consts {

    public static class Dicts {
        public static String[] seasons = new String[]{"春天", "夏天", "秋天", "冬天", "春夏", "秋冬"};
        public static String[] brands = new String[]{"耐克", "阿迪", "安踏", "李宁"};
        public static String[] types = new String[]{"网鞋", "跑步鞋", "帆布鞋", "篮球鞋", "板鞋", "皮鞋", "高跟鞋", "高筒靴"};
        public static String[] colors = new String[]{"白色", "黑色", "蓝色"};
        public static String[] sizes = new String[]{"38", "38.5", "39", "42", "42.5", "43", "43.5"};
        public static String[] tags = new String[]{};
    }

    public static class Prefs {

        public static class Keys {
            public static final String BRANDS = "brands";
            public static final String SEASONS = "seasons";
            public static final String TYPES = "types";
            public static final String COLORS = "colors";
            public static final String SIZES = "sizes";
            public static final String TAGS = "tags";

            public static final String PIC_MODE = "pic_mode";

        }

    }
}
