package aj.demo.githubexplorer.repoDetails;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import aj.demo.githubexplorer.R;


public class RepoDetailsFragment extends Fragment implements RepoDetailsView {

    private static final String CONTRIBUTORS_URL = "CONTRIBUTORS_URL";
    private static final String ISSUES_URL = "ISSUES_URL";
    private static final String REPO_DESCRIPTION = "DESCRIPTION";
    private static final String FULL_NAME = "FULL_NAME";

    private TextView repoName;
    private TextView repoDescription;
    private TextView repoContributors;
    private TextView repoIssues;

    private ContributorsPresenter contributorsPresenter;
    private IssuesPresenter issuesPresenter;

    public static Fragment newInstance(String fullName, String description, String contributorsUrl, String issuesUrl) {
        Bundle bundle = new Bundle();
        bundle.putString(FULL_NAME, fullName);
        bundle.putString(REPO_DESCRIPTION, description);
        bundle.putString(CONTRIBUTORS_URL, contributorsUrl);
        bundle.putString(ISSUES_URL, issuesUrl);

        RepoDetailsFragment repoDetailsFragment = new RepoDetailsFragment();
        repoDetailsFragment.setArguments(bundle);
        return repoDetailsFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.repo_details, container, false);
        initViews(view);
        setViewValues();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        contributorsPresenter = new ContributorsPresenter(this);
        issuesPresenter = new IssuesPresenter(this);
        Bundle bundle = getArguments();
        if(bundle != null) {
            contributorsPresenter.fetchContributors(bundle.getString(CONTRIBUTORS_URL));
            issuesPresenter.fetchIssues(bundle.getString(ISSUES_URL));
        }
    }

    @Override
    public void showContributors(List<String> names) {
        if(isVisible()) {
            StringBuilder contributorsString = new StringBuilder(getString(R.string.top_3_contributors) + "\n\n");
            for (String name : names) {
                contributorsString.append(name + "\n");
            }
            repoContributors.setText(contributorsString.toString());
        }
    }

    @Override
    public void showIssues(List<IssuePresenterModel> issues) {
        if(isVisible()) {
            StringBuilder issuesString = new StringBuilder(getString(R.string.three_newest_issues) + "\n\n");
            for (IssuePresenterModel issue : issues) {
                issuesString.append(" " + issue.getTitle() + " was created on " + issue.getCreatedAt() + "\n");
            }
            repoIssues.setText(issuesString.toString());
        }
    }

    @Override
    public void fetchContributorsFailed() {
        Toast.makeText(getContext(), "We were unable to fetch the list of contributors", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void fetchIssuesFailed() {
        Toast.makeText(getContext(), "We were unable to fetch the list of issues", Toast.LENGTH_SHORT).show();
    }

    private void setViewValues() {
        Bundle bundle = getArguments();
        if(bundle != null) {
            repoName.setText(bundle.getString(FULL_NAME));
            repoDescription.setText(bundle.getString(REPO_DESCRIPTION));
        }
    }

    private void initViews(View view) {
        repoName = view.findViewById(R.id.repo_name_title);
        repoDescription = view.findViewById(R.id.repo_description);
        repoContributors = view.findViewById(R.id.repo_contributors);
        repoIssues = view.findViewById(R.id.repo_issues);
    }
}
