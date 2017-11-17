package aj.demo.githubexplorer.enterLanguage;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Created by AJ on 15-Nov-17.
 */
@RunWith(MockitoJUnitRunner.class)
public class EnterLanguagePresenterTest {

    @Mock
    EnterLanguageView mockEnterLanguageView;

    private EnterLanguagePresenter enterLanguagePresenter;

    @Before
    public void setUp() {
        enterLanguagePresenter = new EnterLanguagePresenter(mockEnterLanguageView);
    }

    @Test
    public void startFetchingRepos_nullInput() throws Exception {
        enterLanguagePresenter.startFetchingRepos(null);
        Mockito.verify(mockEnterLanguageView).showInvalidInput();
    }

    @Test
    public void startFetchingRepos_emptyInput() throws Exception {
        enterLanguagePresenter.startFetchingRepos("");
        Mockito.verify(mockEnterLanguageView).showInvalidInput();
    }

    @Test
    public void startFetchingRepos_validInput() throws Exception {
        enterLanguagePresenter.startFetchingRepos("Java");
        Mockito.verify(mockEnterLanguageView).launchReposFragment();
    }

}