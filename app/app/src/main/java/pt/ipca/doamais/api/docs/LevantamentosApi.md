# LevantamentosApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**levantamentosGet**](LevantamentosApi.md#levantamentosGet) | **GET** /Levantamentos |  |
| [**levantamentosIdDelete**](LevantamentosApi.md#levantamentosIdDelete) | **DELETE** /Levantamentos/{id} |  |
| [**levantamentosIdGet**](LevantamentosApi.md#levantamentosIdGet) | **GET** /Levantamentos/{id} |  |
| [**levantamentosIdPut**](LevantamentosApi.md#levantamentosIdPut) | **PUT** /Levantamentos/{id} |  |
| [**levantamentosPost**](LevantamentosApi.md#levantamentosPost) | **POST** /Levantamentos |  |


<a id="levantamentosGet"></a>
# **levantamentosGet**
> kotlin.collections.List&lt;Levantamento&gt; levantamentosGet()



### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import pt.ipca.doamais.api.model.*

val apiInstance = LevantamentosApi()
try {
    val result : kotlin.collections.List<Levantamento> = apiInstance.levantamentosGet()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling LevantamentosApi#levantamentosGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling LevantamentosApi#levantamentosGet")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**kotlin.collections.List&lt;Levantamento&gt;**](Levantamento.md)

### Authorization


Configure ApiKey:
    ApiClient.apiKey["Authorization"] = ""
    ApiClient.apiKeyPrefix["Authorization"] = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="levantamentosIdDelete"></a>
# **levantamentosIdDelete**
> levantamentosIdDelete(id)



### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import pt.ipca.doamais.api.model.*

val apiInstance = LevantamentosApi()
val id : kotlin.Int = 56 // kotlin.Int | 
try {
    apiInstance.levantamentosIdDelete(id)
} catch (e: ClientException) {
    println("4xx response calling LevantamentosApi#levantamentosIdDelete")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling LevantamentosApi#levantamentosIdDelete")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **id** | **kotlin.Int**|  | |

### Return type

null (empty response body)

### Authorization


Configure ApiKey:
    ApiClient.apiKey["Authorization"] = ""
    ApiClient.apiKeyPrefix["Authorization"] = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a id="levantamentosIdGet"></a>
# **levantamentosIdGet**
> Levantamento levantamentosIdGet(id)



### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import pt.ipca.doamais.api.model.*

val apiInstance = LevantamentosApi()
val id : kotlin.Int = 56 // kotlin.Int | 
try {
    val result : Levantamento = apiInstance.levantamentosIdGet(id)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling LevantamentosApi#levantamentosIdGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling LevantamentosApi#levantamentosIdGet")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **id** | **kotlin.Int**|  | |

### Return type

[**Levantamento**](Levantamento.md)

### Authorization


Configure ApiKey:
    ApiClient.apiKey["Authorization"] = ""
    ApiClient.apiKeyPrefix["Authorization"] = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="levantamentosIdPut"></a>
# **levantamentosIdPut**
> levantamentosIdPut(id, levantamento)



### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import pt.ipca.doamais.api.model.*

val apiInstance = LevantamentosApi()
val id : kotlin.Int = 56 // kotlin.Int | 
val levantamento : Levantamento =  // Levantamento | 
try {
    apiInstance.levantamentosIdPut(id, levantamento)
} catch (e: ClientException) {
    println("4xx response calling LevantamentosApi#levantamentosIdPut")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling LevantamentosApi#levantamentosIdPut")
    e.printStackTrace()
}
```

### Parameters
| **id** | **kotlin.Int**|  | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **levantamento** | [**Levantamento**](Levantamento.md)|  | [optional] |

### Return type

null (empty response body)

### Authorization


Configure ApiKey:
    ApiClient.apiKey["Authorization"] = ""
    ApiClient.apiKeyPrefix["Authorization"] = ""

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

<a id="levantamentosPost"></a>
# **levantamentosPost**
> Levantamento levantamentosPost(levantamento)



### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import pt.ipca.doamais.api.model.*

val apiInstance = LevantamentosApi()
val levantamento : Levantamento =  // Levantamento | 
try {
    val result : Levantamento = apiInstance.levantamentosPost(levantamento)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling LevantamentosApi#levantamentosPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling LevantamentosApi#levantamentosPost")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **levantamento** | [**Levantamento**](Levantamento.md)|  | [optional] |

### Return type

[**Levantamento**](Levantamento.md)

### Authorization


Configure ApiKey:
    ApiClient.apiKey["Authorization"] = ""
    ApiClient.apiKeyPrefix["Authorization"] = ""

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

