package aj.demo.githubexplorer.models;

public class GitHubIssue {

    private String title;
    private String created_at;

    public GitHubIssue(String title, String created_at) {
        this.title = title;
        this.created_at = created_at;
    }

    public String getTitle() {
        return title;
    }

    public String getCreatedAt() {
        return created_at;
    }
}
