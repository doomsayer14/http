package main.java.com.my.Demo;

public enum Request {

    GET_ALL_EMPLOYEES("http://dummy.restapiexample.com/api/v1/employee", "GET"),
    GET_EMPLOYEE_BY_ID("http://dummy.restapiexample.com/api/v1/employee/%s", "GET"),
    POST_CREATE_NEW_EMPLOYEE("http://dummy.restapiexample.com/api/v1/create", "POST"),
    PUT_UPDATE_EMPLOYEE_BY_ID("http://dummy.restapiexample.com/api/v1/update/%s", "PUT"),
    DELETE_EMPLOYEE_BY_ID("http://dummy.restapiexample.com/api/v1/delete/%s", "DELETE");

    private final String url;
    private final String methodType;

    Request(String url, String methodType) {
        this.url = url;
        this.methodType = methodType;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlWithId(int id) {
        return String.format(url, id);
    }

    public String getMethodType() {
        return methodType;
    }

}
