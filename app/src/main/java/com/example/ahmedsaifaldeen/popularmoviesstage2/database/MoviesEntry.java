package com.example.ahmedsaifaldeen.popularmoviesstage2.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "favourite_movies")
public class MoviesEntry implements Parcelable{


    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "poster_path")
    private String posterPath;

    private String title;

    public double getVoteaverage() {
        return voteaverage;
    }

    public void setVoteaverage(double voteaverage) {
        this.voteaverage = voteaverage;
    }

    @ColumnInfo(name = "vote_average")
    private double voteaverage;

    private String overview;

    public String getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(String releasedate) {
        this.releasedate = releasedate;
    }

    @ColumnInfo(name = "release_date")
    private String releasedate;


    private int favID;

    @Ignore
    public MoviesEntry(String posterPath, String title, double voteaverage, String overview, String releasedate, int favID) {
        this.posterPath = posterPath;
        this.title = title;
        this.voteaverage = voteaverage;
        this.overview = overview;
        this.releasedate = releasedate;
        this.favID = favID;
    }

    public MoviesEntry(int id, String posterPath, String title, double voteaverage, String overview, String releasedate, int favID) {

        this.id = id;
        this.posterPath = posterPath;
        this.title = title;
        this.voteaverage = voteaverage;
        this.overview = overview;
        this.releasedate = releasedate;
        this.favID = favID;
    }

    protected MoviesEntry(Parcel in) {
        id = in.readInt();
        posterPath = in.readString();
        title = in.readString();
        voteaverage = in.readDouble();
        overview = in.readString();
        releasedate = in.readString();
        favID = in.readInt();
    }

    public static final Creator<MoviesEntry> CREATOR = new Creator<MoviesEntry>() {
        @Override
        public MoviesEntry createFromParcel(Parcel in) {
            return new MoviesEntry(in);
        }

        @Override
        public MoviesEntry[] newArray(int size) {
            return new MoviesEntry[size];
        }
    };

   /* public double getVoteAverage() {

        return voteaverage;
    }

    public void setVoteaverage(double voteaverage) {
        this.voteaverage = voteaverage;
    }*/

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

  /*  public String getReleaseDate() {
        return releasedate;
    }

    public void setReleasedate(String releasedate) {
        this.releasedate = releasedate;
    }*/

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFavID() {
        return favID;
    }

    public void setFavID(int favID) {
        this.favID = favID;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(posterPath);
        dest.writeString(title);
        dest.writeDouble(voteaverage);
        dest.writeString(overview);
        dest.writeString(releasedate);
        dest.writeInt(favID);
    }
}
