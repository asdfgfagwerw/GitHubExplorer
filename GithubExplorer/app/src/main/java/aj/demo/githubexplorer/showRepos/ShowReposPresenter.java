package aj.demo.githubexplorer.showRepos;

import android.support.annotation.VisibleForTesting;

import aj.demo.githubexplorer.repository.GitHubRepository;
import aj.demo.githubexplorer.repository.RetrofitGitHubRepository;

/**
 * Created by AJ on 15-Nov-17.
 */

public class ShowReposPresenter implements GitHubRepository<GitHubAdapterModel> {

    private final ShowReposView showReposView;

    private RetrofitGitHubRepository retrofitGitHubRepository;

    public ShowReposPresenter(ShowReposView showReposView) {
        this.showReposView = showReposView;
        retrofitGitHubRepository = new RetrofitGitHubRepository(this);
    }

    public void fetchRepos(String languageToBeSearched) {
        retrofitGitHubRepository.fetchRepos(languageToBeSearched);
    }

    @Override
    public void fetchReposSuccess(GitHubAdapterModel gitHubAdapterModel) {
        if(gitHubAdapterModel != null && gitHubAdapterModel.getGitHubReposResponse() != null) {
            showReposView.showRepos(gitHubAdapterModel);
        } else {
            showReposView.showError();
        }
    }

    @Override
    public void fetchReposError() {
        showReposView.showError();
    }

    @VisibleForTesting void setMockRetrofitGitHubRepository(RetrofitGitHubRepository mockRetrofitGitHubRepository) {
        this.retrofitGitHubRepository = mockRetrofitGitHubRepository;
    }

    public void fetchSubsequentPage(String nextPageLink) {
        retrofitGitHubRepository.fetchSubsequentRepos(nextPageLink);
    }
}
