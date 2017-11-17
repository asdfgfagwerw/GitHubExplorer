package aj.demo.githubexplorer.repository;

import java.util.List;

import aj.demo.githubexplorer.models.GitHubContributor;
import aj.demo.githubexplorer.models.GitHubIssue;
import aj.demo.githubexplorer.models.GitHubReposResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by AJ on 13-Nov-17.
 */

public interface GitHubRetrofitService {

    @GET("search/repositories")
    Call<GitHubReposResponse> getRepos(@Query("q") String language);

    @GET
    Call<GitHubReposResponse> getSubsequentRepos(@Url String url);

    @GET
    Call<List<GitHubContributor>> getContributors(@Url String url);

    @GET
    Call<List<GitHubIssue>> getIssues(@Url String url);

}
