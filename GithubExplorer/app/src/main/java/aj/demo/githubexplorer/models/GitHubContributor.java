package aj.demo.githubexplorer.models;

public class GitHubContributor {

    private String login;

    public GitHubContributor(String name) {
        this.login = name;
    }

    public String getName() {
        return login;
    }
}
