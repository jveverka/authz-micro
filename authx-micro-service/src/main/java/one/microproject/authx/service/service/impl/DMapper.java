package one.microproject.authx.service.service.impl;

import one.microproject.authx.common.dto.*;
import one.microproject.authx.common.utils.CryptoUtils;
import one.microproject.authx.service.model.Client;
import one.microproject.authx.service.model.Group;
import one.microproject.authx.service.model.Project;
import one.microproject.authx.service.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class DMapper {

    public ProjectDto map(Project project) {
        return new ProjectDto(project.getId(), project.getDescription(), project.getLabels());
    }

    public Project map(CreateProjectRequest request) {
        return new Project(request.id(), request.description(), List.of(request.adminUser().id()), request.labels());
    }

    public ClientDto map(Client client) {
        return new ClientDto(client.getClientId(), client.getProjectId(), client.getDescription(), client.getLabels());
    }

    public Client map(String dbId, String projectId, String secretHash, CreateClientRequest clientRequest, Map<String, KeyPairSerialized> keyPairs) {
        return new Client(dbId, clientRequest.id(), projectId, clientRequest.description(),
                secretHash, clientRequest.labels(), keyPairs);
    }

    public UserDto map(User user) {
        return new UserDto(user.getUserId(), user.getClientId(), user.getEmail(), user.getDescription(), user.getSecret(), user.getLabels());
    }

    public User map(String dbId, String projectId, String clientId, String secretHash, CreateUserRequest request, Map<String, KeyPairSerialized> keyPairs) {
        return new User(dbId, request.id(), projectId, clientId, request.email(), request.description(), secretHash, request.labels(), keyPairs);
    }

    public Group map(String dbId, CreateGroupRequest request) {
        return new Group(dbId, request.id(), request.projectId(), request.description(), request.labels());
    }

    public GroupDto map(Group group) {
        return new GroupDto(group.getGroupId(), group.getProjectId(), group.getDescription(), group.getLabels());
    }

    public KeyPairData map(KeyPairSerialized keyPairSerialized) {
        return CryptoUtils.map(keyPairSerialized);
    }

    public KeyPairSerialized map(KeyPairData keyPairData) {
        return CryptoUtils.map(keyPairData);
    }

}
