package stone.imitationyixi.bean;

import java.util.List;

/**
 * @author stone
 *         讲者
 */


public class LecturerBean {

    private int id;
    private String nickname;
    private String desc;
    private String pic;
    private String background;
    private int is_lecturer;
    private int cate_id;
    private List<LecturesWithCoverBean> lectures_with_cover;

    @Override
    public String toString() {
        return "LecturerBean{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", desc='" + desc + '\'' +
                ", pic='" + pic + '\'' +
                ", background='" + background + '\'' +
                ", is_lecturer=" + is_lecturer +
                ", cate_id=" + cate_id +
                ", lectures_with_cover=" + lectures_with_cover +
                '}';
    }

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public int getIs_lecturer() {
        return is_lecturer;
    }

    public void setIs_lecturer(int is_lecturer) {
        this.is_lecturer = is_lecturer;
    }

    public int getCate_id() {
        return cate_id;
    }

    public void setCate_id(int cate_id) {
        this.cate_id = cate_id;
    }

    public List<LecturesWithCoverBean> getLectures_with_cover() {
        return lectures_with_cover;
    }

    public void setLectures_with_cover(List<LecturesWithCoverBean> lectures_with_cover) {
        this.lectures_with_cover = lectures_with_cover;
    }

    public static class LecturesWithCoverBean {

        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
