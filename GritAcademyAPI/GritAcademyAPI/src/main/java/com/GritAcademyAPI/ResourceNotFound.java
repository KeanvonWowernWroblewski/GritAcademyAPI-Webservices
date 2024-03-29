package com.GritAcademyAPI;


public class ResourceNotFound extends RuntimeException{

    public ResourceNotFound (String message) {
        super(message);
    }

}
