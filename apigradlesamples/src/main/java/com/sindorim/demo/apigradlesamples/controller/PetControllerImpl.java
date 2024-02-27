package com.sindorim.demo.apigradlesamples.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.sindorim.demo.openapi.domain.Pet;
import com.sindorim.demo.openapi.rest.PetApi;

@RestController
public class PetControllerImpl implements PetApi {

    @Override
    public ResponseEntity<Pet> getPetById(Long petId) {
        return PetApi.super.getPetById(petId);
    }

}
