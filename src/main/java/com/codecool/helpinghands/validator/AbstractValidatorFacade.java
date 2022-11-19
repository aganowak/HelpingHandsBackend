package com.codecool.helpinghands.validator;

import java.util.List;

public abstract class AbstractValidatorFacade<T, E extends Exception> {

    private final ValidatorChain<T, E> headValidator;

    public AbstractValidatorFacade( List<ValidatorChain<T, E>> validators ) {
        this.headValidator = buildChain( validators );
    }

    public T validate( T input ) throws E {
        return headValidator.validate( input );
    }

    private ValidatorChain<T, E> buildChain( List<ValidatorChain<T, E>> validators ) {
        if ( validators.isEmpty() ) {
            return new LastValidator<>();
        }
        for ( int i = 0; i < validators.size(); i++ ) {
            ValidatorChain<T, E> currentValidator = validators.get( i );
            ValidatorChain<T, E> nextValidator = i < validators.size() - 1 ? validators.get( i + 1 ) : new LastValidator<T, E>();
            currentValidator.setNext( nextValidator );
        }
        return validators.get( 0 );
    }
}
