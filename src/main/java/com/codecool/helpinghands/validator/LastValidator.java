package com.codecool.helpinghands.validator;

public class LastValidator <T, E extends Exception> implements ValidatorChain <T,E>{
    @Override
    public T validate(T input) throws E {
        return input;
    }

    @Override
    public void setNext(ValidatorChain<T,E> nextValidator) {

    }
}
