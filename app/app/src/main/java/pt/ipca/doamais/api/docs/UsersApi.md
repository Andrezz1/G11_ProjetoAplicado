# UsersApi

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**usersIdGet**](UsersApi.md#usersIdGet) | **GET** /Users/{id} | 
[**usersLoginPost**](UsersApi.md#usersLoginPost) | **POST** /Users/Login | 
[**usersPost**](UsersApi.md#usersPost) | **POST** /Users | 



## usersIdGet

> User usersIdGet(id)



### Example

```java
// Import classes:
//import pt.ipca.doamais.api.api.UsersApi;

UsersApi apiInstance = new UsersApi();
Integer id = null; // Integer | 
try {
    User result = apiInstance.usersIdGet(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UsersApi#usersIdGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Integer**|  | [default to null]

### Return type

[**User**](User.md)

### Authorization

[ApiKey](../README.md#ApiKey)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: text/plain, application/json, text/json


## usersLoginPost

> User usersLoginPost(userLogin)



### Example

```java
// Import classes:
//import pt.ipca.doamais.api.api.UsersApi;

UsersApi apiInstance = new UsersApi();
UserLogin userLogin = new UserLogin(); // UserLogin | 
try {
    User result = apiInstance.usersLoginPost(userLogin);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UsersApi#usersLoginPost");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userLogin** | [**UserLogin**](UserLogin.md)|  | [optional]

### Return type

[**User**](User.md)

### Authorization

[ApiKey](../README.md#ApiKey)

### HTTP request headers

- **Content-Type**: application/json, text/json, application/*+json
- **Accept**: text/plain, application/json, text/json


## usersPost

> User usersPost(user)



### Example

```java
// Import classes:
//import pt.ipca.doamais.api.api.UsersApi;

UsersApi apiInstance = new UsersApi();
User user = new User(); // User | 
try {
    User result = apiInstance.usersPost(user);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UsersApi#usersPost");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **user** | [**User**](User.md)|  | [optional]

### Return type

[**User**](User.md)

### Authorization

[ApiKey](../README.md#ApiKey)

### HTTP request headers

- **Content-Type**: application/json, text/json, application/*+json
- **Accept**: text/plain, application/json, text/json

