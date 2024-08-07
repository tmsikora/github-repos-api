package com.example.github_repos_api;

import com.example.github_repos_api.client.GitHubClient;
import com.example.github_repos_api.model.Repository;
import com.example.github_repos_api.service.GitHubService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class GitHubServiceTest {

    @Mock
    private GitHubClient gitHubClient;

    @InjectMocks
    private GitHubService gitHubService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUserRepositories() {
        Repository.Owner owner = new Repository.Owner();
        owner.setLogin("user");

        Repository repository1 = new Repository();
        repository1.setName("Repository1");
        repository1.setOwner(owner);
        repository1.setFork(false);

        Repository repository2 = new Repository();
        repository2.setName("Repository2");
        repository2.setOwner(owner);
        repository2.setFork(true);

        when(gitHubClient.getUserRepositories("user", "application/json")).thenReturn(List.of(repository1, repository2));

        List<Repository> repositories = gitHubService.getUserRepositories("user");
        assertEquals(1, repositories.size());
        assertEquals("Repository1", repositories.getFirst().getName());
    }
}
