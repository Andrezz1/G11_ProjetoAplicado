# BeneficiariosApi

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**beneficiariosGet**](BeneficiariosApi.md#beneficiariosGet) | **GET** /Beneficiarios | 
[**beneficiariosIdDelete**](BeneficiariosApi.md#beneficiariosIdDelete) | **DELETE** /Beneficiarios/{id} | 
[**beneficiariosIdGet**](BeneficiariosApi.md#beneficiariosIdGet) | **GET** /Beneficiarios/{id} | 
[**beneficiariosIdPut**](BeneficiariosApi.md#beneficiariosIdPut) | **PUT** /Beneficiarios/{id} | 
[**beneficiariosPost**](BeneficiariosApi.md#beneficiariosPost) | **POST** /Beneficiarios | 



## beneficiariosGet

> List&lt;Beneficiario&gt; beneficiariosGet()



### Example

```java
// Import classes:
//import pt.ipca.doamais.api.api.BeneficiariosApi;

BeneficiariosApi apiInstance = new BeneficiariosApi();
try {
    List<Beneficiario> result = apiInstance.beneficiariosGet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BeneficiariosApi#beneficiariosGet");
    e.printStackTrace();
}
```

### Parameters

This endpoint does not need any parameter.

### Return type

[**List&lt;Beneficiario&gt;**](Beneficiario.md)

### Authorization

[ApiKey](../README.md#ApiKey)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: text/plain, application/json, text/json


## beneficiariosIdDelete

> beneficiariosIdDelete(id)



### Example

```java
// Import classes:
//import pt.ipca.doamais.api.api.BeneficiariosApi;

BeneficiariosApi apiInstance = new BeneficiariosApi();
Integer id = null; // Integer | 
try {
    apiInstance.beneficiariosIdDelete(id);
} catch (ApiException e) {
    System.err.println("Exception when calling BeneficiariosApi#beneficiariosIdDelete");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Integer**|  | [default to null]

### Return type

null (empty response body)

### Authorization

[ApiKey](../README.md#ApiKey)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: Not defined


## beneficiariosIdGet

> Beneficiario beneficiariosIdGet(id)



### Example

```java
// Import classes:
//import pt.ipca.doamais.api.api.BeneficiariosApi;

BeneficiariosApi apiInstance = new BeneficiariosApi();
Integer id = null; // Integer | 
try {
    Beneficiario result = apiInstance.beneficiariosIdGet(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BeneficiariosApi#beneficiariosIdGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Integer**|  | [default to null]

### Return type

[**Beneficiario**](Beneficiario.md)

### Authorization

[ApiKey](../README.md#ApiKey)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: text/plain, application/json, text/json


## beneficiariosIdPut

> beneficiariosIdPut(id, beneficiario)



### Example

```java
// Import classes:
//import pt.ipca.doamais.api.api.BeneficiariosApi;

BeneficiariosApi apiInstance = new BeneficiariosApi();
String id = null; // String | 
Beneficiario beneficiario = new Beneficiario(); // Beneficiario | 
try {
    apiInstance.beneficiariosIdPut(id, beneficiario);
} catch (ApiException e) {
    System.err.println("Exception when calling BeneficiariosApi#beneficiariosIdPut");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**|  | [default to null]
 **beneficiario** | [**Beneficiario**](Beneficiario.md)|  | [optional]

### Return type

null (empty response body)

### Authorization

[ApiKey](../README.md#ApiKey)

### HTTP request headers

- **Content-Type**: application/json, text/json, application/*+json
- **Accept**: Not defined


## beneficiariosPost

> Object beneficiariosPost(beneficiario)



### Example

```java
// Import classes:
//import pt.ipca.doamais.api.api.BeneficiariosApi;

BeneficiariosApi apiInstance = new BeneficiariosApi();
Beneficiario beneficiario = new Beneficiario(); // Beneficiario | 
try {
    Object result = apiInstance.beneficiariosPost(beneficiario);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BeneficiariosApi#beneficiariosPost");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **beneficiario** | [**Beneficiario**](Beneficiario.md)|  | [optional]

### Return type

**Object**

### Authorization

[ApiKey](../README.md#ApiKey)

### HTTP request headers

- **Content-Type**: application/json, text/json, application/*+json
- **Accept**: text/plain, application/json, text/json

