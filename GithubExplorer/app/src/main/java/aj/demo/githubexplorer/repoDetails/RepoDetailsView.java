package aj.demo.githubexplorer.repoDetails;

import java.util.List;

public interface RepoDetailsView {

    void showContributors(List<String> names);

    void showIssues(List<IssuePresenterModel> issues);

    void fetchContributorsFailed();

    void fetchIssuesFailed();
}
