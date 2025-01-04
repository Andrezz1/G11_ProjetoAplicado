# TurnoApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**turnoGet**](TurnoApi.md#turnoGet) | **GET** /Turno |  |
| [**turnoIdDelete**](TurnoApi.md#turnoIdDelete) | **DELETE** /Turno/{id} |  |
| [**turnoIdGet**](TurnoApi.md#turnoIdGet) | **GET** /Turno/{id} |  |
| [**turnoIdPut**](TurnoApi.md#turnoIdPut) | **PUT** /Turno/{id} |  |
| [**turnoPost**](TurnoApi.md#turnoPost) | **POST** /Turno |  |


<a id="turnoGet"></a>
# **turnoGet**
> kotlin.collections.List&lt;Turno&gt; turnoGet(id)



### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import pt.ipca.doamais.api.model.*

val apiInstance = TurnoApi()
val id : kotlin.Int = 56 // kotlin.Int | 
try {
    val result : kotlin.collections.List<Turno> = apiInstance.turnoGet(id)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling TurnoApi#turnoGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling TurnoApi#turnoGet")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **id** | **kotlin.Int**|  | [optional] |

### Return type

[**kotlin.collections.List&lt;Turno&gt;**](Turno.md)

### Authorization


Configure ApiKey:
    ApiClient.apiKey["Authorization"] = ""
    ApiClient.apiKeyPrefix["Authorization"] = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="turnoIdDelete"></a>
# **turnoIdDelete**
> turnoIdDelete(id)



### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import pt.ipca.doamais.api.model.*

val apiInstance = TurnoApi()
val id : kotlin.Int = 56 // kotlin.Int | 
try {
    apiInstance.turnoIdDelete(id)
} catch (e: ClientException) {
    println("4xx response calling TurnoApi#turnoIdDelete")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling TurnoApi#turnoIdDelete")
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

<a id="turnoIdGet"></a>
# **turnoIdGet**
> Turno turnoIdGet(id)



### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import pt.ipca.doamais.api.model.*

val apiInstance = TurnoApi()
val id : kotlin.Int = 56 // kotlin.Int | 
try {
    val result : Turno = apiInstance.turnoIdGet(id)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling TurnoApi#turnoIdGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling TurnoApi#turnoIdGet")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **id** | **kotlin.Int**|  | |

### Return type

[**Turno**](Turno.md)

### Authorization


Configure ApiKey:
    ApiClient.apiKey["Authorization"] = ""
    ApiClient.apiKeyPrefix["Authorization"] = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="turnoIdPut"></a>
# **turnoIdPut**
> turnoIdPut(id, turno)



### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import pt.ipca.doamais.api.model.*

val apiInstance = TurnoApi()
val id : kotlin.String = id_example // kotlin.String | 
val turno : Turno =  // Turno | 
try {
    apiInstance.turnoIdPut(id, turno)
} catch (e: ClientException) {
    println("4xx response calling TurnoApi#turnoIdPut")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling TurnoApi#turnoIdPut")
    e.printStackTrace()
}
```

### Parameters
| **id** | **kotlin.String**|  | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **turno** | [**Turno**](Turno.md)|  | [optional] |

### Return type

null (empty response body)

### Authorization


Configure ApiKey:
    ApiClient.apiKey["Authorization"] = ""
    ApiClient.apiKeyPrefix["Authorization"] = ""

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

<a id="turnoPost"></a>
# **turnoPost**
> kotlin.Any turnoPost(turno)



### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import pt.ipca.doamais.api.model.*

val apiInstance = TurnoApi()
val turno : Turno =  // Turno | 
try {
    val result : kotlin.Any = apiInstance.turnoPost(turno)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling TurnoApi#turnoPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling TurnoApi#turnoPost")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **turno** | [**Turno**](Turno.md)|  | [optional] |

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization


Configure ApiKey:
    ApiClient.apiKey["Authorization"] = ""
    ApiClient.apiKeyPrefix["Authorization"] = ""

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

