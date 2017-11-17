package aj.demo.githubexplorer.showRepos;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import aj.demo.githubexplorer.R;
import aj.demo.githubexplorer.models.GitHubRepoItem;

/**
 * Created by AJ on 15-Nov-17.
 */

public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.ReposViewHolder> {

    private List<GitHubRepoItem> gitHubRepoItems;
    private ShowReposFragment showReposFragment;

    private RecyclerView reposRecyclerView;
    private LinearLayoutManager reposLayoutManager;

    private LoadMoreReposListener loadMoreReposListener;

    private boolean isLoading;
    private int visibleThreshold = 15;
    private int lastVisibleItem;
    private int totalItemCount;

    ReposAdapter(ShowReposFragment showReposFragment, RecyclerView reposRecyclerView) {
        this.showReposFragment = showReposFragment;
        this.reposRecyclerView = reposRecyclerView;
        this.reposLayoutManager = (LinearLayoutManager) reposRecyclerView.getLayoutManager();
        this.reposRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                totalItemCount = reposLayoutManager.getItemCount();
                lastVisibleItem = reposLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (loadMoreReposListener != null) {
                        loadMoreReposListener.loadMoreRepos();
                    }
                    isLoading = true;
                }
            }
        });
    }

    void setLoadMoreReposListener(LoadMoreReposListener loadMoreReposListener) {
        this.loadMoreReposListener = loadMoreReposListener;
    }

    void setGitHubRepoItems(List<GitHubRepoItem> gitHubRepoItems) {
        this.gitHubRepoItems = gitHubRepoItems;
    }

    List<GitHubRepoItem> getGitHubReposItems() {
        return gitHubRepoItems;
    }

    @Override
    public ReposViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_list_item, parent, false);
        return new ReposViewHolder(linearLayout);
    }

    @Override
    public void onBindViewHolder(ReposViewHolder holder, int position) {
        GitHubRepoItem gitHubRepoItem = gitHubRepoItems.get(position);
        holder.repoName.setText(gitHubRepoItem.getName());
        holder.repoName.setOnClickListener(v -> launchRepoDetails(gitHubRepoItem));
    }

    private void launchRepoDetails(GitHubRepoItem gitHubRepoItem) {
        showReposFragment.launchRepoDetailsFragment(gitHubRepoItem);
    }

    @Override
    public int getItemCount() {
        if(gitHubRepoItems != null) {
            return gitHubRepoItems.size();
        }
        return 0;
    }

    static class ReposViewHolder extends RecyclerView.ViewHolder {
        TextView repoName;
        ReposViewHolder(View itemView) {
            super(itemView);
            repoName = itemView.findViewById(R.id.repo_item_name);
        }
    }

    void resetLoaded() {
        isLoading = false;
    }
}
