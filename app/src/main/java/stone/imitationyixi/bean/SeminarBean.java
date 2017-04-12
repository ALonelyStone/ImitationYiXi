package stone.imitationyixi.bean;

import java.util.List;

/**
 * @author stone
 *         专题演讲
 */

public class SeminarBean {

    private int id;
    private String title;
    private String desc;
    private String webcontent;
    private String background;
    private String purecontent;
    private String newpc;
    private int published;
    private int sort;
    private String created_at;
    private List<LecturesBean> lectures;

    @Override
    public String toString() {
        return "SeminarBean{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", webcontent='" + webcontent + '\'' +
                ", background='" + background + '\'' +
                ", purecontent='" + purecontent + '\'' +
                ", newpc='" + newpc + '\'' +
                ", published=" + published +
                ", sort=" + sort +
                ", created_at='" + created_at + '\'' +
                ", lectures=" + lectures +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getWebcontent() {
        return webcontent;
    }

    public void setWebcontent(String webcontent) {
        this.webcontent = webcontent;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getPurecontent() {
        return purecontent;
    }

    public void setPurecontent(String purecontent) {
        this.purecontent = purecontent;
    }

    public String getNewpc() {
        return newpc;
    }

    public void setNewpc(String newpc) {
        this.newpc = newpc;
    }

    public int getPublished() {
        return published;
    }

    public void setPublished(int published) {
        this.published = published;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public List<LecturesBean> getLectures() {
        return lectures;
    }

    public void setLectures(List<LecturesBean> lectures) {
        this.lectures = lectures;
    }


    public static class  LecturesBean {

        private int id;
        private String title;
        private String viewnum;
        private String likenum;
        private String cmtnum;
        private String cover;
        private String time;
        private LecturerBean lecturer;

        @Override
        public String toString() {
            return "LecturesBean{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", viewnum='" + viewnum + '\'' +
                    ", likenum='" + likenum + '\'' +
                    ", cmtnum='" + cmtnum + '\'' +
                    ", cover='" + cover + '\'' +
                    ", time='" + time + '\'' +
                    ", lecturer=" + lecturer +
                    '}';
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getViewnum() {
            return viewnum;
        }

        public void setViewnum(String viewnum) {
            this.viewnum = viewnum;
        }

        public String getLikenum() {
            return likenum;
        }

        public void setLikenum(String likenum) {
            this.likenum = likenum;
        }

        public String getCmtnum() {
            return cmtnum;
        }

        public void setCmtnum(String cmtnum) {
            this.cmtnum = cmtnum;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public LecturerBean getLecturer() {
            return lecturer;
        }

        public void setLecturer(LecturerBean lecturer) {
            this.lecturer = lecturer;
        }
    }
}
