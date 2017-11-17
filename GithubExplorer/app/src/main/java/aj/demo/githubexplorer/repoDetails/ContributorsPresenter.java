package aj.demo.githubexplorer.repoDetails;

import android.support.annotation.VisibleForTesting;

import java.util.ArrayList;
import java.util.List;

import aj.demo.githubexplorer.models.GitHubContributor;
import aj.demo.githubexplorer.repository.GitHubRepository;
import aj.demo.githubexplorer.repository.RetrofitGitHubRepository;

public class ContributorsPresenter implements GitHubRepository<List<GitHubContributor>> {

    private final RepoDetailsView repoDetailsView;

    private RetrofitGitHubRepository retrofitGitHubRepository;

    ContributorsPresenter(RepoDetailsView repoDetailsView) {
        this.repoDetailsView = repoDetailsView;
        retrofitGitHubRepository = new RetrofitGitHubRepository(this);
    }

    void fetchContributors(String contributorsUrl) {
        retrofitGitHubRepository.fetchContributors(contributorsUrl);
    }

    @Override
    public void fetchReposSuccess(List<GitHubContributor> gitHubContributors) {
        List<String> names = new ArrayList<>();
        /**
         *  This check is in place for some repos where the number of contributors may be less than 3
         */
        int iterateTill = gitHubContributors.size() <= 3 ? gitHubContributors.size() : 3;

        for(int i = 0; i < iterateTill; i++) {
            names.add(gitHubContributors.get(i).getName());
        }
        repoDetailsView.showContributors(names);
    }

    @Override
    public void fetchReposError() {
        repoDetailsView.fetchContributorsFailed();
    }

    @VisibleForTesting void setMockRepository(RetrofitGitHubRepository mockretrofitGitHubRepository) {
        this.retrofitGitHubRepository = mockretrofitGitHubRepository;
    }
}
