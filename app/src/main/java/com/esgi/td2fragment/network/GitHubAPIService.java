package com.esgi.td2fragment.network;

import android.util.Log;

import com.esgi.td2fragment.models.Repository;
import com.esgi.td2fragment.models.User;
import retrofit2.Retrofit;
import retrofit2.Call;
import com.github.aurae.retrofit2.LoganSquareConverterFactory;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;
/**
 * Created by maxime on 15/06/16.
 */
public class GitHubAPIService {

    private static final String API_URL = "https://api.github.com";
    private Retrofit retrofit;
    private static final String TAG_STATUS = " status: ";

    // Singleton
    private static final GitHubAPIService INSTANCE = new GitHubAPIService();

    private UserService userService;

    public static GitHubAPIService getInstance() {
        return INSTANCE;
    }

    private GitHubAPIService() {
        this.initRetrofit();
    }

    public void getUser(String username, final ApiResult<User> cb) {
        Log.d(getClass().getSimpleName(), "getUser : " + username);
        Call<User> call = this.userService.getUser(username);
        call.enqueue(new GenericCallback<>(200, "getUser", cb));
    }

    public void getReposUser(String username, final ApiResult<List<Repository>> cb) {
        Log.d(getClass().getSimpleName(), "getReposUser : " + username);
        Call<List<Repository>> call = this.userService.getUsersRepos(username);
        call.enqueue(new GenericCallback<>(200, "getReposUser", cb));
    }

    private void initRetrofit() {
        if (this.retrofit != null) {
            this.retrofit = null;
        }

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(LoganSquareConverterFactory.create());

        this.retrofit = retrofitBuilder.build();

        this.userService = this.retrofit.create(UserService.class);
    }
    public interface ApiResult<T> {
        void success(T res);

        void error(int code, String message);
    }

    static class GenericCallback<T> implements Callback<T> {
        private final int statusCodeOK;
        private final String methodName;
        private final ApiResult<T> callback;

        protected GenericCallback(int statusCodeOk, String methodName, ApiResult<T> callback) {
            this.statusCodeOK = statusCodeOk;
            this.methodName = methodName;
            this.callback = callback;
        }
        @Override
        public void onResponse(Call<T> call, Response<T> response) {
            int statusCode = response.code();
            Log.d(getClass().getSimpleName(), methodName + TAG_STATUS + statusCode);
            if (statusCode == statusCodeOK) {
                callback.success(response.body());
            } else {
                callback.error(statusCode, response.message());
            }
        }

        @Override
        public void onFailure(Call<T> call, Throwable t) {
            Log.e(getClass().getSimpleName(), "Error while calling the '" + methodName + "' method! " + t.getLocalizedMessage(), t);
            callback.error(-1, t.getMessage());
        }
    }
}
