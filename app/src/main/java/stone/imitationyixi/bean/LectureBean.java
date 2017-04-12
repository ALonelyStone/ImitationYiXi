package stone.imitationyixi.bean;

import java.util.List;

/**
 * @author stone
 *         演讲、记录、枝桠
 */

public class LectureBean {

    private int id;
    private String title;
    private String desc;
    private int lecturer_id;
    private String viewnum;
    private String likenum;
    private String cmtnum;
    private String purecontent;
    private String entitle;
    private String ennickname;
    private String enpurecontent;
    private String cover;
    private String background;
    private String video;
    private int cate_id;
    private String type;
    private int published;
    private String site;
    private String time;
    private String created_at;
    private LecturerBean lecturer;
    private CategoryBean category;
    private int liked;
    private int bookmarked;
    private List<TagsBean> tags;

    @Override
    public String toString() {
        return "LectureBean{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", lecturer_id=" + lecturer_id +
                ", viewnum='" + viewnum + '\'' +
                ", likenum='" + likenum + '\'' +
                ", cmtnum='" + cmtnum + '\'' +
                ", purecontent='" + purecontent + '\'' +
                ", entitle='" + entitle + '\'' +
                ", ennickname='" + ennickname + '\'' +
                ", enpurecontent='" + enpurecontent + '\'' +
                ", cover='" + cover + '\'' +
                ", background='" + background + '\'' +
                ", video='" + video + '\'' +
                ", cate_id=" + cate_id +
                ", type='" + type + '\'' +
                ", published=" + published +
                ", site='" + site + '\'' +
                ", time='" + time + '\'' +
                ", created_at='" + created_at + '\'' +
                ", lecturer=" + lecturer +
                ", category=" + category +
                ", liked=" + liked +
                ", bookmarked=" + bookmarked +
                ", tags=" + tags +
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

    public int getLecturer_id() {
        return lecturer_id;
    }

    public void setLecturer_id(int lecturer_id) {
        this.lecturer_id = lecturer_id;
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

    public String getPurecontent() {
        return purecontent;
    }

    public void setPurecontent(String purecontent) {
        this.purecontent = purecontent;
    }

    public String getEntitle() {
        return entitle;
    }

    public void setEntitle(String entitle) {
        this.entitle = entitle;
    }

    public String getEnnickname() {
        return ennickname;
    }

    public void setEnnickname(String ennickname) {
        this.ennickname = ennickname;
    }

    public String getEnpurecontent() {
        return enpurecontent;
    }

    public void setEnpurecontent(String enpurecontent) {
        this.enpurecontent = enpurecontent;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public int getCate_id() {
        return cate_id;
    }

    public void setCate_id(int cate_id) {
        this.cate_id = cate_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPublished() {
        return published;
    }

    public void setPublished(int published) {
        this.published = published;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
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

    public CategoryBean getCategory() {
        return category;
    }

    public void setCategory(CategoryBean category) {
        this.category = category;
    }

    public int getLiked() {
        return liked;
    }

    public void setLiked(int liked) {
        this.liked = liked;
    }

    public int getBookmarked() {
        return bookmarked;
    }

    public void setBookmarked(int bookmarked) {
        this.bookmarked = bookmarked;
    }

    public List<TagsBean> getTags() {
        return tags;
    }

    public void setTags(List<TagsBean> tags) {
        this.tags = tags;
    }


}
