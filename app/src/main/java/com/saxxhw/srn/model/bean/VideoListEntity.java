package com.saxxhw.srn.model.bean;

import java.util.List;

/**
 * Created by Saxxhw on 2017/5/15.
 * 邮箱：Saxxhw@126.com
 * 功能：
 */

public class VideoListEntity {

    private int total;
    private List<VideosBean> videos;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<VideosBean> getVideos() {
        return videos;
    }

    public void setVideos(List<VideosBean> videos) {
        this.videos = videos;
    }

    public static class VideosBean {
        private String id;
        private String title;
        private String link;
        private String thumbnail;
        private String thumbnail_v2;
        private String bigThumbnail;
        private String bigThumbnail_v2;
        private String duration;
        private String category;
        private String tags;
        private String state;
        private int view_count;
        private int comment_count;
        private int favorite_count;
        private int up_count;
        private int down_count;
        private String published;
        private UserBean user;
        private String public_type;
        private String paid;
        private int is_panorama;
        private List<String> streamtypes;

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

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getThumbnail_v2() {
            return thumbnail_v2;
        }

        public void setThumbnail_v2(String thumbnail_v2) {
            this.thumbnail_v2 = thumbnail_v2;
        }

        public String getBigThumbnail() {
            return bigThumbnail;
        }

        public void setBigThumbnail(String bigThumbnail) {
            this.bigThumbnail = bigThumbnail;
        }

        public String getBigThumbnail_v2() {
            return bigThumbnail_v2;
        }

        public void setBigThumbnail_v2(String bigThumbnail_v2) {
            this.bigThumbnail_v2 = bigThumbnail_v2;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public int getView_count() {
            return view_count;
        }

        public void setView_count(int view_count) {
            this.view_count = view_count;
        }

        public int getComment_count() {
            return comment_count;
        }

        public void setComment_count(int comment_count) {
            this.comment_count = comment_count;
        }

        public int getFavorite_count() {
            return favorite_count;
        }

        public void setFavorite_count(int favorite_count) {
            this.favorite_count = favorite_count;
        }

        public int getUp_count() {
            return up_count;
        }

        public void setUp_count(int up_count) {
            this.up_count = up_count;
        }

        public int getDown_count() {
            return down_count;
        }

        public void setDown_count(int down_count) {
            this.down_count = down_count;
        }

        public String getPublished() {
            return published;
        }

        public void setPublished(String published) {
            this.published = published;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public String getPublic_type() {
            return public_type;
        }

        public void setPublic_type(String public_type) {
            this.public_type = public_type;
        }

        public String getPaid() {
            return paid;
        }

        public void setPaid(String paid) {
            this.paid = paid;
        }

        public int getIs_panorama() {
            return is_panorama;
        }

        public void setIs_panorama(int is_panorama) {
            this.is_panorama = is_panorama;
        }

        public List<String> getStreamtypes() {
            return streamtypes;
        }

        public void setStreamtypes(List<String> streamtypes) {
            this.streamtypes = streamtypes;
        }

        public static class UserBean {
            /**
             * id : 909208394
             * name : 优酷用户1462869873084181
             * link : http://i.youku.com/i/UMzYzNjgzMzU3Ng==
             */

            private int id;
            private String name;
            private String link;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }
        }
    }
}
