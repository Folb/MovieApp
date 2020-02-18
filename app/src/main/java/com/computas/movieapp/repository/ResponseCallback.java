
package com.computas.movieapp.repository;
public interface ResponseCallback<T> {

    void onSuccess(T response);

    void onFailure(String errorMessage);
}
