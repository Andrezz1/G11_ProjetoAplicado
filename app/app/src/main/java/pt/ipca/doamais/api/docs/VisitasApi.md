# VisitasApi

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**visitasGet**](VisitasApi.md#visitasGet) | **GET** /Visitas | 
[**visitasIdDelete**](VisitasApi.md#visitasIdDelete) | **DELETE** /Visitas/{id} | 
[**visitasIdGet**](VisitasApi.md#visitasIdGet) | **GET** /Visitas/{id} | 
[**visitasIdPut**](VisitasApi.md#visitasIdPut) | **PUT** /Visitas/{id} | 
[**visitasPost**](VisitasApi.md#visitasPost) | **POST** /Visitas | 



## visitasGet

> List&lt;Visita&gt; visitasGet()



### Example

```java
// Import classes:
//import pt.ipca.doamais.api.api.VisitasApi;

VisitasApi apiInstance = new VisitasApi();
try {
    List<Visita> result = apiInstance.visitasGet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling VisitasApi#visitasGet");
    e.printStackTrace();
}
```

### Parameters

This endpoint does not need any parameter.

### Return type

[**List&lt;Visita&gt;**](Visita.md)

### Authorization

[ApiKey](../README.md#ApiKey)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: text/plain, application/json, text/json


## visitasIdDelete

> visitasIdDelete(id)



### Example

```java
// Import classes:
//import pt.ipca.doamais.api.api.VisitasApi;

VisitasApi apiInstance = new VisitasApi();
Integer id = null; // Integer | 
try {
    apiInstance.visitasIdDelete(id);
} catch (ApiException e) {
    System.err.println("Exception when calling VisitasApi#visitasIdDelete");
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


## visitasIdGet

> Visita visitasIdGet(id)



### Example

```java
// Import classes:
//import pt.ipca.doamais.api.api.VisitasApi;

VisitasApi apiInstance = new VisitasApi();
Integer id = null; // Integer | 
try {
    Visita result = apiInstance.visitasIdGet(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling VisitasApi#visitasIdGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Integer**|  | [default to null]

### Return type

[**Visita**](Visita.md)

### Authorization

[ApiKey](../README.md#ApiKey)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: text/plain, application/json, text/json


## visitasIdPut

> visitasIdPut(id, visita)



### Example

```java
// Import classes:
//import pt.ipca.doamais.api.api.VisitasApi;

VisitasApi apiInstance = new VisitasApi();
Integer id = null; // Integer | 
Visita visita = new Visita(); // Visita | 
try {
    apiInstance.visitasIdPut(id, visita);
} catch (ApiException e) {
    System.err.println("Exception when calling VisitasApi#visitasIdPut");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Integer**|  | [default to null]
 **visita** | [**Visita**](Visita.md)|  | [optional]

### Return type

null (empty response body)

### Authorization

[ApiKey](../README.md#ApiKey)

### HTTP request headers

- **Content-Type**: application/json, text/json, application/*+json
- **Accept**: Not defined


## visitasPost

> Visita visitasPost(visita)



### Example

```java
// Import classes:
//import pt.ipca.doamais.api.api.VisitasApi;

VisitasApi apiInstance = new VisitasApi();
Visita visita = new Visita(); // Visita | 
try {
    Visita result = apiInstance.visitasPost(visita);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling VisitasApi#visitasPost");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **visita** | [**Visita**](Visita.md)|  | [optional]

### Return type

[**Visita**](Visita.md)

### Authorization

[ApiKey](../README.md#ApiKey)

### HTTP request headers

- **Content-Type**: application/json, text/json, application/*+json
- **Accept**: text/plain, application/json, text/json

