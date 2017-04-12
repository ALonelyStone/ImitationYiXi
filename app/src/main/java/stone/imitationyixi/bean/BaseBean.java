package stone.imitationyixi.bean;

/**
 * @author stone
 */

public class BaseBean<T> {
    private int res;
    private T data;

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseBean{" +
                "res=" + res +
                ", data=" + data +
                '}';
    }
}
