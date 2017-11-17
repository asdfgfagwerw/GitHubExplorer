package aj.demo.githubexplorer.showRepos;

import aj.demo.githubexplorer.models.GitHubReposResponse;

/**
 * Created by AJ on 15-Nov-17.
 */

public class GitHubAdapterModel {

    private final GitHubReposResponse gitHubReposResponse;
    private final String nextPageLink;

    public GitHubAdapterModel(GitHubReposResponse gitHubReposResponse, String nextPageLink) {
        this.gitHubReposResponse = gitHubReposResponse;
        this.nextPageLink = nextPageLink;
    }

    GitHubReposResponse getGitHubReposResponse() {
        return gitHubReposResponse;
    }

    String getNextPageLink() {
        return nextPageLink;
    }
}
