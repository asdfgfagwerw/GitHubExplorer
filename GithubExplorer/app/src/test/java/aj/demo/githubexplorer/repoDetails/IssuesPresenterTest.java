package aj.demo.githubexplorer.repoDetails;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import aj.demo.githubexplorer.models.GitHubIssue;
import aj.demo.githubexplorer.repository.RetrofitGitHubRepository;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class IssuesPresenterTest {

    @Mock
    RepoDetailsView mockRepoDetailsView;

    @Mock
    RetrofitGitHubRepository mockRetrofitGitHubRepository;

    private IssuesPresenter issuesPresenter;

    @Before
    public void setUp() {
        issuesPresenter = new IssuesPresenter(mockRepoDetailsView);
        issuesPresenter.setMockRepository(mockRetrofitGitHubRepository);
    }

    @Test
    public void fetchIssues() throws Exception {
        issuesPresenter.fetchIssues("url");
        Mockito.verify(mockRetrofitGitHubRepository).fetchIssues("url");
    }

    @Test
    public void fetchReposSuccess_lessThan3Issues() throws Exception {
        List<GitHubIssue> issues = Arrays.asList(new GitHubIssue("Issue 1", "2017-11-15T07:34:27Z"));
        ArgumentCaptor<List> issueList = ArgumentCaptor.forClass(List.class);
        issuesPresenter.fetchReposSuccess(issues);
        verify(mockRepoDetailsView).showIssues(issueList.capture());
        Assert.assertEquals(1, issueList.getValue().size());
    }

    @Test
    public void fetchReposSuccess_3Issues() throws Exception {
        List<GitHubIssue> issues = Arrays.asList(
                new GitHubIssue("Issue 1", "2017-11-15T07:34:27Z"),
                new GitHubIssue("Issue 2", "2017-11-15T07:34:27Z"),
                new GitHubIssue("Issue 3", "2017-11-15T07:34:27Z"));
        ArgumentCaptor<List> issueList = ArgumentCaptor.forClass(List.class);
        issuesPresenter.fetchReposSuccess(issues);
        verify(mockRepoDetailsView).showIssues(issueList.capture());
        Assert.assertEquals(3, issueList.getValue().size());
    }

    @Test
    public void fetchReposSuccess_moreThan3Issues() throws Exception {
        List<GitHubIssue> issues = Arrays.asList(
                new GitHubIssue("Issue 1", "2017-11-15T07:34:27Z"),
                new GitHubIssue("Issue 2", "2017-11-15T07:34:27Z"),
                new GitHubIssue("Issue 3", "2017-11-15T07:34:27Z"),
                new GitHubIssue("Issue 4", "2017-11-15T07:34:27Z"),
                new GitHubIssue("Issue 5", "2017-11-15T07:34:27Z"),
                new GitHubIssue("Issue 6", "2017-11-15T07:34:27Z"));
        ArgumentCaptor<List> issueList = ArgumentCaptor.forClass(List.class);
        issuesPresenter.fetchReposSuccess(issues);
        verify(mockRepoDetailsView).showIssues(issueList.capture());
        Assert.assertEquals(3, issueList.getValue().size());
    }

    @Test
    public void fetchReposError() throws Exception {
        issuesPresenter.fetchReposError();
        verify(mockRepoDetailsView).fetchIssuesFailed();
    }

}