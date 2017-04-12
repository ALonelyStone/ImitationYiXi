package stone.imitationyixi.bean;

/**
 * Created by Administrator on 2017/3/7.
 */

public class LonginAndRegistBean {
    private String data;//注册成功才有数据
    private String msg;//
    private int res;//返回3种int类型,返回0:登录成功/注册错误(帐号错误?),返回1:注册用户已经存在,返回2:登录帐号错误/注册成功

    @Override
    public String toString() {
        return "RegistBean{" +
                "data='" + data + '\'' +
                ", msg='" + msg + '\'' +
                ", res=" + res +
                '}';
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }
}
