package com.cuixbo.shoesbox.data.local;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * shoes_item
 * id,owner_name,brand,material,color,season,gender,size,images,tags,comment,extra,create_at
 *
 * @author xiaobocui
 * @date 2019-12-09
 */
@Entity
public class Shoes {
    @Id
    public long id;
    /**
     * 编号
     */
    public String sNumber;
    public String ownerName;
    public String brand;
    /**
     * 类型
     */
    public String type;
    public String material;
    public String color;
    public String season;
    public int gender;
    public float size;
    public String images;
    public String tags;
    public String comment;
    public String extra;
    public String createAt;
    public String updateAt;

//    public long brand_id;
//    public long material_id;
}
