/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (4.2.2).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.yotpo.managetask.api.generated;

import com.yotpo.managetask.api.generated.model.TaskRequest;
import com.yotpo.managetask.api.generated.model.TaskResponse;
import com.yotpo.managetask.api.generated.model.TasksResponse;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Validated
@Api(value = "Tasks", description = "the Tasks API")
public interface TasksApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    @ApiOperation(value = "Create a new task", nickname = "create", notes = "", response = TaskResponse.class, authorizations = {
        @Authorization(value = "bearer")
    }, tags={ "tasks", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "The create task", response = TaskResponse.class) })
    @RequestMapping(value = "/tasks",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    default ResponseEntity<TaskResponse> create(@ApiParam(value = "" ,required=true )  @Valid @RequestBody TaskRequest taskRequest) throws Exception {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"task\" : { \"due_date\" : \"2000-01-23\", \"task_id\" : 0, \"assignee\" : { \"assignee\" : { \"last_name\" : \"last_name\", \"first_name\" : \"first_name\", \"assignee_id\" : 6 } }, \"title\" : \"title\", \"status\" : \"status\" } }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @ApiOperation(value = "Delete task by id", nickname = "delete", notes = "", authorizations = {
        @Authorization(value = "bearer")
    }, tags={ "tasks", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "The task was deleted"),
        @ApiResponse(code = 404, message = "Not found") })
    @RequestMapping(value = "/tasks/{task_id}",
        method = RequestMethod.DELETE)
    default ResponseEntity<Void> delete(@ApiParam(value = "The task id",required=true) @PathVariable("task_id") Long taskId) throws Exception {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @ApiOperation(value = "Get task by internal id", nickname = "findById", notes = "", response = TaskResponse.class, authorizations = {
        @Authorization(value = "bearer")
    }, tags={ "tasks", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Task", response = TaskResponse.class),
        @ApiResponse(code = 400, message = "The request contains illegal argument/s"),
        @ApiResponse(code = 401, message = "Access token is missing or invalid"),
        @ApiResponse(code = 404, message = "Task was not found") })
    @RequestMapping(value = "/tasks/{task_id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<TaskResponse> findById(@ApiParam(value = "The task id",required=true) @PathVariable("task_id") Long taskId) throws Exception {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"task\" : { \"due_date\" : \"2000-01-23\", \"task_id\" : 0, \"assignee\" : { \"assignee\" : { \"last_name\" : \"last_name\", \"first_name\" : \"first_name\", \"assignee_id\" : 6 } }, \"title\" : \"title\", \"status\" : \"status\" } }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @ApiOperation(value = "Get all tasks", nickname = "index", notes = "", response = TasksResponse.class, authorizations = {
        @Authorization(value = "bearer")
    }, tags={ "tasks", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "All the existing tasks", response = TasksResponse.class),
        @ApiResponse(code = 404, message = "Not found") })
    @RequestMapping(value = "/tasks",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<TasksResponse> index() throws Exception {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"tasks\" : [ { \"task\" : { \"due_date\" : \"2000-01-23\", \"task_id\" : 0, \"assignee\" : { \"assignee\" : { \"last_name\" : \"last_name\", \"first_name\" : \"first_name\", \"assignee_id\" : 6 } }, \"title\" : \"title\", \"status\" : \"status\" } }, { \"task\" : { \"due_date\" : \"2000-01-23\", \"task_id\" : 0, \"assignee\" : { \"assignee\" : { \"last_name\" : \"last_name\", \"first_name\" : \"first_name\", \"assignee_id\" : 6 } }, \"title\" : \"title\", \"status\" : \"status\" } } ] }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    @ApiOperation(value = "Updating existing task", nickname = "update", notes = "", response = TaskResponse.class, authorizations = {
        @Authorization(value = "bearer")
    }, tags={ "tasks", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "The updated task", response = TaskResponse.class),
        @ApiResponse(code = 404, message = "Not found") })
    @RequestMapping(value = "/tasks/{task_id}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    default ResponseEntity<TaskResponse> update(@ApiParam(value = "The task id",required=true) @PathVariable("task_id") Long taskId,@ApiParam(value = "" ,required=true )  @Valid @RequestBody TaskRequest taskRequest) throws Exception {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"task\" : { \"due_date\" : \"2000-01-23\", \"task_id\" : 0, \"assignee\" : { \"assignee\" : { \"last_name\" : \"last_name\", \"first_name\" : \"first_name\", \"assignee_id\" : 6 } }, \"title\" : \"title\", \"status\" : \"status\" } }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
