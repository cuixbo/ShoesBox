package com.cuixbo.shoesbox.model;

import com.cuixbo.shoesbox.contract.OwnerManagerContract;
import com.cuixbo.shoesbox.data.local.ObjectBox;
import com.cuixbo.shoesbox.data.local.Owner;

import java.util.List;

import io.objectbox.Box;

/**
 * @author xiaobocui
 * @date 2020-01-15
 */
public class OwnerModel implements OwnerManagerContract.Model {
    /**
     * 获取Owner的Box对象
     *
     * @return Box<Owner> 实例
     */
    private Box<Owner> getOwnerBox() {
        return ObjectBox.get().boxFor(Owner.class);
    }

    /**
     * 获取Owner列表
     *
     * @return List<Owner> 列表
     */
    public List<Owner> getOwners() {
        return getOwnerBox()
                .query()
                .build()
                .find();
    }

    /**
     * 添加Owner
     *
     * @return long
     */
    public long addOwner(String name) {
        Owner owner = new Owner();
        owner.name = name;
        return getOwnerBox().put(owner);
    }
}
