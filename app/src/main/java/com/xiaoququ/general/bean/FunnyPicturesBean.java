package com.xiaoququ.general.bean;

import java.util.List;

/**
 * 趣图类
 *
 * Created by lizhihhui on 2016/11/4 14:59.
 */

public class FunnyPicturesBean {

    /**
     * error_code : 0
     * reason : Success
     * result : [{"id":"1","title":"你们是来搞笑的吗","url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201609/18/190B25E454C75D24510C603FB08ADD95.gif","hashId":"190B25E454C75D24510C603FB08ADD95","lastUpdateTime":"2016-10-26 11:42:46","fp_pvNums":"0","fp_praiseNums":"0","fp_treadNums":"0","fp_commentsNums":"0","fp_shareNums":"0"},{"id":"2","title":"告诉我，这理发店在哪里","url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201609/18/FAE0B8191EF4A7A47CD9A6E5B3EC4229.jpg","hashId":"FAE0B8191EF4A7A47CD9A6E5B3EC4229","lastUpdateTime":"2016-10-26 11:42:46","fp_pvNums":"200","fp_praiseNums":"145","fp_treadNums":"36","fp_commentsNums":"88","fp_shareNums":"126"},{"id":"3","title":"看你摔倒我就放心了","url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201609/18/C408D891917A0BB8AFFBD91B49F5574F.gif","hashId":"C408D891917A0BB8AFFBD91B49F5574F","lastUpdateTime":"2016-10-26 11:42:46","fp_pvNums":"0","fp_praiseNums":"0","fp_treadNums":"0","fp_commentsNums":"0","fp_shareNums":"0"},{"id":"4","title":"我特么的差点就信了","url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201609/18/960C24C75C79B9A05A0AF03DA73F38C4.gif","hashId":"960C24C75C79B9A05A0AF03DA73F38C4","lastUpdateTime":"2016-10-26 11:42:46","fp_pvNums":"0","fp_praiseNums":"0","fp_treadNums":"0","fp_commentsNums":"0","fp_shareNums":"0"},{"id":"5","title":"这样的女鬼，来十个我灭十个！","url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201609/18/89A91F0C2DFD8A8F7F545D15405BF65B.gif","hashId":"89A91F0C2DFD8A8F7F545D15405BF65B","lastUpdateTime":"2016-10-26 11:42:46","fp_pvNums":"0","fp_praiseNums":"0","fp_treadNums":"0","fp_commentsNums":"0","fp_shareNums":"0"},{"id":"6","title":"中国重量级男神 [性感时尚运动装] T台秀\u2026\u2026","url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201609/18/0D1DA63F5943FE6E2070E43F88E15E30.png","hashId":"0D1DA63F5943FE6E2070E43F88E15E30","lastUpdateTime":"2016-10-26 11:42:46","fp_pvNums":"0","fp_praiseNums":"0","fp_treadNums":"0","fp_commentsNums":"0","fp_shareNums":"0"},{"id":"7","title":"据说每个男孩子都被这样的聊天逼疯过！","url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201609/18/8BC3DA24C28BADD035D0222EDF9938F1.png","hashId":"8BC3DA24C28BADD035D0222EDF9938F1","lastUpdateTime":"2016-10-26 11:42:46","fp_pvNums":"0","fp_praiseNums":"0","fp_treadNums":"0","fp_commentsNums":"0","fp_shareNums":"0"},{"id":"8","title":"playstation VR.PRO曝光","url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201609/18/BAFE8B6BBF2CCA565BD9BD5BEA0EC1C8.jpg","hashId":"BAFE8B6BBF2CCA565BD9BD5BEA0EC1C8","lastUpdateTime":"2016-10-26 11:42:46","fp_pvNums":"0","fp_praiseNums":"0","fp_treadNums":"0","fp_commentsNums":"0","fp_shareNums":"0"},{"id":"9","title":"槽点太多，不知道该先吐哪个\u2026\u2026","url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201609/18/92E77DCB028F1318516BF1D82EA947FA.jpg","hashId":"92E77DCB028F1318516BF1D82EA947FA","lastUpdateTime":"2016-10-26 11:42:46","fp_pvNums":"0","fp_praiseNums":"0","fp_treadNums":"0","fp_commentsNums":"0","fp_shareNums":"0"},{"id":"10","title":"北京：拟建旅游不文明行为记录制度","url":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201609/18/246372ED2BD96FA9326DA8A067917527.png","hashId":"246372ED2BD96FA9326DA8A067917527","lastUpdateTime":"2016-10-26 11:42:46","fp_pvNums":"0","fp_praiseNums":"0","fp_treadNums":"0","fp_commentsNums":"0","fp_shareNums":"0"}]
     */

    private int error_code;
    private String reason;
    private List<ResultBean> result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 1
         * title : 你们是来搞笑的吗
         * url : http://juheimg.oss-cn-hangzhou.aliyuncs.com/joke/201609/18/190B25E454C75D24510C603FB08ADD95.gif
         * hashId : 190B25E454C75D24510C603FB08ADD95
         * lastUpdateTime : 2016-10-26 11:42:46
         * fp_pvNums : 0
         * fp_praiseNums : 0
         * fp_treadNums : 0
         * fp_commentsNums : 0
         * fp_shareNums : 0
         */

        private String id;
        private String title;
        private String url;
        private String hashId;
        private String lastUpdateTime;
        private String fp_pvNums;
        private String fp_praiseNums;
        private String fp_treadNums;
        private String fp_commentsNums;
        private String fp_shareNums;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getHashId() {
            return hashId;
        }

        public void setHashId(String hashId) {
            this.hashId = hashId;
        }

        public String getLastUpdateTime() {
            return lastUpdateTime;
        }

        public void setLastUpdateTime(String lastUpdateTime) {
            this.lastUpdateTime = lastUpdateTime;
        }

        public String getFp_pvNums() {
            return fp_pvNums;
        }

        public void setFp_pvNums(String fp_pvNums) {
            this.fp_pvNums = fp_pvNums;
        }

        public String getFp_praiseNums() {
            return fp_praiseNums;
        }

        public void setFp_praiseNums(String fp_praiseNums) {
            this.fp_praiseNums = fp_praiseNums;
        }

        public String getFp_treadNums() {
            return fp_treadNums;
        }

        public void setFp_treadNums(String fp_treadNums) {
            this.fp_treadNums = fp_treadNums;
        }

        public String getFp_commentsNums() {
            return fp_commentsNums;
        }

        public void setFp_commentsNums(String fp_commentsNums) {
            this.fp_commentsNums = fp_commentsNums;
        }

        public String getFp_shareNums() {
            return fp_shareNums;
        }

        public void setFp_shareNums(String fp_shareNums) {
            this.fp_shareNums = fp_shareNums;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "id='" + id + '\'' +
                    ", title='" + title + '\'' +
                    ", url='" + url + '\'' +
                    ", hashId='" + hashId + '\'' +
                    ", lastUpdateTime='" + lastUpdateTime + '\'' +
                    ", fp_pvNums='" + fp_pvNums + '\'' +
                    ", fp_praiseNums='" + fp_praiseNums + '\'' +
                    ", fp_treadNums='" + fp_treadNums + '\'' +
                    ", fp_commentsNums='" + fp_commentsNums + '\'' +
                    ", fp_shareNums='" + fp_shareNums + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "FunnyPicturesBean{" +
                "error_code=" + error_code +
                ", reason='" + reason + '\'' +
                ", result=" + result +
                '}';
    }
}
