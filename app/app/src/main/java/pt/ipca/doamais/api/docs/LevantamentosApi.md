# LevantamentosApi

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**levantamentosGet**](LevantamentosApi.md#levantamentosGet) | **GET** /Levantamentos | 
[**levantamentosIdDelete**](LevantamentosApi.md#levantamentosIdDelete) | **DELETE** /Levantamentos/{id} | 
[**levantamentosIdGet**](LevantamentosApi.md#levantamentosIdGet) | **GET** /Levantamentos/{id} | 
[**levantamentosIdPut**](LevantamentosApi.md#levantamentosIdPut) | **PUT** /Levantamentos/{id} | 
[**levantamentosPost**](LevantamentosApi.md#levantamentosPost) | **POST** /Levantamentos | 



## levantamentosGet

> List&lt;Levantamento&gt; levantamentosGet()



### Example

```java
// Import classes:
//import pt.ipca.doamais.api.api.LevantamentosApi;

LevantamentosApi apiInstance = new LevantamentosApi();
try {
    List<Levantamento> result = apiInstance.levantamentosGet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling LevantamentosApi#levantamentosGet");
    e.printStackTrace();
}
```

### Parameters

This endpoint does not need any parameter.

### Return type

[**List&lt;Levantamento&gt;**](Levantamento.md)

### Authorization

[ApiKey](../README.md#ApiKey)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: text/plain, application/json, text/json


## levantamentosIdDelete

> levantamentosIdDelete(id)



### Example

```java
// Import classes:
//import pt.ipca.doamais.api.api.LevantamentosApi;

LevantamentosApi apiInstance = new LevantamentosApi();
Integer id = null; // Integer | 
try {
    apiInstance.levantamentosIdDelete(id);
} catch (ApiException e) {
    System.err.println("Exception when calling LevantamentosApi#levantamentosIdDelete");
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


## levantamentosIdGet

> Levantamento levantamentosIdGet(id)



### Example

```java
// Import classes:
//import pt.ipca.doamais.api.api.LevantamentosApi;

LevantamentosApi apiInstance = new LevantamentosApi();
Integer id = null; // Integer | 
try {
    Levantamento result = apiInstance.levantamentosIdGet(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling LevantamentosApi#levantamentosIdGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Integer**|  | [default to null]

### Return type

[**Levantamento**](Levantamento.md)

### Authorization

[ApiKey](../README.md#ApiKey)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: text/plain, application/json, text/json


## levantamentosIdPut

> levantamentosIdPut(id, levantamento)



### Example

```java
// Import classes:
//import pt.ipca.doamais.api.api.LevantamentosApi;

LevantamentosApi apiInstance = new LevantamentosApi();
Integer id = null; // Integer | 
Levantamento levantamento = new Levantamento(); // Levantamento | 
try {
    apiInstance.levantamentosIdPut(id, levantamento);
} catch (ApiException e) {
    System.err.println("Exception when calling LevantamentosApi#levantamentosIdPut");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Integer**|  | [default to null]
 **levantamento** | [**Levantamento**](Levantamento.md)|  | [optional]

### Return type

null (empty response body)

### Authorization

[ApiKey](../README.md#ApiKey)

### HTTP request headers

- **Content-Type**: application/json, text/json, application/*+json
- **Accept**: Not defined


## levantamentosPost

> Levantamento levantamentosPost(levantamento)



### Example

```java
// Import classes:
//import pt.ipca.doamais.api.api.LevantamentosApi;

LevantamentosApi apiInstance = new LevantamentosApi();
Levantamento levantamento = new Levantamento(); // Levantamento | 
try {
    Levantamento result = apiInstance.levantamentosPost(levantamento);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling LevantamentosApi#levantamentosPost");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **levantamento** | [**Levantamento**](Levantamento.md)|  | [optional]

### Return type

[**Levantamento**](Levantamento.md)

### Authorization

[ApiKey](../README.md#ApiKey)

### HTTP request headers

- **Content-Type**: application/json, text/json, application/*+json
- **Accept**: text/plain, application/json, text/json

