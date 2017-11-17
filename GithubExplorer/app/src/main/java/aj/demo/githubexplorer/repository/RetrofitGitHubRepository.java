package aj.demo.githubexplorer.repository;

import java.util.List;

import aj.demo.githubexplorer.Parser;
import aj.demo.githubexplorer.models.GitHubContributor;
import aj.demo.githubexplorer.models.GitHubIssue;
import aj.demo.githubexplorer.models.GitHubReposResponse;
import aj.demo.githubexplorer.showRepos.GitHubAdapterModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by AJ on 15-Nov-17.
 */

public class RetrofitGitHubRepository {

    private final GitHubRepository gitHubRepository;

    public RetrofitGitHubRepository(GitHubRepository gitHubRepository) {
        this.gitHubRepository = gitHubRepository;
    }

    public void fetchRepos(String languageToBeSearched) {
        GitHubRetrofitService githubRetrofitService = GitHubRepository.getRetrofitService();
        Call<GitHubReposResponse> repos = githubRetrofitService.getRepos("language:" + languageToBeSearched);
        repos.enqueue(new Callback<GitHubReposResponse>() {
            @Override
            public void onResponse(Call<GitHubReposResponse> call, Response<GitHubReposResponse> response) {
                String link = Parser.parseSubsequentPageUrl(response.headers().get("Link"));
                GitHubReposResponse resp = response.body();
                GitHubAdapterModel gitHubAdapterModel = new GitHubAdapterModel(resp, link);
                gitHubRepository.fetchReposSuccess(gitHubAdapterModel);
            }

            @Override
            public void onFailure(Call<GitHubReposResponse> call, Throwable t) {
                gitHubRepository.fetchReposError();
            }
        });
    }

    public void fetchSubsequentRepos(String nextPageUrl) {
        GitHubRetrofitService githubRetrofitService = GitHubRepository.getRetrofitService();
        Call<GitHubReposResponse> repos = githubRetrofitService.getSubsequentRepos(nextPageUrl);
        repos.enqueue(new Callback<GitHubReposResponse>() {
            @Override
            public void onResponse(Call<GitHubReposResponse> call, Response<GitHubReposResponse> response) {
                String link = Parser.parseSubsequentPageUrl(response.headers().get("Link"));
                GitHubReposResponse resp = response.body();
                GitHubAdapterModel gitHubAdapterModel = new GitHubAdapterModel(resp, link);
                gitHubRepository.fetchReposSuccess(gitHubAdapterModel);
            }

            @Override
            public void onFailure(Call<GitHubReposResponse> call, Throwable t) {
                gitHubRepository.fetchReposError();
            }
        });
    }

    public void fetchContributors(String url) {
        GitHubRetrofitService gitHubRetrofitService = GitHubRepository.getRetrofitService();
        Call<List<GitHubContributor>> contributors = gitHubRetrofitService.getContributors(url);
        contributors.enqueue(new Callback<List<GitHubContributor>>() {
            @Override
            public void onResponse(Call<List<GitHubContributor>> call, Response<List<GitHubContributor>> response) {
                List<GitHubContributor> contributorsResponse = response.body();
                gitHubRepository.fetchReposSuccess(contributorsResponse);
            }

            @Override
            public void onFailure(Call<List<GitHubContributor>> call, Throwable t) {
                gitHubRepository.fetchReposError();
            }
        });
    }

    public void fetchIssues(String url) {
        GitHubRetrofitService gitHubRetrofitService = GitHubRepository.getRetrofitService();
        Call<List<GitHubIssue>> issues = gitHubRetrofitService.getIssues(url);
        issues.enqueue(new Callback<List<GitHubIssue>>() {
            @Override
            public void onResponse(Call<List<GitHubIssue>> call, Response<List<GitHubIssue>> response) {
                List<GitHubIssue> gitHubIssues = response.body();
                gitHubRepository.fetchReposSuccess(gitHubIssues);
            }

            @Override
            public void onFailure(Call<List<GitHubIssue>> call, Throwable t) {
                gitHubRepository.fetchReposError();
            }
        });
    }
}
