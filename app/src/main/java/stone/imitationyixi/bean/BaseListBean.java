package stone.imitationyixi.bean;

import java.util.List;

/**
 * @author stone
 */

public class BaseListBean<T> {
    private int res;
    private List<T> data;

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseListBean{" +
                "res=" + res +
                ", data=" + data +
                '}';
    }
}
