package com.example.github_repos_api.controller;

import com.example.github_repos_api.model.Repository;
import com.example.github_repos_api.service.GitHubService;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/github")
public class GitHubController {

    @Autowired
    private GitHubService gitHubService;

    @GetMapping("/users/{username}/repositories")
    public ResponseEntity<?> getUserRepositories(@PathVariable String username) {
        try {
            List<Repository> repositories = gitHubService.getUserRepositories(username);
            List<Map<String, Object>> response = repositories.stream().map(repository -> Map.of(
                    "repositoryName", repository.getName(),
                    "ownerLogin", repository.getOwner().getLogin(),
                    "branches", gitHubService.getRepositoryBranches(repository.getOwner().getLogin(), repository.getName()).stream().map(branch -> Map.of(
                            "branchName", branch.getName(),
                            "lastCommitSha", branch.getCommit().getSha()
                    )).collect(Collectors.toList())
            )).collect(Collectors.toList());

            return ResponseEntity.ok(response);

        } catch (FeignException.NotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "status", HttpStatus.NOT_FOUND.value(),
                    "message", "User not found"
            ));
        }
    }
}
