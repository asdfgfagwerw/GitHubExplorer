package aj.demo.githubexplorer.enterLanguage;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding2.widget.RxTextView;

import aj.demo.githubexplorer.MainActivity;
import aj.demo.githubexplorer.R;
import aj.demo.githubexplorer.showRepos.ShowReposFragment;

/**
 * Created by AJ on 13-Nov-17.
 */

public class EnterLanguageFragment extends Fragment implements EnterLanguageView {

    private static final String LANGUAGE_TO_BE_SEARCHED = "LANGUAGE_TO_BE_SEARCHED";
    private EnterLanguagePresenter enterLanguagePresenter;

    private String languageToBeSearched;

    private Button fetchRepos;
    private EditText languageEntered;
    private TextView errorMessage;

    private SharedPreferences sharedPreferences;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        enterLanguagePresenter = new EnterLanguagePresenter(this);
        sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.enter_language, container, false);
        initViews(view);
        return view;
    }

    @Override
    public void launchReposFragment() {
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.launchFragment(ShowReposFragment.newInstance(languageToBeSearched));
    }

    @Override
    public void showInvalidInput() {
        setErrorVisibility(View.VISIBLE);
    }

    private void setErrorVisibility(int visibility) {
        errorMessage.setVisibility(visibility);
    }

    private void initViews(View view) {
        fetchRepos = view.findViewById(R.id.fetch_button);
        fetchRepos.setOnClickListener(v -> fetchReposClicked());

        languageEntered = view.findViewById(R.id.language_editText);
        RxTextView.textChanges(languageEntered)
                .map(CharSequence::toString)
                .subscribe(this::setLanguageToBeSearched);

        errorMessage = view.findViewById(R.id.error_message);
        setErrorVisibility(View.GONE);
    }

    private void setLanguageToBeSearched(String languageToBeSearched) {
        this.languageToBeSearched = languageToBeSearched;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(LANGUAGE_TO_BE_SEARCHED, languageToBeSearched);
        editor.apply();
    }

    private void fetchReposClicked() {
        enterLanguagePresenter.startFetchingRepos(languageToBeSearched);
        hideKeyboard();
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }
}
