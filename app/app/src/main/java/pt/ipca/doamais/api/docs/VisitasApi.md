# VisitasApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**visitasGet**](VisitasApi.md#visitasGet) | **GET** /Visitas |  |
| [**visitasIdDelete**](VisitasApi.md#visitasIdDelete) | **DELETE** /Visitas/{id} |  |
| [**visitasIdGet**](VisitasApi.md#visitasIdGet) | **GET** /Visitas/{id} |  |
| [**visitasIdPut**](VisitasApi.md#visitasIdPut) | **PUT** /Visitas/{id} |  |
| [**visitasPost**](VisitasApi.md#visitasPost) | **POST** /Visitas |  |


<a id="visitasGet"></a>
# **visitasGet**
> kotlin.collections.List&lt;Visita&gt; visitasGet()



### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import pt.ipca.doamais.api.model.*

val apiInstance = VisitasApi()
try {
    val result : kotlin.collections.List<Visita> = apiInstance.visitasGet()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling VisitasApi#visitasGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling VisitasApi#visitasGet")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**kotlin.collections.List&lt;Visita&gt;**](Visita.md)

### Authorization


Configure ApiKey:
    ApiClient.apiKey["Authorization"] = ""
    ApiClient.apiKeyPrefix["Authorization"] = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="visitasIdDelete"></a>
# **visitasIdDelete**
> visitasIdDelete(id)



### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import pt.ipca.doamais.api.model.*

val apiInstance = VisitasApi()
val id : kotlin.Int = 56 // kotlin.Int | 
try {
    apiInstance.visitasIdDelete(id)
} catch (e: ClientException) {
    println("4xx response calling VisitasApi#visitasIdDelete")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling VisitasApi#visitasIdDelete")
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

<a id="visitasIdGet"></a>
# **visitasIdGet**
> Visita visitasIdGet(id)



### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import pt.ipca.doamais.api.model.*

val apiInstance = VisitasApi()
val id : kotlin.Int = 56 // kotlin.Int | 
try {
    val result : Visita = apiInstance.visitasIdGet(id)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling VisitasApi#visitasIdGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling VisitasApi#visitasIdGet")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **id** | **kotlin.Int**|  | |

### Return type

[**Visita**](Visita.md)

### Authorization


Configure ApiKey:
    ApiClient.apiKey["Authorization"] = ""
    ApiClient.apiKeyPrefix["Authorization"] = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="visitasIdPut"></a>
# **visitasIdPut**
> visitasIdPut(id, visita)



### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import pt.ipca.doamais.api.model.*

val apiInstance = VisitasApi()
val id : kotlin.Int = 56 // kotlin.Int | 
val visita : Visita =  // Visita | 
try {
    apiInstance.visitasIdPut(id, visita)
} catch (e: ClientException) {
    println("4xx response calling VisitasApi#visitasIdPut")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling VisitasApi#visitasIdPut")
    e.printStackTrace()
}
```

### Parameters
| **id** | **kotlin.Int**|  | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **visita** | [**Visita**](Visita.md)|  | [optional] |

### Return type

null (empty response body)

### Authorization


Configure ApiKey:
    ApiClient.apiKey["Authorization"] = ""
    ApiClient.apiKeyPrefix["Authorization"] = ""

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

<a id="visitasPost"></a>
# **visitasPost**
> Visita visitasPost(visita)



### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import pt.ipca.doamais.api.model.*

val apiInstance = VisitasApi()
val visita : Visita =  // Visita | 
try {
    val result : Visita = apiInstance.visitasPost(visita)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling VisitasApi#visitasPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling VisitasApi#visitasPost")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **visita** | [**Visita**](Visita.md)|  | [optional] |

### Return type

[**Visita**](Visita.md)

### Authorization


Configure ApiKey:
    ApiClient.apiKey["Authorization"] = ""
    ApiClient.apiKeyPrefix["Authorization"] = ""

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

