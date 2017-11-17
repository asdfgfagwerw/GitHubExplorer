package aj.demo.githubexplorer.showRepos;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import aj.demo.githubexplorer.models.GitHubRepoItem;
import aj.demo.githubexplorer.models.GitHubReposResponse;
import aj.demo.githubexplorer.repository.RetrofitGitHubRepository;

import static org.mockito.Mockito.verify;

/**
 * Created by AJ on 15-Nov-17.
 */
@RunWith(MockitoJUnitRunner.class)
public class ShowReposPresenterTest {

    @Mock
    ShowReposView mockShowReposView;

    @Mock
    RetrofitGitHubRepository mockRetrofitGitHubRepository;

    private ShowReposPresenter showReposPresenter;

    @Before
    public void setUp() {
        showReposPresenter = new ShowReposPresenter(mockShowReposView);
        showReposPresenter.setMockRetrofitGitHubRepository(mockRetrofitGitHubRepository);
    }

    @Test
    public void fetchRepos() throws Exception {
        showReposPresenter.fetchRepos("Java");
        verify(mockRetrofitGitHubRepository).fetchRepos("Java");
    }

    @Test
    public void fetchReposSuccess_invalidResult() throws Exception {
        showReposPresenter.fetchReposSuccess(null);
        verify(mockShowReposView).showError();
    }

    @Test
    public void fetchReposSuccess_validResult() throws Exception {
        GitHubRepoItem item = new GitHubRepoItem(1, "RxJava", "Reactive/RxJava", "desc", "dummy_url", "dummy_url");
        List<GitHubRepoItem> items = Arrays.asList(item);
        GitHubReposResponse gitHubReposResponse = new GitHubReposResponse(items);
        GitHubAdapterModel gitHubAdapterModel = new GitHubAdapterModel(gitHubReposResponse, "some_link");
        showReposPresenter.fetchReposSuccess(gitHubAdapterModel);
        verify(mockShowReposView).showRepos(gitHubAdapterModel);
    }

    @Test
    public void fetchReposError() throws Exception {
        showReposPresenter.fetchReposError();
        verify(mockShowReposView).showError();
    }

    @Test
    public void fetchSubsequentPage() throws Exception {
        showReposPresenter.fetchSubsequentPage("nextPage");
        verify(mockRetrofitGitHubRepository).fetchSubsequentRepos("nextPage");
    }

}