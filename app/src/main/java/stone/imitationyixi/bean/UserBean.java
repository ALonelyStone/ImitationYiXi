package stone.imitationyixi.bean;

/**
 * @author stone
 *         用户信息
 */

public class UserBean {

    private UserBean data;
    private int res;

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    private boolean isLogin;

    public UserBean getData() {
        return data;
    }

    public void setData(UserBean data) {
        this.data = data;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    private int id;
    private String nickname;
    private String pic;
    private String background;
    private String desc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "data=" + data +
                ", res=" + res +
                ", id=" + id +
                ", nickname='" + nickname + '\'' +
                ", pic='" + pic + '\'' +
                ", background='" + background + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }

    public void setDesc(String desc) {
        this.desc = desc;

    }
}
