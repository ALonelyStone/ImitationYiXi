package stone.imitationyixi.bean;

import java.util.List;

/**
 * @author stone
 *         搜索
 */

public class SearchBean {

    private List<LecsBean> lecs;
    private List<LecturerBean> lecturers;
    private List<LecturerBean> records;

    @Override
    public String toString() {
        return "SearchBean{" +
                "lecs=" + lecs +
                ", lecturers=" + lecturers +
                ", records=" + records +
                '}';
    }

    public List<LecturerBean> getRecords() {
        return records;
    }

    public void setRecords(List<LecturerBean> records) {
        this.records = records;
    }

    public List<LecturerBean> getLecturers() {
        return lecturers;
    }

    public void setLecturers(List<LecturerBean> lecturers) {
        this.lecturers = lecturers;
    }

    public List<LecsBean> getLecs() {
        return lecs;
    }

    public void setLecs(List<LecsBean> lecs) {
        this.lecs = lecs;
    }

    public class LecsBean{

        private int id;
        private String title;
        private String cover;
        private String type;
        private String time;
        private String created_at;
        private LecturerBean lecturer;

        @Override
        public String toString() {
            return "LecsBean{" +
                    "id=" + id +
                    ", title='" + title + '\'' +
                    ", cover='" + cover + '\'' +
                    ", type='" + type + '\'' +
                    ", time='" + time + '\'' +
                    ", created_at='" + created_at + '\'' +
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

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public LecturerBean getLecturer() {
            return lecturer;
        }

        public void setLecturer(LecturerBean lecturer) {
            this.lecturer = lecturer;
        }

    }

}
