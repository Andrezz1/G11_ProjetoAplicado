# UsersApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**usersIdGet**](UsersApi.md#usersIdGet) | **GET** /Users/{id} |  |
| [**usersLoginPost**](UsersApi.md#usersLoginPost) | **POST** /Users/Login |  |
| [**usersPost**](UsersApi.md#usersPost) | **POST** /Users |  |


<a id="usersIdGet"></a>
# **usersIdGet**
> User usersIdGet(id)



### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import pt.ipca.doamais.api.model.*

val apiInstance = UsersApi()
val id : kotlin.Int = 56 // kotlin.Int | 
try {
    val result : User = apiInstance.usersIdGet(id)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling UsersApi#usersIdGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling UsersApi#usersIdGet")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **id** | **kotlin.Int**|  | |

### Return type

[**User**](User.md)

### Authorization


Configure ApiKey:
    ApiClient.apiKey["Authorization"] = ""
    ApiClient.apiKeyPrefix["Authorization"] = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="usersLoginPost"></a>
# **usersLoginPost**
> User usersLoginPost(userLogin)



### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import pt.ipca.doamais.api.model.*

val apiInstance = UsersApi()
val userLogin : UserLogin =  // UserLogin | 
try {
    val result : User = apiInstance.usersLoginPost(userLogin)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling UsersApi#usersLoginPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling UsersApi#usersLoginPost")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **userLogin** | [**UserLogin**](UserLogin.md)|  | [optional] |

### Return type

[**User**](User.md)

### Authorization


Configure ApiKey:
    ApiClient.apiKey["Authorization"] = ""
    ApiClient.apiKeyPrefix["Authorization"] = ""

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a id="usersPost"></a>
# **usersPost**
> User usersPost(user)



### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import pt.ipca.doamais.api.model.*

val apiInstance = UsersApi()
val user : User =  // User | 
try {
    val result : User = apiInstance.usersPost(user)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling UsersApi#usersPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling UsersApi#usersPost")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **user** | [**User**](User.md)|  | [optional] |

### Return type

[**User**](User.md)

### Authorization


Configure ApiKey:
    ApiClient.apiKey["Authorization"] = ""
    ApiClient.apiKeyPrefix["Authorization"] = ""

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

