# BeneficiariosApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**beneficiariosGet**](BeneficiariosApi.md#beneficiariosGet) | **GET** /Beneficiarios |  |
| [**beneficiariosIdDelete**](BeneficiariosApi.md#beneficiariosIdDelete) | **DELETE** /Beneficiarios/{id} |  |
| [**beneficiariosIdGet**](BeneficiariosApi.md#beneficiariosIdGet) | **GET** /Beneficiarios/{id} |  |
| [**beneficiariosIdPut**](BeneficiariosApi.md#beneficiariosIdPut) | **PUT** /Beneficiarios/{id} |  |
| [**beneficiariosPost**](BeneficiariosApi.md#beneficiariosPost) | **POST** /Beneficiarios |  |


<a id="beneficiariosGet"></a>
# **beneficiariosGet**
> kotlin.collections.List&lt;Beneficiario&gt; beneficiariosGet()



### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import pt.ipca.doamais.api.model.*

val apiInstance = BeneficiariosApi()
try {
    val result : kotlin.collections.List<Beneficiario> = apiInstance.beneficiariosGet()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling BeneficiariosApi#beneficiariosGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling BeneficiariosApi#beneficiariosGet")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**kotlin.collections.List&lt;Beneficiario&gt;**](Beneficiario.md)

### Authorization


Configure ApiKey:
    ApiClient.apiKey["Authorization"] = ""
    ApiClient.apiKeyPrefix["Authorization"] = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="beneficiariosIdDelete"></a>
# **beneficiariosIdDelete**
> beneficiariosIdDelete(id)



### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import pt.ipca.doamais.api.model.*

val apiInstance = BeneficiariosApi()
val id : kotlin.Int = 56 // kotlin.Int | 
try {
    apiInstance.beneficiariosIdDelete(id)
} catch (e: ClientException) {
    println("4xx response calling BeneficiariosApi#beneficiariosIdDelete")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling BeneficiariosApi#beneficiariosIdDelete")
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

<a id="beneficiariosIdGet"></a>
# **beneficiariosIdGet**
> Beneficiario beneficiariosIdGet(id)



### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import pt.ipca.doamais.api.model.*

val apiInstance = BeneficiariosApi()
val id : kotlin.Int = 56 // kotlin.Int | 
try {
    val result : Beneficiario = apiInstance.beneficiariosIdGet(id)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling BeneficiariosApi#beneficiariosIdGet")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling BeneficiariosApi#beneficiariosIdGet")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **id** | **kotlin.Int**|  | |

### Return type

[**Beneficiario**](Beneficiario.md)

### Authorization


Configure ApiKey:
    ApiClient.apiKey["Authorization"] = ""
    ApiClient.apiKeyPrefix["Authorization"] = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="beneficiariosIdPut"></a>
# **beneficiariosIdPut**
> beneficiariosIdPut(id, beneficiario)



### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import pt.ipca.doamais.api.model.*

val apiInstance = BeneficiariosApi()
val id : kotlin.String = id_example // kotlin.String | 
val beneficiario : Beneficiario =  // Beneficiario | 
try {
    apiInstance.beneficiariosIdPut(id, beneficiario)
} catch (e: ClientException) {
    println("4xx response calling BeneficiariosApi#beneficiariosIdPut")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling BeneficiariosApi#beneficiariosIdPut")
    e.printStackTrace()
}
```

### Parameters
| **id** | **kotlin.String**|  | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **beneficiario** | [**Beneficiario**](Beneficiario.md)|  | [optional] |

### Return type

null (empty response body)

### Authorization


Configure ApiKey:
    ApiClient.apiKey["Authorization"] = ""
    ApiClient.apiKeyPrefix["Authorization"] = ""

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

<a id="beneficiariosPost"></a>
# **beneficiariosPost**
> kotlin.Any beneficiariosPost(beneficiario)



### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import pt.ipca.doamais.api.model.*

val apiInstance = BeneficiariosApi()
val beneficiario : Beneficiario =  // Beneficiario | 
try {
    val result : kotlin.Any = apiInstance.beneficiariosPost(beneficiario)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling BeneficiariosApi#beneficiariosPost")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling BeneficiariosApi#beneficiariosPost")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **beneficiario** | [**Beneficiario**](Beneficiario.md)|  | [optional] |

### Return type

[**kotlin.Any**](kotlin.Any.md)

### Authorization


Configure ApiKey:
    ApiClient.apiKey["Authorization"] = ""
    ApiClient.apiKeyPrefix["Authorization"] = ""

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

