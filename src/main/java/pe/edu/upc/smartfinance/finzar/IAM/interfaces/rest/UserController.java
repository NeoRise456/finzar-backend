package pe.edu.upc.smartfinance.finzar.IAM.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.smartfinance.finzar.IAM.domain.model.commands.DeleteUserCommand;
import pe.edu.upc.smartfinance.finzar.IAM.domain.model.queries.GetAllUsersQuery;
import pe.edu.upc.smartfinance.finzar.IAM.domain.model.queries.GetUserByIdQuery;
import pe.edu.upc.smartfinance.finzar.IAM.domain.services.UserCommandService;
import pe.edu.upc.smartfinance.finzar.IAM.domain.services.UserQueryService;
import pe.edu.upc.smartfinance.finzar.IAM.interfaces.rest.resources.CreateUserResource;
import pe.edu.upc.smartfinance.finzar.IAM.interfaces.rest.resources.UpdateUserResource;
import pe.edu.upc.smartfinance.finzar.IAM.interfaces.rest.resources.UserResource;
import pe.edu.upc.smartfinance.finzar.IAM.interfaces.rest.transform.CreateUserCommandFromResourceAssembler;
import pe.edu.upc.smartfinance.finzar.IAM.interfaces.rest.transform.UpdateUserCommandFromResourceAssembler;
import pe.edu.upc.smartfinance.finzar.IAM.interfaces.rest.transform.UserResourceFromEntityAssembler;

import java.util.List;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Users", description = "User Management Endpoints")
public class UserController {
    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    public UserController(UserCommandService userCommandService, UserQueryService userQueryService) {
        this.userCommandService = userCommandService;
        this.userQueryService = userQueryService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResource> getUserById(@PathVariable Long userId) {
        var getUserByIdQuery = new GetUserByIdQuery(userId);
        var user = userQueryService.handle(getUserByIdQuery);
        if (user.isEmpty()) return ResponseEntity.notFound().build();

        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return ResponseEntity.ok(userResource);
    }

    @GetMapping
    public ResponseEntity<List<UserResource>> getAllUsers() {
        var getAllUsersQuery = new GetAllUsersQuery();
        var users = userQueryService.handle(getAllUsersQuery);
        var userResources = users.stream()
                .map(UserResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(userResources);
    }

    @PostMapping
    public ResponseEntity<UserResource> createUser(@RequestBody CreateUserResource resource) {
        var createUserCommand = CreateUserCommandFromResourceAssembler.toCommandFromResource(resource);
        var userId = userCommandService.handle(createUserCommand);
        if (userId == 0L) return ResponseEntity.badRequest().build();

        var getUserByIdQuery = new GetUserByIdQuery(userId);
        var user = userQueryService.handle(getUserByIdQuery);
        if (user.isEmpty()) return ResponseEntity.badRequest().build();

        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return new ResponseEntity<>(userResource, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResource> updateUser(@PathVariable Long userId, @RequestBody UpdateUserResource updateUserResource) {
        var updateUserCommand = UpdateUserCommandFromResourceAssembler.toCommandFromResource(userId, updateUserResource);
        var updatedUser = userCommandService.handle(updateUserCommand);
        if (updatedUser.isEmpty()) return ResponseEntity.notFound().build();

        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(updatedUser.get());
        return ResponseEntity.ok(userResource);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        var deleteUserCommand = new DeleteUserCommand(userId);
        userCommandService.handle(deleteUserCommand);
        return ResponseEntity.ok("User with given id deleted successfully");
    }
}
