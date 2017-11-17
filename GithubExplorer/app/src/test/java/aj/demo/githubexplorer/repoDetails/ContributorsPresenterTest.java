package aj.demo.githubexplorer.repoDetails;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import aj.demo.githubexplorer.models.GitHubContributor;
import aj.demo.githubexplorer.repository.RetrofitGitHubRepository;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ContributorsPresenterTest {

    @Mock
    RepoDetailsView mockRepoDetailsView;

    @Mock
    RetrofitGitHubRepository mockRetrofitGitHubRepository;

    private ContributorsPresenter contributorsPresenter;

    @Before
    public void setUp() {
        contributorsPresenter = new ContributorsPresenter(mockRepoDetailsView);
        contributorsPresenter.setMockRepository(mockRetrofitGitHubRepository);
    }

    @Test
    public void fetchContributors() throws Exception {
        contributorsPresenter.fetchContributors("url");
        verify(mockRetrofitGitHubRepository).fetchContributors("url");
    }

    @Test
    public void fetchReposSuccess_lessThan3Contributors() throws Exception {
        List<GitHubContributor> contributors = Arrays.asList(new GitHubContributor("A"), new GitHubContributor("B"));
        contributorsPresenter.fetchReposSuccess(contributors);
        verify(mockRepoDetailsView).showContributors(Arrays.asList("A", "B"));
    }

    @Test
    public void fetchReposSuccess_3Contributors() throws Exception {
        List<GitHubContributor> contributors = Arrays.asList(new GitHubContributor("A"), new GitHubContributor("B"), new GitHubContributor("C"));
        contributorsPresenter.fetchReposSuccess(contributors);
        verify(mockRepoDetailsView).showContributors(Arrays.asList("A", "B", "C"));
    }

    @Test
    public void fetchReposSuccess_moreThan3Contributors() throws Exception {
        List<GitHubContributor> contributors = Arrays.asList(new GitHubContributor("A"), new GitHubContributor("B"), new GitHubContributor("C"),
                new GitHubContributor("D"), new GitHubContributor("E"), new GitHubContributor("F"), new GitHubContributor("G"));
        contributorsPresenter.fetchReposSuccess(contributors);
        verify(mockRepoDetailsView).showContributors(Arrays.asList("A", "B", "C"));
    }

    @Test
    public void fetchReposError() throws Exception {
        contributorsPresenter.fetchReposError();
        verify(mockRepoDetailsView).fetchContributorsFailed();
    }

}