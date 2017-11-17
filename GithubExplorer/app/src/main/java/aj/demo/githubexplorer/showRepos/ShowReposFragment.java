package aj.demo.githubexplorer.showRepos;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import aj.demo.githubexplorer.MainActivity;
import aj.demo.githubexplorer.Parser;
import aj.demo.githubexplorer.R;
import aj.demo.githubexplorer.models.GitHubRepoItem;
import aj.demo.githubexplorer.repoDetails.RepoDetailsFragment;

/**
 * Created by AJ on 15-Nov-17.
 */

public class ShowReposFragment extends Fragment implements ShowReposView {

    private static final String DEFAULT_LANGUAGE = "Java";
    private static final String LANGUAGE_TO_BE_SEARCHED = "LANGUAGE_TO_BE_SEARCHED";

    private RecyclerView reposRecyclerView;
    private ReposAdapter reposAdapter;
    private LinearLayoutManager reposLayoutManager;
    private ProgressBar fetchingReposProgress;
    private GitHubAdapterModel gitHubAdapterModel;

    private ShowReposPresenter showReposPresenter;

    public static ShowReposFragment newInstance(String languageToBeSearched) {
        Bundle bundle = new Bundle();
        bundle.putString("LANGUAGE", languageToBeSearched);

        ShowReposFragment showReposFragment = new ShowReposFragment();
        showReposFragment.setArguments(bundle);
        return showReposFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.repos_list, container, false);
        initReposRecyclerView(view);
        fetchingReposProgress = view.findViewById(R.id.fetching_repos);
        showReposPresenter = new ShowReposPresenter(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(getArguments() != null) {
            Bundle bundle = getArguments();
            String languageToBeSearched = bundle.getString("LANGUAGE");
            showReposPresenter.fetchRepos(languageToBeSearched);
        } else {
            String languageToBeSearched = getActivity().getPreferences(Context.MODE_PRIVATE).getString(LANGUAGE_TO_BE_SEARCHED, DEFAULT_LANGUAGE);
            showReposPresenter.fetchRepos(languageToBeSearched);
        }
    }

    @Override
    public void showRepos(GitHubAdapterModel gitHubAdapterModelResponse) {
        fetchingReposProgress.setVisibility(View.GONE);
        gitHubAdapterModel = gitHubAdapterModelResponse;
        List<GitHubRepoItem> olderItems = reposAdapter.getGitHubReposItems();
        List<GitHubRepoItem> items = gitHubAdapterModelResponse.getGitHubReposResponse().getItems();
        if(olderItems != null) {
            List<GitHubRepoItem> totalItems = new ArrayList<>(olderItems);
            totalItems.addAll(items);
            reposAdapter.setGitHubRepoItems(totalItems);
        } else {
            reposAdapter.setGitHubRepoItems(items);
        }
        reposAdapter.setLoadMoreReposListener(() ->
            showReposPresenter.fetchSubsequentPage(gitHubAdapterModel.getNextPageLink()));
        reposAdapter.resetLoaded();
        reposAdapter.notifyDataSetChanged();
    }



    public void launchRepoDetailsFragment(GitHubRepoItem gitHubRepoItem) {
        MainActivity mainActivity = (MainActivity) getActivity();
        String fullName = gitHubRepoItem.getFull_name();
        String description = gitHubRepoItem.getDescription();
        String contributorsUrl = gitHubRepoItem.getContributors_url();
        String issuesUrl = Parser.parseIssuesUrl(gitHubRepoItem.getIssues_url());
        mainActivity.launchFragment(RepoDetailsFragment.newInstance(fullName, description, contributorsUrl, issuesUrl));
    }

    @Override
    public void showError() {
        fetchingReposProgress.setVisibility(View.GONE);
        Toast.makeText(getContext(), "Couldn't fetch repos for the given language", Toast.LENGTH_SHORT).show();
        getActivity().onBackPressed();
    }

    private void initReposRecyclerView(View view) {
        reposRecyclerView = view.findViewById(R.id.repos_list);
        reposLayoutManager = new LinearLayoutManager(this.getContext());
        reposRecyclerView.setLayoutManager(reposLayoutManager);
        reposAdapter = new ReposAdapter(this, reposRecyclerView);
        reposRecyclerView.setAdapter(reposAdapter);
    }
}
