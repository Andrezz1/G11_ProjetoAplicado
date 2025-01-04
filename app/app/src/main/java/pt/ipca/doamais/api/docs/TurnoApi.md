# TurnoApi

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**turnoGet**](TurnoApi.md#turnoGet) | **GET** /Turno | 
[**turnoIdDelete**](TurnoApi.md#turnoIdDelete) | **DELETE** /Turno/{id} | 
[**turnoIdGet**](TurnoApi.md#turnoIdGet) | **GET** /Turno/{id} | 
[**turnoIdPut**](TurnoApi.md#turnoIdPut) | **PUT** /Turno/{id} | 
[**turnoPost**](TurnoApi.md#turnoPost) | **POST** /Turno | 



## turnoGet

> List&lt;Turno&gt; turnoGet(id)



### Example

```java
// Import classes:
//import pt.ipca.doamais.api.api.TurnoApi;

TurnoApi apiInstance = new TurnoApi();
Integer id = null; // Integer | 
try {
    List<Turno> result = apiInstance.turnoGet(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TurnoApi#turnoGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Integer**|  | [optional] [default to null]

### Return type

[**List&lt;Turno&gt;**](Turno.md)

### Authorization

[ApiKey](../README.md#ApiKey)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: text/plain, application/json, text/json


## turnoIdDelete

> turnoIdDelete(id)



### Example

```java
// Import classes:
//import pt.ipca.doamais.api.api.TurnoApi;

TurnoApi apiInstance = new TurnoApi();
Integer id = null; // Integer | 
try {
    apiInstance.turnoIdDelete(id);
} catch (ApiException e) {
    System.err.println("Exception when calling TurnoApi#turnoIdDelete");
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


## turnoIdGet

> Turno turnoIdGet(id)



### Example

```java
// Import classes:
//import pt.ipca.doamais.api.api.TurnoApi;

TurnoApi apiInstance = new TurnoApi();
Integer id = null; // Integer | 
try {
    Turno result = apiInstance.turnoIdGet(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TurnoApi#turnoIdGet");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Integer**|  | [default to null]

### Return type

[**Turno**](Turno.md)

### Authorization

[ApiKey](../README.md#ApiKey)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: text/plain, application/json, text/json


## turnoIdPut

> turnoIdPut(id, turno)



### Example

```java
// Import classes:
//import pt.ipca.doamais.api.api.TurnoApi;

TurnoApi apiInstance = new TurnoApi();
String id = null; // String | 
Turno turno = new Turno(); // Turno | 
try {
    apiInstance.turnoIdPut(id, turno);
} catch (ApiException e) {
    System.err.println("Exception when calling TurnoApi#turnoIdPut");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **String**|  | [default to null]
 **turno** | [**Turno**](Turno.md)|  | [optional]

### Return type

null (empty response body)

### Authorization

[ApiKey](../README.md#ApiKey)

### HTTP request headers

- **Content-Type**: application/json, text/json, application/*+json
- **Accept**: Not defined


## turnoPost

> Object turnoPost(turno)



### Example

```java
// Import classes:
//import pt.ipca.doamais.api.api.TurnoApi;

TurnoApi apiInstance = new TurnoApi();
Turno turno = new Turno(); // Turno | 
try {
    Object result = apiInstance.turnoPost(turno);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling TurnoApi#turnoPost");
    e.printStackTrace();
}
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **turno** | [**Turno**](Turno.md)|  | [optional]

### Return type

**Object**

### Authorization

[ApiKey](../README.md#ApiKey)

### HTTP request headers

- **Content-Type**: application/json, text/json, application/*+json
- **Accept**: text/plain, application/json, text/json

