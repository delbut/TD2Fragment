package com.esgi.td2fragment.network;

import com.esgi.td2fragment.models.Repository;
import com.esgi.td2fragment.models.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by maxime on 15/06/16.
 */
public interface UserService {

    @GET("/users/{username}")
    Call<User> getUser(@Path("username") String username);

    @GET("/users/{username}/repos")
    Call<List<Repository>> getUsersRepos(@Path("username") String username);

}

