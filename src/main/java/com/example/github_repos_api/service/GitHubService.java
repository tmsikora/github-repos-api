package com.example.github_repos_api.service;

import com.example.github_repos_api.client.GitHubClient;
import com.example.github_repos_api.model.Branch;
import com.example.github_repos_api.model.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GitHubService {

    @Autowired
    private GitHubClient gitHubClient;

    public List<Repository> getUserRepositories(String username) {
        List<Repository> repositories = gitHubClient.getUserRepositories(username, "application/json");
        return repositories.stream().filter(repository -> !repository.isFork()).collect(Collectors.toList());
    }

    public List<Branch> getRepositoryBranches(String owner, String repository) {
        return gitHubClient.getRepositoryBranches(owner, repository, "application/json");
    }
}
