package stone.imitationyixi.common;

/**
 * @author stone
 */

public class NetworkConstant {

    private static final String BASE_URL = "http://api.yixi.tv/api/v1/";//服务器地址
    //========================================================================================================
    private static final String ALBUM_URL = BASE_URL + "album";//【GET】主页
    //========================================================================================================
    private static final String LECTURE_DETAIL_URL = BASE_URL + "lecture/detail/{id}";//【GET】讲座、记录详细信息,还需要加上讲座、记录id
    private static final String LECTURE_COMMENTS_URL = BASE_URL + "lecture/comments/{id}/page/{page}";//【GET】评论,还需要页数page（1页20条数据）
    private static final String RELATED_LECTURES_URL = BASE_URL + "lecture/{id}/related";//【GET】推荐,还需要讲座、记录id
    private static final String LECTURE_LIST_URL = BASE_URL + "lecture/list/{type}/{page}/desc";//【GET】演讲列表界面,还需要类型type（like喜欢 view观看 date日期），页数page（1页20条数据）
    //========================================================================================================
    private static final String RECORD_LIST_URL = BASE_URL + "record/{page}";//【GET】记录页面，还需要页数（1页20条数据）
    //========================================================================================================
    private static final String CATEGORY_LIST_URL = BASE_URL + "category/list";//【GET】讲者类型
    private static final String LECTURERS_BY_CATEGORY_URL = BASE_URL + "category/{id}/lecturers";//【GET】讲者界面,还需要讲者类型id
    //========================================================================================================
    private static final String ACTIVITY_URL = BASE_URL + "activity";//【GET】活动页面
    //========================================================================================================
    private static final String LOGIN_URL = BASE_URL + "user/login";//【POST】登录（参数 email账号，password密码）
    private static final String SETTING_URL = BASE_URL + "user/setting";//【POST】修改资料（POST参数 nickname昵称，desc简介）
    private static final String REGIST_URL = BASE_URL + "user/register";//【POST】注册（POST参数 nickname昵称，desc简介）
    private static final String LOGOUT_URL = BASE_URL + "user/logout";//【GET】登出
    private static final String USER_INFO_URL = BASE_URL + "user/info/{id}";//【GET】用户信息,还需要用户id
    private static final String REPLY_LIST_URL = BASE_URL + "user/replylist/{page}";//【GET】用户回复,还需要页数page（1页20条数据）
    private static final String BOOKMARK_LIST_URL = BASE_URL + "user/{id}/bookmarks/{page}";//【GET】用户收藏页,还需要用户id,页数page（1页20条数据）
    //========================================================================================================
    private static final String ZHIYA_URL = BASE_URL + "zhiya/list/{page}";//【GET】枝桠页面
    //========================================================================================================
    private static final String SEARCH_URL = BASE_URL + "search/{content}";//【GET】搜索

    public static String getRegistUrl() {
        return REGIST_URL;
    }

    public static String getAlbumUrl() {
        return ALBUM_URL;
    }

    public static String getLectureDetailUrl(String id) {
        return LECTURE_DETAIL_URL.replace("{id}", id);
    }

    public static String getLectureCommentsUrl(String id, String page) {
        return LECTURE_COMMENTS_URL.replace("{id}", id).replace("{page}", page);
    }

    public static String getRelatedLecturesUrl(String id) {
        return RELATED_LECTURES_URL.replace("{id}", id);
    }

    public static String getLectureListUrl(String type, String id) {
        return LECTURE_LIST_URL.replace("{type}", type).replace("{id}", id);
    }

    public static String getRecordListUrl(String page) {
        return RECORD_LIST_URL.replace("{page}", page);
    }

    public static String getCategoryListUrl() {
        return CATEGORY_LIST_URL;
    }

    public static String getLecturersByCategoryUrl(String id) {
        return LECTURERS_BY_CATEGORY_URL.replace("{id}", id);
    }

    public static String getActivityUrl() {
        return ACTIVITY_URL;
    }

    public static String getLoginUrl() {
        return LOGIN_URL;
    }

    public static String getSettingUrl() {
        return SETTING_URL;
    }

    public static String getLogoutUrl() {
        return LOGOUT_URL;
    }

    public static String getUserInfoUrl(String id) {
        return USER_INFO_URL.replace("{id}", id);
    }

    public static String getReplyListUrl(String page) {
        return REPLY_LIST_URL.replace("{page}", page);
    }

    public static String getBookmarkListUrl(String id, String page) {
        return BOOKMARK_LIST_URL.replace("{id}", id).replace("{page}", page);
    }

    public static String getZhiyaUrl(String page) {
        return ZHIYA_URL.replace("{page}", page);
    }

    public static String getSearchUrl(String content) {
        return SEARCH_URL.replace("{content}", content);
    }
}
