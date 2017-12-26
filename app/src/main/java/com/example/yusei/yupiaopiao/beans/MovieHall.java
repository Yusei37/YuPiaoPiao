package com.example.yusei.yupiaopiao.beans;

/**
 * Created by yusei on 2017/12/15
 */
public class MovieHall {
    private String movieHallName;
    private int seatNumber;
    private String IMAX;
    private String belongTheatre;
    private double discount;
    private int row;
    private int col;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public String getMovieHallName() {
        return movieHallName;
    }

    public void setMovieHallName(String movieHallName) {
        this.movieHallName = movieHallName;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getIMAX() {
        return IMAX;
    }

    public void setIMAX(String iMAX) {
        IMAX = iMAX;
    }

    public String getBelongTheatre() {
        return belongTheatre;
    }

    public void setBelongTheatre(String belongTheatre) {
        this.belongTheatre = belongTheatre;
    }

    public double getDiscount() {
        return discount;
    }
    
    public void setDiscount(double discount) {
        this.discount = discount;
    }

}