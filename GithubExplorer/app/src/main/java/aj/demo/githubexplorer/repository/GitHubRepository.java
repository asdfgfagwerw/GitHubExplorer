package aj.demo.githubexplorer.repository;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by AJ on 13-Nov-17.
 *
 * This repository acts as an interface between the front end and the back end.
 *
 */

public interface GitHubRepository<T> {

    void fetchReposSuccess(T t);

    void fetchReposError();

    static GitHubRetrofitService getRetrofitService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(GitHubRetrofitService.class);
    }
}
