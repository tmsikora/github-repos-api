package com.example.github_repos_api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Branch {

    private String name;

    @JsonProperty("commit")
    private Commit commit;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Commit getCommit() {
        return commit;
    }

    public void setCommit(Commit commit) {
        this.commit = commit;
    }


    public static class Commit {

        private String sha;

        public String getSha() {
            return sha;
        }

        public void setSha(String sha) {
            this.sha = sha;
        }
    }
}
