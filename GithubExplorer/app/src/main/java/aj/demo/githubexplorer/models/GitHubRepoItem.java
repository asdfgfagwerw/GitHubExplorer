package aj.demo.githubexplorer.models;

/**
 * Created by AJ on 14-Nov-17.
 */

public class GitHubRepoItem {
    private int id;
    private String name;
    private String full_name;
    private String description;
    private String contributors_url;
    private String issues_url;

    public GitHubRepoItem(int id, String name, String full_name, String description, String contributors_url, String issues_url) {
        this.id = id;
        this.name = name;
        this.full_name = full_name;
        this.description = description;
        this.contributors_url = contributors_url;
        this.issues_url = issues_url;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getDescription() {
        return description;
    }

    public String getContributors_url() {
        return contributors_url;
    }

    public String getIssues_url() {
        return issues_url;
    }
}
