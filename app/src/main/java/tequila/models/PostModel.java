package tequila.models;

import com.google.gson.annotations.Expose;

import java.util.Date;

/**
 * Created by williamc1986 on 7/15/15.
 */
public class PostModel {
    @Expose
    public Integer id;
    @Expose
    public String title;
    @Expose
    public String content;
    @Expose
    public String summary;
    @Expose
    public String type;
    @Expose
    public Integer traffic;
    @Expose
    public Integer questionId;
    @Expose
    public String titleImg;
    @Expose
    public Date createdAt;
    @Expose
    public Date lastUpdate;
    @Expose
    public Boolean published;
    @Expose
    public Boolean featured;
    @Expose
    public Boolean deleted;
    @Expose
    public Integer userId;
    @Expose
    public Integer expertId;
    @Expose
    public String tags;
    @Expose
    public Integer migrationId;
    @Expose
    public Integer answerCount;
}
