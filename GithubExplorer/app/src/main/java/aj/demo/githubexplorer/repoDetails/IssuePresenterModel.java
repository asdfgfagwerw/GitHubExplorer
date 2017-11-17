package aj.demo.githubexplorer.repoDetails;

public class IssuePresenterModel {

    private final String title;
    private final String createdAt;

    IssuePresenterModel(String title, String created_at) {
        this.title = title;
        this.createdAt = created_at;
    }

    String getTitle() {
        return title;
    }

    String getCreatedAt() {
        return createdAt;
    }
}
