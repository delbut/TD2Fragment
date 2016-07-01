package com.esgi.td2fragment.models;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.util.Objects;

/**
 * Created by maxime on 15/06/16.
 */
//@SuppressLint("ParcelCreator")
@JsonObject
public class User implements Parcelable{

//    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
//        public User createFromParcel(Parcel in) {
//            return new User(in);
//        }
//
//        public User[] newArray(int size) {
//            return new User[size];
//        }
//    };


    public User() {
    }

    @JsonField
    private String login;

    @JsonField
    private String avatar_url;

    @JsonField
    private String followers_url;

    @JsonField
    private String repos_url;

    @JsonField
    private String events_url;

    @JsonField
    private int public_repos;

    @JsonField
    private int followers;

    @JsonField
    private int following;

    protected User(Parcel in) {
        login = in.readString();
        avatar_url = in.readString();
        followers_url = in.readString();
        repos_url = in.readString();
        events_url = in.readString();
        public_repos = in.readInt();
        followers = in.readInt();
        following = in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getFollowers_url() {
        return followers_url;
    }

    public void setFollowers_url(String followers_url) {
        this.followers_url = followers_url;
    }

    public String getRepos_url() {
        return repos_url;
    }

    public void setRepos_url(String repos_url) {
        this.repos_url = repos_url;
    }

    public String getEvents_url() {
        return events_url;
    }

    public void setEvents_url(String events_url) {
        this.events_url = events_url;
    }

    public int getPublic_repos() {
        return public_repos;
    }

    public void setPublic_repos(int public_repos) {
        this.public_repos = public_repos;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //noop
        dest.writeString(login);
        dest.writeString(avatar_url);
        dest.writeString(followers_url);
        dest.writeString(repos_url);
        dest.writeString(events_url);
        dest.writeInt(public_repos);
        dest.writeInt(followers);
        dest.writeInt(following);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return public_repos == user.public_repos &&
                followers == user.followers &&
                following == user.following &&
                Objects.equals(login, user.login) &&
                Objects.equals(avatar_url, user.avatar_url) &&
                Objects.equals(followers_url, user.followers_url) &&
                Objects.equals(repos_url, user.repos_url) &&
                Objects.equals(events_url, user.events_url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, avatar_url, followers_url, repos_url, events_url, public_repos, followers, following);
    }
}
