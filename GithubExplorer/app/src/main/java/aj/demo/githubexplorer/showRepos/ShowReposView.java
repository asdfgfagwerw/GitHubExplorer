package aj.demo.githubexplorer.showRepos;

/**
 * Created by AJ on 15-Nov-17.
 */

public interface ShowReposView {
    void showRepos(GitHubAdapterModel gitHubAdapterModel);

    void showError();
}
