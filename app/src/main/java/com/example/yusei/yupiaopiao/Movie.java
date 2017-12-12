package com.example.yusei.yupiaopiao;

/**
 * Created by yusei on 2017/12/12
 */
public class Movie {

    private String movieName;
    private String director;
    private String actor;
    private java.util.Date releaseTime;
    private java.util.Date projectionTime;
    private int poster;
    private String brief;
    private int duration;

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public java.util.Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(java.util.Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public java.util.Date getProjectionTime() {
        return projectionTime;
    }

    public void setProjectionTime(java.util.Date projectionTime) {
        this.projectionTime = projectionTime;
    }

    public int getPoster() {
        return poster;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

}
