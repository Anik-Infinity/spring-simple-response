## Global Response Modules


## Usage

### Simple Response

```java
public ResponseEntity<ApiResponse> simpleRespons() {
    return ResponseUtils.buildResponse("It is a simple response");
}
```

It will generate response as follows

```json
{
    "timestamp": "2024-02-08T15:41:20.326662Z",
    "status": 200,
    "title": "OK",
    "message": "It is a simple response"
}
```

### Build Response with Resources

**For Single Resource**

```java
public ResponseEntity<ApiResponse> singleResource(int id) {
    UserDto user = userService.findById(id);
    return ResponseUtils.buildResponse(HttpStatus.OK, user, "User found");
}
```

It will generate response as follows

```json
{
    "timestamp": "2024-02-08T15:41:20.326662Z",
    "status": 200,
    "title": "OK",
    "message": "User found",
    "data": {
        "id": "d7564268-d815-4e8c-b6e8-b6e1e7235e23",
        "createdAt": "2024-02-08T15:29:29.333656Z",
        "updatedAt": "2024-02-08T15:29:29.333656Z",
        "name": "Charles Duhigg"
    }
}
```

**For Paginated Collection Resource**

```java
public ResponseEntity<ApiResponse> paginatedCollectionResource(Pageable pageable) {
    Page<UserDto> users = userService.findAll(pageable);
    return ResponseUtils.buildPaginatedResponse(users, "Author List");
}
```

It will generate response as follows

```json
{
    "timestamp": "2024-02-08T15:48:16.455288Z",
    "status": 200,
    "title": "OK",
    "message": "User list",
    "size": 1,
    "page": 0,
    "totalPages": 5,
    "totalRecords": 10,
    "data": [
        {
            "id": "d7564268-d815-4e8c-b6e8-b6e1e7235e23",
            "createdAt": "2024-02-08T15:29:29.333656Z",
            "updatedAt": "2024-02-08T15:29:29.333656Z",
            "name": "Charles Duhigg"
        }
    ]
}
```
