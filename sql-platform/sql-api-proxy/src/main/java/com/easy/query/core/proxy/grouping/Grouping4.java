package com.easy.query.core.proxy.grouping;

import com.easy.query.core.proxy.core.draft.AbstractDraft;

/**
 * create time 2023/12/28 21:00
 * 文件说明
 *
 * @author xuejiaming
 */
public class Grouping4<TKey1, TKey2, TKey3, TKey4> extends AbstractDraft {
    private TKey1 key1;
    private TKey2 key2;
    private TKey3 key3;
    private TKey4 key4;

    public TKey1 getKey1() {
        return key1;
    }

    public void setKey1(TKey1 key1) {
        this.key1 = key1;
    }

    public TKey2 getKey2() {
        return key2;
    }

    public void setKey2(TKey2 key2) {
        this.key2 = key2;
    }

    public TKey3 getKey3() {
        return key3;
    }

    public void setKey3(TKey3 key3) {
        this.key3 = key3;
    }

    public TKey4 getKey4() {
        return key4;
    }

    public void setKey4(TKey4 key4) {
        this.key4 = key4;
    }

    @Override
    public int capacity() {
        return 4;
    }

    @Override
    public void setValues(int index, Object value) {
        switch (index) {
            case 0:
                this.setKey1((TKey1) value);
                break;
            case 1:
                this.setKey2((TKey2) value);
                break;
            case 2:
                this.setKey3((TKey3) value);
                break;
            case 3:
                this.setKey4((TKey4) value);
                break;
        }
    }
}
