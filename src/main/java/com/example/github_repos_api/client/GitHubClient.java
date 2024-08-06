package com.example.github_repos_api.client;

import com.example.github_repos_api.model.Branch;
import com.example.github_repos_api.model.Repository;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "githubClient", url = "${github.api.url}")
public interface GitHubClient {

    @GetMapping("/users/{username}/repos")
    List<Repository> getUserRepositories(@PathVariable("username") String username,
                                         @RequestHeader("Accept") String acceptHeader);

    @GetMapping("/repos/{owner}/{repository}/branches")
    List<Branch> getRepositoryBranches(@PathVariable("owner") String owner,
                                       @PathVariable("repository") String repository,
                                       @RequestHeader("Accept") String acceptHeader);
}
