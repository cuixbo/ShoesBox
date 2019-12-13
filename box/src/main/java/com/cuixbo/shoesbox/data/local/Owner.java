package com.cuixbo.shoesbox.data.local;

import java.io.Serializable;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Unique;

/**
 * owner
 * id,name,avatar,gender,comment
 *
 * @author xiaobocui
 * @date 2019-12-09
 */
@Entity
public class Owner implements Serializable {

    @Id
    public long id;
    @Unique
    public String name;
    public int gender;
    public String avatar;
    public String comment;
}
