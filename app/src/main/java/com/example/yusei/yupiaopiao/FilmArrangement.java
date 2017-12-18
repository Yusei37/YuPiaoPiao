package com.example.yusei.yupiaopiao;

/**
 * Created by yusei on 2017/12/17
 */
public class FilmArrangement {
    private int filmArrangementID;
    private String movieName;
    private String theatreName;
    private String movieHallName;
    private java.util.Date beginTime;
    private java.util.Date endTime;
    private double price;
    private String EmployeeID;

    public int getFilmArrangementID() {
        return filmArrangementID;
    }

    public void setFilmArrangementID(int filmArrangementID) {
        this.filmArrangementID = filmArrangementID;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getTheatreName() {
        return theatreName;
    }

    public void setTheatreName(String theatreName) {
        this.theatreName = theatreName;
    }

    public String getMovieHallName() {
        return movieHallName;
    }

    public void setMovieHallName(String movieHallName) {
        this.movieHallName = movieHallName;
    }

    public java.util.Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(java.util.Date beginTime) {
        this.beginTime = beginTime;
    }

    public java.util.Date getEndTime() {
        return endTime;
    }

    public void setEndTime(java.util.Date endTime) {
        this.endTime = endTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(String employeeID) {
        EmployeeID = employeeID;
    }
}
