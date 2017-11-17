package aj.demo.githubexplorer.enterLanguage;

/**
 * Created by AJ on 13-Nov-17.
 */

class EnterLanguagePresenter {

    private final EnterLanguageView enterLanguageView;

    EnterLanguagePresenter(EnterLanguageView enterLanguageView) {
        this.enterLanguageView = enterLanguageView;
    }

    void startFetchingRepos(String languageToBeSearched) {
        if(languageToBeSearched == null || languageToBeSearched.isEmpty()) {
            enterLanguageView.showInvalidInput();
        }
        enterLanguageView.launchReposFragment();
    }
}
