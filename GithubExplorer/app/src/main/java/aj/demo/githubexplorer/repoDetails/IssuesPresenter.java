package aj.demo.githubexplorer.repoDetails;

import android.support.annotation.VisibleForTesting;

import java.util.ArrayList;
import java.util.List;

import aj.demo.githubexplorer.Parser;
import aj.demo.githubexplorer.models.GitHubIssue;
import aj.demo.githubexplorer.repository.GitHubRepository;
import aj.demo.githubexplorer.repository.RetrofitGitHubRepository;

public class IssuesPresenter implements GitHubRepository<List<GitHubIssue>> {

    private final RepoDetailsView repoDetailsView;

    private RetrofitGitHubRepository retrofitGitHubRepository;

    IssuesPresenter(RepoDetailsView repoDetailsView) {
        this.repoDetailsView = repoDetailsView;
        retrofitGitHubRepository = new RetrofitGitHubRepository(this);
    }

    void fetchIssues(String issuesUrl) {
        retrofitGitHubRepository.fetchIssues(issuesUrl);
    }

    @Override
    public void fetchReposSuccess(List<GitHubIssue> gitHubIssues) {
        List<IssuePresenterModel> issues = new ArrayList<>();
        int iterateTill = gitHubIssues.size() <= 3 ? gitHubIssues.size() : 3;

        for(int i = 0; i < iterateTill; i++) {
            String title = gitHubIssues.get(i).getTitle();
            String createdAt = Parser.extractDate(gitHubIssues.get(i).getCreatedAt());
            issues.add(new IssuePresenterModel(title, createdAt));
        }
        repoDetailsView.showIssues(issues);
    }

    @Override
    public void fetchReposError() {
        repoDetailsView.fetchIssuesFailed();

    }

    @VisibleForTesting void setMockRepository(RetrofitGitHubRepository mockretrofitGitHubRepository) {
        this.retrofitGitHubRepository = mockretrofitGitHubRepository;
    }
}
