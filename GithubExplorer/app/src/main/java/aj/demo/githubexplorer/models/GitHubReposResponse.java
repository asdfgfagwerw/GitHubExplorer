package aj.demo.githubexplorer.models;

import java.util.List;

/**
 * Created by AJ on 14-Nov-17.
 */

public class GitHubReposResponse {

    private List<GitHubRepoItem> items;

    public GitHubReposResponse(List<GitHubRepoItem> items) {
        this.items = items;
    }

    public List<GitHubRepoItem> getItems() {
        return items;
    }
}
